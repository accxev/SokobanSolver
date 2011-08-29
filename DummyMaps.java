/**
 * Class holding some sample Sokoban maps for testing the solver<br>
 * <br>
 * Coordinate System is y up, x right
 */
public class DummyMaps {

	/**
	 * The map on the original project instruction sheet
	 */
	public static SokoMapPoint[][] map_1() {
		SokoMapPoint[][] mp = new SokoMapPoint[6][6];
		// erste Zeile
		mp[2][4] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[3][4] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[4][4] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		// zweite Zeile
		mp[0][3] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[1][3] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.GREEN);
		mp[2][3] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.RED);
		mp[3][3] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.GREEN);
		mp[4][3] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);
		// dritte Zeile
		mp[0][2] = new SokoMapPoint(new boolean[] { true, true, false, false }, SokoMapPoint.RED);
		mp[1][2] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[2][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[3][2] = new SokoMapPoint(new boolean[] { false, true, false, true }, SokoMapPoint.RED);
		mp[4][2] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);
		// vierte Zeile
		mp[2][1] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.RED);
		// fuenfte Zeile
		mp[2][0] = new SokoMapPoint(new boolean[] { true, false, false, false }, SokoMapPoint.VIOLET);

		return mp;
	}

	public static SokoMapPoint[][] map_1rev() {
		SokoMapPoint[][] example = new SokoMapPoint[6][6];
		// erste Zeile
		example[2][0] = new SokoMapPoint(new boolean[] { true, true, false, false }, SokoMapPoint.RED);
		example[3][0] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		example[4][0] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);
		// zweite Zeile
		example[0][1] = new SokoMapPoint(new boolean[] { true, true, false, false }, SokoMapPoint.RED);
		example[1][1] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.GREEN);
		example[2][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.RED);
		example[3][1] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.GREEN);
		example[4][1] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);
		// dritte Zeile
		example[0][2] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		example[1][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		example[2][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		example[3][2] = new SokoMapPoint(new boolean[] { false, true, false, true }, SokoMapPoint.RED);
		example[4][2] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		// vierte Zeile
		example[2][3] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.RED);
		// fuenfte Zeile
		example[2][4] = new SokoMapPoint(new boolean[] { false, false, true, false }, SokoMapPoint.VIOLET);

		return example;
	}

	/**
	 * First printed map
	 */
	public static SokoMapPoint[][] map_2() {
		SokoMapPoint[][] mp = new SokoMapPoint[6][6];
		// erste Zeile
		mp[0][4] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[1][4] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		// zweite Zeile
		mp[0][3] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][3] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[2][3] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		// dritte Zeile
		mp[0][2] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[2][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[3][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[4][2] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		// vierte Zeile
		mp[0][1] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.GREEN);
		mp[1][1] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.GREEN);
		mp[2][1] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[3][1] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[4][1] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);
		// fuenfte Zeile
		mp[0][0] = new SokoMapPoint(new boolean[] { true, false, false, false }, SokoMapPoint.VIOLET);

		return mp;
	}

	/**
	 * Tiny map
	 */
	public static SokoMapPoint[][] map_tiny() {
		SokoMapPoint[][] mp = new SokoMapPoint[8][8];
		// erste Zeile
		mp[0][0] = new SokoMapPoint(new boolean[] { true, false, false, false }, SokoMapPoint.VIOLET);
		// zweite Zeile
		mp[0][1] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][1] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);
		// dritte Zeile
		mp[0][2] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.GREEN);
		mp[1][2] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.RED);
		// vierte Zeile
		mp[0][3] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][3] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);
		// fuenfte Zeile
		mp[0][4] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.GREEN);
		mp[1][4] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.RED);
		// 6. Zeile
		mp[0][5] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][5] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);
		// 7. Zeile
		mp[0][6] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.GREEN);
		mp[1][6] = new SokoMapPoint(new boolean[] { true, false, true, false }, SokoMapPoint.RED);
		// 8. Zeile
		mp[0][7] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[1][7] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);

		return mp;
	}

	
	public static SokoMapPoint[][] map_symmetrical() {
		SokoMapPoint[][] mp = new SokoMapPoint[4][4];
		// erste Zeile
		mp[0][0] = new SokoMapPoint(new boolean[] { true, true, false, false }, SokoMapPoint.VIOLET);
		mp[1][0] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[2][0] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[3][0] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);

		mp[0][1] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.RED);
		mp[2][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[3][1] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);

		mp[0][2] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[2][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[3][2] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);

		mp[0][3] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[1][3] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[2][3] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[3][3] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);
		return mp;
	}
	

	/**
	 * Second printed map
	 */
	public static SokoMapPoint[][] map_3() {
		SokoMapPoint[][] mp = new SokoMapPoint[6][6];

		mp[1][3] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[2][3] = new SokoMapPoint(new boolean[] { false, true, false, true }, SokoMapPoint.RED);
		mp[3][3] = new SokoMapPoint(new boolean[] { false, true, false, true }, SokoMapPoint.RED);
		mp[4][3] = new SokoMapPoint(new boolean[] { false, true, false, true }, SokoMapPoint.RED);
		mp[5][3] = new SokoMapPoint(new boolean[] { false, false, true, true }, SokoMapPoint.RED);

		mp[0][2] = new SokoMapPoint(new boolean[] { false, true, true, false }, SokoMapPoint.RED);
		mp[1][2] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.RED);
		mp[2][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.GREEN);
		mp[3][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[4][2] = new SokoMapPoint(new boolean[] { false, true, true, true }, SokoMapPoint.RED);
		mp[5][2] = new SokoMapPoint(new boolean[] { true, false, true, true }, SokoMapPoint.RED);

		mp[0][1] = new SokoMapPoint(new boolean[] { true, true, true, false }, SokoMapPoint.RED);
		mp[1][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.RED);
		mp[2][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[3][1] = new SokoMapPoint(new boolean[] { true, true, true, true }, SokoMapPoint.GREEN);
		mp[4][1] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[5][1] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);

		mp[0][0] = new SokoMapPoint(new boolean[] { true, false, false, false }, SokoMapPoint.VIOLET);
		mp[1][0] = new SokoMapPoint(new boolean[] { true, true, false, false }, SokoMapPoint.RED);
		mp[2][0] = new SokoMapPoint(new boolean[] { true, true, false, true }, SokoMapPoint.RED);
		mp[3][0] = new SokoMapPoint(new boolean[] { true, false, false, true }, SokoMapPoint.RED);

		return mp;
	}
}
