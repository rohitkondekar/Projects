<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<%! String s,s1;
int x,j,i;
%>
<%
Student sd= new Student(); 
Student sd1= new Student(); 
StudentDAO sda=new StudentDAO();
StudentDAO sda1=new StudentDAO();
Student[] s2=null;
sd=sda.getStudent();
s1=request.getParameter("fnamesu");
if(s1==null){
    sd1=sda1.getStudent();
    
    if(sd1==null){
        %><jsp:forward page="msginsertfail.jsp"/><%
    }
    s1=sd1.getFirstName();
}
else{
x=Integer.parseInt(s1);
s2=sda1.getStudentByParameter(x,null,null,null,null,null, null,null);  
}
%>

<!DOCTYPE html PUBLIC "-//W3C//Dsd XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dsd/xhtml1-strict.dsd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<script type="text/javascript">
    function fwd()
    {
       document.forms["delstdform"].submit();   
    }
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<script type="text/javascript" src="tree.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="main.css" />
<script type="text/javascript" src="main.js"></script>
</head>
<body>

<div id="wrap">



<!DOCTYPE HTML PUBLIC "-//W3C//Dsd HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dsd">


<div id="top"></div>
<div id="header">
    <img align="left" src="images/4a.jpg" height="90" width="120"/>
<div class="logo">
    <h1>Indian Institute of Technology Bombay</h1>

</div>
<div class="details">
    <div>Welcome : Admin</div>
    
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
         
            <form id="delstdform" name="delstdform" method="post" action="student_delete.jsp" >
                                <h3>Delete Student</h3>
                
                
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>
                
                <noscript>
                <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                </noscript>

                <label><span>Enter First Name :<strong>*</strong></span>
               
                <select name="fnamesu" type="submit" onchange="fwd();">
                   <%if(s2==null){%><option  value="-1">select one</option><% }
                     else {%><option value="<%=s2[0].getIdStudent() %>"><%=s2[0].getFirstName()%> <%=s2[0].getLastName()%></option><% }
                    while(sd!=null) { %>
                   <option value="<%=sd.getIdStudent() %>"><%=sd.getFirstName()%> <%=sd.getLastName()%></option>
                   <% sd=sda.getStudent(); }
                   
                   %>                             
               </select> </label>

                <div id="lastNameInfo" class="required">Last name should have 3 to 30 characters. Only letters are allowed.</div>
                <h5  id="nameError">Invalid last name.</h5>
                

               
               
                
                    <% if(s2!=null){
                        %>         

                    <label><span>Student Roll no. :<strong>*</strong></span>
                        
                    <input id="srollno" type="text" disabled="diasabled" name="srollno" value="<%=s2[0].getRollNumber()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                </label>
                <h5  id="srollnoError">Select roll no.</h5>
                 <% if(s2!=null){
                        %>  
                <label><span>Gender :</span>
                   <input id="gender" type="text" disabled="diasabled" name="gender" value="<%=s2[0].getGender()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %> </label>
                
                <% if(s2!=null){
                        %> 
               
                <label><span>Date of Birth :</span>
                   <input id="dob" type="text" disabled="disabled" name="dob" value="<%=s2[0].getBirthDate()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                
                    </label>
                <% if(s2!=null){
                        %> 
                <label><span>Email :</span>
                   <input id="email" type="text" disabled="disabled" name="email" value="<%=s2[0].getEmail()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %> </label>
                
                
               <% if(s2!=null){
                        %> 
                <label><span>Mobile No :</span>
                <input id="mobile" type="text" disabled="disabled" name="mobile" value="<%=s2[0].getMobile()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %> </label></form>
               
               
               
               
               </form> 
                <form name="delstd2" method="post" action="delstd.jsp" onsubmit="return validateDelStdForm();">
                
                <div class="spacer"></div>
                <input type="hidden" name="fnamesu" value="<%=s1%>"></input>
                <div class="controls">
                    <button type="submit">Delete</button>
                   
                    <button type="reset" onclick="resetStdDelForm();">Cancel</button>
                </div>
                <br />
               </form>
        
        </div>  
</div>


<!DOCTYPE HTML PUBLIC "-//W3C//Dsd HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dsd">








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