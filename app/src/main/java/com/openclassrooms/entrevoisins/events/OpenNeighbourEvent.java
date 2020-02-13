package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Created by Steve LEROY on 2019-11-05.
 */
public class OpenNeighbourEvent {

    /**
     * Neighbour to open
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public OpenNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
