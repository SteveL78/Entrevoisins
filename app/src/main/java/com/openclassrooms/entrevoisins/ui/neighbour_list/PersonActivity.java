package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

public class PersonActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_favorite_star);

        ImageView profilPicture = findViewById(R.id.profil_img);
        TextView nameProfil = findViewById(R.id.name_profil);
        TextView myName = findViewById(R.id.my_name);
        TextView cityname = findViewById(R.id.city_name_txt);
        TextView phoneNumber1 = findViewById(R.id.phone_number_txt);
        TextView facebookEmail = findViewById(R.id.fb_link_txt);
        TextView description = findViewById(R.id.description_txt);


        mApiService = DI.getNeighbourApiService();


        if (getIntent().getExtras() != null) {
            Neighbour neighbour = (Neighbour) getIntent().getExtras().getSerializable("neighbour");

            if(neighbour !=null) {
                nameProfil.setText(neighbour.getName());
                Glide.with(this)
                        .load(neighbour.getAvatarUrl())
                        .into(profilPicture);

                myName.setText(neighbour.getName());

                phoneNumber1.setText(neighbour.getTelephone());

                cityname.setText(neighbour.getCity());

                description.setText(neighbour.getAbout());

                facebookEmail.setText(neighbour.getFbAdress());


                if (neighbour.isFavorite())
                    floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
                else
                    floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));


                floatingActionButton.setOnClickListener(view -> {
                    if (neighbour.isFavorite()) {
                        mApiService.removeFavorite(neighbour);
                        floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));

                    } else {
                        mApiService.addFavorite(neighbour);
                        floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
