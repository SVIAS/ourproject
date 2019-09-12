
package frame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;


public final class ScrollBarUI extends BasicScrollBarUI {

	
	@Override
	protected void configureScrollBarColors() {
	
		trackColor = new Color(0, 0, 0, 0);

		
		scrollBarWidth = 10;
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		super.paintTrack(g, c, trackBounds);
	}

	
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
	
		g.translate(thumbBounds.x, thumbBounds.y);

	
		g.setColor(Color.black);

	
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.addRenderingHints(rh);


		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));


		g2.fillRoundRect(0, 0, 10, thumbBounds.height - 1, 15, 15);
	}

	
	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new JButton();

	
		button.setVisible(false);
		return button;
	}

	
	@Override
	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton();
		button.setVisible(false);
		return button;
	}
}
