<%@page import= "java.util.*" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%! String s,s1;
int x,j,i;
%>
<%
GroupTbl td= new GroupTbl(); 
GroupTbl td1= new GroupTbl(); 
GroupTblDAO tda=new GroupTblDAO();
GroupTblDAO tda1=new GroupTblDAO();
GroupTbl[] t2=null;
td=tda.getGroupTbl();
try{
s1=request.getParameter("gname1");
if(s1==null){
    td1=tda1.getGroupTbl();
    
    if(td1==null){
        %><jsp:forward page="msginsertfail.jsp"/><%
    }
    s1=td1.getDisplayName();
}
else{
x=Integer.parseInt(s1);
t2=tda1.getGroupByParameter(x,0,null,null);
    }
}
catch(Exception e){
    %>
 <jsp:forward page="msgdeletefail.jsp"/>
<%
}
%>





<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<script type="text/javascript">
    function fwd()
    {
     document.forms["grpdelform"].submit();   
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
         
            <form id="grpdelform" name="grpdelform" method="POST"  action="group_delete.jsp" >  <%--action="group_delete.jsp"--%>
                                <h3>Delete Group</h3>
                
                
                
                 
                
                                <p>    Fields marked with<strong>*</strong>are mandatory</p>
                <noscript> <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                
                

                <label><span>Group Name :<strong>*</strong></span>
                    
                 <select name="gname1" type="submit" onchange="fwd();" onfocus="showRequiredInfo('GrpInfo');" onblur="hideRequiredInfo()">
                   <%if(t2==null){%><option  value="-1">select one</option><% }
                     else {%><option value="<%=t2[0].getIdGroup() %>"><%=t2[0].getDisplayName()%></option><% }
                    while(td!=null) { 
                      
                            %>
                   <option value="<%=td.getIdGroup() %>"><%=td.getDisplayName()%></option>
                   <% td=tda.getGroupTbl(); }
                   
                   %>                         
               </select></label>
               <div id="GrpInfo" class="required">You cannot delete the root.</div>
               <h5 id="gname1Error">Please select one.</h5>
                   
                   <% if(t2!=null){
                        %>         

                <label><span>Parent Name :<strong>*</strong></span>
                    
                <input id="pgname1" type="text" disabled="diasabled" name="pgname1" value="<%=t2[0].getDisplayName()%>" />
               <% } %>
                </label>
               
               <% if(t2!=null) { %> 
                
               <label><span>Description :</span>
                   
                     <input id="desc" type="text" name="desc" value="<%=t2[0].getFullDescription()%>" disabled="disabled" value="" />
                 </label>
               <% } %> 
                
       
               
            </form>

            <form  method="POST" name="grpdel2" action="grpdel.jsp" onsubmit="return validateGrpDelForm();"> <%--action="grpdel.jsp"--%>
                <div class="spacer"></div>
                <input type="hidden" name="gname1" value="<%=s1%>"></input>
                <div class="controls">
                    <button type="submit" >Delete</button>
                    
                    <button type="reset" onclick="resetGrpDelForm();">Cancel</button>
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