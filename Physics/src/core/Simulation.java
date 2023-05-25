package core;

import java.util.ArrayList;

import physics.Force;
import physics.Point;
import physics.PointShadow;
import physics.Vector2;
import util.Config;

public class Simulation {
	public ArrayList<Point> pts = new ArrayList<Point>();
	public ArrayList<PointShadow> ptsShadow = new ArrayList<PointShadow>();

	public ArrayList<Point> rpts = new ArrayList<Point>();
	public ArrayList<PointShadow> rptsShadow = new ArrayList<PointShadow>();

	
	public Simulation(long seed) {}
	
	public void Update(){
		pts.forEach(e1 ->{
			pts.forEach(e2 ->{
				if(e1 != e2 && !(e1.bV || e2.bV)) {
					e1.Vel.Add(Force.GetAccFromForce(Force.GetGravity(e1, e2),e1.mass));
				}				
			});
		});
		
		//Add Vector2 to Velocity, V++, Accel.
		pts.forEach(e1 ->{
			pts.forEach(e2 ->{
				if(e1 != e2 && !(e1.bV || e2.bV)) {					
					//Annihilate objects moving near the speed of light
					if(e1.Vel.GetMag() >= Config.C) {
						e1.bV = true;
						rpts.add(e1);
					}
					
					
					if(Point.GetDistance(e1, e2) < Config.FIELD + e1.radius + e2.radius && !e1.bC && !e2.bC) {
//						if(Math.max(e1.mass, e2.mass) < 100.0f) {
//							Vector2.GetCollision(e1,e2);
//						}
////						else {
							rpts.add(Vector2.GetAnnihilation(e1,e2));
//						}
						
						e1.bC = true;
						e2.bC = true;
					}
				}
			});
		});

		
		ptsShadow.forEach(el ->{
			if(el.z == 0) {	rptsShadow.add(el);}
		});
		rpts.forEach(el -> {
			pts.remove(el);
		});
		rptsShadow.forEach(el -> {
			ptsShadow.remove(el);
		});


		
		//Add Velocity to Position, P++, Speed
		pts.forEach(el ->{
			if(!el.bV) {
				el.p.Add(el.Vel);
				if(Application.path.isSelected()) {
					ptsShadow.add(new PointShadow(el));					
				}
				el.bC = false;
			}
		});
		
		rpts.clear();
		rptsShadow.clear();
		
	}

	public int getMass() {
		int t = 0;
		for(int i = 0; i < pts.size(); i++) {
			t += pts.get(i).mass;
		}
		return t;
	}
	public int getHeat() {
		int t = 0;
		for(int i = 0; i < pts.size(); i++) {
			t += pts.get(i).Vel.GetMag() * pts.get(i).mass;
		}
		return t;
	}
}
