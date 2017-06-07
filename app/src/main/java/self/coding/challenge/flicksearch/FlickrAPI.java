package self.coding.challenge.flicksearch;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by subin on 6/7/2017.
 */

public interface FlickrAPI {

    String BASE_URL = "https://api.flickr.com";

    @GET("/services/rest/?method=flickr.photos.getrecent&api_key=16611eaa294697f13aa4034235b24d22&format=json&nojsoncallback=1&extras=url_z")
    Call<Photos> getImages();
}
