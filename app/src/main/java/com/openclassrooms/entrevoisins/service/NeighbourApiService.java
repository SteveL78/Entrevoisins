package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();   // renvoie neighbour

    /**
     * Deletes a neighbour
     *
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);  // supprime le neighbour


    /**
     * Add neighbour
     */
    void addNeighbour(Neighbour neighbour);     // ajoute un neigbour


    /**
     * Add to favorite
     */
    void addFavorite(Neighbour neighbour);     // ajoute un neighbour aux favoris


    /**
     * Remove for favorite
     */
    void removeFavorite(Neighbour neighbour);          // supprime un neighbour des favoris


    List<Neighbour> getFavoriteNeighbours();        // renvoie et met à jour la liste de neigbours favoris

}

