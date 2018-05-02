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
    private DataSnapshot dataSnapshot = null;

    public LoginFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_login, container, false);
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

                view = iview;
                EditText inputUsername = (EditText) view.findViewById(R.id.username);
                EditText inputPassword = (EditText) view.findViewById(R.id.password);
                username = inputUsername.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                view.setEnabled(false);
                System.out.println("CLicked Login");

                //if valid login, move to next screen.
                if(getValidLogin() == 1) {
                    System.out.println("Button Login");
                    //run function that checks sql for username and password if the login was successful
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("loggedInName", username);
                    startActivity(intent);
                }
            }
        });

        //on click listener for the create account button that calls the create account button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(usernameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
                view = iview;
                if(getValidAccount() == 1) {
                    EditText inputUsername = (EditText) view.findViewById(R.id.username);
                    EditText inputPassword = (EditText) view.findViewById(R.id.password);
                    inputUsername.setText("");
                    inputPassword.setText("");
                }
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
                System.out.println("DataSnap: " + dataSnapshot);
                if(dataSnapshot.child(user.getUsername()).exists()){
                    Toast.makeText(getActivity(), "Username already exists, try Again",Toast.LENGTH_SHORT).show();
                    setValidAccount(0);
                }
                else{
                    setValidAccount(1);
                    users.child(user.getUsername()).setValue(user);
                    Toast.makeText(getActivity(), "Success, account registered",Toast.LENGTH_SHORT).show();
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
                System.out.println(dataSnapshot.child(username).child("password").getValue());
                if(dataSnapshot.child(username).child("password").getValue().equals(password)){
                    System.out.println("Valid Login");
                    setValidLogin(1);
                    //send user to the next screen
                    Toast.makeText(getActivity(), "Login Successful",Toast.LENGTH_SHORT).show();
                }else{
                    setValidLogin(0);
                    Toast.makeText(getActivity(), "Username is incorrect or does not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public int getValidLogin() {
        return validLogin;
    }

    public void setValidLogin(int validLogins) {validLogin = validLogins;
    }

    public int getValidAccount() {
        return validAccount;
    }

    public void setValidAccount(int validAccount) {
        this.validAccount = validAccount;
    }

    public DatabaseReference getDataBaseReference(){
        return users;
    }

    public User getUsername(){
        return user;
    }

}