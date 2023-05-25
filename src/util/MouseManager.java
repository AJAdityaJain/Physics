package util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import core.Application;
import physics.Point;
import physics.Vector2;

public class MouseManager implements MouseListener, MouseMotionListener {

	public boolean leftPressed,middlePressed, rightPressed;
	public int mouseX, mouseY;
	
	public Vector2 Velocity = new Vector2(0,0);
	public Vector2 PersistentOff = new Vector2(0,0);
	public Vector2 u = new Vector2(0,0);
	public Vector2 v = new Vector2(0,0);
	
	public MouseManager(){
 
	}
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		PersistentOff.x = Application.OffX;
		PersistentOff.y = Application.OffY;
		
		
		u = new Vector2(mouseX,mouseY);
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;		
		}
		else if(e.getButton() == MouseEvent.BUTTON2) {
			middlePressed = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && leftPressed) { 
			leftPressed = false;
			v = new Vector2(e.getX() + Application.OffX,e.getY() + Application.OffY);
			v.Subtract(u);
			Velocity = Vector2.DivideF(Vector2.MultiplyF(v, Config.FPS),-1000);
			Point p = new Point(
						(e.getX()/Application.Scale) - (Application.OffX),
						(e.getY()/Application.Scale) - (Application.OffY),
						Float.parseFloat(Application.mass.getText()),
						Float.parseFloat(Application.dens.getText())
					);
//			p.Vel = new Vector2(Config.C,0);
			if(Velocity.GetMag() < Config.C*0.5f) {
				p.Vel = Velocity;				
			}
			else {
				Vector2.DivideF(Velocity, Velocity.GetMag());
				p.Vel = Vector2.MultiplyF(Velocity, Config.C*0.5f);
			}
			Application.sim.pts.add(p);
		}
		else if(e.getButton() == MouseEvent.BUTTON2) {
			middlePressed = false;
		}
		else if(e.getButton() == MouseEvent.BUTTON3&& rightPressed)
			rightPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() + Application.OffX);
		mouseY = (int) (e.getY() + Application.OffY);		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {		
		if(middlePressed) {
			v = new Vector2(e.getX(),e.getY());
			v.Subtract(u);

			v.Add(PersistentOff);
			v.Add(PersistentOff);
			
			Application.OffX = v.x / Application.Scale;
			Application.OffY = v.y;

		}
		if(rightPressed) {
//			Application.Scale = Math.abs((e.getX()+1)/1000.0f);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
