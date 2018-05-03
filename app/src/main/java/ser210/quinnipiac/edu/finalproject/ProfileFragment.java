package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView profile;
    private ListView reviews;
    private ArrayList<String> userReviews;
    private FirebaseDatabase database;
    private DatabaseReference users;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView username = (TextView) iview.findViewById(R.id.usernameProfile);
        username.setText(MainActivity.userLoggedIn);

        // upload profile picture
        profile = (ImageView) iview.findViewById(R.id.profilePic);
        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        reviews = (ListView) iview.findViewById(R.id.myReviews);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        userReviews = new ArrayList<String>();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp : dataSnapshot.child(MainActivity.userLoggedIn).child("Review").getChildren()) {
                    System.out.println("We cooking meow");
                    Log.d("Review ", String.valueOf(dsp.getKey()));
                    System.out.println(userReviews.size());
                    userReviews.add(dsp.getKey());
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userReviews);
                    //adapter.add(users.child(MainActivity.userLoggedIn).child("Review").child("My Hero Academia").child("Rating").getKey());
                    //adapter.add(users.child(MainActivity.userLoggedIn).child("Review").child("My Hero Academia").child("Review Statement").getKey());

                    reviews.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reviews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String review = (String) adapterView.getItemAtPosition(position).toString();
                //Intent intent = new Intent(getActivity(), );
            }

        });

        return iview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            profile.setImageURI(selectedImage);
        }
    }
}