package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoverComponente extends MouseAdapter {

    public static final String DRAGGED_MARK = "was.dragged";

    private boolean dragging = false;
    private Point startPoint = null;
    private Rectangle startBounds = null;

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            dragging = true;
            startPoint = e.getLocationOnScreen();
            startBounds = e.getComponent().getBounds();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {

            Component c = e.getComponent();

            Rectangle r = new Rectangle(startBounds.x + e.getLocationOnScreen().x - startPoint.x,
                    startBounds.y + e.getLocationOnScreen().y - startPoint.y, startBounds.width, startBounds.height);

            if (r.getX() < 0) {
                r.x = 0;
            } else if (r.x + c.getWidth() > c.getParent().getWidth()) {
                r.x = c.getParent().getWidth() - c.getWidth();
            }

            if (r.getY() < 0) {
                r.y = 0;
            } else if (r.y + c.getHeight() > c.getParent().getHeight()) {
                r.y = c.getParent().getHeight() - c.getHeight();
            }

            e.getComponent().setBounds(r);

            if (e.getComponent() instanceof JComponent) {
                ((JComponent) e.getComponent()).putClientProperty(DRAGGED_MARK, true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && dragging) {
            Rectangle bounds = e.getComponent().getBounds();
            Container parent = e.getComponent().getParent();
            boolean setBounds = false;
            for (int i = 1; i < parent.getWidth(); i += 125) {
                for (int j = 1; j < parent.getHeight(); j += 100) {
                    Rectangle cell = new Rectangle(i, j, 10, 10);
                    if (cell.intersects(bounds)) {
                        Rectangle intersection = cell.intersection(bounds);
                        if (intersection.width * intersection.height >= bounds.width * bounds.height / 8) {
                            System.out.println("hu3");
                            e.getComponent().setBounds(cell);
                            setBounds = true;
                            break;
                        }
                    }
                }
                if (setBounds) {
                    break;
                }
            }

            if (e.getComponent() instanceof JComponent) {
                ((JComponent) e.getComponent()).putClientProperty(DRAGGED_MARK, null);
            }
            dragging = false;
        }
    }
}
