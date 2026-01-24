package com.marvelsnap.util;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.Card;

/* L'idea per questa classe è creare dei mazzi di carte predefiniti (DeckType) per evitare di dover implementare una sezione
   di costruzione del mazzo nell'applicazione, che complicherebbe notevolmente il progetto.
   Inoltre per permettere che ogni carta abbia il suo effetto specifico come su Marvel Snap, andrebbero implementati
   dei metodi specifici per ogni carta, prolly tramite l'uso di anonimous class o lambda expressions.
   
   Per il momento l'idea è questa per evitare di avere 200 classi, una per ogni carta del gioco ma se non dovessi 
   riuscire a implementare quanto scritto sopra, opterò per la soluzione delle 200 classi.
   */

public class CardFactory {
    public List<Card> createDeck(DeckType deckType) {
        return new ArrayList<>();
    }

    public Card createCard(String id) {
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }
}
