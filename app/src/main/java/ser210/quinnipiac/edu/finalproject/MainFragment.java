package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout;


public class MainFragment extends Fragment {

    private RelativeLayout mainFrag;

    ListView listView;
    private RelativeLayout mainFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_main, container, false);
        mainFrag = (RelativeLayout) iview.findViewById(R.id.mainFragment);
        listView = (ListView) iview.findViewById(R.id.listGenres);

        //listview on item clickedl istener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String itemSelected = (String) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("genre", itemSelected);
                startActivity(intent);
            }
        });
        // add everything you're getting here

        TextView welcomeLogin = (TextView) iview.findViewById(R.id.welcome);
        welcomeLogin.setText("Welcome " + MainActivity.userLoggedIn);
        return iview;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
    }
}