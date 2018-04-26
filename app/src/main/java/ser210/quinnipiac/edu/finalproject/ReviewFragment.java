package ser210.quinnipiac.edu.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReviewFragment extends Fragment {

    private RelativeLayout reviewFrag;
    private TextView title;
    private TextView overview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_review, container, false);
        reviewFrag = (RelativeLayout) v.findViewById(R.id.reviewFragment);
        reviewFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        title = (TextView) v.findViewById(R.id.title);
        title.setText(ReviewActivity.title);

        overview = (TextView) v.findViewById(R.id.overview);
        overview.setText(ReviewActivity.overview);

        return v;
    }
}