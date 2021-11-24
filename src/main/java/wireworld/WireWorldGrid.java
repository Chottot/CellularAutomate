package wireworld;

import core.CellularAutomateGrid;

public class WireWorldGrid extends CellularAutomateGrid<WireWorldState> {


    public WireWorldGrid(WireWorldState[][] grid) {
        super(grid, new WireWorldRule());
    }

    @Override
    public void initGrid() {

    }

    @Override
    public WireWorldState[][] getNewGrid() {
        return new WireWorldState[height][width];
    }
}
