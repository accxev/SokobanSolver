import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */
public class GameState implements Comparable<GameState> {
	public final Point[] diamonds;
	public final Point pusher;
	public final Point exit;

	public final List<Node> path;

	public GameState(Point[] diamonds, Point pusher, Point exit, List<Node> path) {
		this.diamonds = diamonds;
		Arrays.sort(this.diamonds);
		this.pusher = pusher;
		this.exit = exit;
		this.path = path;
	}

	/**
	 * Actual push: moves the Diamond to new position, and the pusher to the
	 * position the diamond was on. If the move was not possible, the result is
	 * null.
	 */
	public GameState pushDiamond(Point from, int dir, Map<Point, Node> nodeMap) {
		// check if the fromNode is valid, has a diamond on it and a neighbor in
		// the direction given by dir for the diamond to move to
		Node fromNode = nodeMap.get(from);
		if (fromNode == null || fromNode.e[dir] == null || hasDiamondOn(fromNode.e[dir].p)
				|| !hasDiamondOn(from))
			return null;

		// check if the Node where the pusher has to go to exists
		Node pusherTarget = fromNode.e[oppositeDirOf(dir)];
		if (pusherTarget == null)
			return null;

		// all necessary Nodes exist, now use AStar to check if there is a path
		// for the pusher between its current Node to the pusherTarget

		// diamond nodes are blockedNodes for AStar to consider as walls
		Node[] blockedNodes = getBlockedNodes(diamonds, nodeMap);

		// only run AStar if the pusher isn't already at the necessary position
		List<Node> path = new LinkedList<Node>();
		Node pusherNode = nodeMap.get(pusher);
		path.add(pusherNode);
		if (!pusherTarget.equals(pusherNode)) {
			path = AStar.findPath(pusherNode, pusherTarget, blockedNodes);
			if (path == null)
				return null; // no path
		}

		// path now contains the route of the pusher to the pushing position
		// now perform the push by moving the diamond, and the pusher

		// calculate path
		List<Node> fullPath;
		if (this.path != null && !this.path.isEmpty()) {
			fullPath = new LinkedList<Node>(this.path);
			if (path != null)
				fullPath.addAll(path);
		} else {
			fullPath = path;
		}

		return new GameState(moveDiamond(from, dir), from, exit, fullPath);
	}

	private Point[] moveDiamond(Point from, int dir) {
		Point[] newDiamonds = diamonds.clone();
		for (int d = 0; d < diamonds.length; d++) {
			if (newDiamonds[d].equals(from)) {
				newDiamonds[d] = from.pointInDir(dir);
				break;
			}
		}
		Arrays.sort(newDiamonds);
		return newDiamonds;
	}

	private Node[] getBlockedNodes(Point[] diamonds, Map<Point, Node> nodeMap) {
		Node[] blockedNodes = new Node[diamonds.length];
		for (int d = 0; d < diamonds.length; d++) {
			if (nodeMap.get(diamonds[d]) == null)
				return null; // invalid

			blockedNodes[d] = nodeMap.get(diamonds[d]);
		}
		return blockedNodes;
	}

	private boolean hasDiamondOn(Point point) {
		if (point.equals(exit))
			return false;

		for (Point d : diamonds) {
			if (d.equals(point))
				return true;
		}
		return false;
	}

	public boolean isSolution() {
		for (Point diamond : diamonds) {
			if (!exit.equals(diamond)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "<";
		for (Point p : diamonds) {
			s += p.toString();
		}
		return s + "|" + pusher + "|" + (path != null ? path.size() : 0) + ">";
	}

	private int oppositeDirOf(int dir) {
		return (dir + 2) % 4;
	}

	@Override
	public int compareTo(GameState o) {
		if (o.path == null)
			return 1;
		if (path == null || path.size() < o.path.size())
			return -1;
		else if (path.size() > o.path.size())
			return 1;
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameState) {
			GameState state = (GameState) obj;
//			Arrays.sort(state.diamonds);
//			Arrays.sort(diamonds);
			for (int i = 0; i < diamonds.length; i++) {
				if (!diamonds[i].equals(state.diamonds[i]))
					return false;
			}

			return (pusher.equals(state.pusher)) && (exit.equals(state.exit));
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int x=0;
		for (int i = 0; i < diamonds.length; i++) {
			x ^= diamonds[i].hashCode();
		}
		return x ^ pusher.hashCode() ^ exit.hashCode();
	}
}
