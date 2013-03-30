<%@page import="tease.helper.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Location" %>
<%@page import="tease.dao.LocationDAO" %>
<jsp:useBean id="conn" class = "tease.helper.DBConnection" scope="page"/>

<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="loc" class = "tease.bean.Location" scope="page"/>
<%! 
String y,z;
int z1;
Boolean a,b,c,d;

%>
<%

try {
LocationDAO s=new LocationDAO();
y=request.getParameter("locname1");
z=request.getParameter("locst");
a=val.checkName(request.getParameter("locname1"));
b=val.isNumeric(z);
if(a && b ){
    z1=Integer.parseInt(request.getParameter("locst"));
    loc.setLocationName(y);
 loc.setLocationStrength(z1);
 s.insertLocation(loc);
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
catch(Exception e) {
     %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
%>

  