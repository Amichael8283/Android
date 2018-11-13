package org.michaelbel.moviemade.modules_beta.company;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.michaelbel.moviemade.BuildConfig;
import org.michaelbel.moviemade.ConstantsKt;
import org.michaelbel.moviemade.annotation.EmptyViewMode;
import org.michaelbel.moviemade.extensions.AndroidExtensions;
import org.michaelbel.moviemade.model.MovieRealm;
import org.michaelbel.moviemade.ui.modules.main.ResultsMvp;
import org.michaelbel.moviemade.rest.ApiFactory;
import org.michaelbel.moviemade.data.TmdbObject;
import org.michaelbel.moviemade.rest.api.service.COMPANIES;
import org.michaelbel.moviemade.data.dao.Movie;
import org.michaelbel.moviemade.rest.response.MoviesResponse;
import org.michaelbel.moviemade.utils.DateUtils;
import org.michaelbel.moviemade.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

@InjectViewState
public class CompanyMoviesPresenter extends MvpPresenter<ResultsMvp> {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public void loadMovies(int companyId) {
        if (companyId == 0) {
            getViewState().showError(EmptyViewMode.MODE_NO_MOVIES);
            return;
        }

        if (NetworkUtils.notConnected()) {
            getViewState().showError(EmptyViewMode.MODE_NO_CONNECTION);
            return;
        }

        COMPANIES service = ApiFactory.createService2(COMPANIES.class);
        Observable<MoviesResponse> observable = service.getMovies(companyId, BuildConfig.TMDB_API_KEY, ConstantsKt.en_US).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(new DisposableObserver<MoviesResponse>() {
            @Override
            public void onNext(MoviesResponse response) {
                List<Movie> results = new ArrayList<>(response.movies);
                if (results.isEmpty()) {
                    getViewState().showError(EmptyViewMode.MODE_NO_MOVIES);
                    return;
                }
                getViewState().showResults(results, true);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(EmptyViewMode.MODE_NO_MOVIES);
            }

            @Override
            public void onComplete() {}
        }));
    }

    public void movieFavoritesChange(Movie m) {
        Realm realm = Realm.getDefaultInstance();
        MovieRealm movie = realm.where(MovieRealm.class).equalTo("id", m.getId()).findFirst();
        if (movie == null) {
            realm.beginTransaction();

            MovieRealm newMovie = realm.createObject(MovieRealm.class);
            newMovie.id = m.getId();
            newMovie.title = m.getTitle();
            newMovie.posterPath = m.getPosterPath();
            newMovie.releaseDate = AndroidExtensions.formatReleaseDate(m.getReleaseDate());
            //newMovie.originalTitle = m.originalTitle;
            //newMovie.originalLanguage = AndroidUtils.formatOriginalLanguage(m.originalLanguage);
            newMovie.overview = m.getOverview();
            newMovie.addedDate = DateUtils.getCurrentDateAndTimeWithMilliseconds();
            //newMovie.adult = m.adult;
            //newMovie.backdropPath = m.backdropPath;
            //newMovie.budget = AndroidUtils.formatCurrency(m.budget);
            //newMovie.revenue = AndroidUtils.formatCurrency(m.revenue);
            //newMovie.status = m.status;
            //newMovie.tagline = m.tagline;
            //newMovie.imdbId = m.imdbId;
            //newMovie.homepage = m.homepage;
            //newMovie.popularity = m.popularity;
            //newMovie.video = m.video;
            //newMovie.runtime = AndroidUtils.formatRuntime(m.runtime);
            newMovie.voteAverage = m.getVoteAverage();
            newMovie.voteCount = m.getVoteCount();
            //newMovie.favorite = true;

            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            MovieRealm movieRealm = realm.where(MovieRealm.class).equalTo("id", m.getId()).findFirst();
            //movieRealm.favorite = !movie.favorite;
            realm.commitTransaction();
        }
    }

    public void movieWatchlistChange(Movie m) {
        Realm realm = Realm.getDefaultInstance();
        MovieRealm movie = realm.where(MovieRealm.class).equalTo("id", m.getId()).findFirst();
        if (movie == null) {
            realm.beginTransaction();

            MovieRealm newMovie = realm.createObject(MovieRealm.class);
            newMovie.id = m.getId();
            newMovie.title = m.getTitle();
            newMovie.posterPath = m.getPosterPath();
            newMovie.releaseDate = AndroidExtensions.formatReleaseDate(m.getReleaseDate());
            newMovie.overview = m.getOverview();
            newMovie.addedDate = DateUtils.getCurrentDateAndTimeWithMilliseconds();
            newMovie.popularity = m.getPopularity();
            newMovie.voteAverage = m.getVoteAverage();
            newMovie.voteCount = m.getVoteCount();
            newMovie.watching = true;

            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            //MovieRealm movieRealm = realm.where(MovieRealm.class).equalTo("id", m.getId().findFirst());
            //movieRealm.watching = !movie.watching;
            realm.commitTransaction();
        }
    }

    /*public boolean isMovieFavorite(int movieId) {
        Realm realm = Realm.getDefaultInstance();
        MovieRealm movie = realm.where(MovieRealm.class).equalTo("id", movieId).findFirst();
        return movie != null && movie.favorite;
    }*/

    public boolean isMovieWatchlist(int movieId) {
        Realm realm = Realm.getDefaultInstance();
        MovieRealm movie = realm.where(MovieRealm.class).equalTo("id", movieId).findFirst();
        return movie != null && movie.watching;
    }

    @Override
    public void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }
}