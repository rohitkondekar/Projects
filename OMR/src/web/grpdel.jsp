<%@page import="tease.helper.*" %>
<%@page import="Validate.Validation" %>

<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="page"/>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>

<%! boolean x,y,z,p,q;
int a;
String str;
%>
<% 


try{
GroupTbl[] gt=null;

//Connection con=lnk3.getConnection();
GroupTblDAO g=new GroupTblDAO();
str=request.getParameter("gname1");
//out.println(str);
if(str!=null){
a=Integer.parseInt(str);
//x=val.checkName(request.getParameter("fname1"));
//y=val.checkName(request.getParameter("lname1"));
//z=val.checkName(request.getParameter("desg"));
//p=val.checkName(request.getParameter("qual"));
//if(x && y && z && p)

    //ts=s.getTeacherByParameter(a,null,null,null,null);
if(a==1){
%>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
   q=g.deleteGroupTbl(a);
   if(q)
             {
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
}
catch(Exception e){
    
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}



//lnk3.closeConnection(con);

%>

