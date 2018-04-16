package ser210.quinnipiac.edu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LoginFragment extends Fragment {

    private Button logInButton;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_login, container, false);

        // add everything you're getting here
        logInButton = (Button) iview.findViewById(R.id.logInButton);

        // set on click listener
        Button bttn = (Button) iview.findViewById(R.id.logInButton);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = iview;
                
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