package self.coding.challenge.flicksearch;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * MainActivity with the only screen in the app
 */
public class MainActivty extends FragmentActivity implements MainView {

    private ViewPager mPager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin(50);

        presenter = new MainPresenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void setImagesToViewPager(List<IndivPhoto> allPhotos) {
        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(MainActivty.this, allPhotos);
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
}