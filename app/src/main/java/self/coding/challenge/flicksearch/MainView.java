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
}
