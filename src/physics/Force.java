package physics;

import util.Config;

public class Force {
	public float m = 1.0f;
	public Vector2 d = new Vector2(0,0);
	
	public static Force GetGravity(Point p1, Point p2) {
		Force f = new Force();
		float dis = Point.GetDistance(p1,p2);
		f.m = Config.G*p1.mass*p2.mass/(dis*dis);
		
		if(Float.isInfinite(f.m)) {
			f.m = 0;
		}
		
		float x = p2.p.x - p1.p.x;
		float y = p2.p.y - p1.p.y;
		float z = Math.max(Math.abs(x),Math.abs(y));

		f.d.x = x/z;
		f.d.y = y/z;

		return f;
	}
	
	public static Vector2 GetAccFromForce(Force f, float mass) {
		float am = f.m/mass;
		
		if(mass == 0) {
			am = Config.C;
		}
		
		Vector2 a = new Vector2(0,0);
		
		a.x = am*f.d.x;
		a.y = am*f.d.y;
//		System.out.print("mag:" + f.m);
		return a;
 	}
	
	
}
