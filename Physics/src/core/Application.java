package core;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gfx.DisplayGraphics;
import util.Config;
import util.JNumberField;
import util.Storage;


public class Application {
	public static Simulation sim = new Simulation(1);
    public static JNumberField scale = new JNumberField("Scale : ");
    public static JNumberField mass = new JNumberField("Mass : ");
    public static JNumberField dens = new JNumberField("Density : ");
    public static JCheckBox path =  new JCheckBox("Show Path");
    public static JCheckBox pause =  new JCheckBox("Pause");
    public static JCheckBox op =  new JCheckBox("Opacity Trail");
    
    static int tick = (int) Config.FPS;
	
	public static float OffX = 0;
	public static float OffY = 0;
	public static float Scale = 1;
	public static JFrame frame = new JFrame("Yeya"); 
    
	
	public static void main(String s[]) {  
        DisplayGraphics display =new DisplayGraphics();
        
        path.setSelected(true);
        
        scale.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println(scale.getText());
				// TODO Auto-generated method stub
				
			}

        	  
        }); 
        
        
        JPanel p = new JPanel(new GridLayout(8,1));
        JPanel p2 = new JPanel(new GridLayout(5,1));
        
        p.setPreferredSize(new Dimension(50,50));
        p.add(new JLabel("Aditya Jain 2023 Copyright ©"));
        p.add(scale);

        p.add(mass);
        p.add(dens);
        p.add(path);
        p.add(pause);
        p.add(op);
        
        
        p2.add(new JLabel("Using :"));
        p2.add(new JLabel(">> Newtonian Law of gravitation"));
        p2.add(new JLabel(">> Schwarzschild Radius"));
        p2.add(new JLabel(">> Conservation of Momentum"));
        p2.add(new JLabel(">> All 3 Laws of Newton"));
        p.add(p2);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p, display);
        
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setSize(500,375);
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
        frame.setVisible(true);  
        frame.add(splitPane);

        
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	pause.setSelected(true);
		    	int o = JOptionPane.showConfirmDialog(frame, 
		                "Do you want to save the data?", "Close Window?", 
		                JOptionPane.YES_NO_CANCEL_OPTION,
		                JOptionPane.QUESTION_MESSAGE);
		    	if (o == JOptionPane.YES_OPTION){
		
		    		String q = Storage.print();
		    		int p = JOptionPane.showConfirmDialog(frame, 
		                    "Data is stored at "+q+". Open it?", "Location", 
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.INFORMATION_MESSAGE);
		    		if(p == JOptionPane.YES_OPTION) {
		    			try {
							Runtime.getRuntime().exec("explorer.exe /select," + q);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		        }
		    	
		        if (o == JOptionPane.YES_OPTION || o == JOptionPane.NO_OPTION){
		            System.exit(0);
		        }
		    	pause.setSelected(false);
		
		    }
		});
        
        Timer timer = new Timer((int) (1000/Config.FPS), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!pause.isSelected()) {
					tick--;
					if(tick == 0) {
						Storage.add(sim.getMass(), sim.getHeat());
						tick = (int) Config.FPS;
					}
					display.repaint();
					display.isPaintingTile();
					sim.Update();
				}
			}
        });
		
        new Thread(new Runnable() {

			@Override
			public void run() {
				timer.start();
			}
        	
        }).start();;
        
    }  
}
