//import lejos.nxt.ColorSensor.Color;


/**
* @author Hieu Dao Trung, Antonia Schmalstieg, Joschka Fischer, August 2011
*/
public class MapPoint {
	
	static final byte RED = 0, GREEN = 1, VIOLET = 2;

	public boolean vertex[];

	public byte color;

	public MapPoint(boolean[] vertex, byte color) {
		this.vertex = vertex;
		this.color = color;
	}

}
