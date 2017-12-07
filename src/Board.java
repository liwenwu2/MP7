import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
	boolean inContinue=false;
	boolean inExit=false;
	boolean inStart =false;
	boolean inExitB = false;
	boolean inExitC = false;
	boolean inStartC = false;
	
	public BoardDraw drawer;
	public BallController bc = null;
	public Graphics2D dbg = null;
	public Image dbImage = null;
	public ImageIcon bg = null;
	public int eX;
	public int eY;
	boolean flag = false;
	
	public Board() {
		super();
		drawer = new BoardDraw(this);
		eX = 7 + (int)(7*Math.random());
		eY = 7 + (int)(7*Math.random());
		this.setBackground(null);
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
       return true;
	}
	public  void paintscence(){
		flag = true;
		Graphics2D g=(Graphics2D) this.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
	    if(g!=null&&dbImage!=null) {
	    g.drawImage(dbImage, 0, 0, null);
	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();
	    }
	}
	public boolean isPainting() {
        return flag;
    }
			
}
