
function isStringInRange(str, minLength, maxLength) {
    var result = false;
    str = trim(str);
    if(isStringValid(str)) {
        if(str.length >= minLength && str.length <= maxLength) {
            result = true;
        }
        
    }
    return result;
}


/*
     * isStringValid() method is used to check that the given string is not null and
     * it's length is in a given range.
     * If given string is not null and it's length is in a given range return true otherwise false.
     * It has one parameter, a string that is to be checked for validity.
     * it returns true or false depending on the validity of the string.
*/
function isStringValid(str) {
    var result = true;
    str = trim(str);
    if(str == "") {
        result=false;
    }
    return result;
}



function validateInteger( strValue ) {
/************************************************
DESCRIPTION: Validates that a string contains only
    valid integer number.

PARAMETERS:
   strValue - String to be tested for validity

RETURNS:
   True if valid, otherwise false.
**************************************************/
  var objRegExp  = /(^\d\d*$)/;

  //check for integer characters
  return objRegExp.test(strValue);
}




function validateDate( strValue ) {
/************************************************
DESCRIPTION: Validates that a string contains only
    valid dates with 2 digit month, 2 digit day,
    4 digit year. Date separator can be ., -, or /.
    Uses combination of regular expressions and
    string parsing to validate date.
    Ex. mm/dd/yyyy or mm-dd-yyyy or mm.dd.yyyy

PARAMETERS:
   strValue - String to be tested for validity

RETURNS:
   True if valid, otherwise false.

REMARKS:
   Avoids some of the limitations of the Date.parse()
   method such as the date separator character.
*************************************************/
  var objRegExp = /^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/
 
  //check to see if in correct format
  if(!objRegExp.test(strValue))
    return false; //doesn't match pattern, bad date
  else{
    var strSeparator = strValue.substring(2,3) 
    var arrayDate = strValue.split(strSeparator); 
    //create a lookup for months not equal to Feb.
    var arrayLookup = { '01' : 31,'03' : 31, 
                        '04' : 30,'05' : 31,
                        '06' : 30,'07' : 31,
                        '08' : 31,'09' : 30,
                        '10' : 31,'11' : 30,'12' : 31}
    var intDay = parseInt(arrayDate[1],10); 

    //check if month value and day value agree
    if(arrayLookup[arrayDate[0]] != null) {
      if(intDay <= arrayLookup[arrayDate[0]] && intDay != 0)
        return true; //found in lookup table, good date
    }
    
    //check for February (bugfix 20050322)
    //bugfix  for parseInt kevin
    //bugfix  biss year  O.Jp Voutat
    var intMonth = parseInt(arrayDate[0],10);
    if (intMonth == 2) { 
       var intYear = parseInt(arrayDate[2]);
       if (intDay > 0 && intDay < 29) {
           return true;
       }
       else if (intDay == 29) {
         if ((intYear % 4 == 0) && (intYear % 100 != 0) || 
             (intYear % 400 == 0)) {
              // year div by 4 and ((not div by 100) or div by 400) ->ok
             return true;
         }   
       }
    }
  }  
  return false; //any other values, bad date
}



function  validateNumeric( strValue ) {
/*****************************************************************
DESCRIPTION: Validates that a string contains only valid numbers.

PARAMETERS:
   strValue - String to be tested for validity

RETURNS:
   True if valid, otherwise false.
******************************************************************/
  var objRegExp  =  /(^\d\d*\.\d*$)|(^\d\d*$)/;

  //check for numeric characters
  return objRegExp.test(strValue);
}


function removeCharacters( strValue, strMatchPattern ) {
/************************************************
DESCRIPTION: Removes characters from a source string
  based upon matches of the supplied pattern.

PARAMETERS:
  strValue - source string containing number.

RETURNS: String modified with characters
  matching search pattern removed

USAGE:  strNoSpaces = removeCharacters( ' sfdf  dfd',
                                '\s*')
*************************************************/
 var objRegExp =  new RegExp( strMatchPattern, 'gi' );

 //replace passed pattern matches with blanks
  return strValue.replace(objRegExp,'');
}

