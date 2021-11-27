package com.fifteen;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow implements KeyListener {
    Table table;
    MainWindow() {
        JFrame w = new JFrame();
        w.setSize(600, 600);
        w.setLocationRelativeTo(null);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table = new Table(w);

        ActionListener listener = e -> {
            String command = e.getActionCommand();
            switch (command){
                case "newGame" -> table.newGame();
                case "closeGame" -> w.dispose();
                default -> throw new IllegalStateException("Unexpected value: " + command);
            }

        };

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem closeGame = new JMenuItem("Close");
        file.add(newGame);
        file.add(closeGame);

        newGame.addActionListener(listener);
        closeGame.addActionListener(listener);

        newGame.setActionCommand("newGame");
        closeGame.setActionCommand("closeGame");

        menuBar.add(file);
        w.setJMenuBar(menuBar);

        w.addKeyListener(this);
        w.setFocusable(true);
        w.setVisible(true);
    }
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> {
                if(e.isControlDown()){
                    while (!table.upLimit())
                        table.swapUp();
                }
                else{
                    table.swapUp();}
            }
            case KeyEvent.VK_DOWN -> {
                if(e.isControlDown()){
                while (!table.downLimit())
                    table.swapDown();
            }
            else{
                table.swapDown();}
            }
            case KeyEvent.VK_LEFT -> {
                if(e.isControlDown()){
                    while (!table.leftLimit())
                        table.swapLeft();
                }
                else{
                    table.swapLeft();}
            }
            case KeyEvent.VK_RIGHT -> {
                if(e.isControlDown()){
                    while (!table.rightLimit())
                        table.swapRight();
                }
                else{
                    table.swapRight();}
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
