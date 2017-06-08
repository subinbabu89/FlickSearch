package self.coding.challenge.flicksearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implementation of the MainPresenter
 */
class MainPresenterImpl implements MainPresenter {
    private MainView mainView;

    /**
     * Constructor for the class
     *
     * @param mainView mainview of the activty
     */
    MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Callback needed for the retrofit call
     */
    private Callback<Photos> photosCallback = new Callback<Photos>() {
        @Override
        public void onResponse(@NonNull Call<Photos> call, @NonNull Response<Photos> response) {
            if (response.isSuccessful()) {
                Photos photos = response.body();
                assert photos != null;
                List<IndivPhoto> allPhotos = photos.getPhotos().getPhoto();
                mainView.setImagesToViewPager(allPhotos);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(@NonNull Call<Photos> call, @NonNull Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    public void onResume() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FlickrAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        FlickrAPI flickrApi = retrofit.create(FlickrAPI.class);
        flickrApi.getImages().enqueue(photosCallback);
    }
}
