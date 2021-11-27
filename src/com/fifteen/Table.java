package com.fifteen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table{
    List<Button> buttons = new ArrayList();
    List<String> labels = new ArrayList();
    int blank;
    ActionListener listener;

    Table(JFrame w){
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Button b = (Button) e.getSource();
                int in = buttons.indexOf(b);
                switch (in-blank){
                    case Button.DOWN, Button.UP -> swapLabels(b);
                    case Button.LEFT ->{
                        if(in%4 != 0){
                            swapLabels(b);
                        }
                    }
                    case Button.RIGHT -> {
                        if(in%4 != 3){
                            swapLabels(b);
                        }
                    }
                }
                w.requestFocus();
            }
        };

        w.setLayout(new GridLayout(4,4));
        for(int i=0; i<15; i++){
            labels.add( Integer.toString(i+1));
        }
        labels.add("");
        Collections.shuffle(labels);

        blank = labels.indexOf("");

        for(int i=0; i<16; i++){
            Button b = new Button();
            b.setFont(new Font("Verdana", Font.BOLD, 25));
            buttons.add(b);
            w.add(b);
            b.addActionListener(listener);
            b.setText( labels.get(i) );
        }
        recolor();
    }

    void newGame(){
        Collections.shuffle(labels);
        for (int i=0; i<16; i++){
            buttons.get(i).setText( labels.get(i));
        }
        blank = labels.indexOf("");
        recolor();
    }
    void recolor(){
        for(int i=0; i<16; i++){
            String label = labels.get(i);
            Button b = buttons.get(i);

            if(!label.matches("")){
                if((i+1)==Integer.parseInt(label)){  //if position is right
                    b.setBackground(Button.CRIGHT);}
                else{ //position is wrong
                    b.setBackground(Button.WRONG);}
            }
            else {b.setBackground(Button.BLANK);}
        }
    }
    boolean upLimit(){
        return blank >= 12;
    }
    boolean downLimit(){
        return blank < 4;
    }
    boolean leftLimit(){
        return (blank%4 == 3);
    }
    boolean rightLimit(){
        return (blank%4 == 0);
    }
    void swapUp(){
        if(!upLimit())
            swapLabels(buttons.get(blank + Button.UP));
    }
    void swapDown(){
        if(!downLimit())
            swapLabels(buttons.get(blank + Button.DOWN));
    }
    void swapLeft(){
        if(!leftLimit())
            swapLabels(buttons.get(blank + Button.LEFT));
    }
    void swapRight(){
        if(!rightLimit())
            swapLabels(buttons.get(blank + Button.RIGHT));
    }
    private void swapLabels(Button b){
        int in = buttons.indexOf(b);
        buttons.get(blank).setText( b.getText());
        b.setText("");
        Collections.swap(labels, in, blank);
        blank = in;

        recolor();
    }
}
