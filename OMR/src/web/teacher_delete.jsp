<%@page import= "java.util.*" %>
<%@page import="tease.bean.Teacher" %>
<%@page import="tease.dao.TeacherDAO" %>
<%! String s,s1;
int x,j,i;
%>
<%
Teacher td= new Teacher(); 
Teacher td1= new Teacher(); 
TeacherDAO tda=new TeacherDAO();
TeacherDAO tda1=new TeacherDAO();
Teacher[] t2=null;
td=tda.getTeacher();
s1=request.getParameter("fname1tv");
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<script type="text/javascript">
    function fwd()
    {
     document.forms["teachdelform"].submit();   
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
<ul><
                        
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
         
            <form id="teachdelform" name="teachdelform" method="POST" action="teacher_delete.jsp" onsubmit="return validateTeachDelForm();">
                                <h3>Delete Teacher</h3>
                
                
                
                 
                
                                <p> Fields marked with<strong>*</strong>are mandatory</p>
                <noscript> <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                
                

                <label><span>First Name :<strong>*</strong></span>
                    
                
               <select name="fname1tv" type="submit" onchange="fwd();">
                   <%if(t2==null){%><option  value="-1">select one</option><% }
                     else {%><option value="<%=t2[0].getIdTeacher() %>"><%=t2[0].getFirstName()%></option><% }
                    while(td!=null) { %>
                   <option value="<%=td.getIdTeacher() %>"><%=td.getFirstName()%></option>
                   <% td=tda.getTeacher(); }
                   
                   %>                         
               </select></label>
               <h5 id="fname1Error">Please select one.</h5>
                   
                   <% if(t2!=null){
                        %>         

                <label><span>Last Name :<strong>*</strong></span>
                    
                <input id="lname2" type="text" disabled="diasabled" name="lname1" value="<%=t2[0].getLastName()%>" onfocus="showRequiredInfo('lname2Info');" onblur="hideRequiredInfo()"/>
               <% } %>
                </label>
                <% if(t2!=null) { %> 
                
               <label><span>Designation :</span>
                   
                     <input id="desId" type="text" name="desId" value="<%=t2[0].getDesignation()%>" disabled="disabled" value="" />
                 </label>
               <% } %> 
                
                <% if(t2!=null) { %> 
                <label><span>Qualification :</span>
                     <input id="qualId" type="text" name="qualId" value="<%=t2[0].getQualification()%>" disabled="disabled" value=""/>
   
                </label>
               
                <% } %> 
            </form>
            <form action="teachdel.jsp" method="POST" name="teachdel2" onsubmit="return validateTeachDelForm();">
                <div class="spacer"></div>
                <input type="hidden" name="fname1" value="<%=s1%>"></input>
                <div class="controls">
                    <button type="submit" >Delete</button>
                    <button type="reset" onclick="resetTeachDel();">Cancel</button>
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
  
    <!--<li><a href='#'>Physics</a><ul><li><a href='showContents.do?topicId=5'>Electricity</a></li><li><a href='showContents.do?topicId=6'>Magnetic Effect</a></li><li><a href='showContents.do?topicId=7'>Energy</a></li><li><a href='showContents.do?topicId=18'>Mechanics</a></li><li><a href='showContents.do?topicId=19'>Light</a></li></ul><li><a href='#'>Chemistry</a><ul><li><a href='#'>Chemical Reaction</a><ul><li><a href='showContents.do?topicId=13'>Acids</a></li><li><a href='showContents.do?topicId=14'>Bases</a></li><li><a href='showContents.do?topicId=15'>Salts</a></li></ul></ul><li><a href='#'>Maths</a><ul><li><a href='#'>Geometry</a><ul><li><a href='showContents.do?topicId=16'>Circle</a></li><li><a href='showContents.do?topicId=17'>Triangle</a></li></ul><li><a href='showContents.do?topicId=9'>Trignometry</a></li><li><a href='showContents.do?topicId=10'>Statistics</a></li><li><a href='showContents.do?topicId=11'>Profit and Loss</a></li></ul><li><a href='showContents.do?topicId=4'>Biology</a></li>

-->
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