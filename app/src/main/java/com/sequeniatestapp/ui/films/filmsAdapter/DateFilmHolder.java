package com.sequeniatestapp.ui.films.filmsAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sequeniatestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateFilmHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.holderDateFilmTvDate) TextView mTvDate;

    public DateFilmHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(String date){
        mTvDate.setText(date);
    }
}
