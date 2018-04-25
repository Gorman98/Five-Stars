package ser210.quinnipiac.edu.finalproject;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SearchFragment extends Fragment {

    private RelativeLayout searchFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchFrag = (RelativeLayout) v.findViewById(R.id.searchFragment);
        searchFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        ImageView genreBanner = (ImageView) v.findViewById(R.id.genreBanner);

        System.out.println("GENRE " + SearchActivity.genreSelected);

        if(SearchActivity.genreSelected.equals("Anime")) {
            //set banner and api for anime
            genreBanner.setImageResource(R.drawable.animebanner);
        }
        else if(SearchActivity.genreSelected.equals("Movies")) {
            //st banner and api for movies
            genreBanner.setImageResource(R.drawable.moviebanner);
        }
        else if(SearchActivity.genreSelected.equals("TV")) {
            //set banner and api for tv
            genreBanner.setImageResource(R.drawable.tvbanner);

        }
        else  {
            //set banner and api for video games
            genreBanner.setImageResource(R.drawable.videogamebanner);
        }
        return v;
    }
}