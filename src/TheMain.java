
public class TheMain {
	public static void main(String[] args) {
		Menu menu= new Menu();
		Board board = new Board();

		WindowFrame window = new WindowFrame(menu, board);
		
		BallController bc = new BallController(board);
		MenuController mc = new MenuController(menu);
		PanelController pc = new PanelController(window.layoutpanel,mc,bc);

		window.layoutpanel.addKeyListener(bc);
		window.layoutpanel.setFocusable(true);
		
		Thread thread = new Thread(pc);
		
		thread.start();
		window.setVisible(true);
	}
}
