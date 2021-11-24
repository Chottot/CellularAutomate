package core;

import java.awt.*;
import java.util.TreeMap;

public class ColorRangerDrawer<T extends Comparable<T>> implements ICellularAutomateDrawer<T>{

    protected TreeMap<T, Color> colorMap;

    public ColorRangerDrawer(TreeMap<T, Color> colorMap) {
        this.colorMap = colorMap;
    }

    @Override
    public void draw(Graphics2D g, CellularAutomateGrid<T> grid, int size) {
        g.setColor(Color.gray);
        g.clearRect(0, 0, grid.width, grid.height);

        for(int y = 0; y < grid.height; y++){
            for (int x = 0; x < grid.width; x++){
                g.setColor( getCellColor(grid, y, x));
                g.fillRect(x * size, y * size, size, size);
            }
        }
    }

    @Override
    public Color getCellColor(CellularAutomateGrid<T> grid, int cellY, int cellX) {
        T cell = grid.grid[cellY][cellX];

        return colorMap.floorEntry(cell).getValue();
    }
}
