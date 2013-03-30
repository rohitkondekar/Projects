<%@page import= "java.util.*" %>
<%@page import="tease.bean.Test" %>
<%@page import="tease.dao.TestDAO" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%
TestDAO td=new TestDAO();
Test t=new Test();
t=td.getTest();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
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
         
            <form id="test_detailsform" name="test_detailsform" method="post" action="regform_testdtls.jsp" onsubmit="return validateTestdetailsForm();">
                                <h3>Test Details</h3>
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>  
                <noscript>
                   <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                </noscript>
                

                <label><span> Select Test name : <strong>*</strong></span><select title="Test Name" class="" id="testname1" name="testname1" onfocus="showRequiredInfo('testnameInfo');" onblur="hideRequiredInfo();">
                    <option value="-1"><-Select one-></option>
                    
                    
                    <% while(t!=null){ %>
                    <option value="<%=t.getIdTest()%>"><%=t.getDisplayName()%> <%=t.getIdTest()%></option>
                    
                    <% t=td.getTest(); } %>
               </select>
                </label>
                <div id="testnameInfo" class="required">Select test name.</div>
                <h5 id="testnameError">Invalid Name.</h5>
                
                <label><span>Question number :<strong>*</strong></span>
                <input id="seq" type="text" name="seq" value="" onfocus="showRequiredInfo('seqInfo');" onblur="hideRequiredInfo()"/>
                </label>

                <div id="seqInfo" class="required">Enter the question no of test. Only integers are allowed (without spaces).</div>
                <h5  id="seqError">Invalid Sequence.</h5>
                

                <label><span>Question Type :<strong>*</strong></span>
                    <input id="qtype" type="text" name="qtype" value=""  onfocus="showRequiredInfo('qtypeInfo');" onblur="hideRequiredInfo()"/>
                    </label>
                <div id="qtypeInfo" class="required">
                               Enter the question type. Only integers are allowed.
       
                </div>
            <h5  id="qtypeError">Invalid type.</h5>
            
                <label><span>Question options :<strong>*</strong></span>
                    <input id="qopt" type="text" name="qopt" value="" onfocus="showRequiredInfo('qoptInfo');" onblur="hideRequiredInfo();"/>
                </label>
                <div id="qoptInfo" class="required">Enter the number of options. Enter only integer values</div>
                <h5 id="qoptError">Please enter integer values.</h5>
             
                

                <label><span>Correct Answer :<strong>*</strong></span>
                <input id="ans" type="text" name="ans" value=""  onfocus="showRequiredInfo('ansInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="ansInfo" class="required">Enter the correct answer of the question.Upto 15 characters are allowed.</div>
                <h5 id="ansError">Please enter answer.</h5>
                
                
                  <label><span>Marks :<strong>*</strong></span>
                <input id="marks" type="text" name="marks" value=""  onfocus="showRequiredInfo('marksInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="marksInfo" class="required">Gives the marks allotted to the question.Enter only numbers.</div>
                <h5 id="marksError">Please enter numeric values.</h5>
                
                
                <label><span>Negative Marks :<strong>*</strong></span>
                <input id="nmarks" type="text" name="nmarks" value=""  onfocus="showRequiredInfo('nmarksInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="nmarksInfo" class="required">Gives the negative marks allotted to the question.Enter only numbers (no "-" sign).</div>
                <h5 id="nmarksError">Please enter numeric values.</h5>
                
              
                <div class="spacer"></div>

                <div class="controls">
                    <button type="submit">Done</button>
                    <button type="reset" onclick="resetTestDetForm();">Reset</button>
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