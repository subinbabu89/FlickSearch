package self.coding.challenge.flicksearch;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Flickr API interface needed for retrofit 2 call
 */
interface FlickrAPI {
    String BASE_URL = "https://api.flickr.com";

    @GET("/services/rest/?method=flickr.photos.getrecent&api_key=16611eaa294697f13aa4034235b24d22&format=json&nojsoncallback=1&extras=url_z")
    Call<Photos> getImages();
}
