
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Teacher" %>
<%@page import="tease.dao.TeacherDAO" %>
<%! String s1;
int x;
%>
<% 
Teacher td= new Teacher(); 
Teacher td1= new Teacher(); 
TeacherDAO tda=new TeacherDAO();
TeacherDAO tda1=new TeacherDAO();
Teacher[] t2=null;
td=tda.getTeacher();
s1=request.getParameter("fname1tu");
if(s1==null){
    td1=tda1.getTeacher();
   
    if(td1==null){
        %><jsp:forward page="msginsertfail.jsp"/><%
    }
     s1=td1.getFirstName();
}
else{
x=Integer.parseInt(s1);
t2=tda1.getTeacherByParameter(x,null,null,null,null);
    }
%>

<script type="text/javascript">
    function fwd()
    {
        //window.location.href="teacher_delete.jsp";
     document.forms["teachupdfrm"].submit();   
    }
   
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<script type="text/javascript" src="tree.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="main.css" />
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
</div>
<div id="content">

<div class="right">
    <div id="stylized" class="myform">
        <form id="teachupdfrm" name="teachupdfrm" method="post" action="teacher_update.jsp"  >
                                <h3>Edit Teacher Information :</h3>
                                 <p>     
                   Fields marked with<strong>*</strong>are mandatory
                                </p>
                   <noscript> 
                       <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                               
                <label><span>Enter First Name whose record to update :</span>
               
                 <select name="fname1tu" type="submit" onchange="fwd();">
                   <%if(t2==null){%><option  value="-1">select one</option><% }
                     else {%><option value="<%=t2[0].getIdTeacher() %>"><%=t2[0].getFirstName()%></option><% }
                    while(td!=null) { %>
                   <option value="<%=td.getIdTeacher() %>"><%=td.getFirstName()%></option>
                   <% td=tda.getTeacher(); }
                   
                   %>                         
               </select></label>
                   
                          <h5 id="firstnameError">Invalid First Name.</h5>
                                
                    
                                
                 </form>
               
            <form  id="teachupdfrm2" name="teachupdfrm2" method="post" action="teachupd.jsp" onsubmit="return validateTeacherUpdateForm();">
                
                
                                <label>Now refill the information : </label>               

               
                
                <label><span>First Name :<strong>*</strong></span>
                    
                    <% if(t2==null){
                        %><input id="tufname1" type="text" name="tufname1" value=" " onfocus="showRequiredInfo('fname1Info');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="tufname1" type="text" name="tufname1" value="<%=t2[0].getFirstName()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                </label>
                <div id="fname1Info" class="required" >Please provide first name. Use 2 to 20 characters.Only letters are allowed.</div>
                <h5 id="fname1Error">Invalid First Name.</h5>
                            

                <label><span>Last Name :<strong>*</strong></span>
                    
                    <% if(t2==null){
                        %><input id="tulname1" type="text" name="tulname1" value=" " onfocus="showRequiredInfo('lnameInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="tulname1" type="text" name="tulname1" value="<%=t2[0].getLastName()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                    
                    
                    
                    
                    
                </label>
                <div id="lnameInfo" class="required">
                              Please provide last name of group. Use 2 to 20 characters.Only letters are allowed.
       
                </div>
                
                <h5 id="lnameError">Invalid Last name.</h5>
                

                
                
                       
         
                 <label><span>Designation :<strong>*</strong></span> 
                     
                     <% if(t2==null){
                        %><input id="tudes" type="text" name="tudes" value=" " onfocus="showRequiredInfo('desInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="tudes" type="text" name="tudes" value="<%=t2[0].getDesignation()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                     
                                        
                     
                    
                </label>
                <div id="desInfo" class="required" text-align="right">
                              Please provide your designation in maximum of 45 characters. 
       
                </div>
                
                <h5 id="desError">Invalid designation.</h5>
                
                <label><span>Qualification :<strong>*</strong></span>
                    <% if(t2==null){
                        %><input id="tuquali" type="text" name="tuquali" value=" " onfocus="showRequiredInfo('qualiInfo');" onblur="hideRequiredInfo()"/>
               <% } else { %>
               <input id="tuquali" type="text" name="tuquali" value="<%=t2[0].getQualification()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                    
                    
                    
                </label>
                <div id="qualiInfo" class="required">
                              Please provide your Qualification in maximum of 45 characters.
       
                </div>
                
                <h5 id="qualiError">Invalid entry.</h5>
                
                <div class="spacer"></div>

               
                <div class="spacer"></div>
                <input type="hidden" name="fname2" value="<%=s1%>"></input>
                
                <div class="controls">
                    <button type="submit" >Update</button>
                    <button type="reset" onclick="resetTeachupdForm();">Cancel</button>
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