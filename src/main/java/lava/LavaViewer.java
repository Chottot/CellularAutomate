package lava;

import core.CellularAutomateViewer;
import core.ColorRangerDrawer;
import core.CellularAutomateGrid;

import java.awt.*;
import java.util.TreeMap;

public class LavaViewer extends CellularAutomateViewer<Integer> {

    private static final TreeMap<Integer, Color> colors = new TreeMap<>();
    static {
        colors.put(0, new Color(255, 255, 0));
        colors.put(1, new Color(255, 170, 0));
        colors.put(2, new Color(255, 85, 0));
        colors.put(3, new Color(255, 0, 0));
    }

    public LavaViewer(CellularAutomateGrid<Integer> grid, int size) {
        super( new ColorRangerDrawer<>(colors), grid, size);
    }


}
