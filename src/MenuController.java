import java.awt.event.*;

public class MenuController implements MouseListener,MouseMotionListener, Runnable{
	boolean stop = true;
	public Menu slave;
	public Thread menuThread;
	public MenuController(Menu menu) {
		menu.addController(this);
		slave = menu;
	}
	public void start() {
		menuThread = new Thread(this);
		stop = false;
		menuThread.start();
	}
	public void resume() {
		stop = false;
	}
	synchronized public void run() {
		while (true) {
			try {
				if (!stop||!slave.shut) {
					if (slave.render()) {
						slave.paintscence();
					Thread.sleep(5);
					}
				}
			} catch (InterruptedException e) {};
		}
	}
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < 682 && e.getX() > 518
				&& e.getY() > 450 && e.getY() < 490) {
			if(!slave.inStart) {
				slave.inStart = true;
			}
			slave.inExit = false;
		} else if(e.getX() < 657 && e.getX() > 543
				&& e.getY() > 550&&e.getY() < 590){
			if(!slave.inExit) {
				slave.inExit = true;
			}
			slave.inStart = false;
		} else {
			slave.inExit = false;
			slave.inStart = false;
		}
	}
	public void mouseDragged(MouseEvent e) {};
	public void mouseClicked(MouseEvent e) {
		if(slave.inStart) {
			slave.shut = true;
			stop = true;
		}
		if (slave.inExit) {
			System.exit(0);
		}
	}
	public void mousePressed(MouseEvent e) {};
	public void mouseReleased(MouseEvent e) {};
	public void mouseEntered(MouseEvent e) {};
	public void mouseExited(MouseEvent e) {};
	
	public boolean isPainting(){
        return slave.isPainting();
    }
}
