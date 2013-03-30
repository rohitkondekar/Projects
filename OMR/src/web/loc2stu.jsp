






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
         
            <form id="teachupdfrm" name="teachupdfrm" onsubmit="return validateTeacherUpdateForm();">
                                <h3>Allotment of students :</h3>
                
                
                
                 
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                
                                 
                                     <label><span>Enter Test Name :</span>
               
                <input id="tName" type="text" name="tName" value="" />
                </label>
                              
                <label><span>Enter Location Name :</span>
               
                <input id="loc" type="text" name="loc" value="" />
                </label>
                
                <label><span>Location Strength :</span>
               
                <input id="locst" type="text" name="locst" disabled="disabled" value="" />
                </label>
                
                <label><span>Seats left in Location :</span>
               
                <input id="seatloc" type="text" name="seatloc" disabled="disabled" value="" />
                </label>
                </p>
                
                                <label>Now fill the information : </label>               

               
                                 
                <label><span> Select Group name : <strong>*</strong></span><select title="Group Name" class="" id="gname1" name="gname1">
                    <option value="-1" SELECTED >- Select One -</option>
                    <option title="1" value="gp1"   >Group abc</option>
                    <option title="2" value="gp2"   >Group def</option>
               </select>
                </label>
                 <h5 id="grpError">Please select one.</h5>
                
               
               
             <div>
                 
                 <div id="trn1" class="requiredabc">
                    
                    <label> To Roll no:<strong>*</strong><input id="trn1" type="text" name="trn1" value=""  />
                    </label>
                
                </div>
<label><span>From Roll no: <strong>*</strong></span>
                <input id="frn1" type="text" name="frn1" value="" />
                </label>
                
                         
             </div>
                
                       
         
                  <label><span> Select Group name : <strong>*</strong></span><select title="Group Name2" class="" id="gname2" name="gname2">
                    <option value="-1" SELECTED >- Select One -</option>
                    <option title="1" value="gp1"   >Group abc</option>
                    <option title="2" value="gp2"   >Group def</option>
               </select>
                </label>
                 <h5 id="grpError2">Please select one.</h5>
                
               
               
             <div>
                 
                 <div id="trn2" class="requiredabc">
                    
                    <label> To Roll no:<strong>*</strong><input id="trn2" type="text" name="trn2" value=""  />
                    </label>
                
                </div>
<label><span>From Roll no: <strong>*</strong></span>
                <input id="frn2" type="text" name="frn2" value="" />
                </label>
                
                         
             </div>
                
                 
                 
                 
                               
                 
                 
                                         
                 
                 
                 
                <div class="spacer"></div>

                <div class="controls">
                    <button type="submit">Done</button>
                    <button type="submit">Next Allotment</button>
                    <button type="button">Add more groups</button>
                    <button type="reset" onclick="resetRegistrarationForm();">Cancel</button>
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

     <li><h6><a href='#'>Students</a></h6><ul><li><a href="student_register.jsp"><br>Add Student</a></li><li><a href="student_view.jsp">View Students</a></li><li><a href="student_del.jsp">Delete Student</a></li><li><a href="student_update.jsp">Update Student </a></li><li><a href="enroll.jsp">Enrollment</a></li></ul>
    <br><li><h6><a href='#'>Teachers</a></h6><ul><li><a href="teacher_register.jsp"><br>Add Teacher</a></li><li><a href="teachers_view.jsp">View Teachers</a></li><li><a href="teacher_delete.jsp">Delete Teacher</a></li><li><a href="teacher_update.jsp">Update Teacher</a></li><li><a href="assgn.jsp">Assignment</a></li></ul>
    <br><li><h6><a href='#'>Groups</a></h6><ul><li><a href="subgroup_add.jsp">Add Group</a></li><li><a href="group_view.jsp">View Groups</a></li><li><a href="group_delete.jsp">Delete Group </a></li><li><a href="group_edit.jsp">Edit Group</a></li></ul>
    <br><li><h6><a href='#'>Tests</a></h6><ul><li><a href="test_schedule.jsp"><br>Test Schedule</a></li><li><a href="test_eligiblity.jsp">Test Eligibility</a></li><li><a href="test_desc.jsp">Test Description</a></li><li><a href="test_details.jsp">Test Details</a></li></ul>

   
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