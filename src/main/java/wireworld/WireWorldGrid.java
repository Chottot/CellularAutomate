package wireworld;

import core.CellularAutomateGrid;

public class WireWorldGrid extends CellularAutomateGrid<WireWorldState> {


    public WireWorldGrid(WireWorldState[][] grid) {
        super(grid, new WireWorldRule());
    }

    public WireWorldGrid(int width, int height) {
        super(width, height, new WireWorldRule());
    }

    @Override
    public void initGrid() {
        for(int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){
                grid[y][x] = WireWorldState.VOID;
            }
        }
    }

    @Override
    public WireWorldState[][] getNewGrid() {
        return new WireWorldState[height][width];
    }
}
