package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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


        final View v = inflater.inflate(R.layout.fragment_review, container, false);
        reviewFrag = (RelativeLayout) v.findViewById(R.id.reviewFragment);
        reviewFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
        username = MainActivity.userLoggedIn;
        title = (TextView) v.findViewById(R.id.title);
        title.setText(ReviewActivity.title);
        titleText = title.getText().toString().trim();

        rating = (RatingBar) v.findViewById(R.id.rateBar);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                ratingValue = (int) rating;
            }
        });

        overview = (TextView) v.findViewById(R.id.overview);
        overview.setText(ReviewActivity.overview);

        button = (Button) v.findViewById(R.id.submitReview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = v;
                review = (EditText) view.findViewById(R.id.review);
                reviewText = review.getText().toString().trim();
                System.out.println("Username: " + username);

                //add to the user reference, adds the rating and review
                users.child(username).child("Ratings").child(titleText).child(String.valueOf(ratingValue)).push().setValue(ratingValue);
                users.child(username).child("Ratings").child(titleText).child(reviewText).push().setValue(ratingValue);
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