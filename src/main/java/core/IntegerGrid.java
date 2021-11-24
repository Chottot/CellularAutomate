package core;

import java.awt.*;

public class IntegerGrid extends CellularAutomateGrid<Integer> {

    public IntegerGrid(Integer[][] grid, ICellularAutomateRuler<Integer> rule) {
        super(grid, rule);
    }

    @Override
    public void initGrid() {

    }

    public void draw(Graphics g, int size) {
        g.setColor(Color.gray);
        g.clearRect(0, 0, this.width, this.height);

        for(int y = 0; y < this.height; y++){
            for (int x = 0; x < this.width; x++){
                Integer i = grid[y][x];
                if(i>255) i = 255;

                switch (i){
                    case 0:
                        g.setColor(Color.yellow);
                        break;
                    case 1:
                        g.setColor(Color.PINK);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE);
                        break;
                    case 3:
                        g.setColor(Color.RED);
                        break;
                    default:
                        g.setColor(new Color(0, i*2, i*2));
                        break;
                }
                g.fillRect(x*size, y*size, size, size);
            }
        }

    }

    @Override
    public Integer[][] getNewGrid() {
        return new Integer[height][width];
    }
}
