import java.awt.Point;

/**
* @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
*/
public class Node {

	public Node[] e;
	public boolean deadlock;

	public Point p;

	public Node(Point p)
	{
		e = new Node[4];
		this.p = p;
	}
	
	@Override
	public String toString() {
		return p.toString();
	}
}
