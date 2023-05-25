package util;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class JNumberField extends JPanel{

	/**
	 * 
	 */
	JTextField tf = new JTextField("0");

	private static final long serialVersionUID = 1L;

	public JNumberField(String name) {
		setLayout(new GridLayout(1,3));
		add(new JLabel(name));
		
		tf.setSize(300,50);
		tf.addKeyListener((KeyListener) new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')|| ke.getKeyChar() == '.') {
	               tf.setEditable(true);
	            } else {
	            	System.out.println(ke.getKeyCode());
	               tf.setEditable(false);
	            }
	         }
		});
		
		
		add(tf);
	}
	
	public String getText() {
		return tf.getText();
	}
	
	public Document getDocument() {
		return tf.getDocument();
	}
	
}
