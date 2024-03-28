package bral.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SandComponent extends JComponent {

    private final Sand sand;
    private final int[][] currField;

    private final int height;

    private final int width;

    public SandComponent(Sand sand) {
        this.sand = sand;
        this.currField = sand.getField();
        this.height = sand.getHeight();
        this.width = sand.getWidth();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sand.put(e.getX(), e.getY(), 10, 10, .3);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                sand.put(e.getX(), e.getY(), 10, 10, .3);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the sand
        // code from Prof. Schwimmer:
        sand.fall();
        g.setColor(Color.DARK_GRAY);
        for (int y = 0; y < sand.getHeight(); y++) {
            for (int x = 0; x < sand.getWidth(); x++) {
                if (sand.isSand(x, y)) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
        repaint();

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // define size of each cell
        /*int cellWidth = getWidth() / sand.getWidth();
        int cellHeight = getHeight() / sand.getHeight();

        for (int y = 0; y < sand.getHeight(); y += 0.1) {
            for (int x = 0; x < sand.getWidth(); x += 0.1) {
                if (currField[y][x] == 1) {
                    int drawX = x * cellWidth;
                    int drawY = -y * cellHeight;
                    g.setColor(Color.YELLOW);
                    g.fillRect(drawX, drawY, cellWidth, cellHeight);
                }
            }
        }

        // new attempt:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (currField[y][x] == 1) {

                }
            }
        }*/

    }
}
