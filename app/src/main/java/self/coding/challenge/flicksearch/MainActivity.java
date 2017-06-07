package self.coding.challenge.flicksearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String API_KEY = "16611eaa294697f13aa4034235b24d22";
    final String SECRET_KEY = "676d382679a7002d";
    private FlickrAPI flickrApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFlickrAPI();
        flickrApi.getImages().enqueue(photosCallback);
    }

    private void createFlickrAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FlickrAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        flickrApi = retrofit.create(FlickrAPI.class);

    }

    Callback<Photos> photosCallback = new Callback<Photos>() {
        @Override
        public void onResponse(Call<Photos> call, Response<Photos> response) {
            if (response.isSuccessful()) {
                Log.i("APP","HERE");
                Photos photos = response.body();
                Log.i("APP_SIZE",String.valueOf(photos.getPhotos().getPhoto().size()));
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Photos> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
