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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.security.KeyStore;


public class LoginFragment extends Fragment {

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

}