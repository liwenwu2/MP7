import java.awt.*;

import javax.swing.*;

public class Menu extends JPanel{
	boolean shut = false;
	boolean inStart = false;
	boolean inExit = false;
	boolean flag = false;
	
	ImageIcon startB = new ImageIcon("src/resource/start.png");
	ImageIcon startB1= new ImageIcon("src/resource/start2.png");
	ImageIcon exitB = new ImageIcon("src/resource/exit.png");
	ImageIcon exitB1 = new ImageIcon("src/resource/exit2.png");
	
	Image dbImage;
	Graphics2D dbg;
	public Menu() {
		super();
        this.setPreferredSize(new Dimension(1200,1200));
        this.setDoubleBuffered(true);  
	}
	
	public boolean render() {
		if (dbImage == null) {
	         dbImage = createImage(1200, 1200);
	         if (dbImage == null) {
	           return false;
	        }
	        dbg = (Graphics2D) dbImage.getGraphics();
		}
		dbg.fillRect(0, 0, 1200, 1200);
		if(inStart) {
			dbg.drawImage(startB1.getImage(), 600-startB1.getIconWidth()/2, 450, null);
		} else {
			dbg.drawImage(startB.getImage(),600-startB.getIconWidth()/2,450,null);
		}
		if(inExit) {
			dbg.drawImage(exitB1.getImage(), 600-exitB1.getIconWidth()/2, 550, null);
		}else {
			dbg.drawImage(exitB.getImage(), 600-exitB.getIconWidth()/2, 550, null);
		}
        return true;
	}
    public void paintscence(){
    	flag = true;
    	Graphics2D g=(Graphics2D) this.getGraphics();
    	if(g!=null&&dbImage!=null) {
    		g.drawImage(dbImage, 0, 0, null);
        	Toolkit.getDefaultToolkit().sync();
        	g.dispose();
    	}
    }
	
	public void addController(MenuController mc) {
		this.addMouseListener(mc);
        this.addMouseMotionListener(mc);
	}
	
	public boolean isPainting() {
        return flag;
    }
}
