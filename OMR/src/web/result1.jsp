<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%@page import="tease.bean.Test" %>
<%@page import="tease.dao.TestDAO" %>
<% 
StudentDAO sd= new StudentDAO();
Student s=new Student();
s=sd.getStudent();
GroupTblDAO gp= new GroupTblDAO();
GroupTbl g=new GroupTbl();
g=gp.getGroupTbl();
TestDAO ts= new TestDAO();
Test t=new Test();
t=ts.getTest();
%>






<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="SHORTCUT ICON" href="images/myIcon.ico"/>

<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="rss-articles/" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="main.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="student_register.css" />
<script type="text/javascript" src="js/ekShiksha_main.js"></script>
<script type="text/javascript">
    function displaystd()
    {
     
    
     document.getElementById('grpinfo').style.display = "none";
     document.getElementById('testinfo').style.display = "none";
      document.getElementById('stdinfo').style.display = "inline";
    }
    function displaygrp()
    {
     
      document.getElementById('stdinfo').style.display = "none";
       document.getElementById('testinfo').style.display = "none";
        document.getElementById('grpinfo').style.display = "inline";
    }
    function displaytest()
    {
    
      document.getElementById('grpinfo').style.display = "none";
      document.getElementById('stdinfo').style.display = "none";
        document.getElementById('testinfo').style.display = "inline";
    }
     function displaygrptest()
    {
        document.getElementById('stdinfo').style.display = "none";
      document.getElementById('grpinfo').style.display = "inline";
      document.getElementById('testinfo').style.display = "inline";
    }
    function btnclick()
    {
        window.open('result2.jsp','_newtab');

    }
</script>



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
         
           <form name="resultfrm" method="post" action="result2.jsp" onload="resetResult();" >  
               <h3>Result Display : </h3> <br></br>
                        <p>Select the way in which result should be displayed.</p>
                        <noscript> <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                     
                       
                
                
                         <input type="radio" name="result" value="stu_name" onclick="displaystd();"> <b>Student name</b>    
                         <div class="spacer"></div>  
                         <input type="radio" name="result" value="grp_name" onclick="displaygrp();"><b>Group name</b>
                         <div class="spacer"></div>  
                
                      <input type="radio" name="result" value="test_name" onclick="displaytest();"><b>Test name</b>                                    
                          <div class="spacer"></div>  
                         <input type="radio" name="result" value="grp&testname" onclick="displaygrptest();"><b>Group and test name</b>
                            
                                    
                                     <div class="spacer"></div>                
                <div class="spacer"></div>
                
 <div id="stdinfo" style="display: none;"><label><span>Select Student : </span> <select name="std">
             <option value="-1">Name &nbsp; (Roll No)</option>
         <% while(s!=null) { %>
         <option value="<%=s.getIdStudent()%>"><%=s.getFirstName()%> &nbsp;  <%=s.getLastName()%> &nbsp;  (<%=s.getRollNumber()%>)</option>
         <% s=sd.getStudent(); } %>
         
         
         </select></label></div>   
 <div id="grpinfo" style="display: none;"><label><span>Select Group : </span> <select name="grp">
             <option value="-1">Select one</option>
        <% while(g!=null) { %>
         <option value="<%=g.getIdGroup()%>"><%=g.getDisplayName()%></option>
         <% g=gp.getGroupTbl(); } %>
         
             
         </select></label></div>
         <div id="testinfo" style="display: none;"><label><span>Select Test : </span> <select name="test">
             <option value="-1">Select one</option>
        <% while(t!=null) { %>
         <option value="<%=t.getIdTest()%>"><%=t.getDisplayName()%></option>
         <% t=ts.getTest(); } %>
         
             
         </select></label></div>
         
          
                        
                <div class="spacer"></div>                
                <div class="spacer"></div>
                <div class="controls" >
                        <button type="submit" name="gr" value="gr" >Get Result</button>
			<!--<button type="reset" name="clear" value="clear">Reset</button>-->
			
                        </div>  
        </form> 
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

ddtreemenu.createTree("treemenu1", true)

</script>


</div>
<div style="clear: both;"></div>
</div>
<div id="footer">
    

</div>

</body>
</html>