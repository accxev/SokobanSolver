import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
* @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
*/
public class AStar {
	/**
	 * Our own implementation of the A-Star algorithm, for use with our Node
	 * class and custom heuristic function.
	 */
	static public List<Node> findPath(Node start, Node goal, int hash) {
		// create all needed maps
		Map<Node, Object> closedMap = new HashMap<Node, Object>();
		PriorityQueue<NDTuple> openQueue = new PriorityQueue<NDTuple>();
		Map<Node, Node> predMap = new HashMap<Node, Node>();

		Map<Node, Double> gScore = new HashMap<Node, Double>();
		Map<Node, Double> hScore = new HashMap<Node, Double>();
		Map<Node, Double> fScore = new HashMap<Node, Double>();
		
		// add diamond positions to closedMap to ignore those positions 
		for (int i = 0; i < 3; i++) {
			closedMap.put(SokoSolver.getNode(hash, i), null);
		}
		// set scores for start Node
		gScore.put(start, 0.0);
		hScore.put(start, heuristic(start, goal));
		fScore.put(start, hScore.get(start));

		openQueue.add(new NDTuple(fScore.get(start), start));

		while (!openQueue.isEmpty()) {
			Node current = openQueue.remove().node;

			if (current.equals(goal)) {
				List<Node> path = reconstructPath(predMap, predMap.get(goal));
				path.add(goal);
				return path;
			}

			closedMap.put(current, null);

			for (Node neighbor : current.e) {
				if (neighbor == null || closedMap.containsKey(neighbor))
					continue;

				double g = 1000.0;
				if (gScore.get(neighbor) != null)
					g = gScore.get(neighbor);
				double tempGScore = g + 1;

				boolean neighborNotInQueue = false;
				boolean tempScoreBetter = false;

				if (!openQueue.contains(neighbor)) {
					neighborNotInQueue = true;
					tempScoreBetter = true;
				} else if (tempGScore < g) {
					tempScoreBetter = true;
				} else {
					tempScoreBetter = false;
				}

				if (tempScoreBetter) {
					predMap.put(neighbor, current);
					gScore.put(neighbor, tempGScore);
					hScore.put(neighbor, heuristic(neighbor, goal));
					fScore.put(neighbor,
							gScore.get(neighbor) + hScore.get(neighbor));
				}

				if (neighborNotInQueue) {
					openQueue.add(new NDTuple(fScore.get(neighbor), neighbor));
				}
			}

		}

		return null;
	}

	/**
	 * Heuristic function for A-Star, using the Tie-Breaker Manhattan Distance <br>
	 * As tie-breaker the delta y * 0.001 was chosen.
	 */
	static public double heuristic(Node n, Node m) {
		return Math.abs(n.p.x - m.p.x) + Math.abs(n.p.y - m.p.y) * 0.001;
	}

	/**
	 * Reconstructs path recursively
	 */
	static public List<Node> reconstructPath(Map<Node, Node> predMap,
			Node current) {
		if (predMap.containsKey(current)) {
			List<Node> path = reconstructPath(predMap, predMap.get(current));
			path.add(current);
			return path;
		} else {
			List<Node> path = new LinkedList<Node>();
			path.add(current);
			return path;
		}
	}
}
