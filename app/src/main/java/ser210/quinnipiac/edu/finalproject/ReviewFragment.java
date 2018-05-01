package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
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
    private String username;
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
        username = LoginFragment.user.getUsername();
        title = (TextView) v.findViewById(R.id.title);
        title.setText(ReviewActivity.title);

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
                if(users.child(username).child("Ratings") == null){
                    users.child(username).child("Ratings").push();

                }else{
                    System.out.println("Rating Child is Null");
                }
                //add to the user reference, adds the rating and review
               users.child("Ratings").child(title.getText().toString().trim()).push();
               users.child("Ratings").child(title.getText().toString().trim()).child(String.valueOf(ratingValue)).push();
               users.child("Ratings").child(title.getText().toString().trim()).child(String.valueOf(review)).push();

//                LoginFragment.users.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        dataSnapshot.child(LoginFragment.user.getUsername())  ;
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

            }
        });


        return v;
    }
}