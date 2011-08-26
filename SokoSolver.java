import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
* @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
*/
public class SokoSolver {

	static public Queue<Integer> queue = new LinkedList<Integer>();
	static public HashMap<Integer, List<Node>> doneMap = new HashMap<Integer, List<Node>>();
	static public HashMap<Point, Node> nodeMap = new HashMap<Point, Node>();
	static public Node exit;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// ----- DUMMY MAPPOINT ---------------------
		MapPoint[][] example = new MapPoint[6][6];
		// erste Zeile
		example[2][0] = new MapPoint(
				new boolean[] { false, true, true, false }, MapPoint.RED);
		example[3][0] = new MapPoint(new boolean[] { false, true, true, true },
				MapPoint.RED);
		example[4][0] = new MapPoint(
				new boolean[] { false, false, true, true }, MapPoint.RED);
		// zweite Zeile
		example[0][1] = new MapPoint(
				new boolean[] { false, true, true, false }, MapPoint.RED);
		example[1][1] = new MapPoint(new boolean[] { false, true, true, true },
				MapPoint.GREEN);
		example[2][1] = new MapPoint(new boolean[] { true, true, true, true },
				MapPoint.RED);
		example[3][1] = new MapPoint(new boolean[] { true, true, false, true },
				MapPoint.GREEN);
		example[4][1] = new MapPoint(new boolean[] { true, false, true, true },
				MapPoint.RED);
		// dritte Zeile
		example[0][2] = new MapPoint(
				new boolean[] { true, true, false, false }, MapPoint.RED);
		example[1][2] = new MapPoint(new boolean[] { true, true, false, true },
				MapPoint.RED);
		example[2][2] = new MapPoint(new boolean[] { true, true, true, true },
				MapPoint.GREEN);
		example[3][2] = new MapPoint(
				new boolean[] { false, true, false, true }, MapPoint.RED);
		example[4][2] = new MapPoint(
				new boolean[] { true, false, false, true }, MapPoint.RED);
		// vierte Zeile
		example[2][3] = new MapPoint(
				new boolean[] { true, false, true, false }, MapPoint.RED);
		// fuenfte Zeile
		example[2][4] = new MapPoint(
				new boolean[] { true, false, false, false }, MapPoint.VIOLET);
		// ----------------------------------------------

		int h = convertMap(example);
		exit = getNode(h, 3);
		System.out.println(h);

		queue.add(h);
		doneMap.put(h, null);

		System.out.println("exit is: " + exit);
		System.out.println("nodeMap.size: " + nodeMap.size());

