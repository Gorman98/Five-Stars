package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class SearchFragment extends Fragment {

    private RelativeLayout searchFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchFrag = (RelativeLayout) v.findViewById(R.id.searchFragment);
        searchFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        return v;
    }
}