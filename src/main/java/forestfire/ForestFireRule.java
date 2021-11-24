package forestfire;

import core.CellularAutomateGrid;
import core.ICellularAutomateRuler;

public class ForestFireRule implements ICellularAutomateRuler<ForestFireCell> {

    @Override
    public ForestFireCell ComputeNextCellStatus(CellularAutomateGrid<ForestFireCell> cellGrid, int cellY, int cellX) {

        ForestFireCell cell = cellGrid.getGrid()[cellY][cellX];
        if(cell == ForestFireCell.FOREST){
            if( isNeighborCellBurning(cellGrid, cellY, cellX) ){
                cell = ForestFireCell.FIRE;
            }
        }else if(cell == ForestFireCell.FIRE){
            cell = ForestFireCell.BURNT;
        }

        return cell;
    }

    boolean isNeighborCellBurning(CellularAutomateGrid<ForestFireCell> cellGrid, int cellY, int cellX) {

        if(cellX > 0 && cellGrid.getGrid()[cellY][cellX - 1] == ForestFireCell.FIRE){
            return true;
        }

        if(cellY > 0 && cellGrid.getGrid()[cellY - 1][cellX] == ForestFireCell.FIRE){
            return true;
        }

        if(cellX < cellGrid.getW() - 1 && cellGrid.getGrid()[cellY][cellX + 1] == ForestFireCell.FIRE){
            return true;
        }

        if(cellY < cellGrid.getH() - 1 && cellGrid.getGrid()[cellY + 1][cellX] == ForestFireCell.FIRE){
            return true;
        }

        return false;
    }
}
