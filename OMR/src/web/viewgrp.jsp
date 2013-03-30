


<%@page import="tease.helper.*" %>
<%@page import= "tease.dao.EnrollmentDAO" %>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<jsp:useBean id="val2" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="application"/>
<%! String s,s1; 
int i,j,k;
%>
<%
k=0;
EnrollmentDAO ed=new EnrollmentDAO();
//Connection con=lnk3.getConnection();
GroupTbl td= new GroupTbl(); 
GroupTblDAO tda=new GroupTblDAO();
GroupTbl[] td1= null; 
GroupTblDAO tda1=new GroupTblDAO();
td=tda.getGroupTbl();
GroupTbl t21=new GroupTbl();
StudentDAO sd=new StudentDAO();
//if(td==null)
//out.println("HEll");
%>

        
        <%@page import="java.lang.String.*" %>
        <%!
        String[] str;
        %>
        <%
        str=request.getParameterValues("sel");
        %>
        
        
       <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="SHORTCUT ICON" href="images/myIcon.ico"/>

<link rel="stylesheet" type="text/css" href="styview.css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="rss-articles/" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="main.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="test.css" />
<script type="text/javascript" src="js/ekShiksha_main.js"></script>
</head>
<body>

<div id="wrap">



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<div id="top"></div>
<div id="header">
    <img align="left" src="images/4a.jpg" height="90" width="120"/>
<div class="logo">
    <h1>Indian Institute of Technology Bombay</h1>

</div>
<div class="details">
    <div>Welcome : Admin
</div>
    
</div>

</div>

<div id="menu">
<ul>

                        <li><a href="Admin_Home.jsp">Home</a></li>
			<li><a href="about.jsp">About Us</a></li>
                        <li><a href="contact.jsp">Contact Us</a></li>
			<li><a href="feedback.jsp">Feed Back</a></li>
			<li><a href="faq.jsp">FAQ's</a></li>
			<li><a href="http://www.iitb.ac.in">IITB</a></li>
                        <li><a href="index.jsp">Logout</a></li>
</ul>
</div>
<div id="content">

<div class="right">

<div id="stylized" class="myform">
         <h3>Group Info</h3>
                <noscript>
                <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                </noscript>
         
         
         <table id="view" border="1" >
                 
                    
                    
                    <thead>
    <tr>
       
        
        <% 
        try{
        
        for(int i=0;i<str.length;i++)
                       {
            if(str[i].equals("gname"))
                               {
                   %>
        <th>GroupName</th><% } 
        
        else if(str[i].equals("pgname"))
                               {
                   %>
        <th>ParentGroupName</th><% } 
        else if(str[i].equals("desc"))
                               {
                   %>
        <th>Description</th><% }
        else if(str[i].equals("nostud"))
                               {
                   %>
        <th>No of Students</th><% }
                   }
                   
                          }
        catch(Exception e)
                               {
             %>
 <jsp:forward page="group_view.jsp"/>
<%
        }
                   
                   
                   
                   
                   %>
        
    </tr>
</thead>
<tbody>
   
    
 <% 
 
 
                      while(td!=null) {
                          
                      %> <tr> <%
                          for(int i=0;i<str.length;i++){
                          
                            if(str[i].equals("gname")){
                              %><td><%=td.getDisplayName()%> </td><% }
                              if(str[i].equals("pgname")) {
                                if(td.getIdparentGroup()==-1){  %><td>root </td><%  }
                                 else{
                                    j=td.getIdparentGroup();
                                    td1=tda1.getGroupByParameter(j,null, null, null);
                                    %><td><%=td1[0].getDisplayName()%> </td><%  }
                                      }
                              
                              
                                                          
                                                       
                              if(str[i].equals("desc")) {
                              %><td><%=td.getFullDescription()%> </td><% }
                              
                               if(str[i].equals("nostud")){
                                   
                              %><td><%=ed.noOfStudents(td.getIdGroup())%> </td><% }
                              
                             


                           
          
   
     }td=tda.getGroupTbl(); 
     %></tr>  <%               }
                      
                                          
 


                     
                     
                     
       %> 
            
            
    
    

</tbody>
                </table>
     
     
     
           
          </div>  




</div>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">








<div class="left">

<h2>Available features : </h2><br>
<ul id="treemenu1" class="treeview">

    <li><a href='#'>Students</a><ul><li><a href="student_register.jsp"><br>Add Student</a></li><li><a href="student_view.jsp">View Students</a></li><li><a href="student_delete.jsp">Delete Student</a></li><li><a href="student_update.jsp">Update Student </a></li><li><a href="enroll.jsp">Enrollment</a></li></ul>
   <br> <li><a href='#'>Teachers</a><ul><li><a href="teacher_register.jsp"><br>Add Teacher</a></li><li><a href="teachers_view.jsp">View Teachers</a></li><li><a href="teacher_delete.jsp">Delete Teacher</a></li><li><a href="teacher_update.jsp">Update Teacher</a></li><li><a href="assgn.jsp">Assignment</a></li></ul>
   <br><li><a href='#'>Groups</a><ul><li><a href="subgroup_add.jsp"><br>Add Group</a></li><li><a href="group_view.jsp">View Groups</a></li><li><a href="group_delete.jsp">Delete Group </a></li></ul>
   <br><li><a href='#'>Location</a><ul><li><a href="location.jsp"><br>Add Location</a></li></ul>
   <br><li><a href='#'>Topic</a><ul><li><a href="topic.jsp"><br>Create Topic</a></li></ul>     
   <br><li><a href='#'>Tests</a><ul><li><a href="test_schedule.jsp"><br>Test Scheduling</a></li><li><a href="test_eligibility.jsp">Test Eligibility</a></li><li><a href="test_desc.jsp">Test Description</a></li><li><a href="test_details.jsp">Create Test Paper</a></li></ul>
   <br><li><a href='#'>Result</a><ul><li><a href="result1.jsp"><br>Get Result</a></li></ul>     
  
    
</ul>

<script type="text/javascript">
//ddtreemenu.createTree(treeid, enablepersist, opt_persist_in_days (default is 1))
ddtreemenu.createTree("treemenu1", true)

</script>


</div>
<div style="clear: both;"></div>
</div>


</body>
</html>

        