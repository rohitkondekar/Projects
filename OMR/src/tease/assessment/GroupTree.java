/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tease.assessment;
/**
 * @author Abhinav Kumar
 * @version 1.7
 */

import java.util.Collections;
import java.util.Stack;
import tease.bean.GroupTbl;

/**
 * Implements tree
 * @author Abhinav
 */
public class GroupTree
{
	private Node root;
	private int id[][];
	private int size;
    private int idRoot;
        
    /**
     * Constructor
     */
	public GroupTree()
	{
        idRoot = 1;
		root=new Node();
		size = 0;
		root.setIdGroup(idRoot);					//Assuming the group id of the root is 1
	}
        
    /**
     * Used to obtain the root of the tree
     * @return root the root of the tree.
     */
	public Node getRoot()
	{
		return root;
	}

	/**
     * Sets the idGroup and parentGroup  array. 
     * @param size size of the array
     */
    private void setIdGroupAndIdParentGroup(int size)
	{
		id = new int[2][size];      //0 is for idGroup and 1 is for idParentGroup 
	}

	/**
     * Used to get the parentGroup by idGroup
     * @param groupNo idGroup for which we want to get the parentGroup 
     * @return idParentGroup if that is found else returns -1
     */
    private int getIdparentGroupByIdGroup(int groupNo)	//size counts the no of valid entries
	{
        int i=0;
        for(;i<size;++i)
            if(id[0][i] == groupNo )
                return id[1][i];
        return -1;
    }

	/**
	 *Recursive Method that traverses all node belonging to the parent
	 *@param parentNode The node which is the parent of all nodes
	 */
	public void display(Node parentNode )
	{
		if( parentNode == null)
        {
            System.out.println("Id not found");
            return;
        }

		//System.out.print("\n"+ parentNode.getIdGroup()+"\t Childs=" + parentNode.getChildList().size() +"\t records" + parentNode.getCntNodeWithRecord());
		if(parentNode == root)
				System.out.print(" Root Node ");
		else
			System.out.print(" idParent=" + parentNode.getParent().getIdGroup() );

		for( int i=0; i< parentNode.getChildList().size() ; ++i)
		{
	   		Node node=parentNode.getChild(i);
	    	display(node);
	 	}
	}

	/**
	 *Stack method of traversal of tree
	 */
	public void dispNode()
	{

		Stack s= new Stack();
		s.push(this.root);
		root.setVisited(true);

		while(! s.isEmpty())
		{
			Node n = (Node)s.peek();

			Node child;
			child = getUnvisitedChild(n);

			if( getUnvisitedChild(n) != null)
			{
				System.out.print( child.getIdGroup() + "\t");
				child.setVisited(true);
				s.push(child);
			}
			else
				s.pop();
		}

		clearNodes(root);
		s =null;
	}

	/**
	 *Used to get the unvisited Child of a parent Node
	 *	which is used in Depth first Search
	 *@param parentNode The parent Node whose unvisited child is required
	 *@return Node Unvisited Node if that exists
	 *			null if no Node or no unvisited Node exists
	 */
	public Node getUnvisitedChild(Node parentNode)
	{
		int i=0;

		for(;i<parentNode.getChildList().size();++i)
			if(parentNode.getChild(i).getVisited() == false)
				return parentNode.getChild(i);
		return null;
	}

	/**
	 *Recursively Used to set all the nodes to unvisited so that it could be used later
	 *@param parentNode Node from and below which all the nodes will be set as unvisited
	 */
	public void clearNodes(Node parentNode)
	{
		parentNode.setVisited(false);
		for( int i=0; i< parentNode.getChildList().size() ; ++i)
		{
			Node node=parentNode.getChild(i);
			clearNodes(node);
		}
	}
    
