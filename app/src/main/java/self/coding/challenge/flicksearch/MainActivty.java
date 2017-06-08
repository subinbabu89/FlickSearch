package self.coding.challenge.flicksearch;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

/**
 * MainActivity with the only screen in the app
 */
public class MainActivty extends FragmentActivity implements MainView {

    private ViewPager mPager;
    private MainPresenter presenter;
    private Snackbar snackbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin(50);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

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
    public void showSnackbar() {
        if (snackbar == null || !snackbar.isShown()) {
            snackbar = Snackbar.make(mPager, R.string.str_internet_unavailable, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getText(android.R.string.ok), null);
            snackbar.show();
        }
    }

    @Override
    public void dismissSnackBar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void addSwipeRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        presenter.onResume();
                        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
}