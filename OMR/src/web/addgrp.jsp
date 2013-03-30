<%@page import="tease.helper.*" %>

<%@page import= "java.sql.*" %>
<%@page import= "java.util.*" %>
<%@page import="tease.bean.GroupTbl" %>
<%@page import="tease.dao.GroupTblDAO" %>
<jsp:useBean id="lnk3" class = "tease.helper.DBConnection" scope="page"/>
<jsp:useBean id="val" class = "Validate.Validation" scope="page"/>
<jsp:useBean id="lnk2" class = "tease.bean.GroupTbl" scope="page"/>
<%! boolean x,y,p;
String x1;
int z;
%>
<% 


//Connection con=lnk3.getConnection();
GroupTblDAO g=new GroupTblDAO();
x=val.isvarchar(request.getParameter("dnameId"));
x1=request.getParameter("dnameId");
y=val.isStringInRange(request.getParameter("full_description"),1,100);
z=Integer.parseInt(request.getParameter("pGroupname"));
//z=val.checkName(request.getParameter("desId"));
//p=val.checkName(request.getParameter("qualId"));
if(x && y && (z != -1)&& (x1.length()<=20) )
{
    lnk2.setIdparentGroup(z);
       lnk2.setDisplayName(request.getParameter("dnameId"));
       lnk2.setFullDescription(request.getParameter("full_description"));
       p=g.insertGroupTbl(lnk2);
       if(p){%>
 <jsp:forward page="msginsert.jsp"/>
<% }
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

%>

