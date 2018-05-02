package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int SELECT_PHOTO = 100;
    private ImageView profile;
    private ListView reviews;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageRef, imageRef;
    private Uri selectedImage;
    private ProgressDialog progressDialog;
    private UploadTask uploadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView username = (TextView) iview.findViewById(R.id.usernameProfile);
        username.setText(MainActivity.userLoggedIn);

        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference();

        // upload profile picture/
        profile = (ImageView) iview.findViewById(R.id.profilePic);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        reviews = (ListView) iview.findViewById(R.id.myReviews);
        reviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return iview;
    }

    public void selectImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            profile.setImageURI(selectedImage);
            uploadImage();
        }
        switch (requestCode){
            case SELECT_PHOTO:
                if(requestCode == RESULT_OK){
                    Toast.makeText(getActivity(), "Image", Toast.LENGTH_SHORT).show();
                    selectedImage = data.getData();

                }
        }
    }

    public void uploadImage(){
        //create reference to images folder and assing a name to the file that will be uploaded
        imageRef = storageRef.child("images/"+selectedImage.getLastPathSegment());
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
                Toast.makeText(getActivity(),"Error in uploading!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(getActivity(),"Upload successful",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                //showing the uploaded image in ImageView using the download url

            }
        });

            }

}