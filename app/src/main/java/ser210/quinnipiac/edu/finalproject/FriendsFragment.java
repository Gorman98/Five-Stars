package ser210.quinnipiac.edu.finalproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {
    private RelativeLayout friendsFrag;
    private FirebaseDatabase database;
    private DatabaseReference users;
    private ListView listView;
    private ArrayList<String> userList;
    private ArrayAdapter<String> adapter;

    public FriendsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsFrag = (RelativeLayout) v.findViewById(R.id.friendsFragment);
        friendsFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        listView = (ListView) v.findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        userList = new ArrayList<String>();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Log.d("not it?", String.valueOf(dsp.getKey()));
                    System.out.println(userList.size());
                    userList.add(dsp.getKey());
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected = (String) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("user", itemSelected);
                startActivity(intent);
            }
        });

        return v;
    }
}
