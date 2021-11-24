package core;

public interface ICellularAutomateRuler<T> {

    T ComputeNextCellStatus(CellularAutomateGrid<T> cellGrid, int cellY, int cellX);
}
