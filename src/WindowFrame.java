import java.awt.*;
import java.awt.image.MemoryImageSource;

import javax.swing.*;

public class WindowFrame extends JFrame{
	public JPanel layoutpanel;
	public CardLayout layout = new CardLayout();
	public Menu menu;
	public Board board;

	public WindowFrame(Menu m,Board b) {
		super();
		layoutpanel = new JPanel(layout);
		menu = m;
		board = b;

		layoutpanel.add(menu,"menu");
		layoutpanel.add(board,"board");
		
		this.add(layoutpanel);
		this.setPreferredSize(new Dimension(1200,1250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setResizable(false);
		this.setSize(new Dimension(1200,1250));
		this.setTitle("GAME");
		layout.show(layoutpanel, "menu");
		pack();
	}
}
