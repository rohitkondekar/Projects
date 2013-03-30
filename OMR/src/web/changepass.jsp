<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TEASE@IITB</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="sty.css" media="screen" />
<script type="text/javascript" src="tree.js"></script>
<script type="text/javascript" src="main.js"></script>
<link rel="stylesheet" type="text/css" href="tree.css" />
<link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>

<div id="wrap">



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<div id="top"></div>
<div id="header">
    <img align="left" src="images/4a.jpg" height="90" width="120"/>
<div class="logo">
    <h1>Indian Institute of Technology Bombay</h1>

</div>


</div>

<div id="menu">
<ul>
                       
                        <li><a href="Admin_Home.jsp">Home</a></li>
			<li><a href="about.jsp">About Us</a></li>
                        <li><a href="contact.jsp">Contact Us</a></li>
			<li><a href="feedback.jsp">Feed Back</a></li>
			<li><a href="faq.jsp">FAQ's</a></li>
			<li><a href="http://www.iitb.ac.in">IITB</a></li>
                        <li><a href="index.jsp">Logout</a></li>
</ul>
</div>
<div id="content">

<div class="right">

<div id="stylized" class="myform">
         
            <form id="instiregform" name="instiregform" method="post" action="regform_insti.jsp" onsubmit="return validateInstiregForm();">
                                <h3>Change Password </h3>
                
                
                
                 
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>
                 <noscript> 
                            <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                

                <label><span> User Name :<strong>*</strong></span>
               
                <input id="UName" type="text" name="UName" value="" onfocus="showRequiredInfo('NameInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="NameInfo" class="required">Name should have 3 to 50 characters. Only letters are allowed.</div>
                <h5 id="NameError">Invalid name.</h5>
                
                <label><span>Old Password :<strong>*</strong></span>
                <input id="oldpwd" type="password" name="oldpwd" value="" onfocus="showRequiredInfo('mottoInfo');" onblur="hideRequiredInfo()"/>
                </label>

                <div id="mottoInfo" class="required">School motto should have 3 to 30 characters. Only letters are allowed.</div>
                <h5  id="mottoError">Invalid entry.</h5>
                

                <label><span> New Password :<strong>*</strong></span>
                    <input id="newpwd" type="password" name="newpwd" value=""  onfocus="showRequiredInfo('ilogoInfo');" onblur="hideRequiredInfo()"/>
                
                </label>
                
                <h5  id="ilogoError">Invalid entry.</h5>
                
                
                 <label><span>Retype Password :<strong>*</strong></span>
                    <input id="repwd" type="password" name="repwd" value="" onfocus="showRequiredInfo('emailInfo');" onblur="hideRequiredInfo();"/>
                </label>
                <div id="emailInfo" class="required">email address of student is required so that he/she can get all the information, alerts and updates to the registered email address.</div>
                
                <h5 id="iemailError">Invalid email address</h5>


                 <div class="spacer"></div>
                 <div class="spacer"></div>
                 <div class="spacer"></div>
                 <div class="spacer"></div>
                 <div class="spacer"></div>
                 <div class="spacer"></div>

                <div class="controls">
                    <button type="submit" >Register</button>
                    <button type="reset" onclick="resetRegistrarationForm();">Reset</button>
                </div>
                <br />
               
            </form>
        
        </div>  



</div>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">








<div class="left">

</div>
<div style="clear: both;"></div>
</div>
<div id="footer">
    
</div>

</body>
</html>