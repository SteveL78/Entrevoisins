package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.OpenNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/*Permet de faire la liaison (Bind) entre la vue RecyclerView et la liste de données
 * L'adapter permet de contenir l'ensemble des données à afficher dans le RecyclerView
 * la classe MyNeighbourRecyclerViewAdapter étend la classe RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>. Elle la redéfinie */
public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    // POUR LES DONNEES
    private List<Neighbour> mNeighbours;
    private boolean showFavoriteOnly;

    // CONSTRUCTOR
    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, boolean showFavoriteOnly) {
        mNeighbours = items;
        this.showFavoriteOnly = showFavoriteOnly;
    }

    /* OnCreateViewHolder permet de créer un ViewHolder à partir du layout xml représentant chaque ligne de la RecyclerView
     * Elle sera appelée pour les 1ères lignes visibles de la RecyclerView */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_neighbour, parent, false);
        return new ViewHolder(view);
    }

    /* La fonction OnBindViewHolder permet de générer une cellule à partir d'un modèle
     * On y met à jour leur apparence (to bind = lier)*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);            // Dans la liste de voisins on récupère un voisin à la position qui est donnée
        holder.mNeighbourName.setText(neighbour.getName());         // On affiche le nom du voisin à cette position à l'aide du .xml qui s'appelle neigbhourName.
        Glide.with(holder.mNeighbourAvatar.getContext())        // On affiche une image d'une bibliothèque
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        
        // Quand je clique sur toute la vue voilà ce qu'il se passe
        holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.mDeleteButton.getContext(), "click", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new OpenNeighbourEvent(neighbour)); // ouvre le voisin
            }
        });


        /*
         *  ------- BOUTON DELETE ---------
         * On
         * Quand on clique sur le bouton delete on diffuse un évènement précisant la suppression*/
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showFavoriteOnly)
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour)); // EventBus est comme un bus dans lequel on peut mettre plein de choses avec d'un côté les lecteurs et les receveurs qui seront notifiés, s'ils s'y inscrivent, en cas de modification (ajout ou suppression)

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }


    // On relie les informations à afficher
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)  // l'avatar
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)  // le nom du voisin
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button) // le bouton delete
        public ImageButton mDeleteButton;
        @BindView(R.id.item_list_parent)
        public ConstraintLayout mParentView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            if (showFavoriteOnly)
                mDeleteButton.setImageResource(R.drawable.ic_star_yellow_24dp);
        }


    }
}
