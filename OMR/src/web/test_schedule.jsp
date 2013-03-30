
<%@page import= "java.util.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Test" %>
<%@page import="tease.dao.TestDAO" %>
<%@page import="tease.bean.Location" %>
<%@page import="tease.dao.LocationDAO" %>
<%@page import="tease.dao.EnrollmentDAO" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%@page import="tease.dao.StudentDAO" %>

<%
EnrollmentDAO ed=new EnrollmentDAO();
TestDAO td=new TestDAO();
Test t=new Test();

t=td.getTest();
LocationDAO ld=new LocationDAO();
Location l=new Location();
l=ld.getLocation();

GroupTbl g=new GroupTbl();

GroupTblDAO gd=new GroupTblDAO();
g=gd.getGroupTbl();
StudentDAO sda=new StudentDAO();
%>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="main.js"></script>
<script type="text/javascript" src="datetimepicker.js"></script>
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
         
            <form id="testsch" name="testsch" action="regform_testsch.jsp" method="post" onsubmit="return validateTestSchForm();">
                                <h3>Test Scheduling</h3>
                
                
                
                 
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>
                <noscript>
                <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                </noscript>
                

                <label> <span>  Test Name : </span>
                           <select id="testname" name="testname">
                    <option value="-1"><-Select one-></option>
                    <% while(t!=null){ %>
                    <option value="<%=t.getIdTest()%>"><%=t.getDisplayName()%></option>
                    
                    <% t=td.getTest(); } %>
                    
               
                </select>
                </label>
                <h5 id="testnameError2">Invalid test name.</h5>   
                
                <label><span>Group Name (Students in grp): </span>
                    <select id="groupname" name="groupname">
                    <option value="-1"><-Select one-></option>
                    <% while(g!=null){ %>
                    <option value="<%=g.getIdGroup()%>"><%=g.getDisplayName()%>(<%=ed.noOfStudents(g.getIdGroup())%>) </option>
                    
                    <% g=gd.getGroupTbl(); } %>
                    
               
                </select>
                </label>
                <h5 id="gIdError2">Please select any group</h5>
             <label><span>Location (with max strength): </span>
                    <select id="loc" name="loc">
                    <option value="-1"><-Select one-></option>
                    
                    <% while(l!=null){ %>
                    <option value="<%=l.getIdLocation()%>"><%=l.getLocationName()%> (<%=l.getLocationStrength()%>)</option>
                    
                    <% l=ld.getLocation(); } %>

                    
                </select>
                </label>
                <h5 id="locError">Please select any location</h5>
                       
         <label><span> Start Date/Time:</span>
                      
                      <input type="text" id="demo1" name="sdt" value=""><a href="javascript:NewCal('demo1','ddmmmyyyy',false,24)" <img src="images/cal.gif" width="20" height="20" border="0" alt="pick a date"></a>
                        
                        
                
                    </label>
               <div id="sdtInfo" class="required">
                           Please select starting date & time of test by clicking at calender (format:mm/dd/yyyy or mm-dd-yyyy or mm.dd.yyyy).
       
                </div>
                <h5 id="sdtError">Starting date invalid</h5>
                
               <label><span>Ending Date/Time: </span>
<input type="text" id="demo2" name="edt" value=""/><a href="javascript:NewCal('demo2','ddmmmyyyy',true,24)" <img src="images/cal.gif" width="16" height="16" border="0" alt="pick a date"></a>
                        
                
                    </label>
               <div id="edtInfo" class="required">
                           Please select ending date & time of test by clicking at calender (format:mm/dd/yyyy or mm-dd-yyyy or mm.dd.yyyy).
       
               </div>
                <h5 id="edtError">Ending date invalid.</h5>
                
                
                
                
                <div class="spacer"></div>

                <div class="controls">
                    <button type="submit">Make Schedule</button>
                    <button type="reset" onclick="resetTestSch();">Reset</button>
                </div>
                <br />
               
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