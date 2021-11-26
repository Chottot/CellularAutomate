package core;

import javax.swing.*;
import java.awt.*;

public class CellularAutomateGridViewer<T> extends JPanel {
    protected CellularAutomateGrid<T> grid;
    protected int size;
    protected ICellularAutomateDrawer<T> drawer;
    protected boolean showGrid;

    public CellularAutomateGridViewer(CellularAutomateGrid<T> grid, ICellularAutomateDrawer<T> drawer, int size, boolean showGrid) {
        super();
        this.grid = grid;
        this.size = size;
        this.drawer = drawer;
        this.showGrid = showGrid;
        setPrefDim();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        long startTime = System.nanoTime();
        drawer.draw((Graphics2D) g, grid, size);
        if(showGrid){
            g.setColor(Color.darkGray);
            for (int x = 0; x < grid.width; x++) {
                g.drawLine(x * size, 0, x * size, grid.height * size);
            }
            for (int y = 0; y < grid.height; y++) {
                g.drawLine(0, y * size, grid.width * size, y * size);
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("grid render in: "+duration/1000000 );
    }

    private void setPrefDim(){
        Dimension dim = new Dimension(size*grid.getW(), size*grid.getH()+35 );
        this.setPreferredSize(dim);
        this.setMaximumSize(dim);
    }

    public void setSize(int size) {
        this.size = size;
        setPrefDim();
        repaint();
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
        setPrefDim();
        repaint();
    }

    public int getGridSize() {
        return size;
    }
}
