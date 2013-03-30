
package Validate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Validation {
  public  Validation()
    {
        
    }
    
/* trim is perfect */
public  String trim(String s)
{
    int l=0;
    int r=s.length() -1;
    
    while(l < s.length() && s.charAt(l)==' ')
    {l++;}
    while(r > l && s.charAt(r) == ' ')
    {r-=1;}
    return s.substring(l, r+1);
}
/*isstringvalid is perfect */
public  boolean isStringValid(String str) {
    boolean result = true;
    str = trim(str);
    if(str.equals("")) {
        result=false;
    }
    return result;
}
/* isstringinrange is perfect */
public  boolean isStringInRange(String str,int minLength,int maxLength) {
    boolean result = false;
    str = trim(str);
    if(isStringValid(str)) {
        if(str.length() >= minLength && str.length() <= maxLength) {
            result = true;
        }
        
    }
    return result;
}
 /* checkname is perfect */   
public boolean checkName(String name){
     boolean isValid=false;
        String dispNameExp ="^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(dispNameExp);  
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches() && isStringValid(name) && isStringInRange(name,2,20))
        {
            isValid=true;
        }
        
        return isValid;  

        
    }
/*checkpassword is perfect*/

 public boolean checkPassword(String name){
        //String s=null;
     boolean isValid=false;
    
        if(isStringValid(name) && isStringInRange(name,8,30))
        {
            isValid=true;
        }
        
       return isValid;  

        
}
   
 /* Email is perfect */
 public  boolean isEmailValid(String email){  
 boolean isValid = false;  
   
  
 String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";  
 CharSequence inputStr = email;  
 Pattern pattern = Pattern.compile(expression);  
 Matcher matcher = pattern.matcher(inputStr);  
 if(matcher.matches()){  
 isValid = true;  
 }  
 return isValid;  
 }  
    
  /* isphoneno valid is perfect */
public boolean isPhoneNumberValid(String phoneNumber){  
boolean isValid = false;  

String expression = "^[7-9]{1}+[0-9]{9}+$";  
CharSequence inputStr = phoneNumber;  
Pattern pattern = Pattern.compile(expression);  
Matcher matcher = pattern.matcher(inputStr);  
if(matcher.matches()){  
isValid = true;  
}  
return isValid;  
}  

      
/*is float is perfect*/
public  boolean isFloat(String number){  
boolean isValid = false;  

String expression = "^[0-9]*\\.?[0-9]+$";  
CharSequence inputStr = number;  
Pattern pattern = Pattern.compile(expression);  
Matcher matcher = pattern.matcher(inputStr);  
if(matcher.matches()){  
isValid = true;  
}  
return isValid;  
}  
/*oisnumeric is perfect */
public boolean isNumeric(String number){  
boolean isValid = false;  

String expression = "^[0-9]+$";  
CharSequence inputStr = number;  
Pattern pattern = Pattern.compile(expression);  
Matcher matcher = pattern.matcher(inputStr);  
if(matcher.matches()){  
isValid = true;  
}  
return isValid;  
}  
//isvarchar is perfect

public boolean isvarchar(String str)
{
    boolean result= false;
    String varcharexp="^[0-9a-zA-Z]+$";
    Pattern pattern = Pattern.compile(varcharexp);  
Matcher matcher = pattern.matcher(str);  
if(matcher.matches() && isStringInRange(str,2,20)){  
result = true;  
}  
    return result;
    }



}



 
 
