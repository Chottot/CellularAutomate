import core.CellularAutomateGridViewer;
import core.CellularAutomateViewer;
import forestfire.ForestFireGrid;
import forestfire.ForestFireViewer;
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
        Main m = new Main();
    }

    private static final String gameOfLifeStr = "Game Of Life";
    private static final String forestFireStr = "Forest Fire";
    private static final String wireWorldStr = "Wire World";

    private CellularAutomateViewer<?> currentGame;


    public Main() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new BorderLayout() );

        JPanel gameControl = new JPanel();
        gameControl.setLayout(new GridLayout(0, 2, 10, 10));

        JComboBox<String> gameCombo = new JComboBox<>();
        gameCombo.addItem(Main.gameOfLifeStr);
        gameCombo.addItem(Main.forestFireStr);
        gameCombo.addItem(Main.wireWorldStr);
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

            if(gameCombo.getSelectedItem().equals(Main.gameOfLifeStr)){
                GameOfLifeGrid grid = new GameOfLifeGrid(width, height);
                GameOfLifeViewer game = new GameOfLifeViewer(grid, scale.getValue());

                game.setShowGrid(showGrid.isSelected());

                if(currentGame != null)
                    remove(currentGame);

                add(game, BorderLayout.CENTER);
                currentGame = game;

            }else  if(gameCombo.getSelectedItem().equals(Main.wireWorldStr)) {
                WireWorldGrid grid = new WireWorldGrid(width, height);
                WireWorldViewer game = new WireWorldViewer(grid, scale.getValue());

                game.setShowGrid(showGrid.isSelected());

                if(currentGame != null)
                    remove(currentGame);

                add(game, BorderLayout.CENTER);
                currentGame = game;

            }else if(gameCombo.getSelectedItem().equals(Main.forestFireStr)){
                ForestFireGrid grid = new ForestFireGrid(width, height);
                ForestFireViewer game = new ForestFireViewer(grid, scale.getValue());

                game.setShowGrid(showGrid.isSelected());

                if(currentGame != null)
                    remove(currentGame);

                add(game, BorderLayout.CENTER);
                currentGame = game;
            }

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
