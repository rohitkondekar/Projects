<%-- 
    Document   : logfrm
    Created on : 17 Jun, 2011, 7:35:36 PM
    Author     : alefiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@page import="tease.bean.Login" %>
<%@page import="tease.dao.LoginDAO" %>
<%@page import="Validate.Validation" %>
<%! boolean x,y; %>
<%
try{
    
    
Login lg=new Login();
LoginDAO lgd=new LoginDAO();
Validation v=new Validation();
x=v.checkName(request.getParameter("name"));
if(x)
{
    
    lg=lgd.getLogin();
     while(lg!=null)
        {
         if(lg.getUserName().equals(request.getParameter("name")) && lg.getPassword().equals(request.getParameter("password")))
                   { 
             session.setAttribute("uname",request.getParameter("name"));
             %><jsp:forward page="Admin_Home.jsp"/><%  }
 
                  lg=lgd.getLogin();
         }
        }
       
else{
    %><jsp:forward page="topic.jsp"/><%
}
}
       
catch(Exception e)
               {
     %><jsp:forward page="insti_reg.jsp"/><%
       }
%><jsp:forward page="index.jsp"/>
   
