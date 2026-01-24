package com.marvelsnap.model;

public class TurnManager {
    private int currentTurn;
    private int currentPlayerIndex;
    private int maxTurns;

    public TurnManager() {
        this.maxTurns = 6;
        this.currentTurn = 1;
        this.currentPlayerIndex = 0;
    }

    public void nextTurn() {
        return;
    }

    public void switchPlayer() {
        return;
    }

    public boolean isTurnCycleComplete() {
        return false;
    }
    
    public int getCurrentPlayerIndex() {
        return 0;
    }

    public int getEnergyForTurn() {
        return currentTurn; // mi pare che n energia = n turno quindi ho gia messo return currentTurn ma controlla
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
