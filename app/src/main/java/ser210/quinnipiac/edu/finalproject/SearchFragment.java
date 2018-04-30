package ser210.quinnipiac.edu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;


public class SearchFragment extends Fragment {

    private RelativeLayout searchFrag;
    static String key = "d6cc0d2a46052f4e3fd2b5dcdef40db0";
    static String URL = "";
    private EditText input;
    private String search;
    private String genre;
    private Button bttn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchFrag = (RelativeLayout) v.findViewById(R.id.searchFragment);
        searchFrag.setBackgroundColor(getResources().getColor(MainActivity.color));

        ImageView genreBanner = (ImageView) v.findViewById(R.id.genreBanner);

        input = (EditText) v.findViewById(R.id.input);

        search = "";
        genre = "empty";

        if(SearchActivity.genreSelected.equals("Anime")) {
            //set banner and api for anime
            genreBanner.setImageResource(R.drawable.animebanner);
            genre = "anime";
        }
        else if(SearchActivity.genreSelected.equals("Movies")) {
            //st banner and api for movies
            genreBanner.setImageResource(R.drawable.moviebanner);
            genre = "movie";
        }
        else if(SearchActivity.genreSelected.equals("TV")) {
            //set banner and api for tv
            genreBanner.setImageResource(R.drawable.tvbanner);
            genre = "tv";

        }
        else  {
            //set banner and api for video games
            genreBanner.setImageResource(R.drawable.videogamebanner);
            genre = "game";
        }

        bttn = (Button) v.findViewById(R.id.searchBttn);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = input.getText().toString().trim();
                search.replaceAll(" ", "+");
                if(genre == "anime") {
                    URL="https://myanimelist.net/api/anime/search.xml?q=" + search;
                    check(false);
                } else if (genre == "movie") {
                    search = input.getText().toString().trim();
                    search.replaceAll(" ", "+");
                    URL = "http://www.omdbapi.com/?t=" + search +"&apikey=300b9075";
                    check(true);
                } else if (genre == "tv") {
                    search = input.getText().toString().trim();
                    search.replaceAll(" ", "+");
                    URL = "http://api.tvmaze.com/singlesearch/shows?q=" + search;
                    check(true);
                } else if (genre == "game") {
                    URL = "http://thegamesdb.net/api/GetGamesList.php?name=" + search;
                    check(false);
                }
            }
        });

        return v;
    }


    public void jsonResult(JSONObject json) throws JSONException {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        if(genre == "tv") {
            intent.putExtra("title", json.getString("name"));
            intent.putExtra("overview", json.getString("summary"));
        } else if (genre == "movie") {
            intent.putExtra("title", json.getString("Title"));
            intent.putExtra("overview", json.getString("Plot"));
        } else if (genre == "game") {

        } else if (genre == "anime") {

        }
        startActivity(intent);
    }

    public  void xmlResult(Document document) {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
       if (genre == "game") {

        } else if (genre == "anime") {

        } else if (genre == "tv") {

       } else if (genre == "movie") {

       }
        startActivity(intent);
    }

    public void check(Boolean jsonCheck){
        Log.d("URL", URL);
        try {
            new Worker(this, jsonCheck).execute(URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}