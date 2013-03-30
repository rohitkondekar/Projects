/**
 * @author Abhinav Kumar
 * @version 1.7.0.0
 */

package tease.assessment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.WriteAbortedException;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

import tease.bean.Enrollment;
import tease.bean.GroupTbl;
import tease.bean.Student;
import tease.bean.Test;
import tease.bean.TestAnswers;
import tease.bean.TestDetails;
import tease.bean.TestElegibility;

import tease.dao.EnrollmentDAO;
import tease.dao.GroupTblDAO;
import tease.dao.StudentDAO;
import tease.dao.TestAnswersDAO;
import tease.dao.TestDAO;
import tease.dao.TestDetailsDAO;
import tease.dao.TestElegibilityDAO;

/**
 * Contains the result of the TEAsE
 * @author abhinav
 */
public class Result
{
    private GroupTree myTree;
    private String folder ="/home/surabhi/NetBeansProjects/tease/web/Results/"; //home/abhinav/Desktop/Result/";// folder name.
    private String background ="#ebf4fb";
    private String StudentsTestsFile = "StudentsTests.txt";
    private float earlierMarks;

    /**
     * Constructor makes the tree and writes to the files by establishing connection with the database
     */
    public Result()
    {
        File fold = new File(folder);
        fold.mkdirs();
        ArrayList<GroupTbl> al = new ArrayList<GroupTbl>();

        GroupTblDAO gTDAO = new GroupTblDAO();

        GroupTbl gTbl;

        while(gTDAO.hasNext() == true)
        {
            gTbl = gTDAO.getGroupTbl();
            if( gTbl != null)
                al.add(gTbl);
        }

        if(al.size() <= 0)
        {
            System.out.println("No group element passed");
            return;
        }

        GroupTbl[] mygroup = new GroupTbl[ al.size() ];

        al.toArray(mygroup);
        al= null;

        if(mygroup == null )
        {
            System.out.println("No groupTbl object");
            return;
        }

        //System.out.println("Size in tree result is"+mygroup.length);
        myTree = new GroupTree();
        myTree.makeTree(mygroup);

        TestAnswersDAO taDAO = new TestAnswersDAO();

        while(taDAO.hasNext() == true)
        {
            TestAnswers testAnswers = new TestAnswers();
            testAnswers = taDAO.getTestAnswers();

            if(testAnswers == null)
            {
                System.out.println("Test answer record is null");
            }
            else
            {
                //System.out.println("Record details :" + "Stud id" +mydata.getIdStudent +"group" +mydata.getIdStudent);
                EnrollmentDAO eDAO = new EnrollmentDAO();
                Enrollment enrollment [];
                enrollment = eDAO.getEnrollmentByParameter(null, testAnswers.getIdStudent(),null);
                if(enrollment == null)
                {
                    System.out.println("Student is not enrolled");
                }
                else
                {
                    //Evaluate the students' answersheet
                    evaluate(taDAO,testAnswers);

                    //System.out.println("\n ###### Hello" + testAnswers.getIdStudent());
                    noteId(testAnswers.getIdStudent(),"Student");
                    int status = noteIdStudentIdTest( testAnswers.getIdStudent(),testAnswers.getIdTest(),StudentsTestsFile );
                    //System.out.println("STATUS IS"+status);
                    StudData mydata = new StudData();

                    //System.out.println("namaste bhaiya " + mydata.getIdTest());
                    mydata.setIdStudent(testAnswers.getIdStudent());
                    mydata.setIdTest( testAnswers.getIdTest() );
                    mydata.incrementMarks(testAnswers.getMarks() - mydata.getMarks());

                    //This code will work f9 if idGroup is a foreign key
                    mydata.setIdGroup( enrollment[0].getIdGroup() );

                    myTree.clearAllNodes();
                    earlierMarks=0;

                    //System.out.println("in top function before test entry" + earlierMarks);
                    //Getting result per test
                    recordEntry(mydata,folder+"Test"+mydata.getIdTest()+"Group",1,status);
                    myTree.clearAllNodes();

                    //System.out.println("in top function before group"+ earlierMarks);
                    recordEntry(mydata,folder+"Test0Group",0,status);
                    myTree.clearAllNodes();
                    //GroupWiseResult(mydata);
                }//enrollment not null: student is enrolled

            }//testanswer not null

        }//end of while

        makeGroupTestResult(myTree.getRoot());
        makeStudentResult();
        myTree = null;
        
    }

