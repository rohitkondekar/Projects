<%@page import="Validate.Validation" %>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>

<%@page import="tease.bean.Student" %>
<%@page import="tease.bean.GroupTbl" %>

<%@page import="tease.dao.StudentDAO" %>
<%@page import="tease.dao.GroupTblDAO" %>

<%@page import="tease.bean.Enrollment" %>
<%@page import="tease.dao.EnrollmentDAO" %>
<%! int x,y,z; 
boolean p;
%>
<%
try{
StudentDAO sd=new StudentDAO();
Student s=new Student();
GroupTblDAO gp=new GroupTblDAO();
GroupTbl g=new GroupTbl();

Enrollment as=new Enrollment();
EnrollmentDAO asd=new EnrollmentDAO();
x=Integer.parseInt(request.getParameter("sname"));
y=Integer.parseInt(request.getParameter("grpname"));
p=val.checkName(request.getParameter("enroll"));
 if(x!=-1 && y!=-1 && p)
 {
 as.setIdStudent(x);
 as.setIdGroup(y);
 as.setIdEnrollment(request.getParameter("enroll"));
 
 asd.insertEnrollment(as);
 %>
 %>
 <jsp:forward page="msginsert.jsp"/>
<%
}
else
{
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
}
catch(Exception e){
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
%>
