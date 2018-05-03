package ser210.quinnipiac.edu.finalproject.Model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ser210.quinnipiac.edu.finalproject.R;

/**
 * Created by hanlo on 5/2/2018.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context mContext;
    private List<Review> reviewList = new ArrayList<>();

    public ReviewAdapter(@NonNull  Context context, ArrayList<Review> list){
        super(context, 0 , list);
        mContext = context;
        reviewList = list;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listitem = convertView;

        if(listitem == null)
            listitem = LayoutInflater.from(mContext).inflate(R.layout.custom_review_adapter,parent,false);

            Review currentReview = reviewList.get(position);


            TextView title= (TextView) listitem.findViewById(R.id.title_review_adapter);
            title.setText(currentReview.getTitle());

            TextView review = (TextView) listitem.findViewById(R.id.review_review_adapter);
            review.setText(currentReview.getReview());

            TextView rating = (TextView) listitem.findViewById(R.id.rating_review_adapter);
            rating.setText(currentReview.getRating());

        return listitem;

    }

}
