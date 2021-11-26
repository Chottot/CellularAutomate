package gameoflife;

import core.CellularAutomateViewer;
import core.ColorRangerDrawer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

public class GameOfLifeViewer extends CellularAutomateViewer<Boolean> {

    private static final TreeMap<Boolean, Color> colors = new TreeMap<>();
    static {
        colors.put(Boolean.TRUE, Color.white);
        colors.put(Boolean.FALSE, Color.BLACK);
    }

    public GameOfLifeViewer(GameOfLifeGrid grid, int size) {
        super(new ColorRangerDrawer<>(colors), grid,  size);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("test : "+ e.getButton());
        grid.getGrid()[e.getY()/getGridSize()][e.getX()/getGridSize()] = true;
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("test : "+ e.getButton());
        grid.getGrid()[e.getY()/getGridSize()][e.getX()/getGridSize()] = true;
        this.repaint();
    }
}