function trim(s)
{
    var l=0;var r=s.length -1;
    while(l < s.length && s[l] == ' ')
    {l++;}
    while(r > l && s[r] == ' ')
    {r-=1;}
    return s.substring(l, r+1);
}

function emailValidator(email){
    var result = false;
    var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
    if(email.match(emailExp)){
        result = true;
    }
    return result;
}


function isvarchar(str)
{
    var result= false;
    var varcharexp=/^[a-zA-z0-9]+$/;
    if(str.match(varcharexp)){
        result=true;
    }
    return result;
    }

function validateName(str) {
   /* var result = false;
    var dispNameExp = /^[a-zA-Z][a-zA-Z ]+[a-zA-Z]+$/;
    if(str.match(dispNameExp)){
        var previous = str.charAt(0);
        var current;
        for(i=1;i<str.length;i++) {
            current = str.charAt(i);
            if(previous == current && previous == ' ') {
                return false;
            }
            previous = current;
        }
        result = true;
    }
    return result;*/
    
   var re = /^[A-Za-z]{1,45}$/
 if (re.test(str)) return true;
 return false;
}

function validateTestSchForm()
{
 var result = true;
    //getting all the form values
    var ta = document.testsch.testname.value;
    var grpa=document.testsch.groupname.value;
    var grps=document.testsch.selgrp.value;
    var loc=document.testsch.loc.value;
    var ed=document.testsch.edt.value;
    var sd=document.testsch.sdt.value;
    ta=trim(ta);
    grpa=trim(grpa);
    grps=trim(grps);
    loc=trim(loc);
    sd=trim(sd);
    ed=trim(ed);
    
    if(!isStringInRange(ta,3,16) || !isvarchar(ta)) {
         document.getElementById('testnameError2').style.display = "inline";
         result = false;
    }
    if(grpa=="-1") {
             document.getElementById('gIdError2').style.display = "inline";
             result = false;}
         
         if(loc=="-1") {
             document.getElementById('locError').style.display = "inline";
             result = false;
         }
    if( sd=="" || !validateDate(sd)){
        document.getElementById('sdtError').style.display = "inline";
        result=false;
    }
    if( ed=="" || !validateDate(ed)){
        document.getElementById('edtError').style.display = "inline";
        result=false;
    }
    return result;
}
function validateTeacherRegForm() {
    var result = true;
    //getting all the form values
    //var tid1 = document.teachregfrm.tId.value;
    var fname1 = document.teachregfrm.fname1.value;
    var lname1 = document.teachregfrm.lname1.value;
    //var sex1 = document.teachregfrm.gender.value;
    var desg1 = document.teachregfrm.desg.value;
    var qual1 = document.teachregfrm.qual.value;
    var qual2 = document.teachregfrm.qual.value;
    
    fname1 = trim(fname1);
    lname1 = trim(lname1);
    
    desg1 = trim(desg1);
    qual1= trim(qual1);
    qual2= trim(qual2);
    
    qual2=removeCharacters(qual2,'\\.');
   qual2=removeCharacters(qual2,'\\(');
   qual2=removeCharacters(qual2,'\\)');
    
    
    if(!isStringInRange(fname1,2,20) || !validateName(fname1)) {
        document.getElementById('fname1Error').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(lname1,2,20) || !validateName(lname1)) {
        document.getElementById('lname1Error').style.display = "inline";
        result = false;
    }
    
    if(!isStringInRange(desg1,2,45) || !validateName(desg1)) {
        document.getElementById('desError').style.display = "inline";
        result = false;
    }
   
  if(!isStringInRange(qual1,2,45) || !validateName(qual2)) {
        document.getElementById('qualError').style.display = "inline";
        result = false;
    }
   
    return result;
}
function resetTeachRegForm()
{
    //document.getElementById('tIdError').style.display = "none";
     document.getElementById('fname1Error').style.display = "none";
    document.getElementById('lname1Error').style.display = "none";
    document.getElementById('desError').style.display = "none";
    document.getElementById('qualError').style.display = "none";
    
    //document.getElementById('genderError').style.display = "none";
}
function validateRegistrationForm() {
    var result = true;
    //getting all the form values
    var firstName = document.regform.firstName.value;
    var lastName = document.regform.lastName.value;
   var rollno = document.regform.rollno.value;
    var dob = document.regform.dob.value;
    var email = document.regform.email.value;
    var mobile = document.regform.mobile.value;
    var gender = document.regform.gender.value;
    var captchaAnswer = document.regform.captchaText.value;
    //trim all the form values
    firstName = trim(firstName);
    lastName = trim(lastName);
   rollno=trim(rollno);
    email = trim(email);
    mobile = trim(mobile);
    dob=trim(dob);
    captchaAnswer = trim(captchaAnswer);
    //clearing all the previous errors
    resetRegistrarationForm();
    //validating values
    //document.getElementById('firstNameError').style.display = "inline";
    
    if(!isStringInRange(firstName,2,20) || !validateName(firstName)) {
        document.getElementById('firstNameError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(lastName,2,20) || !validateName(lastName)) {
        document.getElementById('lastNameError').style.display = "inline";
        result = false;
    }
    
    if(!isvarchar(rollno) || rollno.length>15) {
      document.getElementById('rollnoError').style.display = "inline";
         result = false;
    }
    if(dob=="") {
        document.getElementById('dobError').style.display = "inline";
        result = false;
    }

if(!isStringValid(email)) {
         document.getElementById('emailError').style.display = "inline";
         result = false;
    }else if(!emailValidator(email)) {
         document.getElementById('iemailError').style.display = "inline";
         result = false;
    }
    if(!isStringValid(mobile)) {
         document.getElementById('mobileError').style.display = "inline";
         result = false;
    }
    else {
         if(isNaN(mobile)) {
             document.getElementById('mobileError').style.display = "inline";
             result = false;
         }else if(mobile.length != 10) {
             document.getElementById('mobileError').style.display = "inline";
             result = false;
         }
    }
    
    if(gender=="-1") {
             document.getElementById('genderError').style.display = "inline";
             result = false;
    }
  
   if(!isStringInRange(captchaAnswer,6,6)) {
          document.getElementById('captchaError').style.display = "inline";
        result = false;
  }
    return result;
}
            
function resetRegistrarationForm() {
    //clearing all the previous errors
    document.getElementById('firstNameError').style.display = "none";
    document.getElementById('lastNameError').style.display = "none";
    //document.getElementById('studentIdError').style.display = "none";
    document.getElementById('emailError').style.display = "none";
    document.getElementById('iemailError').style.display = "none";
    document.getElementById('mobileError').style.display = "none";
    document.getElementById('genderError').style.display = "none";
    document.getElementById('captchaError').style.display = "none";
    //document.getElementById('notAvailable').style.dispaly = "none";
    //document.getElementById('available').style.display = "none";

}

function validateDelStdForm()
{
     var result = true;
    var firstName = document.delstdform.firstName.value;
    var lastName = document.delstdform.lastName.value;
    var sroll = document.delstdform.srollno.value;
    firstName=trim(firstName);
    lastName=trim(lastName);
    
    if(!isStringInRange(firstName,2,20) || !validateName(firstName)) {
        document.getElementById('firstNameError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(lastName,2,20) || !validateName(lastName)) {
        document.getElementById('lastNameError').style.display = "inline";
        result = false;
    }
    if(sroll=="-1") {
        document.getElementById('srollnoError').style.display = "inline";
        result = false;
    }
    return result;
}
function validateUpdateStdForm()
{
     var result = true;
    //var firstName1 = document.updatestdform.firstName1.value;
     var firstName1 = document.updatestdform.fnamesud.value;
     
    // var lastName1 = document.updatestdform.lastName1.value;
     var firstName = document.stdupdform.firstName2.value;
    var lastName = document.stdupdform.lastName2.value;
    var sturoll = document.stdupdform.studentroll2.value;
    var dob = document.stdupdform.dob2.value;
    var email = document.stdupdform.email2.value;
    var mobile = document.stdupdform.mobile2.value;
    var gender = document.stdupdform.gender2.value;
   // var captchaAnswer = document.updatestdform.captchaText.value;
    //trim all the form values
     //trim all the form values
    firstName1 = trim(firstName1);
    firstName = trim(firstName);
    lastName = trim(lastName);
    //lastName1 = trim(lastName1);
    //sturoll = trim(stroll);
    //studentId = trim(studentId);
    email = trim(email);
    mobile = trim(mobile);
    //captchaAnswer = trim(captchaAnswer);
    //clearing all the previous errors
    //resetRegistrarationForm();
    //validating values
    //document.getElementById('firstNameError').style.display = "inline";
    //firstName1=trim(firstName1);
    
    //if(!isStringInRange(firstName1,3,30) || !validateName(firstName1)) {
      //  document.getElementById('firstNameError1').style.display = "inline";
        //result = false;
    //}
    if(firstName1==-1) {
        document.getElementById('nameError').style.display = "inline";
        result = false;
    }
    
    if(!isStringInRange(firstName,2,20) || !validateName(firstName)) {
        document.getElementById('firstNameError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(lastName,2,20) || !validateName(lastName)) {
        document.getElementById('lastNameError').style.display = "inline";
        result = false;
    }
    if(!isvarchar(sturoll) || sturoll.length>15 ) {
        document.getElementById('studentrollError').style.display = "inline";
        result = false;
    }
    
    
    if(!isStringValid(email)) {
         document.getElementById('emailError').style.display = "inline";
         result = false;
    }else if(!emailValidator(email)) {
         document.getElementById('iemailError').style.display = "inline";
         result = false;
    }
    if(!isStringValid(mobile)) {
         document.getElementById('mobileError').style.display = "inline";
         result = false;
    }
    else {
         if(!validateInteger(mobile)) {//isNaN(mobile)
             document.getElementById('mobileError').style.display = "inline";
             result = false;
         }else if(mobile.length != 10) {
             document.getElementById('mobileError').style.display = "inline";
             result = false;
         }
    }
    if(dob==""){
        document.getElementById('dobError').style.display = "inline";
    }
    if(gender=="-1") {
             document.getElementById('genderError').style.display = "inline";
             result = false;
    }
       //if(!isStringInRange(captchaAnswer,7,7)) {
     //       document.getElementById('captchaError').style.display = "inline";
       //      result = false;
    //}
    return result;
     
}
function validateEnrollForm(){
    var result=true;
    var sn = document.enrollfrm.sname.value;
    var gn = document.enrollfrm.grpname.value;
    var en = document.enrollfrm.enroll.value;
    
    if(sn=="-1") {
             document.getElementById('snameError2').style.display = "inline";
             result = false;
    }
    
    if(gn=="-1") {
             document.getElementById('groupnameError2').style.display = "inline";
             result = false;
    }
     if(!isStringInRange(en,1,10) || !validateName(en)) {
        document.getElementById('enrollError2').style.display = "inline";
        result = false;
    }
    
return result;
}

function validateTeachDelForm(){
    var result=true;
    var firstName = document.teachdelform.fname1tv.value;
   // var lastName = document.teachdelform.lname1.value;
    //var desg = document.teachdelform.desId.value;
    //var qual = document.teachdelform.qualId.value;
    
    //firstName = trim(firstName);
    //lastName = trim(lastName);
   if(firstName==-1)
    {
        document.getElementById('fname1Error').style.display = "inline";
        result = false;
    }
   
    return result;
}

function validateTeacherUpdateForm() {
    var result = true;
    //getting all the form values
   
    var fname = document.teachupdfrm.fname1tu.value;
    var fname1 = document.teachupdfrm2.tufname1.value;
    var lname1 = document.teachupdfrm2.tulname1.value;
    //var sex1 = document.teachupdfrm2.tugender.value;
    var desg1 = document.teachupdfrm2.tudes.value;
    var qual1 = document.teachupdfrm2.tuquali.value;
    var qual2 = document.teachupdfrm2.tuquali.value;
    // var firstname1 = document.teachupdfrm2.tufirstName.value;
     // var lastname1 = document.teachupdfrm2.tulastName.value;
      
    //tid1= trim(tid1);
    fname1 = trim(fname1);
    
    lname1 = trim(lname1);
    
    desg1 = trim(desg1);
    qual1= trim(qual1);
    
    qual2= trim(qual2);
    qual2= removeCharacters(qual2,'\\.');
    qual2= removeCharacters(qual2,'\\(');
    qual2= removeCharacters(qual2,'\\)');
    
    //clearing all the previous errors
    //resetTeachRegForm();
    //validating values
    //document.getElementById('firstNameError').style.display = "inline";
   
    if(fname=="-1"){
        document.getElementById('firstnameError').style.display = "inline";
        result = false;
    }
    
    if(!isStringInRange(fname1,2,20) || !validateName(fname1)) {
        document.getElementById('fname1Error').style.display = "inline";
        result = false;
    } 
    
    
    
    if(!isStringInRange(lname1,2,20) || !validateName(lname1)) {
        document.getElementById('lnameError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(desg1,2,45) || !validateName(desg1)) {
        document.getElementById('desError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(qual1,2,45) || !validateName(qual2)) {
        document.getElementById('qualiError').style.display = "inline";
        result = false;
    }
    
  
   
    return result;
}

function validateAssgForm(){
    var result=true;
    var tn = document.assgnfrm.tname.value;
    var gna = document.assgnfrm.gname.value;
    var topn = document.assgnfrm.topicname.value;
    var asn = document.assgnfrm.assgname.value;
    asn = trim(asn);
    if(tn=="-1") {
             document.getElementById('tnameError2').style.display = "inline";
             result = false;
    }
    if(gna=="-1") {
             document.getElementById('gnameError2').style.display = "inline";
             result = false;
    }
    if(topn=="-1") {
             document.getElementById('topicnameError2').style.display = "inline";
             result = false;
    }
    if(!isStringInRange(asn,1,45) || !validateName(asn)) {
        document.getElementById('assgnameError2').style.display = "inline";
        result = false;}
    return result;
}

function validateGrpAddForm(){
    var result=true;
    var pgn=document.grpform.pGroupname.value;
    var dn=document.grpform.dnameId.value;
    var nm=document.grpform.full_description.value;
    dn=trim(dn);
    nm=trim(nm);
    if(pgn=="-1"){
      document.getElementById('pGroupnameError').style.display = "inline";
      result = false;  
    }
    if(!isStringInRange(dn,1,20) || dn=="" || !isvarchar(dn)) {
        document.getElementById('dnameError').style.display = "inline";
        result = false;
    }
    if(!isStringInRange(nm,1,100) || nm=="" ) {
        document.getElementById('descriptionError').style.display = "inline";
        result = false;
    }
    return result;
}
function resetLoginForm()
{
 document.loginform.nameError.value="";
 document.loginform.passError.value="";
}
function  validateLoginForm(){
var result=true;
var name=document.loginform.name.value;
var pass=document.loginform.password.value;
name=trim(name);
pass=trim(pass);
resetLoginForm();
//document.loginform.name.value="hello";
 if(!isStringInRange(name,3,30) || !validateName(name)) {
        document.loginform.nameError.value="  Error";
        result = false;
    }
     if(!isStringInRange(pass,8,80)) {
       document.loginform.passError.value="   Error";
       // document.getElementById('passError').style.display = "inline";
        result = false;
    }
    return result;
}


var lastId;
function showRequiredInfo(id) {
    lastId = id;
    document.getElementById(id).style.display = "inline";
}
function hideRequiredInfo() {
    document.getElementById(lastId).style.display = "none";
}

function validateGrpEdit()
{
    var result = true;
    var gn1 = document.grpeditform.Groupname.value;
    var pn1 = document.grpeditform.pGroupname.value;
    var dn1 = document.grpeditform.dnameId.value;
    var dsp = document.grpeditform.full_description.value;
    gn1 = trim(gn1);
    pn1 = trim(pn1);
    dn1 = trim(dn1);
    dsp = trim(dsp);
    
    if(!isStringInRange(dn1,3,15) || !isvarchar(dn1)) {
       document.getElementById('dnameError').style.display = "inline";
             result = false;
    }
     if(!isStringInRange(dsp,1,100) ) {
       document.getElementById('descriptionError').style.display = "inline";
             result = false;
    }
    if(gn1=="-1") {
             document.getElementById('GroupnameError').style.display = "inline";
             result = false;
    }
    if(pn1=="-1") {
             document.getElementById('pGroupnameError').style.display = "inline";
             result = false;}
         
    return result;
    
        
}

function validateGrpDelForm()
{
    var result = true;
    var gan1 = document.grpdelform.gname1.value;
    //var pan2 = document.grpdelform.pg1name.value;
    
     
       
    
    if(gan1=="-1") {
             document.getElementById('gname1Error').style.display = "inline";
             result = false;
    }
    
         
    return result;
    
        
}



function validateTestegForm(){
    var result=true;
    var tn = document.testegfrm.tname.value;
    var gn = document.testegfrm.groupname.value;
    var st = document.testegfrm.status.value;
    
    if(tn=="-1") {
             document.getElementById('tnameError2').style.display = "inline";
             result = false;
    }
    
    if(gn=="-1") {
             document.getElementById('groupnameError2').style.display = "inline";
             result = false;
    }
    if(!validateInteger(st) ) {
       document.getElementById('statusError2').style.display = "inline";
             result = false;
    }
    
return result;
}


function validateTestdescForm(){
    //var tstn = document.test_descfrm.tstname.value;
    var tnm = document.test_descfrm.topname.value;
    var mk = document.test_descfrm.marks.value;
    var dur = document.test_descfrm.dur.value;
    var dn = document.test_descfrm.dname.value;
    var tds = document.test_descfrm.tdesc.value;
    var result = true;
    
   // tstn = trim(tstn);
    tnm = trim(tnm);
    dn = trim(dn);
    tds = trim(tds);
    
   /* if(!isStringInRange(tstn,3,25) ) {
       document.getElementById('tstnameError').style.display = "inline";
             result = false;
    }*/
    if(tnm=="-1" ) {
       document.getElementById('topnameError').style.display = "inline";
             result = false;
    }
    if(!isStringInRange(dn,3,20)|| !isvarchar(dn) ) {
       document.getElementById('dnameError').style.display = "inline";
             result = false;
    }
    if(!isStringInRange(tds,3,100)) {
       document.getElementById('tdescError').style.display = "inline";
             result = false;
    }
    if(!validateInteger(dur) || dur=="" || dur.length<1 || dur.length>10) {
       document.getElementById('durError').style.display = "inline";
             result = false;
    }
    
    if(!validateNumeric(mk) || mk=="" || mk.length>50) {
       document.getElementById('marksError').style.display = "inline";
             result = false;
    }
    
    return result;
}




function validateviewstuForm() {
    var result = true;
    var s1 = document.viewstufrm.sel.value;
    var s2 = document.viewstufrm.sel2.value;
    
    if(s1=="" && s2==""){
     document.getElementById('selError').style.display = "inline";
     result=false;
     
    }
return result;
}


function validateTestdetailsForm(){
    var tn = document.test_detailsform.testname1.value;
    var seq = document.test_detailsform.seq.value;
    var qty = document.test_detailsform.qtype.value;
    var qop = document.test_detailsform.qopt.value;
    var ans = document.test_detailsform.ans.value;
    var mr = document.test_detailsform.marks.value;
    var nmr = document.test_detailsform.nmarks.value;
    var result= true;
   // ans=trim(ans);
    ans=removeCharacters( ans,'\\s*');
    ans=removeCharacters( ans,',');
    
    if (tn=="-1"){
     document.getElementById('testnameError').style.display = "inline";
     result=false;
    }

if (!validateInteger(qty) || qty=="" || qty.length>10){
    document.getElementById('qtypeError').style.display = "inline";
     result=false;
}
if (!validateInteger(qop) || qop=="" || qop.length>10){
    document.getElementById('qoptError').style.display = "inline";
     result=false;
}
if (!validateNumeric(mr) || mr=="" || mr.length>50){
    document.getElementById('marksError').style.display = "inline";
     result=false;
}
if (!validateNumeric(nmr) || nmr=="" || nmr.length>50){
    document.getElementById('nmarksError').style.display = "inline";
     result=false;
}
if (!validateInteger(seq) || seq=="" || seq.length>10){
    document.getElementById('seqError').style.display = "inline";
     result=false;
}
if(!isStringInRange(ans,1,15)  ) {
       document.getElementById('ansError').style.display = "inline";
             result = false;
    }
    
return result;

}


function validateInstiregForm(){
var result=true;
var ina=document.instiregform.Name.value;
var mt=document.instiregform.motto.value;
var il=document.instiregform.ilogo.value;
var loe=document.instiregform.loe.value;
var em=document.instiregform.email.value;
var dsc=document.instiregform.instidescription.value;

ina=trim(ina);
mt=trim(mt);

if(!isStringInRange(ina,3,50) ) {
    document.getElementById('NameError').style.display = "inline";
             result = false;
    }
if(!isStringInRange(mt,3,30) ) {
       document.getElementById('mottoError').style.display = "inline";
             result = false;
    }
if(il=="" ) {
       document.getElementById('ilogoError').style.display = "inline";
             result = false;
    }
if(loe=="-1" ) {
       document.getElementById('loeError').style.display = "inline";
             result = false;
    }
if(dsc.length==0 || dsc.length>100){
    document.getElementById('idescError').style.display = "inline";
             result = false;
}
if(!emailValidator(em))  {
       document.getElementById('iemailError').style.display = "inline";
             result = false;
    }
    return result;
}

function clickbut()
{
    window.location.href="Admin_Home.jsp";
}
function nextfunc()
    {
        

   window.location.href="student_delete.jsp";
      }
      
      
      
function validateTopicForm(){

var result=true;
var pm=document.topicform.prtname.value;
var dm=document.topicform.tpname.value;
var fd=document.topicform.fl_desc.value;
          
          if(pm=="-1"){
              document.getElementById('prtnameError').style.display = "inline";
             result = false;
              
          }
          
          if(!isStringInRange(dm,2,20) || !isvarchar(dm)){
              document.getElementById('tpnameError').style.display = "inline";
             result = false;
              
          }
          if(!isStringInRange(fd,1,45)){
              document.getElementById('fl_descError').style.display = "inline";
             result = false;
              
          }
      return result;
  }
  
  
  function validateViewgrpForm() {
    var result = true;
    var s1 = document.view_grpfrm.sel.value;
    
    
    if(s1==""){
     document.getElementById('selError').style.display = "inline";
     result=false;
     
    }
return result;
}


function validateviewteachForm() {
    var result = true;
    var s1 = document.viewteachfrm.sel4.value;
    
    
    if(s1==""){
     document.getElementById('selError2').style.display = "inline";
     result=false;
     
    }
return result;
}
function validateDelStdForm()
{
     var result=true;
    var firstName = document.delstdform.fnamesu.value;
   // var lastName = document.teachdelform.lname1.value;
    //var desg = document.teachdelform.desId.value;
    //var qual = document.teachdelform.qualId.value;
    
    //firstName = trim(firstName);
    //lastName = trim(lastName);
   if(firstName==-1)
    {
        document.getElementById('nameError').style.display = "inline";
        result = false;
    }
   
    return result;
}

function validateLocationForm() {
var result = true;
var s1 = document.locationfrm.locname1.value;
var s2 = document.locationfrm.locst.value;


if(!isStringInRange(s1,2,30) || !validateName(s1)){
document.getElementById('locname1Error').style.display = "inline";
result=false;
}

if( !validateInteger(s2)|| s2.length >10){
document.getElementById('locstError').style.display = "inline";
result=false;
}
return result;
}

function resetassgnfrm()
{
    
    document.getElementById('tnameError2').style.display = "none";
    document.getElementById('gnameError2').style.display = "none";
    document.getElementById('topicnameError2').style.display = "none";
    document.getElementById('assgnameError2').style.display = "none";
    //document.assgnfrm.reload(true);
}
function resetenroll()
{
     document.getElementById('snameError2').style.display = "none";
    document.getElementById('groupnameError2').style.display ="none";
     document.getElementById('enrollError2').style.display = "none";
       
}
function resetGrpAddForm()
{
    document.getElementById('pGroupnameError').style.display = "inline";
    document.getElementById('dnameError').style.display = "inline";
    document.getElementById('descriptionError').style.display = "inline";
    
}
function resetGrpDelForm()
{
     document.getElementById('gname1Error').style.display = "none";
}
function resetGrpView()
{
    document.getElementById('selError').style.display = "none";
}
function resetLoc()
{
document.getElementById('locname1Error').style.display = "none";
document.getElementById('locstError').style.display = "none";
}
function resetStdDelForm()
{
    document.getElementById('firstNameError').style.display = "none";
    document.getElementById('lastNameError').style.display = "none";
    document.getElementById('srollnoError').style.display = "none";
}
function resetStdUpForm()
{
    document.getElementById('nameError').style.display = "none";
    document.getElementById('firstNameError').style.display = "none";
    document.getElementById('lastNameError').style.display = "none";
    document.getElementById('studentrollError').style.display = "none";
    document.getElementById('emailError').style.display = "none";
    document.getElementById('iemailError').style.display = "none";
    document.getElementById('mobileError').style.display = "none";
    document.getElementById('mobileError').style.display = "none";
     document.getElementById('mobileError').style.display = "none";
     document.getElementById('dobError').style.display = "none";
    document.getElementById('genderError').style.display = "none";
    
}
function resetStdView()
{
    document.getElementById('selError').style.display = "none";
}
function resetTeachDel()
{
document.getElementById('fname1Error').style.display = "none";    
}
function resetTeachReg()
{
 document.getElementById('fname1Error').style.display = "none";
 document.getElementById('lname1Error').style.display = "none";
 document.getElementById('desError').style.display = "none";
 document.getElementById('qualError').style.display = "none";   
}
function resetTeachupdForm()
{
 document.getElementById('firstnameError').style.display = "none";
 document.getElementById('fname1Error').style.display = "none";
 document.getElementById('lnameError').style.display = "none";
 document.getElementById('desError').style.display = "none";
 document.getElementById('qualiError').style.display = "none";
  
}
function resetTeachView()
{
  document.getElementById('selError2').style.display = "none";   
}
function resetTestDescForm()
{
document.getElementById('topnameError').style.display = "none";
document.getElementById('dnameError').style.display = "none";
document.getElementById('tdescError').style.display = "none";
document.getElementById('durError').style.display = "none";
document.getElementById('marksError').style.display = "none";
 
}
function resetTestDetForm()
{
 document.getElementById('testnameError').style.display = "none";
document.getElementById('qtypeError').style.display = "none";
document.getElementById('qoptError').style.display = "none";
document.getElementById('marksError').style.display = "none";
document.getElementById('nmarksError').style.display = "none";
document.getElementById('seqError').style.display = "none";
document.getElementById('ansError').style.display = "none";   
}
function resetTestEli()
{
document.getElementById('tnameError2').style.display = "none";
document.getElementById('groupnameError2').style.display = "none";
document.getElementById('statusError2').style.display = "none";
  
}
function resetTestSch()
{
 document.getElementById('testnameError2').style.display = "none";
 document.getElementById('gIdError2').style.display = "none";
 document.getElementById('locError').style.display = "none";
 document.getElementById('sdtError').style.display = "none";
 document.getElementById('edtError').style.display = "none";   
}
function resetTopicForm()
{
document.getElementById('prtnameError').style.display = "none";
document.getElementById('tpnameError').style.display = "none";
document.getElementById('fl_descError').style.display = "none";
   }
   
function  validateResultForm()
   {
       
   }