<%@page import="tease.helper.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Test" %>
<%@page import="tease.dao.TestDAO" %>
<jsp:useBean id="conn" class = "tease.helper.DBConnection" scope="page"/>

<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="test" class = "tease.bean.Test" scope="page"/>
<%! 
int x,r;
Float y;
Boolean m,n,o,p;
String y1,r1,a,b;



%>
<%


try{

Connection con=conn.getConnection();
TestDAO s=new TestDAO();


x=Integer.parseInt(request.getParameter("topname"));


a=request.getParameter("dname");
b=request.getParameter("tdesc");
y1=request.getParameter("marks");
r1=request.getParameter("dur");
m=val.isvarchar(a);
n=val.isStringInRange(b,1,100);
o=val.isFloat(y1);
p=val.isNumeric(r1);
if(m && n && o && p && x!=-1)
       {
    y=Float.parseFloat(request.getParameter("marks"));
 r=Integer.parseInt(request.getParameter("dur"));
 test.setDisplayName(a);
 test.setDuration(r);
 test.setFullDescription(b);
 test.setMarks(y);
 test.setIdTopic(x);
 
s.insertTest(test);
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

  