    /**
     * Extracts the answer of the student.It separates both the question wise and option wise depending upon the separator provided
     * The function extracts answer in such a way that it ignores multiple separators present anywhere ie at the beginning ,middle or end of string
     * @param a String to be extracted
     * @param separator The separator used to separate the answers.
     * @return ans an array of strings. if nothing appropriate is found return null
     */
    private String[] extractAnswer(String a,String separator)
    {
        int pos,init,cnt;

        pos=0;
        init=0;
        cnt=0;

        if(a != null)
        {
            ArrayList<String> s = new ArrayList<String>();

            while(pos < a.length())
            {
                if((  (a.substring(pos,pos+1)).equals(separator)  )== false)			//true only if char is not a separator
                {
                    cnt++;
                    init=pos;
                    pos++;

                    /*priority is that pos should not exceed length of the string */
                    while(   pos <a.length() &&  ((a.substring(pos,pos+1).equals(separator))== false))
                        pos++;
                    if(	pos==a.length() || (a.substring(pos,pos+1)).equals(separator))  //last character
                        s.add(a.substring(init,pos));
                             //s[cnt-1] = a.substring(init,pos);
                }
                else        //this code makes it multiple separator free.
                    pos++;
            }
            String[] ans = new String[s.size()];    //Making an array of strings with size that of arraylist
            s.toArray(ans);
            s= null;        //pointing to nullmakes it eigibile for Garbage Collection
            return ans;
        }
        return null;
    }

