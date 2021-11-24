import core.CellularAutomateViewer;
import gameoflife.GameOfLifeGrid;
import gameoflife.GameOfLifeViewer;
import wireworld.WireWorldGrid;
import wireworld.WireWorldState;
import wireworld.WireWorldViewer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

public class Main extends JFrame{

    public static void main( String[] args){
       /* JFrame frame = new JFrame("Jeux de la vie");
        frame.setResizable(true);

        int height = 20;
        int width = 40;
        int nb = 5;
        Random rand = new Random();*/

       /* Boolean[][] tab = new Boolean[height][width];
        for(int y = 0; y<height; y++){
            for (int x = 0; x<width; x++){
                tab[y][x] = Boolean.FALSE;
            }
        }

        for(int i = 0; i < nb; i++){
            tab[rand.nextInt(height)][rand.nextInt(width)] = Boolean.TRUE;
        }

        GameOfLifeGrid grid = new GameOfLifeGrid(tab);
        CellularAutomateViewer<?> game = new GameOfLifeViewer(grid, 50);*/

        /*ForestFireCell[][] tab = new ForestFireCell[height][width];
        for(int y = 0; y<height; y++){
            for (int x = 0; x<width; x++){
                if(rand.nextBoolean()){
                    tab[y][x] = ForestFireCell.FOREST;
                }else{
                    tab[y][x] = ForestFireCell.NONE;
                }
            }
        }

        ForestFireGrid grid = new ForestFireGrid(tab);
        CellularAutomateViewer<?> game = new ForestFireViewer(grid, 25);*/

        /*int max = 3;
        WireWorldState[][] tab = new WireWorldState[height][width];
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                tab[y][x] = WireWorldState.VOID;
            }
        }
        WireWorldGrid grid = new WireWorldGrid(tab);
        CellularAutomateViewer<?> game = new WireWorldViewer(grid, 50);

        frame.add( game, BorderLayout.CENTER);
        frame.pack();*/
        Main m = new Main();
    }

    private CellularAutomateViewer<?> currentGame;

    public Main() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new BorderLayout() );

        JPanel gameControl = new JPanel();
        gameControl.setLayout(new GridLayout(0, 2, 10, 10));

        JComboBox<String> gameCombo = new JComboBox<>();
        gameControl.add(new JLabel("select game:"));
        gameControl.add(gameCombo);

        JFormattedTextField widthField = new JFormattedTextField( new NumberFormatter( NumberFormat.getIntegerInstance()));
        widthField.setPreferredSize(new Dimension(200, 50));
        gameControl.add(new JLabel("grid width:"));
        gameControl.add(widthField);

        JFormattedTextField heightField = new JFormattedTextField( new NumberFormatter( NumberFormat.getIntegerInstance()));
        gameControl.add(new JLabel("grid height:"));
        gameControl.add(heightField);

        JSlider scale = new JSlider(JSlider.HORIZONTAL, 1, 50, 10);
        scale.addChangeListener(e -> {
            if(currentGame != null){
                currentGame.setSize( scale.getValue());
                pack();
            }
        });
        gameControl.add(new JLabel("grid cell size:"));
        gameControl.add(scale);

        JCheckBox showGrid = new JCheckBox("show grid");
        showGrid.setSelected(true);
        showGrid.addActionListener(e -> {
            if(currentGame != null){
                currentGame.setShowGrid(showGrid.isSelected());
            }
        });
        gameControl.add(showGrid);

        JButton generate = new JButton("change mode");
        generate.addActionListener(e -> {
            int width = getFormatNumber(widthField);
            if(width <= 0){
                return;
            }

            int height = getFormatNumber(heightField);
            if(height <= 0){
                return;
            }
            GameOfLifeGrid grid = new GameOfLifeGrid(width, height);
            GameOfLifeViewer game = new GameOfLifeViewer(grid, scale.getValue());
            game.setShowGrid(showGrid.isSelected());

            if(currentGame != null)
                remove(currentGame);

            add(game, BorderLayout.CENTER);
            currentGame = game;
            pack();
        });

        gameControl.add(generate);

        JPanel pan = new JPanel();
        pan.setLayout( new FlowLayout());
        pan.add(gameControl);
        this.add(pan, BorderLayout.WEST);

        GameOfLifeGrid grid = new GameOfLifeGrid(20, 20);
        currentGame = new GameOfLifeViewer(grid, 25);
        this.add(currentGame, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private int getFormatNumber(JFormattedTextField field){
        if(field.getValue() != null){
            return (int) (long) field.getValue();
        }else{
            return -1;
        }
    }
}
