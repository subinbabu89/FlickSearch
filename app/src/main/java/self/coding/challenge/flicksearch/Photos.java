package self.coding.challenge.flicksearch;

import java.util.List;

/**
 * Photo class .. Needed for GSON parsing
 */
class Photos {
    private Photo photos;

    /**
     * Getter for Photo within the Photos class
     *
     * @return Photo object
     */
    Photo getPhotos() {
        return photos;
    }
}

/**
 * Photo class .. Needed for GSON parsing
 */
class Photo {
    private List<IndivPhoto> photo;

    /**
     * Getter for List of IndivPhoto
     *
     * @return list of IndivPhoto
     */
    List<IndivPhoto> getPhoto() {
        return photo;
    }
}

/**
 * IndivPhoto class .. Needed for GSON parsing
 */
class IndivPhoto {

    private String title;
    private String url_z;

    /**
     * Getter for image title
     *
     * @return title of the image
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for image url
     *
     * @return url for the image
     */
    String getUrl_z() {
        return url_z;
    }

}
