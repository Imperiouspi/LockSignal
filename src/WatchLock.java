import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class WatchLock {
	static Toolkit tools;
	static SystemTray tray;
	
	static ImageIcon numicon;
	static Image numImage;
	static TrayIcon NumIndicator;
	
	static ImageIcon capsicon;
	static Image capsImage;
	static TrayIcon CapsIndicator;
	
	static String prefix;
	
	static boolean isCaps;
	static boolean isNum;
	static JFrame frame;
	
	public static void main(String[] args){
		tools = Toolkit.getDefaultToolkit();
		isCaps = tools.getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		isNum = tools.getLockingKeyState(KeyEvent.VK_NUM_LOCK);
		prefix = "C:\\Users\\Noah\\Documents\\Code\\Workspace\\LockSignal\\src\\";
		numicon = new ImageIcon((prefix + "resources\\NumOff.png"));
		capsicon = new ImageIcon((prefix + "resources\\CapsOff.png"));
		numImage = numicon.getImage();
		capsImage = capsicon.getImage();
		NumIndicator = new TrayIcon(numImage, "NumLock");
		NumIndicator.setImageAutoSize(true);
		CapsIndicator = new TrayIcon(capsImage, "CapsLock");
		CapsIndicator.setImageAutoSize(true);
		tray = SystemTray.getSystemTray();
		try {
			tray.add(CapsIndicator);
			tray.add(NumIndicator);		
		} catch (AWTException e) {
			e.printStackTrace();
		}

		 AWTEventListener listener = new AWTEventListener() {
				@Override
				public void eventDispatched(AWTEvent event) {
					if (event instanceof KeyEvent) {
		                KeyEvent ke = (KeyEvent) event;
		                if (ke.getID() == KeyEvent.KEY_PRESSED) {
		                    if (ke.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
		                        isCaps = !isCaps;
		                    }
		                    if (ke.getKeyCode() == KeyEvent.VK_NUM_LOCK) {
		                        isNum = !isNum;
		                    }
		                }
		            }
				}
		    };
		tools.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(0,0);
        frame.requestFocus();
        frame.setVisible(true);

        
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Timer time = new Timer();
            	time.scheduleAtFixedRate(new TimerTask(){

					@Override
					public void run() {
		                updateTips();
					}
            		
            	}, 100L, 100L);
            }
        });
	}
	public static void updateTips(){
		if(isNum){
			numicon = new ImageIcon((prefix + "resources\\NumOn.png"));
			numImage = numicon.getImage();
			NumIndicator.setImage(numImage);
		}
		else{
			System.out.println("False");
			numicon = new ImageIcon((prefix + "resources\\NumOff.png"));
			numImage = numicon.getImage();
			NumIndicator.setImage(numImage);
		}
		if(isCaps){
			capsicon = new ImageIcon((prefix + "resources\\CapsOn.png"));
			capsImage = capsicon.getImage();
			CapsIndicator.setImage(capsImage);
		}
		else{
			capsicon = new ImageIcon((prefix + "resources\\CapsOff.png"));
			capsImage = capsicon.getImage();
			CapsIndicator.setImage(capsImage);
		}
	}

}
