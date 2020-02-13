package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


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
        neighbours.remove(neighbour);
    }

    @Override
    public void updateNeighbour(Neighbour neighbour) {
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour neighbourTemp = neighbours.get(i);
            if (neighbour.equals(neighbourTemp) ){
                neighbours.set(i, neighbour);
                break;
            }

        }

    }


    @Override
    public void addNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        ArrayList<Neighbour> favoriteNeighbours= new ArrayList<>();
        for (Neighbour neighbour:neighbours){
            if (neighbour.isFavorite()){
                favoriteNeighbours.add(neighbour);
            }
        }
        return favoriteNeighbours;
    }
}
