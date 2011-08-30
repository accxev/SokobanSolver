import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2nd, completely overhauled version
 * 
 * @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
 */
public class SokoSolver {

	public static void main(String[] args) {
		long timeStart = Calendar.getInstance().getTimeInMillis();

		List<Node> solution = (new SokoSolver()).solve(DummyMaps.map_tiny());

		long timeElapsed = Calendar.getInstance().getTimeInMillis() - timeStart;
		System.out.println("Time elapsed: " + timeElapsed + " ms");

		System.out.printf("Solution %sfound\n", (solution == null ? "not " : ""));
		if (solution != null) {
			System.out.println(solution.size() + " steps: " + solution);
		}
	}

	// -------------------------------------------------------------------------

	/**
	 * Priority Queue of open GameStates, sorted by the length of the path to
	 * that GameState. That way the solver looks for the shortest solution
	 */
	private PriorityQueue<GameState> queue;
	// private Queue<GameState> queue;

	/** Set with all the GameStates already visited */
	private HashSet<GameState> doneStates;

	/** HashMap that maps Points to Nodes for looking up Nodes by coordinates */
	private HashMap<Point, Node> nodeMap;
	
	private GameState startingState;

	/** Node that represents the start/exit point of the map */
	// private Node exit;

	public SokoSolver() {
		// queue = new LinkedList<GameState>();
		queue = new PriorityQueue<GameState>();
		doneStates = new HashSet<GameState>(20000);
		nodeMap = new HashMap<Point, Node>();
		// exit = null;
	}

	public List<Node> solve(SokoMapPoint[][] map) {
		// convert the given map (it adds the nodes to the nodeMap as well) and
		// save the starting state in the queue to initialize it
		startingState = SokoMapPoint.convertMap(map, nodeMap);
		// exit = nodeMap.get(startingState.pusher);
		queue.add(startingState);
		// doneStates.add(startingState);

		GameState state;
		while (!queue.isEmpty()) {
			// System.out.println("queue: " + queue.size() + " | doneStates: " +
			// doneStates.size()); // debug

			state = queue.poll();
			if (doneStates.contains(state))
				continue;
			doneStates.add(state);
			// System.out.println(state.toString() + " " + state.hashCode()); //
			// debug

			// check if we are done (all diamonds are at the exit)
			if (state.isSolution()) {
				System.out.println("States processed: " + doneStates.size());
				List<Node> fullPath = new LinkedList<Node>(state.path);
				fullPath.add(nodeMap.get(state.pusher));
				return fullPath;
			}

			// not done
			List<GameState> validMoves = searchValidMoves(state);
			// System.out.print(""); // debug
			for (int i = 0; i < validMoves.size(); i++) {
				if (!doneStates.contains(validMoves.get(i))) {
					queue.add(validMoves.get(i));
					// doneStates.add(validMoves.get(i));
				}
			}
			// System.out.print(""); // debug
		}

		return null;
	}

	private List<GameState> searchValidMoves(GameState state) {
		List<GameState> validMoves = new LinkedList<GameState>();
		for (int diamond = 0; diamond < state.diamonds.length; diamond++) {
			Node n = nodeMap.get(state.diamonds[diamond]);
			for (int direction = 0; direction < n.e.length; direction++) {
				GameState push = state.pushDiamond(state.diamonds[diamond], direction, nodeMap);
				if (push != null)
					validMoves.add(push);
			}
		}
		return validMoves;
	}

	public GameState getStartingState() {
		return startingState;
	}
}
