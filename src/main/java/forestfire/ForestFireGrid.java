package forestfire;

import core.CellularAutomateGrid;

import java.util.Random;

public class ForestFireGrid extends CellularAutomateGrid<ForestFireCell> {

    public ForestFireGrid(ForestFireCell[][] grid) {
        super(grid, new ForestFireRule());
    }

    public ForestFireGrid(int width, int height){
        super (width, height, new ForestFireRule());
    }

    @Override
    public void initGrid() {
        Random rand = new Random();
        for(int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){
                if(rand.nextBoolean())
                    grid[y][x] = ForestFireCell.FOREST;
                else
                    grid[y][x] = ForestFireCell.NONE;
            }
        }
    }

    @Override
    public ForestFireCell[][] getNewGrid() {
        return new ForestFireCell[height][width];
    }
}
