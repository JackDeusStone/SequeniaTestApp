package com.sequeniatestapp.ui.films.filmsAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.enitty.filmItem.DateFilmItem;
import com.sequeniatestapp.model.enitty.filmItem.FilmItem;
import com.sequeniatestapp.model.enitty.filmItem.GeneralFilmItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FilmHolder.FilmHolderListener {

    public interface FilmsAdapterListener{
        void moveToFilmById(int id);
    }

    private FilmsAdapterListener mFilmsAdapterListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case FilmItem.TYPE_DATE:
                View viewDate = inflater.inflate(R.layout.holder_date_film, parent, false);
                viewHolder = new DateFilmHolder(viewDate);
                break;
            case FilmItem.TYPE_GENERAL:
                View viewGeneral = inflater.inflate(R.layout.holder_film, parent, false);
                viewHolder = new FilmHolder(viewGeneral);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()){
            case FilmItem.TYPE_DATE:
                DateFilmHolder dateFilmHolder = (DateFilmHolder) viewHolder;
                dateFilmHolder.init(((DateFilmItem)mFilmItems.get(position)).getDate());
                break;
            case FilmItem.TYPE_GENERAL:
                FilmHolder filmHolder = (FilmHolder) viewHolder;
                filmHolder.init(position, ((GeneralFilmItem)mFilmItems.get(position)).getFilm(), this);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFilmItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mFilmItems.get(position).getType();
    }

    private List<FilmItem> mFilmItems;

    public FilmsAdapter(FilmsAdapterListener filmsAdapterListener){
        mFilmsAdapterListener = filmsAdapterListener;
        mFilmItems = new ArrayList<>();
    }

    public void refreshAdapter(List<Film> films){
        mFilmItems.clear();

        makeGroupList(films);
        notifyDataSetChanged();
    }

    @Override
    public void onFilmClick(int position) {
        if (mFilmsAdapterListener != null)
            mFilmsAdapterListener.moveToFilmById(((GeneralFilmItem)mFilmItems.get(position)).getFilm().getId());
    }

    private void makeGroupList(List<Film> films){

        LinkedHashMap<String, List<Film>> groupHashMap = makeGroupArrayMap(films);

        for (String date : groupHashMap.keySet()) {
            DateFilmItem dateItem = new DateFilmItem();
            dateItem.setDate(date);
            mFilmItems.add(dateItem);

            List<GeneralFilmItem> tmpList = new ArrayList<>();
            for (Film film : groupHashMap.get(date)) {
                GeneralFilmItem generalItem = new GeneralFilmItem();
                generalItem.setFilm(film);
                tmpList.add(generalItem);
            }
            Collections.sort(tmpList, (o1, o2) -> o2.getFilm().getRating().compareTo(o1.getFilm().getRating()));
            mFilmItems.addAll(tmpList);
        }
    }

    private LinkedHashMap<String, List<Film>> makeGroupArrayMap(List<Film> films){

        LinkedHashMap<String, List<Film>> groupHashMap = new LinkedHashMap<>();

        Collections.sort(films, (o1, o2) -> o1.getYear().compareTo(o2.getYear()));

        for (Film film : films){
            String hashMapKey = String.valueOf(film.getYear());
            if (groupHashMap.containsKey(hashMapKey)) {
                groupHashMap.get(hashMapKey).add(film);
            } else {
                List<Film> list = new ArrayList<>();
                list.add(film);
                groupHashMap.put(hashMapKey, list);
            }
        }

        return groupHashMap;
    }
}
