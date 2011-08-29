import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */
public class SokoMapPoint {

	static final byte RED = 0, GREEN = 1, VIOLET = 2;

	public boolean vertex[];

	public byte color;

	public SokoMapPoint(boolean[] vertex, byte color) {
		this.vertex = vertex;
		this.color = color;
	}

	/**
	 * Converts a SlimMapPoint-Matrix to a map made from Nodes. The second
	 * parameter (nodeMap) will be filled with the Nodes that this function
	 * finds in the SlimMapPoints[]. It will be cleared at the beginning.
	 */
	public static GameState convertMap(SokoMapPoint[][] mp,
			Map<Point, Node> nodeMap) {
		nodeMap.clear();

		List<Point> diamonds = new LinkedList<Point>();
		Point start = null;

		// go through the map points and create all corresponding nodes first
		for (int x = 0; x < mp.length; x++) {
			for (int y = 0; y < mp[x].length; y++) {
				if (mp[x] != null && mp[x][y] != null) {
					Point p = new Point(x, y);
					nodeMap.put(p, new Node(p));
					if (mp[x][y].color == GREEN) {
						diamonds.add(p);
					} else if (mp[x][y].color == VIOLET) {
						start = p;
					}
				}
			}
		}

		// set edges to all nodes
		Iterator<Node> nodes = nodeMap.values().iterator();
		while (nodes.hasNext()) {
			Node node = nodes.next();

			for (int dir = 0; dir < node.e.length; dir++) {
				if (mp[node.p.x][node.p.y].vertex[dir]) {
					// there is an edge to the direct neighbor
					node.e[dir] = nodeMap.get(node.p.pointInDir(dir));
				}
			}
		}

		if (diamonds.size() != 3 || start == null)
			throw new RuntimeException(); // invalid map

		return new GameState(diamonds.toArray(new Point[0]), start, start, null);
	}

}
