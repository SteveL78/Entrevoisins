package com.openclassrooms.entrevoisins.service;

import android.content.Intent;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);


    /**
     * Update neighbour
     */
    void updateNeighbour(Neighbour neighbour);


    /**
     * Add neighbour
     */
    void addNeighbour(Neighbour neighbour);



    List<Neighbour> getFavoriteNeighbours();

}

