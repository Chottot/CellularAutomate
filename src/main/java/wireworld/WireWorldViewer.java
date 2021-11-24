package wireworld;

import core.CellularAutomateViewer;
import core.ColorRangerDrawer;
import core.CellularAutomateGrid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

public class WireWorldViewer extends CellularAutomateViewer<WireWorldState> {

    private static final TreeMap<WireWorldState, Color> colors = new TreeMap<>();
    static {
        colors.put(WireWorldState.VOID, Color.black);
        colors.put(WireWorldState.CONDUCTOR, Color.yellow);
        colors.put(WireWorldState.ELECTRON_HEAD, Color.blue);
        colors.put(WireWorldState.ELECTRON_TAIL, Color.red);
    }

    public WireWorldViewer(CellularAutomateGrid<WireWorldState> grid, int size) {
        super( new ColorRangerDrawer<>(colors), grid, size);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        WireWorldState state = WireWorldState.CONDUCTOR;
        System.out.println("test: "+e.getButton());
        if(e.getButton() == MouseEvent.BUTTON3){
            state = WireWorldState.ELECTRON_HEAD;
        }else if(e.getButton() == MouseEvent.BUTTON2){
            state = WireWorldState.VOID;
        }
        grid.getGrid()[e.getY()/getGridSize()][e.getX()/getGridSize()] = state;
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        grid.getGrid()[e.getY()/getGridSize()][e.getX()/getGridSize()] = WireWorldState.CONDUCTOR;
        this.repaint();
    }
}
