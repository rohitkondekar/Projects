<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.Student" %>
<%@page import="tease.dao.StudentDAO" %>
<%@page import="Validate.Validation" %>
<%! 
boolean x,y,z,p,q,r,n,o,e;
char d;
String s1,s2,captcha,s3;
int b=0,t=0;
long m;
%>
<%
try{

Validation val=new Validation();
StudentDAO s=new StudentDAO();
Student std=new Student();
      
    s1=request.getParameter("gender");
    
x=val.checkName(request.getParameter("firstName"));
y=val.checkName(request.getParameter("lastName"));
z=val.isvarchar(request.getParameter("rollno"));
p=val.isPhoneNumberValid(request.getParameter("mobile"));
q=val.isStringValid(request.getParameter("dob"));
r=val.isEmailValid(request.getParameter("email"));
    s3=request.getParameter("captchaText");
n=val.isStringInRange(s3, 6, 6);
o=val.isStringValid(s3);
captcha=request.getSession().getAttribute("captcha").toString();
if( s1!=null && x && y && z && p && q && r && n && o && s3.equals(captcha) ) {
d=s1.charAt(0);

s2=request.getParameter("dob");
SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
Date date=null;
out.println(formatter.parseObject(s2));
date=(Date)formatter.parse(s2);


m=Long.parseLong(request.getParameter("mobile"));
 
 std.setFirstName(request.getParameter("firstName"));
 std.setLastName(request.getParameter("lastName"));
 std.setMobile(m);
 std.setEmail(request.getParameter("email"));
 std.setRollNumber(request.getParameter("rollno"));  
 std.setBirthDate(date);
 std.setGender(d);
e=s.insertStudent(std);

if(e){
    %><jsp:forward page="msginsert.jsp"/>
    <%
}
else {
    %><jsp:forward page="msginsertfail.jsp"/>
    <%
}
    
    
} 

else
{
   %><jsp:forward page="msginsertfail.jsp"/>
    <%
}
}
catch(Exception e){
    %><jsp:forward page="msginsertfail.jsp"/>
    <%
}
%>

