package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView profile;
    ListView reviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView username = (TextView) iview.findViewById(R.id.usernameProfile);
        username.setText(MainActivity.userLoggedIn);

        // upload profile picture
        profile = (ImageView) iview.findViewById(R.id.profilePic);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        reviews = (ListView) iview.findViewById(R.id.myReviews);
        reviews.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                
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