package com.marvelsnap.util;

import java.util.*;

import com.marvelsnap.model.Game;
import com.marvelsnap.model.Location;
import com.marvelsnap.model.NormalLocation;
import com.marvelsnap.model.ReducedCostLocation;

public class LocationFactory {

    private List<Location> locations;

    public List<Location> createLocations() {

        this.locations = new ArrayList<>();
        
        this.locations.add(new NormalLocation("Nidavellir", "Aggiunge +5 di forza alle carte presenti qui", 5, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new NormalLocation("Necrosha", "Rimuove -2 di forza alle carte presenti qui", -2, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new NormalLocation("Xandar", "Aggiunge +1 di forza alle carte presenti qui", 1, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new NormalLocation("Metropoli dei Mostri", "Aggiunge +3 forza alle carte di costo 2 presenti qui", 3, List.of(2)));

        this.locations.add(new NormalLocation("Zona Negativa", "Rimuove -3 di forza alle carte presenti qui", -3, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new NormalLocation("Rete Fognaria", "Rimuove -1 di forza alle carte presenti qui", -1, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new NormalLocation("Lago Hellas", "Aggiunge +2 di forza alle carte di costo 1 presenti qui", 2, List.of(1)));

        this.locations.add(new ReducedCostLocation("Campi Elisi", "Riduce di 1 il costo delle carte presenti in mano", 1, List.of(1, 2, 3, 4, 5, 6)));

        this.locations.add(new ReducedCostLocation("Dimensione dei sogni", "Aumenta di 1 il costo delle carte presenti in mano", -1, List.of(0, 1, 2, 3, 4, 5, 6)));

        this.locations.add(new ReducedCostLocation("Titano", "Riduce di 1 il costo delle carte di costo 6 presenti in mano", 1, List.of(6)));

        this.locations.add(new ReducedCostLocation("Utopia", "Riduce di 1 il costo delle carte di costo 3 e 4 presenti in mano", 1, List.of(3, 4)));

        this.locations.add(new Location("Il Trono Spaziale", "Solo una carta può essere posizionata qui") {
            protected void applyEffect(Game game) {
                this.capacity = 1;   
            }
        });

        this.locations.add(new Location("Sanctum Sanctorum", "Nessun giocatore può giocare carte qui") {
            protected void applyEffect(Game game) {
                this.capacity = 0;
            }
        });

        this.locations.add(new Location("Progetto Pegasus", "+5 energia durante questo turno") {
            protected void applyEffect(Game game) {
                game.getPlayer1().resetEnergy(game.getPlayer1().getCurrentEnergy() + 5);
                game.getPlayer2().resetEnergy(game.getPlayer2().getCurrentEnergy() + 5);
            }
        });

        this.locations.add(new Location("Officina del Riparatore", "+1 energia durante questo turno") {
            protected void applyEffect(Game game) {
                game.getPlayer1().resetEnergy(game.getPlayer1().getCurrentEnergy() + 1);
                game.getPlayer2().resetEnergy(game.getPlayer2().getCurrentEnergy() + 1);
            }
        });

        this.locations.add(new Location("Nova Roma", "Pesca una carta") {
            protected void applyEffect(Game game) {
                game.getPlayer1().drawCard();
                game.getPlayer2().drawCard();
            }
        });

        this.locations.add(new Location("Olympia", "Pesca due carte") {
            protected void applyEffect(Game game) {
                for (int i = 0; i < 2; i++) {
                    game.getPlayer1().drawCard();
                    game.getPlayer2().drawCard();
                }
            }
        });

        // debug
        List<Location> threeLocations = new ArrayList<>();
        threeLocations.add(this.locations.getFirst());
        threeLocations.add(this.locations.getLast());
        threeLocations.add(this.locations.get(5));
        return threeLocations;
    }
}
