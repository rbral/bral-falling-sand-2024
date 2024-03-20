package bral.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandComponent extends JComponent {

    private final Sand sand;
    private final int[][] currField;

    public SandComponent(Sand sand) {
        this.sand = sand;
        this.currField = sand.getField();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(0, -getHeight());

        // define size of each cell
        int cellWidth = getWidth() / sand.getWidth();
        int cellHeight = getHeight() / sand.getHeight();

        for (int y = 0; y < sand.getHeight(); y++) {
            for (int x = 0; x < sand.getWidth(); x++) {
                if (currField[x][y] == 1) {
                    int drawX = x * cellWidth;
                    int drawY = -y * cellHeight;
                    g.setColor(Color.YELLOW);
                    g.fillRect(drawX, drawY, cellWidth, cellHeight);
                }
            }
        }
    }
}
