
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Location" %>
<%@page import="tease.dao.LocationDAO" %>
<%@page import="tease.bean.TestSchedule" %>
<%@page import="tease.dao.TestScheduleDAO" %>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="testsch" class = "tease.bean.TestSchedule" scope="page"/>
<%! 
boolean z,p;
int x,t,y;



%>
<%

try{
java.util.Date date = new java.util.Date();

TestScheduleDAO s=new TestScheduleDAO();


x=Integer.parseInt(request.getParameter("testname"));
//t=Integer.parseInt(request.getParameter("loc"));
z=val.isStringValid(request.getParameter("sdt"));
p=val.isStringValid(request.getParameter("edt"));
Location l=new Location();
LocationDAO ld= new LocationDAO();
l=ld.getlocationByIdlocation(request.getParameter("loc"));
y=Integer.parseInt(request.getParameter("groupname"));
//if(x && y && z && p &&  t!=-1)

if(x!=-1  && z && p  && y!=-1)
    
{
   
    testsch.setLocation(l.getLocationName());
 //testsch.setStartTime(date);
    testsch.setIdGroup(y);
    testsch.setIdTest(x);

 //testsch.setEndTime(date);
 
s.insertTestSchedule(testsch);
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

