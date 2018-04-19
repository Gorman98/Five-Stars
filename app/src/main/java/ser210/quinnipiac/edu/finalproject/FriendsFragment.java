package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class FriendsFragment extends Fragment {
    private RelativeLayout friendsFrag;

    public FriendsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsFrag = (RelativeLayout) v.findViewById(R.id.friendsFragment);
        friendsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
        // add everything you're getting here

        // set on click listener
        ImageButton bttn = (ImageButton) v.findViewById(R.id.addFriendButton);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = v;

            }
        });
        /*logInButton.setOnClickListener(new View.OnClickListener(){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        });
        */

        return v;
    }
}
