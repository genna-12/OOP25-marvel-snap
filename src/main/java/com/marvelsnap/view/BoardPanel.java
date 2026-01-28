package com.marvelsnap.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.List;
import com.marvelsnap.model.Location;

public class BoardPanel extends JPanel {

    private List<LocationPanel> locationPanels;

    public BoardPanel() {
        // finche non implementate la classe scrivo il seguente codice di debug
        setBackground(new Color(100, 50, 50));
        setPreferredSize(new Dimension(800, 400));
        add(new JLabel("AREA BOARD"));
    }

    public void refresh(List<Location> locations) {

    }
}