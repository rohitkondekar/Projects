/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.test;

/**
 *
 * @author administrator
 */
import tease.bean.Topic;
import tease.dao.TopicDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import tease.helper.DBConnection;
import java.util.*;

public class TestTopicDAO {

    TopicDAO dao = new TopicDAO();
    Topic topic = new Topic();
    Scanner scan = new Scanner(System.in);
    public TestTopicDAO() {
    }
    public void TestInsertTopic() {
        System.out.println("enter the parameter value to insert");
        System.out.println("topic id:");
        int i = scan.nextInt();
        topic.setIdTopic(i);
        System.out.println("topicparent id:");
         int parent = scan.nextInt();
        topic.setIdParent(parent);
        System.out.println("display name:");
        String display = scan.next();
        topic.setDisplayName(display);
        System.out.println("full name:");
        String full = scan.next();
        topic.setFullName(full);
        System.out.println(dao.insertTopic(topic));
    }
    public void TestDeleteTopic() {
        System.out.println("enter the topicid to be deleted");
        int i = scan.nextInt();
        System.out.println(dao.deleteTopic(i));
    }
    public void TestUpdateTopic() {
        System.out.println("enter the parameter value to update");
        System.out.println("topic id:");
        int i = scan.nextInt();
        topic.setIdTopic(i);
        System.out.println("topicparent id:");
         int parent = scan.nextInt();
        topic.setIdParent(parent);
        System.out.println("display name:");
        String display = scan.next();
        topic.setDisplayName(display);
        System.out.println("full name:");
        String full = scan.next();
        topic.setFullName(full);
        System.out.println(dao.updateTopic(topic));
    }
    public void TestHasNext() {
        System.out.println(dao.hasNext());
    }
    public void TestGetTopic() {
        for(topic =dao.getTopic();topic!=null;) {
        System.out.println( topic.getIdTopic());
        System.out.println( topic.getIdParent());
        System.out.println( topic.getDisplayName());
        System.out.println( topic.getFullName());
         topic = dao.getTopic();
        }
    }
    public void TestGetTopicByIdTopic() {
        System.out.println("enter the topicid for detail of topic");
        int i = scan.nextInt();
        topic = dao.getTopicByIdTopic(i);
        System.out.println(topic.getIdParent());
        System.out.println(topic.getDisplayName());
        System.out.println(topic.getFullName());
    }
    public void TestGetTopicByParameter() {
        Topic[] topic = null;
        System.out.println("enter the value for parameters");
        System.out.println("topicid:");
        int id = scan.nextInt();
        System.out.println("parenttopicid:");
        int parent = scan.nextInt();
        System.out.println("\ndisplay name:");
        String display = scan.next();
        System.out.println("\nfull name:");
        String full = scan.next();
        topic = dao.getTopicByParameter(id,parent,display,full);
        if(topic != null) {
        int size = topic.length;
           for(int i=0; i<size;i++) {
           System.out.println( topic[i].getIdTopic());
           System.out.println( topic[i].getIdParent());
           System.out.println( topic[i].getDisplayName());
           System.out.println( topic[i].getFullName());
           }
        }else
            System.out.println("no record found");
    }

    public static void main(String[] args) {
        TestTopicDAO test = new TestTopicDAO();
        System.out.println("enter 1.insert 2.delete 3.update 4.hasnext 5.get topic 6.get topic by id topic 7.get topic by parameter\n");
        System.out.println("enter ur choice to test method");
        Scanner scan = new Scanner(System.in);
        int i= scan.nextInt();
        switch(i) {
            case 1:test.TestInsertTopic();
            break;
            case 2:test.TestDeleteTopic();
            break;
            case 3:test.TestUpdateTopic();
            break;
            case 4:test.TestHasNext();
            break;
            case 5:test.TestGetTopic();
                break;
            case 6:test.TestGetTopicByIdTopic();
                break;
            case 7:test.TestGetTopicByParameter();
                break;
            default: System.out.println("exit");
        }
    }
}