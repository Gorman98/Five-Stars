package ser210.quinnipiac.edu.finalproject;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class SearchFragment extends Fragment {

    private RelativeLayout searchFrag;
    static String key = "d6cc0d2a46052f4e3fd2b5dcdef40db0";
    static String URL = "";
    private EditText input;
    private String search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchFrag = (RelativeLayout) v.findViewById(R.id.searchFragment);
        searchFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        ImageView genreBanner = (ImageView) v.findViewById(R.id.genreBanner);

        input = (EditText) v.findViewById(R.id.input);
        search = input.getText().toString().trim();

        System.out.println("GENRE " + SearchActivity.genreSelected);

        if(SearchActivity.genreSelected.equals("Anime")) {
            //set banner and api for anime
            genreBanner.setImageResource(R.drawable.animebanner);
            search.replaceAll(" ", "+");
            URL="https://myanimelist.net/api/anime/search.xml?q=" + search;
        }
        else if(SearchActivity.genreSelected.equals("Movies")) {
            //st banner and api for movies
            genreBanner.setImageResource(R.drawable.moviebanner);
            URL = "https://api.themoviedb.org/3/movie/550?api_key=ddea89b7d05ee63353966311f2d7e65f";
        }
        else if(SearchActivity.genreSelected.equals("TV")) {
            //set banner and api for tv
            genreBanner.setImageResource(R.drawable.tvbanner);
            URL = "https://api.themoviedb.org/3/tv/550?api_key=ddea89b7d05ee63353966311f2d7e65f";

        }
        else  {
            //set banner and api for video games
            genreBanner.setImageResource(R.drawable.videogamebanner);
            URL = "https://api-endpoint.igdb.com/games/" + "?mashap-key=d6cc0d2a46052f4e3fd2b5dcdef40db0";
        }
        return v;
    }
}