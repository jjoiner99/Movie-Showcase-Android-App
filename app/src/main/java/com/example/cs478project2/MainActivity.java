package com.example.cs478project2;

//James Joiner

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVClick{

    ArrayList<String> myList;
    int layout = 0;
    private RecyclerView movieView;
    List<String> imdbList = Arrays.asList("https://www.imdb.com/title/tt0829482/", "https://www.imdb.com/title/tt1049413/", "https://www.imdb.com/title/tt0099685/", "https://www.imdb.com/title/tt0091251/",
            "https://www.imdb.com/title/tt0077651/", "https://www.imdb.com/title/tt0079501/", "https://www.imdb.com/title/tt0084787/", "https://www.imdb.com/title/tt0107120/");

    List<String> videoList = Arrays.asList("https://www.imdb.com/video/vi1602730521?playlistId=tt0829482&ref_=tt_ov_vi", "https://www.imdb.com/video/vi2557280793?playlistId=tt1049413&ref_=tt_ov_vi", "https://www.imdb.com/video/vi2673654553?playlistId=tt0099685&ref_=tt_ov_vi", "https://www.imdb.com/video/vi129958169?playlistId=tt0091251&ref_=tt_ov_vi",
            "https://www.imdb.com/video/vi3749757721?playlistId=tt0077651&ref_=tt_ov_vi", "https://www.imdb.com/video/vi961986073?playlistId=tt0079501&ref_=tt_ov_vi",
            "https://www.imdb.com/video/vi2298151193?playlistId=tt0084787&ref_=tt_ov_vi", "https://www.imdb.com/video/vi1763031833?playlistId=tt0107120&ref_=tt_ov_vi");
    List<String> wikiList = Arrays.asList("https://en.wikipedia.org/wiki/Superbad", "https://en.wikipedia.org/wiki/Up_(2009_film)", "https://en.wikipedia.org/wiki/Goodfellas",
            "https://en.wikipedia.org/wiki/Come_and_See", "https://en.wikipedia.org/wiki/Halloween_(1978_film)", "https://en.wikipedia.org/wiki/Mad_Max_(film)",
            "https://en.wikipedia.org/wiki/The_Thing_(1982_film)", "https://en.wikipedia.org/wiki/Hocus_Pocus_(1993_film)");
    List<String> dirList = Arrays.asList("https://en.wikipedia.org/wiki/Greg_Mottola", "https://en.wikipedia.org/wiki/Pete_Docter", "https://en.wikipedia.org/wiki/Martin_Scorsese",
            "https://en.wikipedia.org/wiki/Elem_Klimov", "https://en.wikipedia.org/wiki/John_Carpenter", "https://en.wikipedia.org/wiki/George_Miller_(filmmaker)",
            "https://en.wikipedia.org/wiki/John_Carpenter", "https://en.wikipedia.org/wiki/Kenny_Ortega");
    int[] postersList = {R.drawable.superbad, R.drawable.up, R.drawable.goodfellas, R.drawable.comeandsee, R.drawable.halloween,
            R.drawable.madmax, R.drawable.thething, R.drawable.hocuspocus};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieView = (RecyclerView) findViewById(R.id.RecyclerView);

        List<String> movies = Arrays.asList("Superbad", "Up", "Goodfellas", "Come and See", "Halloween",
                "Mad Max", "The Thing", "Hocus Pocus");

        myList = new ArrayList<>();
        myList.addAll(movies);

        RVClick listener = (view,position)->{
            TextView name = (TextView)view.findViewById(R.id.textView);
            Toast.makeText(this,name.getText(),Toast.LENGTH_SHORT).show();
        };

        MyAdapter adapter = new MyAdapter(myList, postersList, listener);
        movieView.setHasFixedSize(true);
        movieView.setAdapter(adapter);
        movieView.setLayoutManager(new LinearLayoutManager(this)); //List
        //movieView.setLayoutManager(new GridLayoutManager(this, 2)); //use this line to see as a grid

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Sets the View to a List View
    public void listViewSetter(){
        movieView.setLayoutManager(new LinearLayoutManager(this)); //List

    }

    //Sets the View to a Grid View
    public void gridViewSetter(){
        movieView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    //Handles the option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1://Case to handle switching to a list view
                listViewSetter();
                Log.i("ON_CLICK", "clicking list option");
                return true;
            case R.id.item2://Case to handle switching to a grid view
                gridViewSetter();
                Log.i("ON_CLICK", "Clicking grid option");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view, int position) {
        String url = "https://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url";
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url"));
        startActivity(i);
    }
}