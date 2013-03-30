
<%@page import="tease.bean.Topic" %>
<%@page import="tease.dao.TopicDAO" %>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>

<%! 
boolean a,b,c,d;
int x;
String y,z,s1;
%>
<%
try {
Topic top=new Topic();
TopicDAO s=new TopicDAO();
s1=request.getParameter("prtname");
y=request.getParameter("tpname");
a=val.checkName(y);
z=request.getParameter("fl_desc");
b=val.isStringInRange(z, 1, 100);
if(a && b && s1!=null)
       {
    x=Integer.parseInt(request.getParameter("prtname"));
    top.setDisplayName(y);
 top.setFullName(z);
 top.setIdParent(x);
s.insertTopic(top);
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

  