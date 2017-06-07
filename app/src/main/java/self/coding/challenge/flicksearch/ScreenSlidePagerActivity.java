package self.coding.challenge.flicksearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by subin on 6/7/2017.
 */

public class ScreenSlidePagerActivity extends FragmentActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private FlickrAPI flickrApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFlickrAPI();

        flickrApi.getImages().enqueue(photosCallback);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);

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
                mPagerAdapter = new ScreenSlidePagerAdapter(ScreenSlidePagerActivity.this,photos.getPhotos().getPhoto());
                mPager.setAdapter(mPagerAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Photos> call, Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends PagerAdapter {

        private List<IndivPhoto> urls = new ArrayList<>();
        private Context mContext;

        public ScreenSlidePagerAdapter(Context context,List<IndivPhoto> urls) {
            this.urls = urls;
            this.mContext = context;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            SquaredImageView squaredImageView = new SquaredImageView(mContext);
            squaredImageView.setScaleType(CENTER_CROP);

            Picasso.with(mContext) //
                    .load(urls.get(position).getUrl_z()) //
                    .placeholder(R.drawable.placeholder) //
                    .error(R.drawable.error) //
                    .fit() //
                    .tag(mContext) //
                    .into(squaredImageView);

            container.addView(squaredImageView);
            return squaredImageView;
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((SquaredImageView)object);
        }
    }
}