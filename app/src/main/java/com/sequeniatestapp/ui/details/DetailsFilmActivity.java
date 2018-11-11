package com.sequeniatestapp.ui.details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFilmActivity extends AppCompatActivity implements DetailsFilmContract.View {

    private static final String FILM_ID = "filmId";

    @BindView(R.id.detailsFilmToolbar) Toolbar mToolbar;
    @BindView(R.id.detailsFilmIv) ImageView mIvAvatar;
    @BindView(R.id.detailsFilmTvErrorAvatar) TextView mTvErrorAvatar;
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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new DetailsPresenter(this);

        Intent intent = getIntent();
        if (intent.hasExtra(FILM_ID)){
            int filmId = intent.getIntExtra(FILM_ID, 0);
            mPresenter.loadDataByFilmId(filmId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
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
                mTvRating.setText(getString(R.string.errorRating));
            }

            if (film.getDescription() == null || film.getDescription().isEmpty()){
                mTvDescription.setText(getString(R.string.errorDescription));
            }else {
                mTvDescription.setText(film.getDescription());
            }

            Picasso.get()
                    .load(film.getImageUrl())
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            mIvAvatar.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            mIvAvatar.setVisibility(View.GONE);
                            mTvErrorAvatar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        }
    }
}
