package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.ArrayList;
import java.util.List;


// DummyNeighbourApiService -----Récupère les infos depuis ----> DummyNeighbourGenerator
//|
//|----------> Fourni la liste des Neighbour à la demande

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours(); // renvoie un voisin


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);                               // supprime un voisin
    }

// Mets à jour le voisin
    public void updateNeighbour(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {           // On fait le tour de la liste
            Neighbour neighbourTemp = neighbours.get(i);    // On fait défiler la liste tant qu'on obtient pas le voisin attendu
            if (neighbour.equals(neighbourTemp) ){          // si le voisin orrespond à celui attendu alors on l'affiche
                neighbours.set(i, neighbour);
                break;
            }
        }
    }


    @Override
    public void addNeighbour(Neighbour neighbour) {neighbours.add(neighbour);}    // Ajouter un voisin

    @Override
    public void addFavorite(Neighbour neighbour) {          // Ajouter un voisin en favori
        neighbour.setFavorite(true);            // Lorsqu'on ajoute 1 voisin en favori
        updateNeighbour(neighbour);     // On met à jour le voisin
    }

    @Override
    public void removeFavorite(Neighbour neighbour) {
        neighbour.setFavorite(false);               // on retire le voisin des favoris
        updateNeighbour(neighbour);                 // on met à jour la liste
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        ArrayList<Neighbour> favoriteNeighbours= new ArrayList<>();  // Créer une nouvelle liste de favoris
        for (Neighbour neighbour:neighbours){
            if (neighbour.isFavorite()){                // Si le voisin est favori
                favoriteNeighbours.add(neighbour);      // on le met à jour en tant que favori
            }
        }
        return favoriteNeighbours;      // l'affiche en tant que favori
    }
}