		while (!queue.isEmpty()) {
			System.out.println("queue:" + queue.size() + " | doneMap: "
					+ doneMap.size());

			h = queue.poll();
			// if (doneMap.containsKey(h))
			// continue;
			// are we done?
			if (exit.equals(getNode(h, 0)) && exit.equals(getNode(h, 1))
					&& exit.equals(getNode(h, 2))) {
				// yes we are done!
				System.out.println("there is a way");
				System.out.println(h);
				System.out.println(doneMap.containsKey(h) + "  Path length: "+ doneMap.get(h).size() + " | Path: "
						+ doneMap.get(h));
				return;
			}
			// not yet, keep on trying...
			validMoves(h);
		}
		System.out.println("there was no way");
	}

	public static int hash(Point p[]) {
		Point sorted[] = sortPoints(p);

		String s = "";
		for (Point point : sorted) {
			s += point.x + "" + point.y;
		}

		return Integer.parseInt(s);
	}

	public static Point[] sortPoints(Point p[]) {
		Point sorted[];
		int val[] = new int[3];

		for (int i = 0; i < 3; i++)
			val[i] = p[i].x * 100 + p[i].y; // xxyy als int

		// simple sort for 3 values with if's instead of big algorithms, end
		// append pos of robot
		if (val[0] < val[1]) {
			if (val[0] < val[2]) {
				if (val[1] < val[2]) {
					sorted = new Point[] { p[0], p[1], p[2], p[3] };
				} else {
					sorted = new Point[] { p[0], p[2], p[1], p[3] };
				}
			} else {
				sorted = new Point[] { p[2], p[0], p[1], p[3] };
			}
		} else {
			if (val[1] < val[2]) {
				if (val[0] < val[2]) {
					sorted = new Point[] { p[1], p[0], p[2], p[3] };
				} else {
					sorted = new Point[] { p[1], p[2], p[0], p[3] };
				}
			} else {
				sorted = new Point[] { p[2], p[1], p[0], p[3] };
			}
		}

		return sorted;
	}

	public static void validMoves(int hash) {
		for (int d = 0; d < 3; d++) {
			Node n = getNode(hash, d);
			if (n == null)
				return;
			else if (n.equals(exit)) {
				continue;
			}
			loopDir: for (int i = 0; i < 4; i++) {
				if (n.e[i] != null && !n.e[i].deadlock
						&& !hasDiamond(hash, n.e[i])) {

					// calculates following position after moving one diamond to
					// possible position
					Point[] coords = new Point[4];
					boolean badPos = false;
					for (int d2 = 0; d2 < 4; d2++) {
						if (d2 == d) {
							coords[d2] = new Point(n.p.x + (2 - i) % 2, n.p.y
									+ (i - 1) % 2);
							if (n.e[i] == null) {
								badPos = true; // there was actually no way
							}
						} else {
							coords[d2] = getNode(hash, d2).p;
						}
					}
					// new position with moved diamond
					int newHash = hash(coords);

					if (badPos) {
						doneMap.put(newHash, doneMap.get(newHash));
						continue loopDir;
					}

					// check if the pusher can reach the position with A*
					Node pusherTarget = null;
					if (n.e[(i + 2) % 4] != null) {
						pusherTarget = nodeMap.get(n.e[(i + 2) % 4].p);
					} else {
						doneMap.put(newHash, doneMap.get(newHash));
						continue loopDir;
					}
					Node pusherCurrrent = nodeMap.get(getNode(hash, 3).p);

					List<Node> path = null;
					if (pusherTarget != null) {
						if (pusherTarget.equals(pusherCurrrent)) {
							path = new LinkedList<Node>();
							path.add(pusherCurrrent);
						} else
							path = AStar.findPath(pusherCurrrent, pusherTarget,
									hash);
					}

					if (path == null) {
						// there was no path -> add that state to the badMap
						// System.out.println("BAD:" + newHash);
						doneMap.put(newHash, doneMap.get(newHash));
					} else {
						// there was a path, its a possible route to the
						// solution. put it into the queue

						// prepare to calc new hash with moved pusher
						coords = new Point[4];
						for (int d2 = 0; d2 < 4; d2++) {
							if (d2 == 3) {
								coords[d2] = n.p;
							} else {
								coords[d2] = getNode(newHash, d2).p;
							}
						}
						// new position with moved pusher
						newHash = hash(coords);

						if (!doneMap.containsKey(newHash))
							queue.add(newHash);
						List<Node> fullPath = new LinkedList<Node>();
						List<Node> oldPath = doneMap.get(hash);
						if (oldPath != null && !oldPath.isEmpty()) {
							fullPath = new LinkedList<Node>(oldPath);
							fullPath.addAll(path);
						} else
							fullPath = path;

						if (newHash == 22243132/* 23243122 */)
							System.out.print("");

						doneMap.put(newHash, fullPath); // TODO umwandlung von
						// Node-List zu Byte-List
					}

					// wenn nicht, dann zu badMap. Sonst zu Queue und openMap.
				}
			}
		}
	}

	public static Node getNode(int hash, int diamond) {
		int x = (hash / ((int) Math.pow(10, (7 - diamond * 2)))) % 10;
		int y = (hash / ((int) Math.pow(10, (7 - diamond * 2 - 1)))) % 10;
		return nodeMap.get(new Point(x, y));
	}

	public static boolean hasDiamond(int hash, Node n) {
		if (!n.equals(exit))
			for (int i = 0; i < 3; i++) {
				Node d = getNode(hash, i);
				if (d != null && d.equals(n))
					return true;
			}
		return false;
	}

	public static int convertMap(MapPoint[][] mp) {
		// x,y to normalize coordinates, to let them start at 0,0
		int xNorm = 1000, yNorm = 1000;

		List<Node> nodes = new LinkedList<Node>();
		List<Point> diamonds = new LinkedList<Point>();
		Point start = null;

		// set norm values for x, y
		for (int x = 0; x < mp.length; x++) {
			for (int y = 0; y < mp[x].length; y++) {
				if (mp[x] != null && mp[x][y] != null) {
					if (x < xNorm)
						xNorm = x;
					if (y < yNorm)
						yNorm = y;
				}
			}
		}

		// go through the mappoints and create nodes
		for (int x = 0; x < mp.length; x++) {
			for (int y = 0; y < mp[x].length; y++) {
				if (mp[x] != null && mp[x][y] != null) {
					Point p = new Point(x - xNorm, y - yNorm);
					Node n = new Node(p);
					nodes.add(n);
					nodeMap.put(p, n);
					if (mp[x][y].color == MapPoint.GREEN) {
						diamonds.add(p);
					} else if (mp[x][y].color == MapPoint.VIOLET) {
						start = p;
					}
				}
			}
		}

		// prepare for hash
		diamonds.add(start);

		int hash = hash(diamonds.toArray(new Point[0]));

		// set edges to all nodes
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			for (int dir = 0; dir < n.e.length; dir++) {
				if (mp[n.p.x + xNorm][n.p.y + yNorm].vertex[dir]) {
					// there is an edge to the direct neighbor
					n.e[dir] = nodeMap.get(new Point(n.p.x + (2 - dir) % 2,
							n.p.y + (dir - 1) % 2));
				}
			}
		}

		return hash;
	}
}
