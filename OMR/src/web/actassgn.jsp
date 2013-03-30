<%@page import="tease.helper.*" %>
<%@page import="Validate.Validation" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>

<%@page import="tease.bean.Teacher" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.bean.Topic" %>
<%@page import="tease.dao.TeacherDAO" %>
<%@page import="tease.dao.GroupTblDAO" %>
<%@page import="tease.dao.TopicDAO" %>
<%@page import="tease.bean.Assignment" %>
<%@page import="tease.dao.AssignmentDAO" %>
<%! int x,y,z; 
boolean p;
%>
<%
TeacherDAO sd=new TeacherDAO();
Teacher s=new Teacher();
GroupTblDAO gp=new GroupTblDAO();
GroupTbl g=new GroupTbl();
TopicDAO td=new TopicDAO();
Topic t=new Topic();
Assignment as=new Assignment();
AssignmentDAO asd=new AssignmentDAO();
x=Integer.parseInt(request.getParameter("tname"));
y=Integer.parseInt(request.getParameter("gname"));
z=Integer.parseInt(request.getParameter("topicname"));
p=val.checkName(request.getParameter("assgname"));
 if(x>0 && y>0 && z>0 && p)
         {
 as.setIdTeacher(x);
 as.setIdGroup(y);
 as.setIdTopic(z);
 as.setAssignmentcol(request.getParameter("assgname"));
 asd.insertAssignment(as);
 %>
 %>
 <jsp:forward page="Admin_Home.jsp"/>
<%
}
else
{
    %>
 <jsp:forward page="assgn.jsp"/>
<%
}
 
%>
