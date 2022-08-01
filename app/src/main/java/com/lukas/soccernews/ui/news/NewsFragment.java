package com.lukas.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lukas.soccernews.MainActivity;
import com.lukas.soccernews.data.dataLocal.AppDatabase;
import com.lukas.soccernews.databinding.FragmentNewsBinding;
import com.lukas.soccernews.ui.adapter.NewsAdapter;


public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private FragmentNewsBinding binding;
    private  AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.rvnews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvnews.setAdapter(new NewsAdapter(news, updatedNews ->{
                db.newsDao().save(updatedNews);


            }));

        });


        binding.rvnews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->  {
            binding.rvnews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().save(updatedNews);
                }


            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state ->  {
            switch (state){


                case DOING:
                    //TODO incluir swipeRefreshlayout
                    break;

                case DONE:
                    //TODO Finalizar swipeRefreshlayout
                    break;

                case ERROR:
                    //TODO Finalizar swipeRefreshlayout
                    //TODO mostra erro
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}