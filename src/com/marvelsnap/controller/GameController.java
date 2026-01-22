package com.marvelsnap.controller;

import com.marvelsnap.model.*;
import com.marvelsnap.view.*;
public class GameController {
    private Game game = new Game();
    private GamePanel view = new GamePanel();
    private InputState inputState;
    private Card selectedCard = new Card();

    public void onCardClicked(final Card card) {
        return;
    }

    public void onLocationClicked(final int locIdx) {
        return;
    }

    public void onEndTurnClicked() {
        return;
    }

    public void onIntermissionReadyClicked() {
        return;
    }
}