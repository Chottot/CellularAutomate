package wireworld;

import core.CellularAutomateGrid;
import core.ICellularAutomateRuler;

public class WireWorldRule implements ICellularAutomateRuler<WireWorldState> {

    @Override
    public WireWorldState ComputeNextCellStatus(CellularAutomateGrid<WireWorldState> cellGrid, int cellY, int cellX) {

        WireWorldState cell = cellGrid.getGrid()[cellY][cellX];

        switch (cell){

            case VOID: return WireWorldState.VOID;

            case CONDUCTOR:
                int nb = getNbHeadAround(cellGrid, cellY, cellX);
                if(nb >= 1 && nb <= 2){
                    return WireWorldState.ELECTRON_HEAD;
                }else{
                    return WireWorldState.CONDUCTOR;
                }
            case ELECTRON_HEAD: return WireWorldState.ELECTRON_TAIL;
            case ELECTRON_TAIL: return WireWorldState.CONDUCTOR;
        }

        return null;
    }

    private int getNbHeadAround(CellularAutomateGrid<WireWorldState> cellGrid, int y, int x){
        int count = 0;

        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++) {
                if(i==0 && j==0) continue;
                WireWorldState cell =getCell(cellGrid,y + i, x + j);
                if( cell == WireWorldState.ELECTRON_HEAD){
                    count += 1;
                }
            }
        }

        return count;
    }

    private WireWorldState getCell(CellularAutomateGrid<WireWorldState> cellGrid, int y, int x){
        int w = cellGrid.getW(), h = cellGrid.getH();
        WireWorldState[][] grid = cellGrid.getGrid();

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
