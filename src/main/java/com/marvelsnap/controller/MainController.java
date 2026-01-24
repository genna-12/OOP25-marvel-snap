package com.marvelsnap.controller;

import java.awt.event.ActionEvent;

import com.marvelsnap.model.Game;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private GameController gameController;
    private Game game;

    public MainController() {
        this.mainFrame = new MainFrame();
        initListeners();
    }

    private void initListeners() {
        mainFrame.getMenuPanel().setStartAction((ActionEvent e) -> {
            mainFrame.showScreen("SETUP");
        });

        mainFrame.getMenuPanel().setExitAction((ActionEvent e) -> {
            System.exit(0);
        });

        // setup panel (sto piangendo)
        mainFrame.getSetupPanel().setPlayAction((ActionEvent e) -> {
            String p1Name = mainFrame.getSetupPanel().getP1Name();
            DeckType p1Deck = mainFrame.getSetupPanel().getP1DeckType();
            String p2Name = mainFrame.getSetupPanel().getP2Name();
            DeckType p2Deck = mainFrame.getSetupPanel().getP2DeckType();
            
            // controllo sui nomi
            if (p1Name.trim().isEmpty() || p2Name.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(mainFrame, 
                    "Inserisci i nomi di entrambi i giocatori!", 
                    "Errore Setup", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Se la và alloea parte
            onSetupConfirmed(p1Name, p1Deck, p2Name, p2Deck);
        });
    }
    

    public void startApp() {
        mainFrame.setVisible(true);
        mainFrame.showScreen("MENU");
    }

    public void onSetupConfirmed(String p1Name, DeckType d1, String p2Name, DeckType d2) {
        System.out.println("Avvio partita tra: " + p1Name + " e " + p2Name);
        // passa a gamecontroller
    }
}
