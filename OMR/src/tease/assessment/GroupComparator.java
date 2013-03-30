/**
 * @author Abhinav Kumar
 * @version 1.7
 */
package tease.assessment;

import java.util.Comparator;

/**
 *Over-writes the compare method of the Comparator
 *which is the abstract class
 */
public class GroupComparator implements Comparator<Node>
{
	@Override
    /**
     *Compares two Nodes
     * on the basis of their idGroup
     *@param record1 First Student Record
     *@param record2 Second Student Record
     *@return   A no whose value is used by sort function
     */
    public int compare( Node record1, Node record2)
    {

        int f1 = record1.getIdGroup();
        int f2 = record2.getIdGroup();

        if(f1 > f2)
            return -1;			//1 for ascending order
        else if( f1 < f2)
            return 1;			//-1 for ascending order
        else
            return 0;
    }
}
