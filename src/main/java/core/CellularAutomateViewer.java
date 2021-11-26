package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public abstract class CellularAutomateViewer<T> extends JPanel implements ActionListener, Runnable, MouseListener, MouseMotionListener{

    protected final JButton update;
    protected JCheckBox autoRun;
    protected JFormattedTextField speed;
    protected int sleepTime;

    protected Thread t;
    protected CellularAutomateGrid<T> grid;
    protected CellularAutomateGridViewer<T> gridDrawer;

    public CellularAutomateViewer(ICellularAutomateDrawer<T> drawer, CellularAutomateGrid<T> grid, int size){
        super();
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout());
        this.grid = grid;
        gridDrawer = new CellularAutomateGridViewer<>(grid, drawer, size, true);

        update = new JButton("update");
        panel.add(update, BorderLayout.SOUTH);
        update.addActionListener(this);

        autoRun = new JCheckBox("auto run",false);
        autoRun.addActionListener(this);
        panel.add(autoRun, BorderLayout.SOUTH);

        speed = new JFormattedTextField(NumberFormat.getInstance());
        speed.addActionListener(this);
        this.setPreferredSize( new Dimension(size*grid.getW(), size*grid.getH()+35 ));
        speed.setValue(100);
        sleepTime = 100;
        panel.add(speed, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.SOUTH);

        gridDrawer = new CellularAutomateGridViewer<>(grid, drawer, size, true);
        JScrollPane scrollPane = new JScrollPane(gridDrawer);
        this.add(scrollPane, BorderLayout.CENTER);

        gridDrawer.addMouseListener(this);
        gridDrawer.addMouseMotionListener(this);

    }

    public CellularAutomateViewer(ICellularAutomateDrawer<T> drawer, CellularAutomateGrid<T> grid,  int size, boolean showGrid) {
        this(drawer, grid, size);
        this.gridDrawer.setShowGrid(showGrid);
    }

    public void setSize(int size) {
        this.gridDrawer.setSize(size);
    }

    public int getGridSize(){
        return gridDrawer.getGridSize();
    }

    public void setShowGrid(boolean showGrid) {
        this.gridDrawer.setShowGrid(showGrid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource().equals(update)){
            grid.update();
            this.repaint();
        }else if(e.getSource().equals(autoRun) ){
            if(autoRun.isSelected()){
                update.setEnabled(false);
                t = new Thread(this);
                t.start();
            }else if(t != null) {
                try {
                    t.join();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                t = null;
                update.setEnabled(true);
            }
        }
    }

    @Override
    public void run() {
        while (autoRun.isSelected()){
            grid.update();
            this.repaint();
            try {
                String tmp = speed.getText();
                if(tmp.isEmpty()){
                    tmp ="1";
                }
                Thread.sleep( Integer.parseInt( tmp)  );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