    /**
     * Assigns marks to a particular testAnswer which is the job of a teacher
     * and updates in the database.
     * @param taDAO TestAnswers Direct Access Object
     * @param testAnswers An answer sheet of the student
     */
    private void evaluate(TestAnswersDAO taDAO,TestAnswers testAnswers)
    {
        final String questionSeparator = ";" ;
        final String optionSeparator = "," ;
        int i;


        if(taDAO == null)
            System.out.println("TestAnswersDAO is null");
        else if(testAnswers == null)
            System.out.println("TestAnswer Object is null");
        else if (testAnswers.getAnswers() == null)
            System.out.println("Answer is null ");
        else if( testAnswers.getIdStudent() <=0)
            System.out.println("Invalid Id of the student");
        else if(testAnswers.getIdTest() <= 0)
            System.out.println("Invalid Test id ");
                //System.out.println("\n \n You have not given anything for 'answers' in the object !!!");
        else
        {
            String [] stud_answers = extractAnswer(testAnswers.getAnswers(),questionSeparator);		//stores the answers of students in an array

            //for(i=0;i<stud_answer.length;++i)
            //	System.out.println(stud_answer[i]);
            if(stud_answers == null)
                System.out.println("No Answer Given");
            else
            {
                int correct,inCorrect;
                float marks;

                correct =0;
                inCorrect =0;
                marks =0;

                TestDetailsDAO tdDAO = new TestDetailsDAO();

                TestDetails[] testDetails;

                testDetails = tdDAO.getTestDetailsByParameter(new Integer(testAnswers.getIdTest()),null,null,null,null,null,null);

                if(testDetails == null)
                {
                    System.out.println("No testDetails record found");
                }
                else
                {
                    //for each question in the test answer
                    for(i=0;i< stud_answers.length ;++i)
                    {
                        int j;

                        //Checking for the same sequence
                        for(j=0;j< testDetails.length; ++j)
                            if(testDetails[j].getSequence() == (i+1))
                                break;

                        //sequence is there
                        if(j < testDetails.length)
                        {
                            if(testDetails[j].getCorrectAnswer() != null)
                            {

                                String [] optionsChecked = extractAnswer( stud_answers[i],optionSeparator );
                                String [] optionsCorrect = extractAnswer( testDetails[j].getCorrectAnswer(),optionSeparator );

                                if(optionsChecked.length == 0)// == null)
                                    System.out.println("optionsChecked Null");
                                else if (optionsCorrect.length==0)//= null)
                                    System.out.println("optionsCorrect Null");
                                else if(optionsChecked.length !=0)
                                    if(optionsChecked[0].equals(" ")) 		//NO ATTEMPT
                                        ;//System.out.println("No attempt");
                                else if(optionsChecked.length != optionsCorrect.length)		//if less options ticked or more options ticked ,wrong answer
                                {
                                    //System.out.println("Less or more options ticked.Wrong Answer");
                                    marks -= testDetails[j].getNegativeMarks();
                                    inCorrect++;
                                }
                                else
                                {

                                    int flag =0;
                                    for(int k=0;k < optionsChecked.length ; ++k)
                                    {
                                        int l=0;

                                        for(l=0; l< optionsCorrect.length ; ++l)
                                            if(optionsChecked[k].equalsIgnoreCase(optionsCorrect[l]))
                                                break;
                                        if( l >= optionsCorrect.length)		//option marked not found
                                        {
                                            //System.out.println("Not found");
                                            flag=1;
                                            break;
                                        }
                                    }

                                    if(flag==1)
                                    {
                                        //System.out.println("wrong answer");
                                        marks -= testDetails[j].getNegativeMarks();
                                        inCorrect++;
                                    }
                                    else
                                    {
                                        //System.out.println("Correct Answer");
                                        marks += testDetails[j].getMarks();
                                        correct++;
                                    }

                                }//everything fine after extracting the options from the answer

                            }//testdetails[j].correctAnswer not null
                            else
                                System.out.println("Correct answer testDetails is null");
                        }
                        else
                            System.out.println("Not found the sequence");
                    }//end of for

                    testAnswers.setMarks(marks);
                    testAnswers.setCorrect(correct);
                    testAnswers.setInCorrect(inCorrect);

                    //System.out.println("Marks ="+testAnswers.getMarks()+" correct=" + testAnswers.getCorrect()+" incorrect=" + testAnswers.getInCorrect()+" idstudent="+ testAnswers.getIdStudent() + "\n");

                    if(taDAO.updateTestAnswers(testAnswers) == true)
                    {
                        System.out.println("Successfully Updated");
                        testAnswers = null;
                        testDetails = null;
                        stud_answers = null;
                        return;
                    }

                }//end of testDetails not null

            }//stud_answers not null

        }//everything fine
        System.out.println("Abhinav Says:Not updated in the database !!!");

	}//end of function

    /**
     * notes the id/test accordingly the value id is sent and name is sent
     * @param id id is idGroup if name="Group" ,idStudent if name="Student",idTest if name="test"
     * @param name Can be Group/Student/Test
     */
    private void noteId(int id,String name)
    {
        try
        {
            String fileName = folder+name+".txt";
            File file = new File(fileName);

            if(file.exists())
            {
                int flag = 0;
                BufferedReader input = new BufferedReader(new FileReader(file));
                String line = "";
                while((line = input.readLine())!= null)
                {
                    if(Integer.parseInt(line)== id)
                    {
                        flag =1;
                        break;
                    }
                }
                input.close();
                if(flag == 1)
                    ;
                else
                {
                     FileWriter fstream = new FileWriter(fileName,true);
                     BufferedWriter out = new BufferedWriter(fstream);
                     String text = Integer.toString(id);
                     out.write(text);
                     out.newLine();
                     out.close();
                }
            }//end of file exists
            else
            {
                file.createNewFile();
                FileWriter fstream = new FileWriter(fileName,true);
                BufferedWriter out = new BufferedWriter(fstream);
                String text = Integer.toString(id);
                out.write(text);
                out.newLine();
                out.close();

            }
        }
        catch(IOException e)
        {
            System.out.println("IOException caught");
        }
    }

