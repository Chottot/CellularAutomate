package gameoflife;

import core.CellularAutomateGrid;
import core.ICellularAutomateRuler;

public class GameOfLifeRule implements ICellularAutomateRuler<Boolean> {

    @Override
    public Boolean ComputeNextCellStatus(CellularAutomateGrid<Boolean> cellGrid, int cellY, int cellX) {
        int nb = getNbCellAround(cellGrid, cellY, cellX);
        return updateCell( cellGrid.getGrid()[cellY][cellX], nb);
    }

    private Boolean updateCell(Boolean cell, int nbNeighbour){
        if( !cell ){
            return nbNeighbour == 3;
        }else {
            return nbNeighbour >= 2 && nbNeighbour <= 3;
        }
    }

    private int getNbCellAround(CellularAutomateGrid<Boolean> cellGrid, int y, int x){
        int count = 0;

        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if(getCell(cellGrid,y + i, x + j) && !(i==0 && j==0) ){
                    count += 1;
                }
            }
        }

        return count;
    }

    private Boolean getCell(CellularAutomateGrid<Boolean> cellGrid, int y, int x){
        int w = cellGrid.getW(), h = cellGrid.getH();
        Boolean[][] grid = cellGrid.getGrid();

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

}
