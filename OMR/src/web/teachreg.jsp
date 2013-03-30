
<%@page import="tease.bean.Teacher" %>
<%@page import="tease.dao.TeacherDAO" %>

<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk2" class = "tease.bean.Teacher" scope="page"/>
<%! boolean x,y,z,p;
%>
<% 
try{
TeacherDAO s=new TeacherDAO();
x=val.checkName(request.getParameter("fname1"));
y=val.checkName(request.getParameter("lname1"));
z=val.checkName(request.getParameter("desg"));
p=val.isStringValid(request.getParameter("qual"));
if(x && y && z && p)
{lnk2.setFirstName(request.getParameter("fname1"));
 lnk2.setLastName(request.getParameter("lname1"));
 lnk2.setDesignation(request.getParameter("desg"));
 lnk2.setQualification(request.getParameter("qual"));
 s.insertTeacher(lnk2);
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
catch(Exception e)
               {
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
%>

