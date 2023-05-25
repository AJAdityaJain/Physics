package physics;

public class Vector2 {
	public float x;
	public float y;
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void Add(Vector2 v) {
		x += v.x;
		y += v.y;
		
	}

	public float GetMag() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	
	public void Subtract(Vector2 v) {
		x -= v.x;
		y -= v.y;
		
	}
	public static Vector2 MultiplyF(Vector2 v, float f) {
		v.x *= f;
		v.y *= f;
		return v;
	}
	public static Vector2 DivideF(Vector2 v, float f) {
		if(f ==0){
			
			return v;
		}
		v.x /= f;
		v.y /= f;
		return v;
	}

	public static Vector2 GetCollisionOf1(Point e1, Point e2) {
		float a = (e1.mass-e2.mass)	/	(e1.mass+e2.mass);
		float b =(2*e2.mass)		/ 	(e1.mass+e2.mass);
		
		Vector2 nv = MultiplyF(e1.Vel,a);  
		nv.Add(MultiplyF(e2.Vel,b));	
		      
		
		return nv;
	}
	
	public static Vector2 GetCollisionOf2(Point e1, Point e2) {
		float a =(2*e1.mass)		/ 	(e1.mass+e2.mass);
		float b = (e2.mass-e1.mass)	/	(e1.mass+e2.mass);		
		
		Vector2 v = MultiplyF(e1.Vel,a);
		v.Add(MultiplyF(e2.Vel,b));
		
				
		return v;
	}

	public static void GetCollision(Point e1, Point e2) {
		Vector2 v1 = Vector2.GetCollisionOf1(new Point(e1),new Point(e2));
		Vector2 v2 = Vector2.GetCollisionOf2(new Point(e1),new Point(e2));
		
		e1.Vel = (v1);
		e2.Vel = (v2);
	}
	
	public static Vector2 GetAvg(Vector2 v1, Vector2 v2) {
		Vector2 v = new Vector2(0,0);
		v.Add(v1);
		v.Add(v2);
		Vector2.DivideF(v, 2);
		return v;
	}

	public static Point GetAnnihilation(Point e1, Point e2) {
		if(e1.mass > e2.mass) {
			e1.p = Vector2.GetAvg(e1.p, e2.p);
			e1.Vel.Add(Vector2.MultiplyF(e2.Vel, e2.mass/e1.mass));
			e1.volume += e2.volume*e2.mass/e1.mass;			
			e1.mass += e2.mass;
			e1.Radius();
			return e2;
		}
		else{

			e2.p = Vector2.GetAvg(e2.p, e1.p);
			e2.Vel.Add(Vector2.MultiplyF(e1.Vel, e1.mass/e2.mass));
			e2.volume += e1.volume*e1.mass/e2.mass;			
			e2.mass += e1.mass;
			e2.volume += e1.volume;
			e2.Radius();
			return e1;
		}
		
	}
}