import java.util.Comparator;

/**
* @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
*
 * Datastructure to bundle a double with a Node object to create a sorted Queue
 * with Nodes, sorted by a given double value
 */
public class NDTuple implements Comparator<NDTuple>, Comparable<NDTuple> {

	public double fscore;
	public Node node;

	public NDTuple(double d, Node n) {
		this.fscore = d;
		this.node = n;
	}

	@Override
	public int compare(NDTuple o1, NDTuple o2) {
		if (o1.fscore < o2.fscore)
			return -1;
		else if (o1.fscore > o2.fscore)
			return 1;
		return 0;
	}

	@Override
	public int compareTo(NDTuple o) {
		if (fscore < o.fscore)
			return -1;
		else if (fscore > o.fscore)
			return 1;
		return 0;
	}

}
