package forestfire;

import core.CellularAutomateViewer;
import core.ColorRangerDrawer;
import core.CellularAutomateGrid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

public class ForestFireViewer extends CellularAutomateViewer<ForestFireCell> {

    private static final TreeMap<ForestFireCell, Color> colors = new TreeMap<>();
    static {
        colors.put(ForestFireCell.NONE, Color.white);
        colors.put(ForestFireCell.FOREST, Color.green);
        colors.put(ForestFireCell.FIRE, Color.red);
        colors.put(ForestFireCell.BURNT, Color.gray);
    }

    public ForestFireViewer(CellularAutomateGrid<ForestFireCell> grid, int size) {
        super(new ColorRangerDrawer<>(colors), grid,  size);
    }

    private void setOnFire(int x, int y){
        if( x >= 0 && x < grid.getW() && y >= 0 && y < grid.getH() ){
            grid.getGrid()[y][x] = ForestFireCell.FIRE;
            this.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX()/getGridSize();
        int y = e.getY()/getGridSize();
        setOnFire(x, y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX()/getGridSize();
        int y = e.getY()/getGridSize();
        setOnFire(x, y);
    }
}
