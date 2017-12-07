import java.awt.event.*;

public class BallController extends MouseAdapter implements KeyListener,MouseMotionListener,Runnable{
	

	public Board board;
	public Wall wall;
	public BoardDraw drawer;
	Thread gamethread;
	boolean stop = true;
	
	BallController(Board b){
		board = b;
		wall = b.drawer.wall;
		this.addListener();
		this.drawer = b.drawer;
	}
	
	public void run() {
		BallMove bm = new BallMove(wall);
		Thread bt = new Thread(bm);
		bt.start();
		while(true) {
			try {
				drawer.drawWall();
				Thread.sleep(12);
			} catch (InterruptedException ex) {};
		}
	}
	public void start() {
		if (gamethread == null) {
			stop = false;
			gamethread = new Thread(this);
			gamethread.start();
		}
	}
	public void pause() {
		if(isPainting()) {
			stop = true;
			wall.isPause = true;
		}
	}
	public void resume() {
		if(!isPainting()) {
			stop = false;
		}
	}
	
	public boolean isPainting() {
		return board.isPainting();
	}
	
	public void addListener() {
		board.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(wall.isPause) {
					if(board.inContinue) {
						stop = false;
						wall.isPause=false;
					}
					if(board.inExit) {
						System.exit(0);
					}
				}
				if(wall.isOver) {
					if(board.inStart) {
						wall.initWall();
						wall.isOver=false;
						wall.isMove=true;
					}
					if(board.inExitB) {
						System.exit(0);
					}
				}
				if(wall.isDead) {
					if(board.inStartC) {
						wall.initWall();
						wall.isOver=false;
						wall.isMove=true;
					}
					if(board.inExitC) {
						System.exit(0);
					}
				}
			}
			public void mousePressed(MouseEvent e) {};
			public void mouseReleased(MouseEvent e) {};
		});
		board.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				if(wall.isPause) {
					if(e.getX()>300&&e.getX()<536
							&&e.getY()>195&&e.getY()<250) {
						if(!board.inContinue) {
							board.inContinue = true;
						}
						return;
					}
					if(e.getX()>300&e.getX()<415
							&&e.getY()>355&&e.getY()<400) {
						if(!board.inExit) {
							board.inExit=true;
						}
						return;
					}
					board.inContinue = false;
					board.inExit = false;
				}
				if(wall.isOver) {
					if(e.getX()>300&e.getX()<536
							&&e.getY()<760&&e.getWhen()>715) {
						if(!board.inStart) {
							board.inStart = true;
						}
						return;
					}
					if(e.getX()>300&&e.getX()<415
							&&e.getY()<840&&e.getY()>795) {
						if(!board.inExitB) {
							board.inExitB=true;
						}
						return;
					}
					board.inExitB=false;
					board.inStart=false;
				}
				if(wall.isDead) {
					if(e.getX()>300&&e.getX()<415
							&&e.getY()<840&&e.getY()>795) {
						if(!board.inExitC) {
							board.inExitC=true;
						}
						return;
					}
					if(e.getX()>300&e.getX()<536
							&&e.getY()<760&&e.getWhen()>715) {
						if(!board.inStartC) {
							board.inStartC = true;
						}
						return;
					}
					board.inExitC=false;
					board.inStartC=false;
				}
			}
			public void mouseDragged(MouseEvent e) {};
		});
		board.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		if(wall.isOver)return;
		if(wall.isDead)return;
		if (e.getKeyCode()==27) {
			if(wall.isPause) {
				wall.isPause = false;
				stop = false;
				return;
			}
			stop = true;
			wall.isPause = true;
		}
		if (e.getKeyCode() == 37&&!wall.isPause) {
			if (wall.ballX <= 0) {
				return;
			}
			wall.ballX--;
			wall.isMove = true;
		}
		if (e.getKeyCode() == 38&&!wall.isPause) {
			if (wall.ballY <= 0) {
				return;
			}
			wall.ballY--;
			wall.isMove = true;
		}
		if (e.getKeyCode() == 39&&!wall.isPause) {
			if (wall.ballX >= 14) {
				return;
			}
			wall.ballX++;
			wall.isMove = true;
		}
		if (e.getKeyCode() == 40&&!wall.isPause) {
			if (wall.ballY >= 14) {
				return;
			}
			wall.ballY++;
			wall.isMove = true;
		}
		if (wall.ballX == wall.finX && wall.ballY == wall.finY) {
			wall.isOver = true;
		}
		if (wall.wallset[wall.ballX][wall.ballY]) {
			wall.isDead = true;
		}	
	}
	public void keyTyped(KeyEvent evt) {};
	public void keyReleased(KeyEvent evt) {
		if(evt.getKeyCode() == 40||evt.getKeyCode()==39
				||evt.getKeyCode()==38||evt.getKeyCode()==37) {
			wall.isMove = false;
		}
	}
}
