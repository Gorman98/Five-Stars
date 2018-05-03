package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import ser210.quinnipiac.edu.finalproject.Model.Review;
import ser210.quinnipiac.edu.finalproject.Model.ReviewAdapter;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView profile;
    private ListView reviews;
    private FirebaseDatabase database;
    private DatabaseReference users;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageRef, imageRef;
    private Uri selectedImage, downloadURL;
    private ProgressDialog progressDialog;
    private UploadTask uploadTask;
    private ReviewAdapter reviewAdapter;
    private String user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView username = (TextView) iview.findViewById(R.id.usernameProfile);
        if(ProfileActivity.getUser() != null){
            user = ProfileActivity.getUser();
        } else {
            user = MainActivity.userLoggedIn;
        }
        username.setText(user);

        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference();

        // upload profile picture
        profile = (ImageView) iview.findViewById(R.id.profilePic);

        storageRef.child("images/" + user).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                downloadURL = uri;
                updateImage();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


        profile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user == MainActivity.userLoggedIn) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                }
            }
        });


        //get review listview, used later on for adapter
        reviews = (ListView) iview.findViewById(R.id.myReviews);
        //getfirebase reference and create
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        final ArrayList<Review> reviewList = new ArrayList<>();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp : dataSnapshot.child(user).child("Review").getChildren()) {
                    System.out.println("We cooking meow");
                    Log.d("Review ", String.valueOf(dsp.getKey()));
                    System.out.println("Title: " + dsp.getKey().toString());
                    System.out.println("Review: " + dsp.child("Review Statement").getValue().toString());
                    System.out.println("Rating: " + dsp.child("Rating").getValue().toString());

                    System.out.println(reviewList.size());
                    reviewList.add(new Review(dsp.getKey().toString(), dsp.child("Review Statement").getValue().toString(),dsp.child("Rating").getValue().toString()));
                    reviewAdapter = new ReviewAdapter(getContext(), reviewList);
                    reviews.setAdapter(reviewAdapter);
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
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
          // profile.setImageURI(selectedImage);
            uploadImage();
        }
    }

    private void uploadImage() {
        //create reference to images folder and assing a name to the file that will be uploaded
        imageRef = storageRef.child("images/" + user);
        //creating and showing progress dialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);
        //starting upload
        uploadTask = imageRef.putFile(selectedImage);
        // Observe state change events such as progress, pause, and resume
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //sets and increments value of progressbar
                progressDialog.incrementProgressBy((int) progress);
            }
        });
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getActivity(), "Error in uploading!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                downloadURL = taskSnapshot.getDownloadUrl();
                Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                //showing the uploaded image in ImageView using the download url
                updateImage();
            }
        });

    }

    private void updateImage(){
        Glide.with(this)
                .load(downloadURL)
                .into(profile);
    }

}