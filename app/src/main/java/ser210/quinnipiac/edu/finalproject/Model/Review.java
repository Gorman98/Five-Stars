package ser210.quinnipiac.edu.finalproject.Model;

/**
 * Created by hanlo on 5/2/2018.
 */

public class Review {
    private String title,review,rating;

    public Review( String title, String review, String rating){
        this.title = title;
        this.review = review;
        this.rating = rating;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
