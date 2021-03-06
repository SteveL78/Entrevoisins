package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }


    // Supprimer un utilisateur
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour favoriteNeighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(favoriteNeighbourToDelete);
        assertFalse(service.getNeighbours().contains(favoriteNeighbourToDelete));
    }


    // Ajouter un utilisateur

    @Test
    public void addNeighbourWithSuccess() {
        Neighbour favoriteNeighbourToAdd = new Neighbour(128, "Chakir", "http://monavatar.com", "0612345678", "Montpellier", "developpeur Android", "http://facebook.com");
        service.addNeighbour(favoriteNeighbourToAdd);
        assertTrue(service.getNeighbours().contains(favoriteNeighbourToAdd));
    }


    // Ajouter un favori et vérifier s'il a bien été ajouté à la liste des favoris

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        Neighbour favoriteNeighbourToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteNeighbourToAdd);
        assertTrue(service.getFavoriteNeighbours().contains(favoriteNeighbourToAdd));
    }


    @Test
    public void getFavoriteNeighboursWithSuccess() {
        Neighbour favoriteNeighbourToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteNeighbourToAdd);
        List<Neighbour> favoritesNeighbours = service.getFavoriteNeighbours();
        assertTrue(favoritesNeighbours.size() == 1);
    }


    // Supprimer un favori et vérifier s'il a bien été supprimé de la liste
    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        Neighbour favoriteNeighbourToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteNeighbourToAdd);
        Neighbour favoriteNeighbourToDelete = service.getFavoriteNeighbours().get(0);
        service.removeFavorite(favoriteNeighbourToDelete);
        assertFalse(service.getFavoriteNeighbours().contains(favoriteNeighbourToDelete));
    }


}
