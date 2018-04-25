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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainFragment extends Fragment {

    private RelativeLayout mainFrag;
    static String URL = "";
    static String key = "d6cc0d2a46052f4e3fd2b5dcdef40db0";
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View iview = inflater.inflate(R.layout.fragment_main, container, false);
        mainFrag = (RelativeLayout) iview.findViewById(R.id.mainFragment);
        mainFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
        // add everything you're getting here

        // set on click listener
        listView = (ListView) iview.findViewById(R.id.listGenres);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        return iview;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainFrag.setBackgroundColor(getResources().getColor(MainActivity.color));
    }
}