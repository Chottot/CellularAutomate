package core;

public abstract class CellularAutomateGrid<T>{

    protected final int width;
    protected final int height;
    protected T[][] grid;

    protected ICellularAutomateRuler<T> rule;

    public CellularAutomateGrid(T[][] grid, ICellularAutomateRuler<T> rule){
        this.width = grid[0].length;
        this.height = grid.length;
        this.grid = grid;
        this.rule = rule;
    }

    public CellularAutomateGrid(int width, int height, ICellularAutomateRuler<T> rule) {
        this.width = width;
        this.height = height;
        this.rule = rule;
        this.grid = getNewGrid();
        initGrid();
    }

    public abstract void initGrid();

    public void update(){
        long startTime = System.nanoTime();

        T[][] newGrid = getNewGrid();

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                newGrid[y][x] = rule.ComputeNextCellStatus(this, y, x);
            }
        }

        grid = newGrid;
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("grid upddate in: "+duration/1000000 );
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public T[][] getGrid() {
        return (T[][])grid;
    }

    public void setRule(ICellularAutomateRuler<T> rule) {
        this.rule = rule;
    }

    public ICellularAutomateRuler<T> getRule() {
        return rule;
    }

    public abstract T[][] getNewGrid();
}
