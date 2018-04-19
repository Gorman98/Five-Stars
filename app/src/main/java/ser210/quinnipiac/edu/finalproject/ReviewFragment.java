package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ReviewFragment extends Fragment {

    private RelativeLayout reviewFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_review, container, false);
        reviewFrag = (RelativeLayout) v.findViewById(R.id.reviewFragment);
        reviewFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        return v;
    }
}