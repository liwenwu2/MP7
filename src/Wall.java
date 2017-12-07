import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.TimerTask;
import javax.swing.Timer;


public class Wall {
	public boolean[][] wallset = new boolean[15][15];
	public boolean isDead = false;
	public boolean isOver = true;
	public boolean isMove = false;
	public boolean isPause = false;
	public  Color ballColor = new Color(111,105,105);
	public int ballX;
	public int ballY;
	public int finX;
	public int finY;
	public int count;
	
	java.util.Timer timer2 = new java.util.Timer();
	
	public void initWall() {
		count = 0;
		ballColor = new Color(111,105,105);
		ballX = 0;
		ballY = 0;
		isDead = false;
		isOver = true;
		isMove = false;
		isPause = false;
		finX = 7 + (int)(7*Math.random());
		finY = 7 + (int)(7*Math.random());
		for(int i = 0; i < 15;i++) {
			Arrays.fill(wallset[i],false);
		}
	}
	public void ballUpdate() {
		System.out.println(1);
	}
	private class RepaintAction implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
			if(isPause) return;
			if(isOver)return;
			if(isDead)return;
			for(int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if(Math.random()>0.5) {
						wallset[i][j] = !wallset[i][j];
					} 
				}
			}
			if(ballX-1>=0&&ballX+1<15&&ballY-1>=0&&ballY+1<15) {
				int count = 0;
				if (wallset[ballX+1][ballY])count++;
				if(wallset[ballX-1][ballY])count++;
				if(wallset[ballX][ballY-1])count++;
				if(wallset[ballX][ballY+1])count++;
				if(count==4) {
					switch((int)(4*Math.random())) {
					case 0:
						wallset[ballX+1][ballY] = false;
					case 1:
						wallset[ballX-1][ballY] = false;
					case 2:
						wallset[ballX][ballY-1] = false;
					case 3:
						wallset[ballX][ballY+1] = false;
					}
				}
			} else if((ballX-1<0&&ballY-1>=0&&ballY+1<15)
					||(ballX+1>=15&&ballY-1>=0&&ballY+1<15)) {
				switch((int)(2*Math.random())) {
				case 0:
					wallset[ballX][ballY+1]=false;
				case 1:
					wallset[ballX][ballY-1]=false;
				}
			} else if((ballY-1<0&&ballX-1>=0&&ballX+1<15)
					||(ballY+1>=15&&ballX-1>=0&&ballX+1<15)) {
				switch((int)(2*Math.random())){
					case 0:
						wallset[ballX+1][ballY]=false;
						wallset[ballX-1][ballY]=false;
				}
			} else if((ballY-1<0&&ballX-1<0)
					||(ballY+1>=15&&ballX-1<0)) {
				wallset[ballX+1][ballY]=false;
			} else if((ballY-1<0&&ballX+1>=15)
					||(ballY+1>=15&&ballX+1>=15)) {
				wallset[ballX-1][ballY]=false;
			}
			wallset[ballX][ballY]=false;
			wallset[finX][finY]=false;
		}
	}

	public Wall() {
		RepaintAction action  = new RepaintAction();
		Timer timer = new javax.swing.Timer(800, action);
		timer.start();
	}				
}
