package com.marvelsnap.view;

import javax.swing.*;
import javax.swing.border.Border;

import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.GameObserver;
import com.marvelsnap.model.Player;
import com.marvelsnap.util.Constants;

import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {

    private IntermissionPanel intermissionPanel;
    private BoardPanel boardPanel;
    private HandPanel handPanel;
    private CardLayout cardLayout;
    private JPanel infoPanel;
    private GameController controller;
    private JPanel activeGameContainer;
    private String p1Name = "Player 1";
    private String p2Name = "Player 2";
    private JLabel lblTurnInfo;
    private JLabel lblEnergyInfo;
    private JLabel lblPlayerName;

    public GamePanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        intermissionPanel = new IntermissionPanel();
        boardPanel = new BoardPanel();
        handPanel = new HandPanel();
        createInfoPanel();

        activeGameContainer = new JPanel(new BorderLayout());
        activeGameContainer.add(infoPanel, BorderLayout.NORTH);
        activeGameContainer.add(boardPanel, BorderLayout.CENTER);
        activeGameContainer.add(handPanel, BorderLayout.SOUTH);

        add(activeGameContainer, "Board");
        add(intermissionPanel, "Intermission");

        // debug
        JButton btnDebugTurn = new JButton("[DEBUG] Simula Fine Turno");
        btnDebugTurn.setBackground(Color.RED);
        btnDebugTurn.setForeground(Color.WHITE);

        btnDebugTurn.addActionListener(e -> {
            // Simulo che il Player 1 abbia finito e tocchi al Player 2
            onTurnChanged(1); // 1 = Indice del Player 2
        });

        activeGameContainer.add(btnDebugTurn, BorderLayout.WEST);
    }

    // Aggiungere metodo all'UML
    public void setController(GameController controller) {
        this.controller = controller;
    }

    // Aggiungere metodo all'UML
    private void createInfoPanel() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setPreferredSize(new Dimension(0, 50));
        Border lineaGrigia = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border spazioInterno = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(lineaGrigia, spazioInterno));

        // Sinistra
        lblTurnInfo = new JLabel("TURNO: --/" + Constants.MAX_TURNS);
        lblTurnInfo.setForeground(Color.WHITE);
        lblTurnInfo.setFont(new Font("Arial", Font.BOLD, 16));

        // Centro
        lblPlayerName = new JLabel("Caricamento...");
        lblPlayerName.setForeground(Color.ORANGE);
        lblPlayerName.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);

        // Destra
        lblEnergyInfo = new JLabel("ENERGIA: --");
        lblEnergyInfo.setForeground(Color.CYAN);
        lblEnergyInfo.setFont(new Font("Arial", Font.BOLD, 16));

        infoPanel.add(lblTurnInfo, BorderLayout.WEST);
        infoPanel.add(lblPlayerName, BorderLayout.CENTER);
        infoPanel.add(lblEnergyInfo, BorderLayout.EAST);
    }

    // Aggiungere metodo all'UML
    public void setPlayerNames(String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }

    public void updateView(Game game) {
        if (game == null || game.getTurnManager() == null)
            return;
        int turn = game.getTurnManager().getCurrentTurn();
        int playerIdx = game.getTurnManager().getCurrentPlayerIndex();
        Player currentPlayer = game.getPlayer(playerIdx);
        String name = (playerIdx == 0) ? p1Name : p2Name;
        // non passo currentPlayer.getCurrentEnergy() per evitare null pointer exception
        // se p3 è indietro
        int energy = (currentPlayer != null) ? currentPlayer.getCurrentEnergy() : 0;

        // aggiorno
        // infopanel
        lblTurnInfo.setText("TURNO " + turn + "/" + Constants.MAX_TURNS);
        lblPlayerName.setText(name.toUpperCase());
        lblEnergyInfo.setText("ENERGIA: " + energy);
        // boardpanel
        if (boardPanel != null) {
            if (game.getLocations() != null) { // questa condizione non dovrebbe mai verificarsi ma serve per debug se
                                               // altri non hanno ancora finito
                boardPanel.refresh(game.getLocations());
            }
        }
        // handpanel
        if (handPanel != null) {
            // Mostra SOLO la mano del giocatore corrente (aggiungo if per debug per evitare
            // nullpointer)
            if (currentPlayer != null && currentPlayer.getHand() != null) {
                handPanel.setHand(currentPlayer.getHand());
            }
        }

        revalidate();
        repaint();
    }

    public void onReadyToPlay() {
        showBoard();
    }

    public void showBoard() {
        cardLayout.show(this, "Board");
    }

    public void showIntermission() {
        cardLayout.show(this, "Intermission");
    }

    public void showEndGame(String message) {
        JOptionPane.showMessageDialog(this, "Il vincitore è: " + message);
    }

    @Override
    public void onGameUpdated() {
        repaint();
    }

    @Override
    public void onTurnChanged(int playerIndex) {
        String nextName = (playerIndex == 0) ? p1Name : p2Name;
        intermissionPanel.setNextPlayerName(nextName);
        showIntermission();
    }

    @Override
    public void onGameOver(String winnerName) {
        showEndGame("Il vincitore è: " + winnerName);
    }

    public IntermissionPanel getIntermissionPanel() {
        return intermissionPanel;
    }

    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }
}