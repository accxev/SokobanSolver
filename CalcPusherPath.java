import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Calculates drive instructions for Pusher from solution of the SokoSolver.
 */
public class CalcPusherPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long timeStart = Calendar.getInstance().getTimeInMillis();

		SokoMapPoint[][] map = DummyMaps.map_tiny();

		SokoSolver solver = new SokoSolver();
		List<Node> solution = solver.solve(map);
		GameState state = solver.getStartingState();

		long timeElapsedSolver = Calendar.getInstance().getTimeInMillis() - timeStart;
		System.out.println("Time elapsed in solver: " + timeElapsedSolver + " ms");

		String s = (new CalcPusherPath(solution, state)).doConvert();
		
		System.out.println("Instructions: " + s);

		long timeElapsedTotal = Calendar.getInstance().getTimeInMillis() - timeStart;
		System.out.println("Time elapsed in total: " + timeElapsedTotal + " ms");

	}

	private int pusherDir;
	private boolean isPushing;

	private Point[] diamonds;
	private Point pusher;

	private Iterator<Point> path;

	public CalcPusherPath(List<Node> solList, GameState state) {
		pusherDir = 0;
		isPushing = false;
		diamonds = state.diamonds;
		pusher = state.pusher;

		List<Point> pointPath = new LinkedList<Point>();
		for (Node n : solList) {
			pointPath.add(n.p);
		}
		path = pointPath.iterator();
	}

	public String doConvert() {
		// init: take out first point because that is just the starting pusher
		// location
		StringBuilder s = new StringBuilder();
		path.next();

		while (path.hasNext()) {
			Point nextPoint = path.next();

			int nextPusherDir = pusher.directionTo(nextPoint);

			if (isPushing && nextPusherDir != pusherDir) {
				// go forward backward
				s.append('F');
				s.append('B');
				pusherDir = Point.oppositeDirOf(pusherDir);
				isPushing = false;
			}

			s.append(calcDriveInstruction(pusherDir, nextPusherDir));
			pusherDir = nextPusherDir;
			pusher = nextPoint;
			// check if next move will be pushing
			for (int i = 0; i < diamonds.length; i++) {
				if (diamonds[i].equals(pusher)) {
					isPushing = true;
					diamonds[i] = diamonds[i].pointInDir(pusherDir);
					break;
				}
			}
		}
		s.append("FBX");
		return s.toString();
	}

	private char calcDriveInstruction(int oldDir, int newDir) {
		if (oldDir == newDir)
			return 'F';
		return ((oldDir - newDir + 4) % 4 == 3 ? 'R' : 'L');
	}

}
