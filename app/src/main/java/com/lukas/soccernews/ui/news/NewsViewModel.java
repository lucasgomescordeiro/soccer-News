package com.lukas.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lukas.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news;

    public NewsViewModel() {
       this.news = new MutableLiveData<>();
        List<News> news = new ArrayList<>();

        //TODO remover mock
        news.add(new News("Ferroviaria sofre desfalque neste domingo","lorem ipsun dolor lorem ipsun dolor lorem ipsun dolor"));
        news.add(new News("15 de pira vence mais uma","lorem ipsun dolor lorem ipsun dolor lorem ipsun dolor"));
        news.add(new News("Campeonato terá pausa em agosto","lorem ipsun dolor lorem ipsun dolor lorem ipsun dolor lorem ipsun dolor"));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}