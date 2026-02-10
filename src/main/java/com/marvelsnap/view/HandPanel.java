package com.marvelsnap.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;


import com.marvelsnap.model.Card;
import com.marvelsnap.model.Hand;
import com.marvelsnap.controller.GameController;

public class HandPanel extends JPanel {
    private List<CardPanel> cardPanels = new ArrayList<>();

    public HandPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10 , 10));
        this.setBackground(new Color(50, 50, 100));
        this.setPreferredSize(new Dimension(800, 200));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                int width = (getWidth() - 80) / 7;
                int height = (getHeight() - 20);

                for(Component c : getComponents()){
                    c.setPreferredSize(new Dimension(width, height));
                }
                revalidate();
                repaint();
            }
        });
    }

    public void setHand(Hand hand, GameController controller) {
        for(Card card : hand.getCards()){
            CardPanel cp = new CardPanel();
            cp.setCard(card);

            cp.setOnClickAction(() -> controller.onCardClicked(card));
            this.add(cardPanels.get(cardPanels.size() - 1));
        }
        revalidate();
        repaint();
    }
}
