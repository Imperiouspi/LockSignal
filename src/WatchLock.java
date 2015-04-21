import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;

public class WatchLock {
	public static void main(String[] args){
		Toolkit tools = Toolkit.getDefaultToolkit();
		String prefix = "C:\\Users\\Noah\\Documents\\Code\\Workspace\\LockSignal\\src\\";
		System.out.println(new File(prefix + "resources\\NumOff.jpg").exists());
		ImageIcon numicon = new ImageIcon((prefix + "resources\\NumOff.jpg"));
		ImageIcon capsicon = new ImageIcon((prefix + "resources\\CapsOff.jpg"));
		Image numImage = numicon.getImage();
		Image capsImage = capsicon.getImage();
		TrayIcon NumIndicator = new TrayIcon(numImage, "NumLock");
		NumIndicator.setImageAutoSize(true);
		TrayIcon CapsIndicator = new TrayIcon(capsImage, "CapsLock");
		CapsIndicator.setImageAutoSize(true);
		SystemTray tray = SystemTray.getSystemTray();
		
		try {
			tray.add(CapsIndicator);
			tray.add(NumIndicator);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		while(true){
			if(tools.getLockingKeyState(KeyEvent.VK_NUM_LOCK)){
				numicon = new ImageIcon((prefix + "resources\\NumOn.jpg"));
				numImage = numicon.getImage();
				NumIndicator.setImage(numImage);
			}
			else{
				numicon = new ImageIcon((prefix + "resources\\NumOff.jpg"));
				NumIndicator.setImage(numImage);
			}
			if(tools.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)){
				capsicon = new ImageIcon((prefix + "resources\\CapsOn.jpg"));
				capsImage = capsicon.getImage();
				CapsIndicator.setImage(capsImage);
			}
			else{
				capsicon = new ImageIcon((prefix + "resources\\CapsOff.jpg"));
				capsImage = capsicon.getImage();
				CapsIndicator.setImage(capsImage);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
