






<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>
<noscript>
    <meta http-equiv="refresh" content="0; troubles/error.html" />
</noscript>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="SHORTCUT ICON" href="images/myIcon.ico"/>

<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="rss-articles/" />
<script type="text/javascript" src="tree.js"></script>

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
<!--<h1><a href="econtent.do">ekShiksha</a></h1>
<h2>India -> One Country -> One Education</h2>-->
</div>
<div class="details">
    <div>Welcome : Admin
</div>
    
</div>

</div>

<div id="menu">
<ul>
<li><a href="Admin_Home.jsp">	Home</a></li>
			<li><a href="infm.html">	About Us</a></li>

			<li><a href="syllabus.html">	Contact Us</a></li>
			<li><a href="exam_centre.html">Feed Back</a></li>
			<li><a href="how_to_apply.html">FAQ's</a></li>
			<li><a href="http://www.iitb.ac.in">	     IITB</a></li>

			<li><a href="main.jsp">Logout</a></li>
</ul>
</div>
<div id="content">

<div class="right">
    <div id="stylized" class="myform">
         
            <form id="grpeditform" method="post" name="grpeditform" onsubmit="return validateGrpEdit();">
                                <h3>Edit Group Information :</h3>
                
                
                
                 
                                <p>
                   Fields marked with<strong>*</strong>are mandatory
                                </p>
                                 
                                     <label><span>Enter Group Name whose record to update :</span>
               
                 <select id="Groupname" name="Groupname">
                    <option value="-1"><-Select one-></option>
                    
                    <option value="1">01 : class I A</option>
                    
                    <option value="2">02 : class II B</option>

                    
                </select>
                </label>
                   <h5  id="GroupnameError">select one Group name.</h5>
                                  
                
                                <label>Now refill the information : </label>               

         <!--       <label><span>Group ID :<strong>*</strong></span>
               
                <input id="groupId" type="text" name="groupId" value="" onfocus="showRequiredInfo('groupIdInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="groupIdInfo" class="required">GroupId should be of 4 digits and all numbers.First 2 digits represent ParentGroupID and rest 2 digits represents division(01 for A division and so on) e.g. 0304 parentId is 03 and 04 represents D division. .</div>
                <h5 id="groupIdError">Invalid group ID.</h5>-->
                
                <label><span>ParentGroup Name :<strong>*</strong></span>
               <select id="pGroupname" name="pGroupname">
                    <option value="-1"><-Select one-></option>
                    
                    <option value="1">01 : class I</option>
                    
                    <option value="2">02 : class II</option>

                    
                </select>
                </label>

               
                <h5  id="pGroupnameError">select one ParentGroup name.</h5>
                

                <label><span>Display Name :<strong>*</strong></span>
                    <input id="dnameId" type="text" name="dnameId" value=""  onfocus="showRequiredInfo('dnameInfo');" onblur="hideRequiredInfo()"/>
   
                </label>
                <div id="dnameInfo" class="required">
                              Please provide display name of group. Use 3 to 15 characters. Alphanumerals are allowed.
       
                </div>
                
                <h5 id="dnameError">Invalid display name.</h5>
                

                <label><span>Full Description :<strong>*</strong></span>
                    <textarea name="full_description" cols="26" rows="6" onfocus="showRequiredInfo('fdInfo');" onblur="hideRequiredInfo()"></textarea>
                </label>
                <div id="fdInfo" class="required">
                              Please provide description of group. Use upto 210 characters.
       
                </div>
                <h5 id="descriptionError">Improper Description</h5>
                

                <h5 id="mobileError">Invalid mobile number.</h5>
                <div class="spacer"></div>

                <div class="controls">
                    <button type="submit">Update</button>
                    
                    <button type="reset" onclick="resetGrpEditForm();">Cancel</button>
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