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
                                <h3>Registration for Institute</h3>
                
                
                
                 
                <p>
                   Fields marked with<strong>*</strong>are mandatory
                </p>
                 <noscript> 
                            <ul><strong><u>Javascript is not enabled on your browser. For better functionality, please enable it before you fill the information.</u></strong></ul>
                   </noscript>
                

                <label><span>Name :<strong>*</strong></span>
               
                <input id="Name" type="text" name="Name" value="" onfocus="showRequiredInfo('NameInfo');" onblur="hideRequiredInfo()"/>
                </label>
                <div id="NameInfo" class="required">Name should have 3 to 50 characters. Only letters are allowed.</div>
                <h5 id="NameError">Invalid name.</h5>
                
                <label><span>Motto :<strong>*</strong></span>
                <input id="motto" type="text" name="motto" value="" onfocus="showRequiredInfo('mottoInfo');" onblur="hideRequiredInfo()"/>
                </label>

                <div id="mottoInfo" class="required">School motto should have 3 to 30 characters. Only letters are allowed.</div>
                <h5  id="mottoError">Invalid entry.</h5>
                

                <label><span>Institute logo :<strong>*</strong></span>
                    <input id="ilogo" type="text" name="ilogo" value=""  onfocus="showRequiredInfo('ilogoInfo');" onblur="hideRequiredInfo()"/>
                    <input type="button" id="button" onclick="checkStudentId();" class="btn" value="Upload"/>
                </label>
                <div id="ilogoInfo" class="required">
                               Upload the pic of Institute logo.
       
                </div>
                <h5  id="ilogoError">Invalid entry.</h5>
                
                

                <label><span>Level of Education :<strong>*</strong></span>
                <select id="loe" name="loe">
                    <option value="-1"><-Select one-></option>
                    
                    <option value="1">Nursery</option>
                    
                    <option value="2">Primary school</option>
                    
                    <option value="3">Middle school</option>
                    
                    <option value="4">High School</option>
                    
                    <option value="5">Higher-Secondary</option>

                    
                </select>
                </label>
                <h5 id="loeError">Please select any level of education.</h5>
                
                
                               <label><span>Email :<strong>*</strong></span>
                    <input id="email" type="text" name="email" value="" onfocus="showRequiredInfo('emailInfo');" onblur="hideRequiredInfo();"/>
                </label>
                <div id="emailInfo" class="required">email address of student is required so that he/she can get all the information, alerts and updates to the registered email address.</div>
                
                <h5 id="iemailError">Invalid email address</h5>


                <label><span>Institute Description :<strong>*</strong></span>
                 <textarea name="instidescription" cols="26" rows="6" onfocus="showRequiredInfo('idescInfo');" onblur="hideRequiredInfo()"></textarea>
                </label>
                <div id="idescInfo" class="required">Enter the description about the institute. Alpha-numeric characters are allowed (max. 100).</div>
                <h5 id="idescError">Invalid description</h5>
                
                

                
                
                

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