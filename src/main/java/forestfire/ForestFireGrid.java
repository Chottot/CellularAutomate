package forestfire;

import core.CellularAutomateGrid;

public class ForestFireGrid extends CellularAutomateGrid<ForestFireCell> {

    public ForestFireGrid(ForestFireCell[][] grid) {
        super(grid, new ForestFireRule());
    }

    @Override
    public void initGrid() {

    }

    @Override
    public ForestFireCell[][] getNewGrid() {
        return new ForestFireCell[height][width];
    }
}
