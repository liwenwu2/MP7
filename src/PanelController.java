import java.awt.CardLayout;

import javax.swing.*;

public class PanelController implements Runnable{
	MenuController mc;
	BallController bc;
	JPanel showpanel;
	CardLayout layout;
	
	int mainthread = 0;
	
	PanelController (JPanel show,MenuController menuController, BallController ballController){
		mc = menuController;
		bc = ballController;
		showpanel = show;
		layout = (CardLayout)show.getLayout();
	}
	
	synchronized public void run() {
		whoShow();
		while (true) {
			whoShow();
			switch(mainthread) {
			case 0:
				displayMenu();
				break;
			case 1:
				displayGame();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			};
		}
	}
	
	synchronized public void whoShow() {
		if(mc.menuThread==null&&bc.gamethread==null) {
			mainthread = 0;
		}
		if(mc.menuThread!=null) {
			if(mc.stop&&mc.menuThread.isAlive()
					&&bc.gamethread==null) {
				mainthread = 1;
			}
		}
		if(mc.menuThread!=null&&bc.gamethread!=null&&
				!bc.gamethread.isAlive()) {
			mainthread = 0;
		}
		if(mc.menuThread !=null&&bc.gamethread!=null&&
				mc.menuThread.isAlive()&&bc.gamethread.isAlive()) {
			mainthread = 1;
		}
	}
	
	public void displayMenu() {
		if(mc.menuThread==null) {
            mc.start();
            layout.show(showpanel, "menu");
         }
        else if(!mc.isPainting()){                                  
            mc.resume();
            layout.show(showpanel, "menu");
        }
	}
	
	public void displayGame() {
		if(bc.gamethread ==null) {
			bc.start();
			bc.wall.isOver=false;
			layout.show(showpanel, "board");
		} else {
			bc.resume();
			layout.show(showpanel, "board");
		}
	}	
}
