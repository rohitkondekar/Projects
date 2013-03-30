<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import="java.util.*" %>
<%@page import="Validate.Validation" %>
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<%!  
String s1,s2,s3,s4;
char a;
long b;
int s,t;
boolean x,y,p,q,z,r,t2;
Date date=null;
%>
<% try{
    Validation val2=new Validation();
java.util.Date date = new java.util.Date();
StudentDAO sdo=new StudentDAO();
Student s=new Student();
Student lnk4=new Student();
t=Integer.parseInt(request.getParameter("fnamesu"));

s1=request.getParameter("mobile2");
s2=request.getParameter("fnamesu");
s3=request.getParameter("gender2");
a=s3.charAt(0);
x=val2.checkName(request.getParameter("firstName2"));
y=val2.checkName(request.getParameter("lastName2"));
z=val2.isPhoneNumberValid(request.getParameter("mobile2"));
p=val2.isEmailValid(request.getParameter("email2"));
q=val2.isvarchar(request.getParameter("studentroll2"));
//r=val2.isStringValid(request.getParameter("dob2")); 
if(x && y  && z && p && q  && s2!=null)
{
//s4=request.getParameter("dob");
//SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");

//out.println(formatter.parseObject(s4));
//date=(Date)formatter.parse(s4);

b=Long.parseLong(s1);    
t=Integer.parseInt(request.getParameter("fnamesu"));   
lnk4.setFirstName(request.getParameter("firstName2"));
lnk4.setLastName(request.getParameter("lastName2"));
lnk4.setIdStudent(t);
lnk4.setMobile(b);
lnk4.setGender(a);
lnk4.setRollNumber(request.getParameter("studentroll2"));
lnk4.setEmail(request.getParameter("email2"));
lnk4.setBirthDate(date);
t2=sdo.updateStudent(lnk4);
if(t2) {
%>
 <jsp:forward page="msginsert.jsp"/>
<%
}
else {
    
    %>
 <jsp:forward page="msginsertfail.jsp"/>
<%
}
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


