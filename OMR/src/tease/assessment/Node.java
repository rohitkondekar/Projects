/**
 * @author Abhinav Kumar
 * @version 1.7
 */
package tease.assessment;

import java.util.ArrayList;

/**
 * Implements the node of the tree
 * @author Abhinav
 */
public class Node
{
    private int idGroup;
    private Node parent;
    private ArrayList<StudData> record;
    private ArrayList<Node> child;
    private boolean visited;
    private int cntNodeWithRecord;

    /**
     * Creates the ArrayList of records and child nodes.
     * visited is used while searching the tree
     * visited is set to false
     */
    public Node()
    {
        parent = null;
        record = new ArrayList<StudData>();
        child= new ArrayList<Node>();
        visited = false;
        cntNodeWithRecord =0;
    }

    /**
     * Used to set the idGroup of the Node
     * @param idGroup idGroup which will be the value of Node's idGroup
     */
    public void setIdGroup( int idGroup)
    {
        this.idGroup = idGroup;
    }

    /**
     * Used to get the idGroup of the Node
     * @return void
     */
    public int getIdGroup()
    {
        return this.idGroup;
    }

    /**
     *Used to set the parent Node of this node
     *@param parent The parent Node of this node
     */
    public void setParent(Node parent)
    {
        if( parent != null)
            this.parent = parent;
    }

    /**
     *Returns the parent of the node
     *@return parent The parent Node of this node
     */
    public Node getParent()
    {
        return this.parent;
    }

    /**
     * Returns the arrayList of records stored in the node
     * @return record ArrayList of records stored in node of the tree
     */
    public ArrayList<StudData> getRecordList()
    {
        return this.record;
    }

    /**
     * Clears all the recordList
     */
    public void clearRecordList()
    {
        this.record.clear();
    }

    /**
     * Adds a record to the Node's record
     * @param record new record to be entered
     */
    public void addRecord(StudData record)
    {
        if( record != null )
            this.record.add(record);
        if( this.record.size() == 1)
            incrementCntNodeWithRecord(1);

    }


    /**
     *Used to get the record of the node stored at appropriate position
     *@param pos the desired position
     *@return record.get(pos) if position is correct
     *                          else return null
     */
    public StudData getRecord(int pos)
    {
        if(pos < this.record.size())
            return this.record.get(pos);
        else
            return null;
    }

    /**
     * Returns the ArrayList of Child
     * @return child ArrayList of Node types
     */
    public ArrayList<Node> getChildList()
    {
        return this.child;
    }

    /**
     * Adds a child to the present node
     * @param newNode the New Node which has to become the child of present node
     */
    public void addChild(Node newNode)
    {
        if( newNode != null)
            this.child.add(newNode);
    }

    /**
     * Used to get the child node at desired position
     * @param pos position required
     * @return child.get(pos) child node at that position. 
     *          if position is not proper,it returns null
     */
    public Node getChild(int pos)
    {
        if(pos < this.child.size() )		//if that child exists
            return this.child.get(pos);
        else
            return null;
    }

    /**
     * Sets the visited value used in searching
     * @param value can be either true or false
     */
    public void setVisited(boolean value)
    {
        this.visited = value;
    }

    /**
     * Tells if that node is visited or not
     * @return visited if the node has been visited or not
     */
    public boolean getVisited()
    {
        return this.visited;
    }

    /**
     *Increments the no of child with records
     * @param n The increase in no of cntNodeWithRecord
     */
    public void incrementCntNodeWithRecord(int n)
    {
        cntNodeWithRecord+= n;
    }

    /**
     *Returns the no of child with records.
     *@return cntChildWithRecord the no of child of this node who have records
     */
    public int getCntNodeWithRecord()
    {
        return this.cntNodeWithRecord;
    }
}// end of class