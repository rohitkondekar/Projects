
<%@page import="Validate.Validation" %>
<%@page import="tease.bean.Teacher" %>
<%@page import="tease.dao.TeacherDAO" %>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="page"/>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk2" class = "tease.bean.Teacher" scope="page"/>
<%! boolean x,y,z,p,t2;
int a;
String str;
%>
<% 
try{
  
Teacher[] ts=null;
Teacher tr= new Teacher();

TeacherDAO s=new TeacherDAO();
str=request.getParameter("fname2");
out.println(str);
if(str!=null){
a=Integer.parseInt(str);
x=val.checkName(request.getParameter("tufname1"));
y=val.checkName(request.getParameter("tulname1"));
z=val.checkName(request.getParameter("tudes"));
p=val.isStringValid(request.getParameter("tuquali"));
if(x && y && z )
{ //ts=s.getTeacherByParameter(a,null,null,null,null);
tr.setIdTeacher(a);
tr.setFirstName(request.getParameter("tufname1"));
tr.setLastName(request.getParameter("tulname1"));
tr.setDesignation(request.getParameter("tudes"));
tr.setQualification(request.getParameter("tuquali"));
t2=s.updateTeacher(tr);
if(t2){
 %>
 <jsp:forward page="msgupdate.jsp"/>
<%
}
else{
    %>
 <jsp:forward page="msgupdatefail.jsp"/>
<%
}
}
}
}
catch(Exception e){
%>
 <jsp:forward page="msgupdatefail.jsp"/>
<%

}

%>

