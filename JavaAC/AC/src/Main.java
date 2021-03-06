
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener {
	
	static  JButton ColorPreview = new JButton("Autoclicker");
	static  JButton AC1 = new JButton("L: false R: false");

	static boolean ac = false;
	static boolean ac2 = false;
	
	public void nativeKeyPressed(NativeKeyEvent e) {				
		
		if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "X") {
			
	            		
        }
	}
	public void nativeKeyReleased(NativeKeyEvent e) {
		
		
		if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "O") {
			System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			 System.out.println("Exit");
			System.exit(1);
		}
		
		if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "X") {
			System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			if(ac) {
				ac = false;
				System.out.println("AC: OFF");				
				AC1.setText("L: " + ac +" R: "+ ac2);
			} else {
				ac = true;
				System.out.println("AC: ON");				
				AC1.setText("L: " + ac +" R: "+ ac2);
			}
						
		}
		
		if(NativeKeyEvent.getKeyText(e.getKeyCode()) == "Y") {
			System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
			if(ac2) {
				ac2 = false;
				System.out.println("AC 2: OFF");
				AC1.setText("L: " + ac +" R: "+ ac2);
			} else {
				ac2 = true;
				System.out.println("AC 2: ON");
				AC1.setText("L: " + ac +" R: "+ ac2);
			}
						
		}
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		new Thread(() -> {
			Robot robot;
			try {
				robot = new Robot();
				for(int i = 1; i > 0; i++) {
					 System.out.println("AC: " + ac);
					
					if(ac) {
						 	Thread.sleep(55);						    
						    robot.mousePress(InputEvent.BUTTON1_MASK);
						    robot.mouseRelease(InputEvent.BUTTON1_MASK);
						    System.out.println("AC: HIT");
					}
					if(ac2) {
					 	Thread.sleep(30);						    
					    robot.mousePress(InputEvent.BUTTON3_MASK);
					    robot.mouseRelease(InputEvent.BUTTON3_MASK);
					    System.out.println("AC: BUILD");
				}
				}
			} catch (AWTException e) {
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
		}).start();
		System.out.println("Starting..");
		
		//Overlay. Note: Works only in Borderlesswindow mode!
		JFrame frame = new JFrame("Transparent Windows");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);      
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);              
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.getContentPane().add(ColorPreview, java.awt.BorderLayout.NORTH);
        frame.getContentPane().add(AC1); 
        
        frame.setVisible(true);
        frame.pack();
        ColorPreview.setBackground(new java.awt.Color(23, 170, 255));
        ColorPreview.setForeground(new java.awt.Color(255, 255, 255));
        
        AC1.setBackground(new java.awt.Color(23, 170, 255));
        AC1.setForeground(new java.awt.Color(255, 255, 255));
        
       
        
       
		
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new Main());
		

	}
}
