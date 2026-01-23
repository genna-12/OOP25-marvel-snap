package com.marvelsnap.model;

import java.util.*;

public abstract class Location {

    protected String name;
    protected int capacity;
    protected List<Card> Player1;
    protected List<Card> Player2;

    public boolean addCard(int pIdx, Card c) {
        return false;
    }

    public boolean isFull(int pIdx) {
        return false;
    }

    public int calculatePower(int pIdx) {
        return 0;
    }

    public void applyEffect(Game game) {

    }
}
