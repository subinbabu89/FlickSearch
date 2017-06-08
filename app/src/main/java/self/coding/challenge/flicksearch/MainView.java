package self.coding.challenge.flicksearch;

import java.util.List;

/**
 * Main View for the main acitvity
 */
interface MainView {
    /**
     * Method used to set the images to the viewpager adapter
     *
     * @param allPhotos list of photos to be set
     */
    void setImagesToViewPager(List<IndivPhoto> allPhotos);

    /**
     * method used to display snackbar on the screen
     */
    void showSnackbar();

    /**
     * method used to dismiss snackbar from the screen
     */
    void dismissSnackBar();

    /**
     * Add swipe refresh listener to the app
     */
    void addSwipeRefreshListener();
}
