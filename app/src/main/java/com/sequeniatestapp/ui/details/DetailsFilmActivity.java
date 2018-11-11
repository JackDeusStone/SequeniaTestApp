package com.sequeniatestapp.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFilmActivity extends AppCompatActivity implements DetailsFilmContract.View {

    @BindView(R.id.detailsFilmToolbar) Toolbar mToolbar;
    @BindView(R.id.detailsFilmIv) ImageView mIvAvatar;
    @BindView(R.id.detailsFilmTvName) TextView mTvName;
    @BindView(R.id.detailsFilmTvYears) TextView mTvYears;
    @BindView(R.id.detailsFilmTvRating) TextView mTvRating;
    @BindView(R.id.detailsFilmTvDescription) TextView mTvDescription;


    private DetailsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_film);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mPresenter = new DetailsPresenter(this);

        Intent intent = getIntent();
        if (intent.hasExtra("filmId")){
            int filmId = intent.getIntExtra("filmId", 0);
            mPresenter.loadDataByFilmId(filmId);
        }
    }

    @Override
    public void updateUI(Film film) {
        if (film != null){

            getSupportActionBar().setTitle(film.getLocalizedName());

            mTvName.setText(film.getName());
            mTvYears.setText(String.valueOf(film.getYear()));
            if (film.getRating() != null){
                mTvRating.setText(String.valueOf(film.getRating()));
            }else {
                mTvRating.setText("нет данных");
            }

            mTvDescription.setText(film.getDescription());

            Picasso.get()
                    .load(film.getImageUrl())
                    .into(mIvAvatar);
        }
    }
}
