package gfx;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.Application;
import util.Config;
import util.MouseManager;

public class DisplayGraphics extends JPanel{  	
	
	private static final long serialVersionUID = 1L;
	

	public DisplayGraphics(){
		
		setBackground(Color.BLACK);
        setForeground(Color.RED);
        MouseManager mouseManager = new MouseManager();
        addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
		addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
        repaint();

	}

	
	public void paint(Graphics g) {  
		super.paint(g);

		Application.sim.ptsShadow.forEach(el ->{
			el.z--;
			if(Application.op.isSelected()) {
				el.color = new Color(el.color.getRed(), el.color.getGreen(), el.color.getBlue(),(int)(255*(el.z/(Config.FPS*Config.SHADOW))));
			}
			g.setColor(el.color);
			g.fillOval(
					(int)Math.ceil((el.x-el.radius + Application.OffX)*Application.Scale),
					(int)Math.ceil((el.y-el.radius + Application.OffY)*Application.Scale),
					2*(int)Math.ceil(el.radius*Application.Scale),
					2*(int)Math.ceil(el.radius*Application.Scale));
		});

		
		Application.sim.pts.forEach(el ->{
			float radius = (2f*Config.G*el.mass)/(Config.C*Config.C);
			if(radius > el.radius) {
				g.setColor(Color.GRAY);
				g.drawOval(
						(int)Math.ceil((el.p.x-radius + Application.OffX)*Application.Scale),
						(int)Math.ceil((el.p.y-radius + Application.OffY)*Application.Scale),
						2*(int)Math.ceil(radius*Application.Scale),
						2*(int)Math.ceil(radius*Application.Scale));
			}
			
			float a = el.Vel.GetMag();

			el.Color = Color.getHSBColor((0.66f-(a)/(Config.C)), 1, 1);			
			g.setColor(el.Color);
			g.fillOval(
					(int)Math.ceil((el.p.x-el.radius + Application.OffX)*Application.Scale),
					(int)Math.ceil((el.p.y-el.radius + Application.OffY)*Application.Scale),
					2*(int)Math.ceil(el.radius*Application.Scale),
					2*(int)Math.ceil(el.radius*Application.Scale));
		});
	}
}
