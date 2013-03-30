/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;
import tease.bean.Teacher;
import tease.dao.*;

import java.util.*;
/**
 * 
 * @author administrator
 */
public class CHechTeachermain {
    public static void main(String [] args)
    {TeacherDAO dao = new TeacherDAO();
    //boolean n= dao.hasNext();
    //System.out.println ("boolean is" + n);
    System.out.println(dao.hasNext());
}}
