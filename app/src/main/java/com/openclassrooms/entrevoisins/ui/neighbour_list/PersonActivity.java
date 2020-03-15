package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class PersonActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;

    private ImageView mBack;

    private ImageView mProfilPicture;
    private TextView mNameProfil;
    private FloatingActionButton mFavoriteStar;
    private TextView mMyName;
    private ImageView mCityIcon;
    private TextView mCityname;
    private ImageView mPhonePicture;
    private TextView mPhoneNumber;
    private ImageView mWorldMap;
    private TextView mFacebookEmail;
    private TextView mAboutMe;
    private TextView mDescription;

    private Integer id;
    private String name;
    private String avatarUrl;
    private String phoneNumber;
    private String cityName;
    private String about;
    private Neighbour mNeighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_favorite_star);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mProfilPicture = (ImageView) findViewById(R.id.profil_img);
        mNameProfil = (TextView) findViewById(R.id.name_profil);
        mFavoriteStar = (FloatingActionButton) findViewById(R.id.add_favorite_star);
        mMyName = (TextView) findViewById(R.id.my_name);
        mCityIcon = (ImageView) findViewById(R.id.city_img);
        mCityname = (TextView) findViewById(R.id.city_name_txt);
        mPhonePicture = (ImageView) findViewById(R.id.phone_img);
        mPhoneNumber = (TextView) findViewById(R.id.phone_number_txt);
        mWorldMap = (ImageView) findViewById(R.id.world_map_img);
        mFacebookEmail = (TextView) findViewById(R.id.fb_link_txt);
        mAboutMe = (TextView) findViewById(R.id.about_me_txt);
        mDescription = (TextView) findViewById(R.id.description_txt);
        mFacebookEmail = (TextView) findViewById(R.id.fb_link_txt);


        if (getIntent().getExtras() != null) {
            /*id=getIntent().getExtras().getInt("id");
            name = getIntent().getExtras().getString("name");
            avatarUrl = getIntent().getExtras().getString("avatar");*/
            mNeighbour = (Neighbour) getIntent().getExtras().getSerializable("neighbour");

            mNameProfil.setText(mNeighbour.getName());
            Glide.with(this)
                    .load(mNeighbour.getAvatarUrl())
                    .into(mProfilPicture);

            mMyName.setText(mNeighbour.getName());

            phoneNumber = getIntent().getExtras().getString("phone");
            mPhoneNumber.setText(mNeighbour.getTelephone());

            cityName = getIntent().getExtras().getString("city");
            mCityname.setText(mNeighbour.getCity());

            about = getIntent().getExtras().getString("about");
            mDescription.setText(mNeighbour.getAbout());

            avatarUrl = getIntent().getExtras().getString("fbAdress");
            mFacebookEmail.setText(mNeighbour.getFbAdress());

        }


        mApiService = DI.getNeighbourApiService();


        if (mNeighbour.isFavorite())
            mFavoriteStar.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
        else
            mFavoriteStar.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));


        mFavoriteStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNeighbour.isFavorite()) {
                    mApiService.removeFavorite(mNeighbour);
                    mFavoriteStar.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));

                } else {
                    mApiService.addFavorite(mNeighbour);
                    mFavoriteStar.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
