<%@page import="Validate.Validation" %>
<%@page import="tease.bean.Teacher" %>
<%@page import="tease.dao.TeacherDAO" %>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="page"/>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk2" class = "tease.bean.Teacher" scope="page"/>
<%! boolean x,y,z,p,q;
int a;
String str;
%>
<% 
try {
Teacher[] ts=null;
TeacherDAO s=new TeacherDAO();
str=request.getParameter("fname1");
out.println(str);
if(str!=null){
a=Integer.parseInt(str);
q=s.deleteTeacher(a);
if(q){
 %>
 <jsp:forward page="msginsert.jsp"/>
<%
}
else{
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
}
else{
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

