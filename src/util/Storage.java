package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Storage {
	
	static ArrayList<Integer> mass = new ArrayList<Integer>();
	static ArrayList<Integer> heat = new ArrayList<Integer>();
	
	public static void add(int x, int y) {
		mass.add(x);
		heat.add(y);
	}
	
	public static String print() {
		p(Color.green, mass, "MassMap");
		return p(Color.red, heat, "HeatMap");
	}

	private static String p(Color c, ArrayList<Integer> a,String s) {
		String folder =  "Error";
		try {
			int w = a.size()/2;
			BufferedImage bmp = new BufferedImage(w,192, BufferedImage.TYPE_INT_RGB);
			
			Graphics ctx = bmp.getGraphics();
	
			for(int i = 0; i < a.size(); i++) {
				ctx.setColor(c);
				ctx.fillRect(
						i/2,
						192-(int) (a.get(i)/max(a)*192),
						1,
						(int) (a.get(i)/max(a)*192));
				i ++;
			}
	
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");
			LocalDateTime now = LocalDateTime.now();
			String d = dtf.format(now);
			folder = System.getProperty("user.home") + "\\AdityaPhysicsv1.0\\"+d+"\\";
			new File(folder).mkdirs();
			String path = folder + s+".png";
			System.out.println(path);
			File outputfile = new File(path);
			ImageIO.write(bmp, "png", outputfile);
		} catch (IOException e) {e.printStackTrace();}
		
		return folder;
	}
	
	private static float max(ArrayList<Integer> a) {
		int m = 0;
		for(int i =0 ; i< a.size(); i++) {
			if(a.get(i) >m) {
				m = a.get(i);
			}
		}
		return m;
	}
}
