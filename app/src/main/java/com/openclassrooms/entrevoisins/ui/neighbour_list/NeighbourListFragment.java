package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;


public class NeighbourListFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    private boolean showFavoriteOnly;


    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourListFragment}
     */
    public static NeighbourListFragment newInstance(boolean showFavoriteOnly) {
        NeighbourListFragment fragment = new NeighbourListFragment();
        Bundle args = new Bundle();
        args.putBoolean("favorites", showFavoriteOnly);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        showFavoriteOnly = Objects.requireNonNull(getArguments()).getBoolean("favorites");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (showFavoriteOnly) {
            view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        }

        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;

    }

    /**
     * Init the List of neighbours
     * EventBus permet de surveiller le flux de données et de notifier les personnes inscrite à cet évènement d'une modification (ajout ou suppression par exemple) - writer ou lecteur
     */
    private void initList() {
        if (showFavoriteOnly) {
            mNeighbours = mApiService.getFavoriteNeighbours();
        } else {
            mNeighbours = mApiService.getNeighbours();
        }
        MyNeighbourRecyclerViewAdapter myNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours, showFavoriteOnly);
        mRecyclerView.setAdapter(myNeighbourRecyclerViewAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();

    }

    /**
     * Fired if the user clicks on a delete button
     * Envoi un évènement si un voisin est supprimé et remet à jour la liste de Neighbour
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }


}
