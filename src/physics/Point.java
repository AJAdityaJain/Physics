package physics;

import java.awt.Color;

public class Point {
	public Vector2 p = new Vector2(0,0);
	public Vector2 Vel = new Vector2(0,0);
	public float mass = 1;
	public float volume = 1;
	public float radius = 0;
	public boolean bV = false;
	public boolean bC = false;
	public Color Color = java.awt.Color.BLUE;	
	
	public Point(float x , float y, float mass, float density) {
		this.p.x = x;
		this.p.y = y;
		this.mass = mass;
		this.volume = Math.abs(mass/density);
		Radius();
	}
	public Point(Point p) {
		this.p.x = p.p.x;
		this.p.y = p.p.y;
		this.Vel.x = p.Vel.x;
		this.Vel.y = p.Vel.y;
		this.mass = p.mass;
		this.volume = p.volume;
		this.radius = p.radius;
		this.bV = p.bV;
	}
	public static float GetDistance(Point p1, Point p2) {
		float x = p1.p.x-p2.p.x;
		float y = p1.p.y-p2.p.y;
		return (float)Math.abs(Math.sqrt(x*x + y*y));
		
	}
	public void Radius() {
		radius = (float) Math.ceil(Math.sqrt(this.volume/Math.PI));	
	}
}
