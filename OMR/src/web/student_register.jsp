

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



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">


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
         
            <form id="regform" name="regform" method="post" action="regform.jsp" onsubmit="return validateRegistrationForm();"><!--onsubmit="return validateRegistrationForm();"-->
                                <h3>Registration for Students</h3>
                
                
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>
                
                  <noscript> 
                  <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                  </noscript>
                

                <label><span>First Name :<strong>*</strong></span>
               
                <input id="firstName" type="text" name="firstName" value="" onfocus="showRequiredInfo('firstNameInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="firstNameInfo" class="required">First name should have 2 to 20 characters. Only letters are allowed.</div>
                <h5 id="firstNameError">Invalid first name.</h5>
                
                <label><span>Last Name :<strong>*</strong></span>
                <input id="lastName" type="text" name="lastName" value="" onfocus="showRequiredInfo('lastNameInfo');" onblur="hideRequiredInfo()"/>
                </label>

                <div id="lastNameInfo" class="required">Last name should have 2 to 20 characters. Only letters are allowed.</div>
                <h5  id="lastNameError">Invalid last name.</h5>
                

                <label><span>Roll no:<strong>*</strong></span>
                    <input id="rollno" type="text" name="rollno" value="" onfocus="showRequiredInfo('rollnoInfo');" onblur="hideRequiredInfo()"/>
                    
                </label>
                <div id="rollnoInfo" class="required">Rollno can have upto 15 characters. Alphanumerals are allowed without spaces.</div>
                <h5  id="rollnoError">Invalid rollno.</h5>
                
                

                <label><span>        Sex : <strong>*</strong></span>
                    <select title="Sex" class="" id="gender" name="gender">
                    <option value='-1' SELECTED >- Select One -</option>
                    <option title="m" value='m'   >Male</option>
                    <option title="f" value='f'   >Female</option>
               </select>
                </label>
                <h5 id="genderError">Please select any gender</h5>
                
                <label><span>Date of Birth :<strong>*</strong></span>
                   <input type="text" readonly="true" id="demo1" name="dob" value=""  ><a href="javascript:NewCal('demo1','ddmmmyyyy',false,24)" <img src="images/cal.gif" width="20" height="20" border="0" alt="pick a date"></a>
                        
              
                    </label>
               <div id="dobInfo" class="required">
                           Please select date of birth.
       
                </div>
                <h5 id="dobError">Please select date</h5>
                <label><span>Email :<strong>*</strong></span>
                    <input id="email" type="text" name="email" value="" onfocus="showRequiredInfo('emailInfo');" onblur="hideRequiredInfo();"/>
                </label>
                <div id="emailInfo" class="required">email address of student is required so that he/she can get all the information, alerts and updates to the registered email address.</div>
                <h5 id="emailError">Please enter email address</h5>
                <h5 id="iemailError">Invalid email address</h5>

                

                <label><span>Mobile No :<strong>*</strong></span>
                <input id="mobile" type="text" name="mobile" value=""  onfocus="showRequiredInfo('mobileInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="mobileInfo" class="required">Mobile number of the student is required. Only 10 digits are allowed in mobile number. Please don't put 0 in the beginning.</div>
                
                <h5 id="mobileError">Invalid mobile number.</h5>
       

                 <div class="spacer"></div>
                 
                
                <div id="captchaDiv" class="captcha">
                    
                    <div>
                        <img id="imgCaptcha" src="captha.jsp"  alt="Captcha image" width="206" height="60">
                    </div>
                </div>
                <div class="spacer"></div>
                <div id="captchaInfo" class="required" style="position: relative;margin-top: 10px;">These letters are case sensitive.</div>
                <label style="margin-top:4px;"><span>Type the code shown :<strong>*</strong></span>
                <input id="captchaText" style="width:200px;margin-right: 10px;" type="text" name="captchaText"  onfocus="showRequiredInfo('captchaInfo');" onblur="hideRequiredInfo()"/>
                
                <img id="reCaptchaImg" style="display:none;" onclick="changeImage();" src="images/refresh.png" alt="" width="22" height="22">
                <a id="reCaptchaLink" href="javascript:onclick=location.reload(true);" >Try a new code</a>

                </label>
                <h5 class="captchaMsg">Letters are case-sensitive</h5>
                <h5 id="captchaError">The characters you entered didn't match the word verification. Please try again.</h5>
                
                

                <div class="spacer"></div>

                <div class="controls">
                    <button type="submit">Register</button>
                    <button type="reset" onclick="resetRegistrarationForm();">Reset</button>
                </div>
                <br />
                
                
            </form>
        
        </div>  




</div>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">




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