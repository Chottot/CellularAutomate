package lava;

import core.CellularAutomateGrid;
import core.ICellularAutomateRuler;

public class LavaRule implements ICellularAutomateRuler<Integer> {

    protected final Integer minValue, maxValue;

    public LavaRule(Integer min, Integer max) {
        this.minValue = min;
        this.maxValue = max;
    }

    @Override
    public Integer ComputeNextCellStatus(CellularAutomateGrid<Integer> cellGrid, int cellY, int cellX) {

        if(cellGrid.getGrid()[cellY][cellX].equals(maxValue)){
            if( getNbCellMaxAround(cellGrid, cellY, cellX) >= 3){
                return minValue;
            }
        }

        return cellGrid.getGrid()[cellY][cellX] + 1;
    }

    private Integer getCell(CellularAutomateGrid<Integer> cellGrid, int y, int x){
        int w = cellGrid.getW(), h = cellGrid.getH();
        Integer[][] grid = cellGrid.getGrid();

        if(x<0){
            x = w + x;
        }else if(x >= w){
            x %= w;
        }

        if(y<0){
            y = h + y;
        }else if(y >= h){
            y %= h;
        }

        return grid[y][x];
    }

    private int getNbCellMaxAround(CellularAutomateGrid<Integer> cellGrid, int y, int x){
        int count = 0;

        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if(getCell(cellGrid, y + i, x + j) > this.maxValue && !(i==0 && j==0) ){
                    count += 1;
                }
            }
        }

        return count;
    }
}
