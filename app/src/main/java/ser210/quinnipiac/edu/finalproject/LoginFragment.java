package ser210.quinnipiac.edu.finalproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ser210.quinnipiac.edu.finalproject.Model.User;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private FirebaseDatabase database;
    private DatabaseReference users;
    private Button btnCreateAccount, btnLogin;
    private EditText usernameEditText,passwordEditText;
    private RelativeLayout relativeLayout;
    private String username, password;
    public int validLogin, validAccount = 0;
    public static User user = null;
    public View iview = null;
    private DataSnapshot dataSnapshot = null;

    public LoginFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        iview = inflater.inflate(R.layout.fragment_login, container, false);
        setHasOptionsMenu(true);
        relativeLayout = (RelativeLayout) iview.findViewById(R.id.loginFragment);

        //Get instance of the Google Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        usernameEditText = (EditText) iview.findViewById(R.id.username);
        passwordEditText = (EditText) iview.findViewById(R.id.password);

        btnLogin = (Button) iview.findViewById(R.id.logInButton);
        btnCreateAccount = (Button) iview.findViewById(R.id.createAccountButton);

        //onclick listener for the login button, which calls the sign in method when clicked
        //////////////////////////////////////////////////////////////////////////////////
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(usernameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
            }
        });

        //on click listener for the create account button that calls the create account button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(usernameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
            }
        });

        return iview;
    }
    //method that is called when create account is clicked, this then gets the event listener for the database and tries
    //to add the username and passsword to the database if it is not already in it.
    private void createAccount(final String username, final String password) {
        user = new User(username, password);
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    Toast.makeText(getActivity(), "Username already exists, try Again",Toast.LENGTH_SHORT).show();
                }
                else{
                    users.child(username).setValue(user);
                    Toast.makeText(getActivity(), "Success, account registered",Toast.LENGTH_SHORT).show();
                    EditText inputUsername = (EditText) iview.findViewById(R.id.username);
                    EditText inputPassword = (EditText) iview.findViewById(R.id.password);
                    inputUsername.setText("");
                    inputPassword.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //method to sign into application, takes the username and password and checks if the password is correct to login.
    private void signIn(final String username, final String password) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).child("password").getValue().equals(password)){
                    //send user to the next screen
                    Toast.makeText(getActivity(), "Login Successful",Toast.LENGTH_SHORT).show();
                    //run function that checks sql for username and password if the login was successful
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("loggedInName", username);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Username is incorrect or does not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public DatabaseReference getDataBaseReference(){
        return users;
    }

    public User getUsername(){
        return user;
    }

}