    /**
     * Stack method of clearing all nodes.
     */
	public void clearAllNodes()
	{

		Stack s= new Stack();
		s.push(this.root);
		root.clearRecordList();
		root.incrementCntNodeWithRecord( (-1)* root.getCntNodeWithRecord() );
		root.setVisited(true);

		while(! s.isEmpty())
		{
			Node n = (Node)s.peek();

			Node child;
			child = getUnvisitedChild(n);

			if( getUnvisitedChild(n) != null)
			{
					child.clearRecordList();
					child.incrementCntNodeWithRecord( (-1)*child.getCntNodeWithRecord() );
					child.setVisited(true);
					s.push(child);
            }
            else
                s.pop();
        }

        clearNodes(root);
        s =null;
	}
    
	/**
	 *Searches for the given id from the root using DFS
	 *@param idGroup id which is to be searched
	 */
	public Node searchNode(int id)
	{
		if(id == idRoot)
			return root;

		Stack s= new Stack();
		s.push(this.root);
		root.setVisited(true);

		while(! s.isEmpty())
		{
			Node n = (Node)s.peek();

			Node child;
			child = getUnvisitedChild(n);

			if( getUnvisitedChild(n) != null)
			{
				if(child.getIdGroup() == id)
				{
					clearNodes(root);
					return child;
				}
				child.setVisited(true);
				s.push(child);
			}
			else
				s.pop();
		}

		clearNodes(root);
		s =null;
		return null;
	}

	/**
	 *Actually Makes the tree
	 *@param mygroup[] Array of GroupTbl objects
	 */
	public void makeTree(GroupTbl mygroup[])
	{
		if( mygroup == null)
		{
			System.out.println("GroupObj is null.Aborting !!!!");
			 return;
		}
        //System.out.println("size is "+  mygroup.length);
		Node myNode[] = new Node[mygroup.length] ;
		setIdGroupAndIdParentGroup(mygroup.length);

		for(int i=0; i< mygroup.length; ++i)
		{
           if( mygroup[i] != null)
            { 
            if(mygroup[i].getIdGroup()!= idRoot)
            {
                myNode[size] = new Node();
                       
                        myNode[size].setIdGroup(mygroup[i].getIdGroup());
                        id[0][size] = mygroup[i].getIdGroup();
                        id[1][size] = mygroup[i].getIdparentGroup();
                        size++;
                    }}
                else
                    System.out.println("GroupTbl["+ (i) +"] is null ");
            
		}
		GroupComparator compGroup = new GroupComparator();

		for( int i=0;i < size ;++i)
		{
			//System.out.println("hello" + myNode[i].getIdGroup() );

			if(searchNode(myNode[i].getIdGroup()) == null)		//if original node is not found check for its parent.
			{
				//System.out.println("Not found the original node");
				Node temp = myNode[i];						//initially,temp = myNode[i]. Later it goes towards the root
				int id = getIdparentGroupByIdGroup( temp.getIdGroup());	//Getting id of the parent
				do
				{
					id = getIdparentGroupByIdGroup(temp.getIdGroup());
					//System.out.println(" parent id is " + id);

					if( searchNode(id) == null)						//If parent is not found,create it.
					{
						//System.out.println("\n\nid"+id +" is not found.!! creating new node");
						Node newNode = new Node();
						newNode.setIdGroup(id);
						newNode.addChild(temp);
						temp.setParent(newNode);
						temp = newNode;								//Now temp points to the parent
					}
					else										//If parent is found,add the present node to it.
					{
						Node foundNode = searchNode(id);
						//System.out.println("found the node"+ foundNode.getIdGroup());
						foundNode.getChildList().add(temp);
						temp.setParent(foundNode);
						Collections.sort(foundNode.getChildList(),compGroup);

						break;
					}
				}while(id>=1);								//till it reaches root node
			}
			else										//the node already exists
				;//System.out.println("Already Exists");

		}// end of for

		System.out.println("DISPLAYING THE NODES OF TREE ");
		//display(root);
		//id = null;
		myNode =null;
	}

} //end of class
