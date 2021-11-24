package core;

import java.awt.*;

public interface ICellularAutomateDrawer<T> {

    void draw(Graphics2D g, CellularAutomateGrid<T> grid, int size);
    Color getCellColor(CellularAutomateGrid<T> grid, int cellY, int cellX);

}
