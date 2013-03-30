

<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="page"/>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk2" class = "tease.bean.Student" scope="page"/>
<%! String s1;
int x;
%>
<% 

//Connection con=lnk3.getConnection();
Student sd= new Student(); 
Student sd1= new Student(); 
StudentDAO sda=new StudentDAO();
StudentDAO sda1=new StudentDAO();
Student[] s2=null;
sd=sda.getStudent();

//td1=tda1.getStudent();
//x=request.getParameterId("fname1");
s1=request.getParameter("fnamesud");
if(s1==null){
    sd1=sda1.getStudent();
    if(sd1==null){
        %><jsp:forward page="msginsertfail.jsp"/><%
    }
   
}
else{
x=Integer.parseInt(s1);
//j=Integer.parseInt(s1);

    s2=sda.getStudentByParameter(x,null,null,null,null,null, null,null);  
       
}       
%>












<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>




<script type="text/javascript">
    function fwd()
    {
        //window.location.href="teacher_delete.jsp";
     document.forms["updatestdform"].submit();   
    }
    function fwdfrm()
    {
        window.location.href="teachdel.jsp";
    }
</script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="SHORTCUT ICON" href="images/myIcon.ico"/>

<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="rss-articles/" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="datetimepicker.js"></script>

<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="student_register.css" />
<script type="text/javascript" src="main.js"></script>





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
         <!--action="updtreg.jsp"-->
            <form id="updatestdform" name="updatestdform" action="student_update.jsp" >
                                <h3>Edit Student Information :</h3>
                
                
                
                                <p>
                
                   Fields marked with<strong>*</strong>are mandatory
                
                                </p><noscript> <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                                 
                                     <label><span>Enter First Name whose record to update :</span>
               
                <select name="fnamesud" type="submit" onchange="fwd();">
                   <%if(s2==null){%><option  value="-1">select one</option><% }
                     else {%><option value="<%=s2[0].getIdStudent() %>"><%=s2[0].getFirstName()%> <%=s2[0].getLastName()%></option><% }
                    while(sd!=null) { %>
                   <option value="<%=sd.getIdStudent() %>"><%=sd.getFirstName()%> <%=sd.getLastName()%></option>
                   <% sd=sda.getStudent(); }
                   
                   %>                         
               </select></label>
                   
                          <h5 id="nameError">Invalid First Name.</h5>
                
                
                
                
         </form>
               <form id="stdupdform" method="post" name="stdupdform" action="updtreg.jsp" onsubmit="return validateUpdateStdForm();">
                   
                
                  
                
                                <label>Now refill the information : </label>               

                <label><span>First Name :<strong>*</strong></span>
               
                <% if(s2==null){
                        %><input id="firstName2" type="text" name="firstName2" value=" " onfocus="showRequiredInfo('firstNameInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="firstName2" type="text" name="firstName2" value="<%=s2[0].getFirstName()%>" onfocus="showRequiredInfo('firstNameInfo');" onblur="hideRequiredInfo()"/>
               <% } %>
                </label>
                <div id="firstNameInfo" class="required">First name should have 2 to 20 characters. Only letters are allowed.</div>
                <h5 id="firstNameError">Invalid first name.</h5>
                
                <label><span>Last Name :<strong>*</strong></span>
                 <% if(s2==null){
                        %><input id="lastName2" type="text" name="lastName2" value=" " onfocus="showRequiredInfo('lastNameInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="lastName2" type="text" name="lastName2" value="<%=s2[0].getLastName()%>" onfocus="showRequiredInfo('lastNameInfo');" onblur="hideRequiredInfo()"/>
               <% } %> </label>

                <div id="lastNameInfo" class="required">Last name should have 2 to 20 characters. Only letters are allowed.</div>
                <h5  id="lastNameError">Invalid last name.</h5>
                

                <label><span>Student Roll no. :<strong>*</strong></span>
                 <% if(s2==null){
                        %><input id="studentroll2" type="text" name="studentroll2" value=" " onfocus="showRequiredInfo('studentrollInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="studentroll2" type="text" name="studentroll2" value="<%=s2[0].getRollNumber()%>" onfocus="showRequiredInfo('studentrollInfo');" onblur="hideRequiredInfo()"/>
               <% } %>   
                </label>
                <div id="studentrollInfo" class="required">Roll no. can have alpha-numeric characters without spaces.Roll no can have upto 15 characters.</div>
                <h5 id="studentrollError"> Invalid roll number </h5>
                

                <label><span>Gender :<strong>*</strong></span>
                <select id="gender" name="gender2">
                    <option value="-1"><-Select one-></option>
                    
                    <option value="m">Male</option>
                    
                    <option value="f">Female</option>

                    
                </select>
                </label>
                <h5 id="genderError">Please select any gender</h5>
                <!-- <%--<label><span>Date of Birth :<strong>*</strong></span>
                    <% if(s2==null){
                        %><input id="dob2" type="text" name="dob2" value="" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input type="text" readonly="true" id="demo1" name="dob2" value="<%=s2[0].getBirthDate()%>"  ><a href="javascript:NewCal('demo1','ddmmmyyyy',false,24)" <img src="images/cal.gif" width="20" height="20" border="0" alt="pick a date"></a>
                      <% } %>     
                
                    </label>
               <h5 id="dobError">Please select any gender</h5>
               --%>-->
                <label><span>Email :<strong>*</strong></span>
                     <% if(s2==null){
                        %><input id="email2" type="text" name="email2" value=" " onfocus="showRequiredInfo('emailInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="email2" type="text" name="email2" value="<%=s2[0].getEmail()%>" onfocus="showRequiredInfo('emailInfo');" onblur="hideRequiredInfo()"/>
               <% } %>  </label>
                <div id="emailInfo" class="required">email address of student is required so that he/she can get all the information, alerts and updates to the registered email address.</div>
                <h5 id="emailError">Please enter email address</h5>
                <h5 id="iemailError">Invalid email address</h5>

                

                <label><span>Mobile No :<strong>*</strong></span>
                 <% if(s2==null){
                        %><input id="mobile2" type="text" name="mobile2" value=" " onfocus="showRequiredInfo('mobileInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="mobile2" type="text" name="mobile2" value="<%=s2[0].getMobile()%>" onfocus="showRequiredInfo('mobileInfo');" onblur="hideRequiredInfo()"/>
               <% } %>   </label>
                <div id="mobileInfo" class="required">Mobile number of the student is required. Only 10 digits are allowed in mobile number. Please don't put 0 in the beginning.</div>
                
                <h5 id="mobileError">Invalid mobile number.</h5>
                
                
                
                
                
                <div class="spacer"></div>
                <input type="hidden" name="fnamesu" value="<%=s1%>"></input>
                
                
                
                <div class="controls">
                    <button type="submit" >Update</button>
                
                    <button type="reset" onclick="resetStdUpForm();">Cancel</button>
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