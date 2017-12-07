import java.awt.*;
import javax.swing.*;


public class BoardDraw{
	
	public Board board;
	public Wall wall;
	public BoardDraw(Board b) {
		board = b;
		wall = new Wall();
		wall.initWall();
	}
	public synchronized void drawWall() {
		if(board.render()) {
			drawBackground();
			drawBlock();
			drawFinal();
			drawBall();
			if(wall.isPause)drawPause();
			if(wall.isOver)drawWin();
			if(wall.isDead)drawLose();
			board.paintscence();
		}	
	}
	
	ImageIcon exitB = new ImageIcon("src/resource/exit.png");
	ImageIcon  exitB2 = new ImageIcon("src/resource/exit2.png");
	ImageIcon restartB = new ImageIcon("src/resource/restart.png");
	ImageIcon restartB2= new ImageIcon("src/resource/restart2.png");
	ImageIcon continueB = new ImageIcon("src/resource/continue.png");
	ImageIcon continueB2= new ImageIcon("src/resource/continue2.png");
	ImageIcon youloseB = new ImageIcon("src/resource/youlose.png");
	ImageIcon youloseB2 = new ImageIcon("src/resource/youlose2.png");
	ImageIcon youwinB = new ImageIcon("src/resource/youwin.png");
	ImageIcon youwinB2= new ImageIcon("src/resource/youwin2.png");
	
	public void drawBackground() {
		Graphics2D g2 = getGraphics();
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, 1200, 1200);
	}
	public void drawPause() {
		Graphics2D g2 = getGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(250, 150, 700, 800);
		if(board.inContinue) {
			g2.drawImage(continueB2.getImage(),300,200,null);
		}else {
			g2.drawImage(continueB.getImage(),300,200,null);
		}
		if(board.inExit) {
			g2.drawImage(exitB2.getImage(), 300, 360, null);
		} else {
			g2.drawImage(exitB.getImage(), 300, 360, null);
		}
	}
	public void drawBall() {
		Graphics2D g2 = getGraphics();
		g2.setColor(wall.ballColor);
		g2.fillOval(10+wall.ballX*80, 10+wall.ballY*80, 60, 60);
	}
	public void drawBlock() {
		for (int i = 0; i < 15;i++) {
			for (int j = 0; j < 15; j++) {
				if(!wall.wallset[i][j]) {
					Graphics2D g2 = getGraphics();
					g2.setColor(Color.darkGray);
					g2.fillRect(80*i, 80*j, 80, 80);
				} 
			}
		}
	}
	public void drawFinal() {
		Graphics2D g2 = getGraphics();
		g2.setColor(new Color(120,120,120));
		g2.fillRect(wall.finX*80, wall.finY*80,80,80);
	}
	public void drawWin() {
		Graphics2D g2 = getGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(250, 150, 700, 800);
		g2.drawImage(youwinB.getImage(),300,200,null);
		if(board.inStart) {
			g2.drawImage(restartB2.getImage(), 300, 720, null);
		} else {
			g2.drawImage(restartB.getImage(), 300, 720, null);
		}
		if(board.inExitB) {
			g2.drawImage(exitB2.getImage(), 300, 800, null);
		}else{
			g2.drawImage(exitB.getImage(), 300, 800, null);
		}
	}
	public void drawLose() {
		Graphics2D g2 = getGraphics();
		g2.setColor(Color.BLACK);
		g2.fillRect(250, 150, 700, 800);
		g2.drawImage(youloseB.getImage(),300,200,null);
		if(board.inStartC) {
			g2.drawImage(restartB2.getImage(), 300, 720, null);
		} else {
			g2.drawImage(restartB.getImage(), 300, 720, null);
		}
		if(board.inExitC) {
			g2.drawImage(exitB2.getImage(), 300, 800, null);
		} else{
			g2.drawImage(exitB.getImage(), 300, 800, null);
		}
	}

	public Graphics2D getGraphics() {
		return board.dbg;
	}
}
