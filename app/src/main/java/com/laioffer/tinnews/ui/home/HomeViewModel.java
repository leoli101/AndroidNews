package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    // decouple to the following two functions
    public void setCountryInput(String country) { // user input country
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        // if there is output from "countryInput", trigger the getTopHeadlines() with the given input
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    public void setFavoriteArticleInput(Article article) {
        // we don't need to observe() to display on UI
        repository.favoriteArticle(article);
    }
}
