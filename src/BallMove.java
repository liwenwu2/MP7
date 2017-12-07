import java.awt.Color;

public class BallMove implements Runnable{
	Wall wall;
	public BallMove(Wall w) {
		wall = w;
	}
	public void run() {
		while(true) {
			System.out.println(1);
			if(!wall.isPause&&!wall.isOver&&!wall.isDead) {
				if(!wall.isMove) {
					for(int i = wall.count; i < 125; i++) {
						if(wall.isPause) {
							wall.count=i;
							break;
						}
						if(wall.isMove) {
							wall.ballColor = new Color(111,105,105);
							wall.count = 0;
							break;
						}
						Color color = new Color(111+i,105-i/2,105-i/2);
						wall.ballColor = color;
						try{Thread.sleep(8);}
						catch(InterruptedException e) {};
					}
					if(!wall.isMove&&!wall.isPause) {
						wall.isDead = true;
					}
				} 
			}
		}
	}
}
