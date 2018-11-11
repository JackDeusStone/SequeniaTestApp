package com.sequeniatestapp.ui.films.filmsAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmHolder extends RecyclerView.ViewHolder {

    public interface FilmHolderListener {
        void onFilmClick(int position);
    }

    @BindView(R.id.holderFileTvLocalizedName) TextView tvLocalizedName;
    @BindView(R.id.holderFileTvName) TextView tvName;
    @BindView(R.id.holderFileTvRating) TextView tvRating;

    private int mPosition;
    private FilmHolderListener mFilmHolderListener;

    public FilmHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void init(int position,Film film, FilmHolderListener filmHolderListener){

        mPosition = position;
        mFilmHolderListener = filmHolderListener;

        if (film != null){
            tvLocalizedName.setText(film.getLocalizedName());
            tvName.setText(film.getName());
            if (film.getRating() != null){
                tvRating.setText(String.valueOf(film.getRating()));
            }else {
                tvRating.setText("Нет данных");
            }
        }
    }

    @OnClick(R.id.holderFilmMcvRoot)
    public void clickFilm(){
        if (mFilmHolderListener != null){
            mFilmHolderListener.onFilmClick(mPosition);
        }
    }
}
