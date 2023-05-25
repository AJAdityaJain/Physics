package physics;

import java.awt.Color;

import util.Config;

public class PointShadow {
	public float x;
	public float y;
	public int z = (int) (Config.FPS * Config.SHADOW);
	public float radius;
	public Color color;
	
	public PointShadow(Point pt) {
		x = pt.p.x;
		y = pt.p.y;
		radius = pt.radius + Config.FIELD/2;
		color = new Color(pt.Color.getRGB()).darker().darker();
	}
}
