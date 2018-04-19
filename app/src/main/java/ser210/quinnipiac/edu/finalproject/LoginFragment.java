package ser210.quinnipiac.edu.finalproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.security.KeyStore;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ser210.quinnipiac.edu.finalproject.Model.User;

public class LoginFragment extends Fragment {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    FirebaseDatabase database;
    DatabaseReference users;
    private Button btnCreateAccount, btnLogin;
    private EditText usernameEditText,passwordEditText;
    private RelativeLayout relativeLayout;
    private String username, password;
    public LoginFragment(){

    }

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



        Button button = (Button) iview.findViewById(R.id.logInButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view = iview;
                EditText inputUsername = (EditText) view.findViewById(R.id.username);
                EditText inputPassword = (EditText) view.findViewById(R.id.password);
                username = inputUsername.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                view.setEnabled(false);

                //run function that checks sql for username and password
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        /*logInButton.setOnClickListener(new View.OnClickListener(){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        });
        */

        return iview;
    }
    //method that is called when create account is clicked, this then gets the event listener for the database and tries
    //to add the username and passsword to the database if it is not already in it.
    private void createAccount(final String username, final String password) {
        final User user = new User(username, password);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getUsername()).exists()){
                    //Toast.makeText(LoginFragment.this, "Username already exists",Toast.LENGTH_SHORT).show();
                }
                else{
                    users.child(user.getUsername()).setValue(user);
                    //reset the edit text values so the user can login after successfully creating an account.
                    //usernameEditText = "";
                    //passwordEditText = "";
                    //Toast.makeText(LoginFragment.this, "Success, account registered",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //method to sign into application, takes the username and password and checks if the password is correct to login.
    private void signIn(final String username, final String password) {
        final User user = new User(username, password);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getUsername()).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            //send usser to the next screen
                            //Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show();


                        }
                        else{
                            //Toast.makeText(LoginFragment.this, "Password Incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        //Toast.makeText(LoginFragment.this, "Username is incorrect or does not exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}