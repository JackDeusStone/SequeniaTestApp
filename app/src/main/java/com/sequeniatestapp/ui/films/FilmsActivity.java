package com.sequeniatestapp.ui.films;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sequeniatestapp.R;
import com.sequeniatestapp.model.enitty.Film;
import com.sequeniatestapp.model.modelEnum.RequestStatus;
import com.sequeniatestapp.ui.details.DetailsFilmActivity;
import com.sequeniatestapp.ui.films.filmsAdapter.FilmsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmsActivity extends AppCompatActivity implements FilmsContract.View, FilmsAdapter.FilmsAdapterListener {

    @BindView(R.id.filmsToolbar) Toolbar mToolbar;
    @BindView(R.id.filmsPb) ProgressBar mProgressBar;
    @BindView(R.id.filmsRv) RecyclerView mRecyclerView;

    private FilmsPresenter mPresenter;
    private FilmsAdapter mFilmsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        ButterKnife.bind(this);

        mPresenter = new FilmsPresenter(this);
        mFilmsAdapter = new FilmsAdapter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mFilmsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.fetchFilms();
    }

    @Override
    public void onProgressBarStatus(RequestStatus requestStatus) {
        if (requestStatus == RequestStatus.START_DOWNLOAD){
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void makeErrorToast() {
        Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshLIst(List<Film> films) {
        mFilmsAdapter.refreshAdapter(films);
    }

    @Override
    public void moveToFilmById(int id) {
        Intent intent = new Intent(this, DetailsFilmActivity.class);
        intent.putExtra("filmId", id);
        startActivity(intent);
    }
}
