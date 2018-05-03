package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewFragment extends Fragment {

    private RelativeLayout reviewFrag;
    private TextView title;
    private TextView overview;
    private Button button;
    private RatingBar rating;
    private EditText review;
    private String username,titleText,reviewText;
    private int ratingValue;
    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //firebased reference
        //Get instance of the Google Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        //fragment Review that gets all the component
        final View v = inflater.inflate(R.layout.fragment_review, container, false);
        reviewFrag = (RelativeLayout) v.findViewById(R.id.reviewFragment);
        reviewFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
        username = MainActivity.userLoggedIn;
        title = (TextView) v.findViewById(R.id.title);
        title.setText(ReviewActivity.title);
        titleText = title.getText().toString().trim();

        //ratings bar listener
        rating = (RatingBar) v.findViewById(R.id.rateBar);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                ratingValue = (int) rating;
            }
        });

        //gets overview
        overview = (TextView) v.findViewById(R.id.overview);
        overview.setText(ReviewActivity.overview);

        //button that subbmits Review to firebase and sends the user back to the homescreen
        button = (Button) v.findViewById(R.id.submitReview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = v;
                review = (EditText) view.findViewById(R.id.review);
                reviewText = review.getText().toString().trim();
                System.out.println("Username: " + username);

                //add to the user reference, adds the rating and Review
                if(users.child(username).child("Review").child(titleText).getKey() != null){
                    users.child(username).child("Review").child(titleText).child("Rating").setValue(ratingValue);
                    users.child(username).child("Review").child(titleText).child("Review Statement").setValue(reviewText);

                }else {
                    users.child(username).child("Review").child(titleText).child("Rating").push().setValue(ratingValue);
                    users.child(username).child("Review").child(titleText).child("Review Statement").push().setValue(reviewText);
                }
                Toast.makeText(getActivity(), "Review Submitted",Toast.LENGTH_SHORT).show();

                //intent to sendt the user back to main menu.
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("loggedInName", username);
                startActivity(intent);

            }
        });


        return v;
    }
}