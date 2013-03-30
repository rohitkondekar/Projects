
package serverPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import tease.bean.*;
import tease.dao.*;

public class checkResult
{
    serverGUI server;
    
    public checkResult (serverGUI server)
    {
        this.server = server;
    }
   
    public float evaluateAnswers(File file,String testId,String name)
    {
        String rollno;
        String data = null;
        int i;
        float marks = -1;
        try
        {

            BufferedReader in = new BufferedReader(new FileReader(file));
            data = in.readLine();
            in.close();
            System.out.println(data);
            
            i = data.indexOf(";");
            rollno = data.substring(0,i);
            
            System.out.println(rollno+"-"+i+"-"+data.length());
            StudentDAO stDAO = new StudentDAO();
            Student[] student;
            
            student = stDAO.getStudentByParameter(null, null, null , rollno,null,'\u0000', null, 0l);
            
            if(student == null)
            {
                System.out.println("Db Error: Student record not found!\n");
                throw new Exception();
            }  
            
            TestAnswers testSheet = new TestAnswers();
            testSheet.setAnswers(data.substring(i+1));
            testSheet.setIdStudent(student[0].getIdStudent());
            testSheet.setIdTest(Integer.parseInt(testId));
            
            TestAnswers []testAnswers;
            TestAnswersDAO taDAO = new TestAnswersDAO();
            marks = evaluate(taDAO,testSheet);
        }
        catch(Exception e)
        {
            System.out.println("Db Error:Evaluate Answers error occured!\n");
        }
        return marks;
    }  
    /**
     *Does all the processing part ie assignment of marks which is the job of a teacher.
     *
     * @param taDAO TestAnswers Direct Access Object
     * @param testAnswers An answer sheet of the student
     */
     private float evaluate(TestAnswersDAO taDAO,TestAnswers testAnswers)
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
		else
		{
			String [] stud_answers = extractAnswer(testAnswers.getAnswers(),questionSeparator);		//stores the answers of students in an array

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

								//System.out.println("stud answer not null");

								//for(i=0;i< optionsChecked.length;++i)
								//	System.out.println(optionsChecked[i]);
								//for(i=0;i< optionsCorrect.length;++i)
								//	System.out.println(optionsCorrect[i]);
								if(optionsChecked.length == 0)// == null)
									System.out.println("optionsChecked Null");
								else if (optionsCorrect.length==0)//= null)
									System.out.println("optionsCorrect Null");
								else if(optionsChecked.length !=0)
									if(optionsChecked[0].equals(" ")) 		//no attempt
											System.out.println("No attempt");
								else if(optionsChecked.length != optionsCorrect.length)		//if less options ticked or more options ticked ,wrong answer
								{
									System.out.println("Less or more options ticked.Wrong Answer");
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
											System.out.println("Not found");
											flag=1;
											break;
										}
									}

									if(flag==1)
									{
										System.out.println("wrong answer");
										marks -= testDetails[j].getNegativeMarks();
										inCorrect++;
									}
									else
									{
										System.out.println("Correct Answer");
										marks += testDetails[j].getMarks();
										correct++;
									}

								}//everything fine after extracting the options

							}//testdetails[j].correctAnswer not null
							else
								System.out.println("Correct answer testDetails is null\n");
						}
						else
							System.out.println("Not found the sequence");
					}//end of for

					testAnswers.setMarks(marks);
					testAnswers.setCorrect(correct);
					testAnswers.setInCorrect(inCorrect);

					System.out.println("Marks ="+testAnswers.getMarks()+" correct=" + testAnswers.getCorrect()+" incorrect=" + testAnswers.getInCorrect()+" idstudent="+ testAnswers.getIdStudent());
                    
                    TestAnswers[] testAnswersIdTest=taDAO.getTestAnswersByParameter(testAnswers.getIdTest(),null,null,null,null,null);
                    
                    if(testAnswersIdTest != null)
                    {             
                        for(int k=0;k<testAnswersIdTest.length;++k)
                            if(testAnswersIdTest[k].getIdStudent() == testAnswers.getIdStudent())
                            {
                                if(taDAO.updateTestAnswers(testAnswers) == true)
                                {
                                    return marks;
                                }
                            }                       
                    }
                    if(taDAO.insertTestAnswers(testAnswers) == true)
                    {
                        return marks;
                    }
                            
				}//end of testDetails not null

			}//stud_answers not null

		}//everything fine
		System.out.println("Not updated in "+testAnswers.getIdStudent()+"!!");
        return -1;
	}//end of function
    /**
     * Extracts the answer of the student.It separates both the question wise and option wise depending upon the separator provided
     * The function extracts answer in such a way that it ignores multiple separators present anywhere ie at the beginning ,middle or end of string
     * @param a String to be extracted
     * @param separator The separator used
     * @return ans an array of strings. if nothing appropriate is found return null
     */
	public String[] extractAnswer(String a,String separator)
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
					//System.out.println(a.substring(pos,pos+1));
					cnt++;
					init=pos;
					pos++;

					while(   pos <a.length() &&  ( (a.substring(pos,pos+1).equals(separator))== false    )     )
		  					 pos++;
					if(	pos==a.length() || (a.substring(pos,pos+1)).equals(separator))
							 s.add(a.substring(init,pos));
							 //s[cnt-1] = a.substring(init,pos);
				}
				else
					pos++;
			}

			String[] ans = new String[s.size()];
			s.toArray(ans);
			s= null;
			return ans;
		}
		return null;
	}
}