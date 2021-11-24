package gameoflife;

import core.CellularAutomateGrid;

import java.util.Random;

public class GameOfLifeGrid extends CellularAutomateGrid<Boolean> {

    public GameOfLifeGrid(Boolean[][] grid) {
        super(grid, new GameOfLifeRule());
    }

    public GameOfLifeGrid(int width, int height) {
        super(width, height, new GameOfLifeRule());
    }


    /*@Override
    public void draw(Graphics2D g, int size) {
        g.setColor(Color.gray);
        g.clearRect(0, 0, this.width, this.height);

        for(int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){
                if(grid[y][x]){
                    g.setColor(Color.white);
                }else{
                    g.setColor(Color.black);
                }
                g.fillRect(x*size, y*size, size, size);
            }
        }

        g.setColor(Color.darkGray);
        for (int x = 0; x < this.width; x++) {
            g.drawLine(x * size, 0, x * size, this.height * size);
        }
        for (int y = 0; y < this.height; y++) {
            g.drawLine(0, y * size, this.width * size, y * size);
        }
    }*/

    @Override
    public void initGrid() {
        Random rand = new Random();
        for(int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){
                grid[y][x] = rand.nextBoolean();
            }
        }
    }

    @Override
    public Boolean[][] getNewGrid() {
        return new Boolean[height][width];
    }
}
