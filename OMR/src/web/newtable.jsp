
<%@page import= "java.util.Date" %>
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<%@page import="java.lang.String.*" %>
<%! String s;
String[] str;
%>
<%

Student sd= new Student(); 
StudentDAO sda=new StudentDAO();
sd=sda.getStudent();
str=request.getParameterValues("sel");
if(str==null){
    %><jsp:forward page="msginsertfail.jsp"/>
    <%
}
%>
        
        
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="styview.css" media="screen" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="main.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="main.css" />
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
         <h3>Student Info</h3>
         
         <noscript> 
                  <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
             </noscript>  
                <table id="view" border="1" >
                    <thead>
    <tr>
        <% for(int i=0;i<str.length;i++)
                       {
            if(str[i].equals("fnamev"))
                               {
                   %>
        <th>FirstName</th><% } 
        
        else if(str[i].equals("lnamev"))
                               {
                   %>
        <th>LastName</th><% } 
        else if(str[i].equals("rollnov"))
                               {
                   %>
        <th>Rollno</th><% }
         else if(str[i].equals("emailv"))
                               {
                   %>
        <th>Email</th><% }          
                 
        else if(str[i].equals("genderv"))
                               {
                   %>
        <th>Gender</th><% }                
                   
                  else if(str[i].equals("dobv"))
                               {
                   %>
        <th>DateofBirth</th><% } 
          else if(str[i].equals("mobilev"))
                               {
                   %>
        <th>Mobile</th><% }         
                   
                   }%>
        
    </tr>
</thead>
<tbody>
   
    
 <% 
                      while(sd!=null) {
                          
                      %> <tr> <%
                          for(int i=0;i<str.length;i++){
                          
                            if(str[i].equals("fnamev")){
                              %><td><%=sd.getFirstName()%> </td><% }
                              if(str[i].equals("lnamev")) {
                              %><td><%=sd.getLastName()%> </td><% }
                              if(str[i].equals("rollnov")) {
                              %><td><%=sd.getRollNumber()%> </td><% }
                              if(str[i].equals("emailv")){
                              %><td><%=sd.getEmail()%> </td> <% }
                              if(str[i].equals("genderv")) {
                              %><td><%=sd.getGender()%> </td><% }
                              if(str[i].equals("dobv")) {
                              %><td><%=sd.getBirthDate()%> </td><% }
                              
                              if(str[i].equals("mobilev")) {
                              %><td><%=sd.getMobile()%> </td> <% }
                              
                              
                              
                             


                           
          
   
     }sd=sda.getStudent(); 
     %></tr>  <%               }%> 
            
            
    
    

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
<div id="footer">
    
</div>


</body>
</html>

        