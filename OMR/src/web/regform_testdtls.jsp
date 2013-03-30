<%@page import="Validate.Validation"%>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.TestDetails" %>
<%@page import="tease.dao.TestDetailsDAO" %>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="testdt" class = "tease.bean.TestDetails" scope="page"/>
<%! 
int b1,c1,d1,a,b,c,d;
float g,f;
String e;
String a1,f1,g1;
String p,q,r;
Boolean l,m,n,o,z,z1;


%>
<%

try {
TestDetailsDAO s=new TestDetailsDAO();

a1=request.getParameter("testname1");
e=request.getParameter("ans");
l=val.isNumeric(request.getParameter("seq"));
m=val.isNumeric(request.getParameter("qopt"));
n=val.isFloat(request.getParameter("marks"));
o=val.isFloat(request.getParameter("nmarks"));
if(a1!=null && l && m && n && o && val.isStringValid(e))
       {
    
    a=Integer.parseInt(request.getParameter("testname1"));
    b=Integer.parseInt(request.getParameter("seq"));
c=Integer.parseInt(request.getParameter("qtype"));
d=Integer.parseInt(request.getParameter("qopt"));
f=Float.parseFloat(request.getParameter("marks"));
g=Float.parseFloat(request.getParameter("nmarks"));
    
testdt.setMarks(f);
testdt.setNegativeMarks(g);
testdt.setQuestionOptions(d);
testdt.setQuestionType(c);
testdt.setSequence(b);
testdt.setCorrectAnswer(e);
testdt.setIdTest(a);
s.insertTestDetails(testdt);
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

  