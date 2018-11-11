package com.sequeniatestapp.ui.films.filmsAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmHolder> implements FilmHolder.FilmHolderListener {

    public interface FilmsAdapterListener{
        void moveToFilmById(int id);
    }

    private FilmsAdapterListener mFilmsAdapterListener;


    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_film, parent, false);
        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder filmsHolder, int position) {
        filmsHolder.init(position, mFilms.get(position), this);
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    private List<Film> mFilms;

    public FilmsAdapter(FilmsAdapterListener filmsAdapterListener){
        mFilmsAdapterListener = filmsAdapterListener;
        mFilms = new ArrayList<>();
    }

    public void refreshAdapter(List<Film> films){

        Collections.sort(films, (o1, o2) -> o2.getYear().compareTo(o1.getYear()));

        mFilms = films;
        notifyDataSetChanged();
    }

    @Override
    public void onFilmClick(int position) {
        if (mFilmsAdapterListener != null)
            mFilmsAdapterListener.moveToFilmById(mFilms.get(position).getId());
    }
}