    /**
     * Notes the id No and test of the student
     * @param idStudent The id of the Student
     * @param idTest idTest being given by the student
     */
    private int noteIdStudentIdTest(int idStudent, int idTest,String name)
    {
        try
        {
            String file_name =folder+name;
            File file = new File(file_name);

            if(file.exists())
            {

                //System.out.println("File already exists.So,reading from the file");
                BufferedReader input = new BufferedReader(new FileReader(file));
                String string = "";
                String line ="";

                StudData data;
                int flag =0;
                int idStud=-1,id=-1;

                //reading data from the file 1st will be idStudent and 2nd will be idTest
                while((line = input.readLine()) != null)
                {
                    string="";
                    string += line;
                    StringTokenizer strTokenizer = new StringTokenizer(string);

                    //Extracting the words
                    while(strTokenizer.hasMoreTokens())
                    {
                        flag = 0;
                        idStud = new Integer(strTokenizer.nextToken()  );
                        id = new Integer(strTokenizer.nextToken());
                    }
                    if(idStud == idStudent && id == idTest )
                    {
                            flag = 1;
                            break;
                    }
                        //arr.add(data);
                }
                input.close();

                if(flag == 1)		//if found
                {
                    System.out.println("Student "+ idStudent +" is giving the test "+idTest+" again.!!!!");
                    return 1;
                }
                else
                {
                     FileWriter fstream = new FileWriter(file_name,true);
                     BufferedWriter out = new BufferedWriter(fstream);
                     String text = Integer.toString(idStudent) + "\t" + Integer.toString(idTest);
                     out.write(text);
                     out.newLine();

                     out.close();
                 }

            }//fie exista
            else
            {
                //System.out.println("File not found.Creating the file!!!");
                file.createNewFile();

                BufferedWriter output = new BufferedWriter( new FileWriter(file));
                String text = Integer.toString(idStudent)+"\t" + Integer.toString(idTest);

                output.write(text);
                output.newLine();
                output.close();
            }//if file not exist
        }//end of try

        catch(FileNotFoundException e)
        {
            System.out.println("File not there");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Exception found");
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 *Enters the records at appropriate places in the list
	 *@param record A studData entry which is to be processed
     *@param name Name of the file
     *@param type 1 shows test 0 shows group
     *@param status 1 shows already given 0 shows not given
	 */
	private void recordEntry(StudData record,String name,int type,int status)
	{

		if(record == null)
		{
			System.out.println("Record is null");
			return ;
		}
		MarksComparator compMarks = new MarksComparator();

		Node node = myTree.searchNode(record.getIdGroup());

		if( node == null)											//If the idGroup of the node has not been found
		{
			System.out.println("\n\nError!!!! The idGroup of the student record was NOT found in database");
			return;
		}
		else
		{
			node.addRecord(record);								//add to the record of the found node
			//Collections.sort( node.getRecordList(),compMarks);		//sort it according to the descending order

			//CntChildWithNode of the parent will be increased if a record is added in new Child
			Node child = node;
			Node parent = node.getParent();
			int id =-1;
            if(parent != null)
                id= parent.getIdGroup();	//Getting id of the parent

			if(child.getRecordList().size() > 1)	//if in the given node 2nd record is added,no need to do anything.
				;
			else
			{
				do
				{
					if(child.getCntNodeWithRecord() == 1)	//if the added record is 1st to that idGroup changes will be reflected in group above.
					{
                        if(parent == null)
                            break;
						parent.incrementCntNodeWithRecord(1);
						if(parent == myTree.getRoot())					//if root node is reached
						{
							//parent.incrementCntNodeWithRecord();
							break;
						}
						else
						{
							child = parent;
							//System.out.println("child new id " + child.getIdGroup());
							parent= child.getParent();

						   	id = parent.getIdGroup();
						}
					}
					else						 //if in the given node 2nd record is added,no need to do anything.not 1st record break from the loop
						break;
				}while(id >= 0);

			}//end of else: new record is added

		}//end of else: node is not null

		try
		{
            //System.out.println("Earlier Marks record entry" + earlierMarks);
			writeFile(node,record,name,type,status);
		}
		catch(Exception e)//Exception is an object in Java .
		{
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 *Writes the processed data to a file according to the group no.
	 *@param node The value of node from which we have to check
     *@param record A studData entry which is to be processed
     *@param type 1 shows test 0 shows group
     *@param status 1 shows already given 0 shows not given
	 */
	private void writeFile(Node node,StudData record,String name,int type,int status)
	{

		if(node == null)
		{
			System.out.println("Node is null");
			return;
		}

		Node temp = node;
		//System.out.println("Hello !!! NAYA RECORD" + record.getIdStudent() + "\n");
		try
		{
			int id=0;
			do  //for each level
			{

				if(temp.getCntNodeWithRecord() <= 0)
				{
						System.out.println("No need to continue");
						break;
				}

				else//( temp.getNodeWithRecord() >= 1)
				{
					String file_name = name+Integer.toString(temp.getIdGroup());
					File file = new File(file_name+".txt");

					if(file.exists())
					{
						//System.out.println("File already exists.So,reading from the file");

						BufferedReader input = new BufferedReader(new FileReader(file));
						String str = "";
						String line ="";
						ArrayList<StudData> arr = new ArrayList<StudData>();
						StudData data;

						//reading data from the file 1st will be idStudent and 2nd will be marks
						while((line = input.readLine()) != null)
						{
							data = new StudData();
							str="";
							str += line;
							StringTokenizer strTokenizer = new StringTokenizer(str);

							//Extracting the words
							while(strTokenizer.hasMoreTokens())
							{
								int idStud = new Integer(strTokenizer.nextToken()  );
								data.setIdStudent(idStud);
								float mark = new Float(strTokenizer.nextToken());
								data.incrementMarks(mark);

								StudentDAO sDAO = new StudentDAO();
								Student stud = sDAO.getStudentByIdStudent(idStud);
								sDAO = null;
                                                                if(stud != null)
									arr.add(data);		//if student gave the exam and now doesn't exist then his value will not be there
								break;
								
							}
						}
						input.close();

						int pos = searchIdStudent(arr,record.getIdStudent());

						if(pos >= 0)
                        {	  if(type == 1)//testmarks then overwrite
                            {
                                if(status ==1)
                                    earlierMarks = arr.get(pos).getMarks();
                                arr.get(pos).incrementMarks(record.getMarks()- arr.get(pos).getMarks());
                                //System.out.println("EArlier Marks is " + earlierMarks);
                            }
                            else    // for group wise marks will be added if test not given again
                                if(status == 0)// not given the test again
                                    arr.get(pos).incrementMarks(record.getMarks());
                                else
                                {
                                    //System.out.println("Earlier Marks in group" + earlierMarks);
                                    //if that test given add the difference of new and earlier marks
                                    arr.get(pos).incrementMarks(record.getMarks()- earlierMarks);
                                }
                        }
                        // if we write above it will incrementMarks.So
                        //  arr.get(pos).incrementMarks(record.getMarks() - arr.get(pos).getMarks() );
						else
							arr.add(record);

						MarksComparator compMarks = new MarksComparator();
						Collections.sort(arr,compMarks);

						//Deleting the contents of the file
						RandomAccessFile f = new RandomAccessFile(file_name+".txt","rw");
						f.setLength(0);				//deletes the contents of the file.
						f.close();

						//Now writing to a file
						FileWriter fstream = new FileWriter(file,true);
						BufferedWriter output = new BufferedWriter(fstream);

						for(int i=0;i<arr.size();++i)
						{

							output.write(Integer.toString(arr.get(i).getIdStudent()) + "\t" + Float.toString(arr.get(i).getMarks() ) );
							output.newLine();
						}
						output.close();
						arr = null;     //Setting it to null makes it eligible for garbage collection
					}
					else
					{
						//System.out.println("File not found.Creating the file!!!");
						file.createNewFile();
						Writer output = new BufferedWriter( new FileWriter(file));
						String text = Integer.toString(record.getIdStudent()) +"\t" +Float.toString(record.getMarks());

						output.write(text);
						output.close();
					}//if file not exist

                createWebPages(file_name);
                }//end of outer else record <=0;

				id = temp.getIdGroup();
				temp = temp.getParent();

			}while(temp != null); //till we reach  root node
		}
		catch(WriteAbortedException e)
		{
			System.out.println("One of ObjectStream Exceptions was thrown during Write Operation. Error:" + e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("\n Error:I/O exception. " + e.getMessage());
		}
	}//end of function writeFile

    /**
     * Searches for particular idStudent in the ArrayList of the StudData
     * @param arr ArrayList of StudData
     * @param id id to be searched
     * @return i The position of the the StudData Object in the arrayList if
     *           that StudData object is found with that particular id
     *              otherwise return -1
     */
	private int searchIdStudent(ArrayList<StudData> arr,int id)
	{
			for(int i=0;i< arr.size() ;++i)
				if(arr.get(i).getIdStudent() == id)
					return i;
			return -1;
	}

    /**
     * Recursive method that makes the web pages to be displayed in the front end
     * @param node Node whose all combos of group and test is to be used
     */
    private void makeGroupTestResult(Node node)
    {
        //All group and test cardian product
        int idGroup = node.getIdGroup();
        //for each test ids traverse down the tree and get all the test ids.WE CAN GET THESE TEST IDS FROM TESTELIGIBILITY TABLE
        ArrayList<Integer> tests = new ArrayList<Integer>();

        //traverse the tree from this node till down and note the distinct idTests this node can give
        TestElegibilityDAO teDAO = new TestElegibilityDAO();
        TestElegibility [] testelegibility;

        Stack s= new Stack();
        s.push(node);
        node.setVisited(true);

        testelegibility = teDAO.getTestElegibilityByParameter(null, idGroup,null);
        if(testelegibility != null)
        {
            for(int i=0;i< testelegibility.length;++i)
            {
                int j = 0;
                for(;j< tests.size();++j)
                    if(testelegibility[i].getIdTest() == tests.get(j))
                        break;
                if(j>= tests.size())
                    tests.add(testelegibility[i].getIdTest());
            }
        }
        while (!s.isEmpty())
        {
            Node n = (Node)s.peek();

            Node child;
            child = myTree.getUnvisitedChild(n);

            if( child != null)
            {
                //System.out.print( child.getIdGroup() + "\t");
                child.setVisited(true);

                testelegibility = teDAO.getTestElegibilityByParameter(null, idGroup,null);

                if(testelegibility != null)
                {
                    for(int i=0;i< testelegibility.length;++i)
                    {
                        int j = 0;
                        for(;j< tests.size();++j)
                            if(testelegibility[i].getIdTest() == tests.get(j))
                                break;
                        if(j>= tests.size())
                            tests.add(testelegibility[i].getIdTest());
                    }
                }
                s.push(child);
            }
            else
                s.pop();
        }

        myTree.clearNodes(node);
        s =null;

        for(int i=0;i< tests.size();++i)
        {
                String fileName = folder +"Test"+tests.get(i) +"Group" + idGroup;
                File file = new File(fileName+".html");
                if(file.exists())
                    ;
                else
                    createWebPages(fileName);
        }//END OF FOR
        //groupwise
        String fileName = folder + "Test0Group" + idGroup;
        createWebPages(fileName);

        for(int i=0;i< node.getChildList().size();++i)
        {
            makeGroupTestResult(node.getChild(i));
        }

    }//end of makeWebPages

    /**
     * Creates web pages
     * @param fileName Name of  the file from which web page is to be made
     */
    private void createWebPages(String fileName)
    {
        try
        {
            String txtFile =  fileName +".txt";
            File file = new File(txtFile);
            String text="";

            text+= "<HTML><TITLE>Result</TITLE>";
            text+= "<BODY bgcolor="+background+"><H1 align =center><font color= #009999>Assessment Module</font></H1>";

            if(file.exists())
            {
                //System.out.println("Corresponding txt file is present");
                BufferedReader input = new BufferedReader(new FileReader(file));
                String line = "";
                text += "<TABLE BORDER><TR><TH>Roll No<TH>Student Name<TH>Marks";
                while((line = input.readLine()) != null)
                {
                    StringTokenizer strTokenizer = new StringTokenizer(line);

                    String str1,str2;
                    str1 = "";
                    str2 = "";
                    //Extracting the words
                    while(strTokenizer.hasMoreTokens())
                    {
                        str1 = strTokenizer.nextToken();
                        str2 = strTokenizer.nextToken();

                        StudentDAO sDAO = new StudentDAO();
                        Student student = sDAO.getStudentByIdStudent(Integer.parseInt(str1));
                        //System.out.println(str1 + "\t"+str2);
                        if(student != null)
                            text+="<TR><TD>"+student.getRollNumber()+"<TD>"+student.getFirstName()+" "+student.getLastName()+"<TD>"+str2+"</TR>";
                        break;
                    }
                }
                input.close();
                text+="</TABLE>";
            }//end of text file exist
            else
                text+="<TABLE><TR>-----No Records Present----</TABLE>";
            text+="<BR><FONT color=red size =3 align =center>Powered by: TEAsE Team</FONT></BODY></HTML>";

            String htmlFile = fileName +".html";

            File fileObj= new File(htmlFile);

            if(fileObj.exists())
            {
               fileObj.delete();
            }
            fileObj.createNewFile();
            //System.out.println(text);
            FileWriter pw = new FileWriter(htmlFile);
            pw.write(text);
            pw.flush();
            pw.close();
        }
        catch(IOException e)
        {
            System.out.println("IO Exception Caught");
        }
    }//end of maketest

    /**
     * Makes the student wise results
     */
    private void makeStudentResult()
    {
        StudentDAO sDAO = new StudentDAO();
        Student student;

        //for all students
        while(sDAO.hasNext())
        {
            student = sDAO.getStudent();
            makeStudentResultWebPages(student);

        }
    }

    /**
     *Makes the result of a student on web pages
     *@param student Student record
     */
    private void makeStudentResultWebPages(Student student)
    {
        String text="<HTML><TITLE>Result</TITLE>";
        text+="<BODY bgcolor="+background+"><H1  align =center><font color= #009999>Assessment Module</font></H1>";
        text+="<p align=center>Roll No="+ student.getRollNumber() +"<BR>First Name="+student.getFirstName() + "<BR>Last Name=" + student.getLastName()+"<BR></P>";

        int key = student.getIdStudent();
        System.out.println("their id is"+ key);

        //search student is there or not
        EnrollmentDAO eDAO =new EnrollmentDAO();
        Enrollment enrollment[];
        enrollment = eDAO.getEnrollmentByParameter(null,key,null);

        if(enrollment  == null)
        {
           text += "The student with id "+key+" is not enrolled";
        }

        else
        {
            //find which group present
            int idGroup = enrollment[0].getIdGroup();

            Node node = myTree.searchNode(idGroup);

            if(node == null)
               text += "The group of the student can't be found in the database";

            else
            {
                //find what test given(if any test given)searches for each test and till top
                ArrayList<Integer> arr= new ArrayList<Integer>();
                try
                {
                    String file_name =folder+StudentsTestsFile;
                    File file = new File(file_name);

                    if(file.exists())
                    {
                        BufferedReader input = new BufferedReader(new FileReader(file));
                        String str = "";
                        String line ="";
                        //arr = new ArrayList<Integer>();

                        int idStud=-1,id=-1;

                        //reading data from the file 1st will be idStudent and 2nd will be idTest
                        while((line = input.readLine()) != null)
                        {
                            str="";
                            str += line;
                            StringTokenizer strTokenizer = new StringTokenizer(line);

                            //Extracting the words
                            while(strTokenizer.hasMoreTokens())
                            {
                                //1st will be idstudent 2nd will be idtest
                                idStud = Integer.parseInt(strTokenizer.nextToken()  );
                                id = Integer.parseInt(strTokenizer.nextToken());

                                //System.out.println("HELLO SUPERMAN"+idStud+id);
                                if( idStud == key)
                                        arr.add(id);
                                break;
                            }

                        }//end of while:reading the file

                        input.close();
                    }//students file does exist

                    text+="<BR>No of tests student has given=" + (arr.size());

                    Node groupNode=null;

                    if(arr.size() > 0)
                    {
                        arr.add(0);             //0 suggests irrespective of the test
                        groupNode = myTree.searchNode(idGroup);
                    }

                    for( int i=0;i<arr.size();++i)//for total marks + each test
                    {
                        if(arr.get(i) == 0)
                            text += "<BR><BR><P>Total Marks</P>";
                        else
                        {
                            TestDAO tDAO = new TestDAO();
                            Test test = tDAO.getTestByIdTest(arr.get(i));
                            if(test == null)
                            {
                                text += "<BR>no such test";
                            }
                            else
                            {
                                if(test.getDisplayName() == null)
                                    text += "<BR><BR><P>test name= " + "No  name"+"</P>";
                                else
                                    text += "<BR><BR><P>test name= " + test.getDisplayName()+"</P>";
                            }
                        }

                        int idTest = new Integer(arr.get(i));

                        Node temp = groupNode;
                        int id = idGroup;
                        int level = 1;

                        text+="<TABLE BORDER><TR><TH>GroupName<TH>Rank<TH>Marks";

                        do//at each level
                        {
                            String fileName = folder + "Test"+idTest+"Group"+id+".txt";
                            File fileObj =new File(fileName);

                            if(fileObj.exists())
                            {
                                BufferedReader input = new BufferedReader(new FileReader(fileObj));
                                text += showRank(id,input,key,level);
                            }
                            else
                            {
                                text+="Student has not taken the test/File not found";
                            }
                            temp = temp.getParent();

                            if(temp == null)
                                break;
                            else
                                id = temp.getIdGroup();

                            level++;
                        }while(temp!=null);

                        text += "</TABLE>";
                    }//end of for:for each test

                }//end of try

                catch(IOException e)
                {
                    System.out.println("IO exception in noting the Students'giving which test");
                    e.printStackTrace();
                }
            }//end of node found

        }//end of else enrolled

        //System.out.println("BAKwaas ");
        try
        {
            text+="<BR><FONT color=red size=3 align=center>Powered by: TEAsE Team</FONT></BODY></HTML>";
            String htmlFile = folder + student.getIdStudent()+".html";
            File fileObj= new File(htmlFile);

            if(fileObj.exists()) // if html file exists delete that
               fileObj.delete();
            fileObj.createNewFile();

            // System.out.println(text);
            FileWriter pw = new FileWriter(htmlFile);
            pw.write(text);
            pw.flush();

            pw.close();
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
     }

    /**
     * shows the rank of the student in particular id and particular test
     * @param id idGroup
     * @param input BufferedReader of a particular file.
     * @param key idStudent
     * @param level The level in which we are of the tree.level 1 suggests leaf node
     */
    private String showRank(int idGroup,BufferedReader input,int key,int level)
    {
        String text="";
        String str="";
        String line="";
        int rank = 0;
        int pos =0;
        float prevMarks=0;
        try
        {
            while((line = input.readLine()) != null)
            {
                pos++;
                str="";
                str += line;
                StringTokenizer strTokenizer = new StringTokenizer(str);
                int idStud=-1;
                float marks=0;

                //Extracting words
                while(strTokenizer.hasMoreTokens())
                {
                    idStud = new Integer(strTokenizer.nextToken());
                    marks = new Float(strTokenizer.nextToken());

                    if(pos ==1)
                    {
                        prevMarks = marks;
                        rank =1;
                    }
                    if(marks != prevMarks)
                    {
                        prevMarks = marks;
                        rank++;
                    }
                    break;
                }
                if(idStud == key)
                {
                    GroupTblDAO gtDAO = new GroupTblDAO();
                    GroupTbl groupTbl = gtDAO.getGroupByIdGroup(idGroup);
                    if(groupTbl != null)
                    {
                        if(groupTbl.getDisplayName() == null)
                                text+= "<TR><TD>No Group Name<TD>"+rank;
                        else
                                text+= "<TR><TD>"+groupTbl.getDisplayName()+"<TD>"+rank;
                        if(level == 1)
                            text+="<TD>"+marks;
                    }
                    else
                        text+="<TR>Not found ";
                    return text;
                }
            }//end of reading the file
        }
        catch(IOException e)
        {
            text+="<TR>IO Error in getting the rank of a student";
            return text;
        }
        return null;
    }

}//end of class
