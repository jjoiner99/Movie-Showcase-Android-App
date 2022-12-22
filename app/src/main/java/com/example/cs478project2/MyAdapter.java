package com.example.cs478project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> movieList;
    private int[] postersList;
    private RVClick RVListener;
    private Context context;

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

    public MyAdapter(ArrayList<String> theList, int[] posters, RVClick listener){
        movieList = theList;
        postersList = posters;
        this.RVListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(movieList.get(position));
        holder.image.setImageResource(postersList[position]);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }




    /*
        This class creates a wrapper object around a view that contains the layout for
         an individual item in the list. It also implements the onClickListener so each ViewHolder in the list is clickable.
        It's onclick method will call the onClick method of the RVClickListener defined in
        the main activity.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView name;
        public ImageView image;
        private RVClick listener;
        private View itemView;

        public ViewHolder(@NonNull View itemView, RVClick passedListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.listener = passedListener;


            /*
                don't forget to set the listener defined here to the view (list item) that was
                passed in to the constructor.
             */
            itemView.setOnClickListener(this); //set short click listener
        }

        @Override
        public void onClick(View v) { //Handles visiting the movie IMDB pages
            listener.onClick(v, getAdapterPosition());
            Log.i("ON_CLICK", "in the onclick in view holder");
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(imdbList.get(getAdapterPosition())));
            itemView.getContext().startActivity(i);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            //inflate menu from xml

            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu );
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);
        }

        /*
            listener for menu items clicked
         */
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Log.i("ON_CLICK", name.getText() + " adapter pos: " + getAdapterPosition());
                Intent i;
                switch(item.getItemId()){
                    case R.id.item1:
                        Log.i("ON_CLICK", "Movie Clicked");
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse(videoList.get(getAdapterPosition())));
                        itemView.getContext().startActivity(i);
                        return true;
                    case R.id.item2:
                        Log.i("ON_CLICK", "stuff Clicked");
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse(wikiList.get(getAdapterPosition())));
                        itemView.getContext().startActivity(i);
                        return true;
                    case R.id.item3:
                        Log.i("ON_CLICK", "Item3 Clicked");
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse(dirList.get(getAdapterPosition())));
                        itemView.getContext().startActivity(i);
                        return true;
                    default:
                        return onMenuItemClick(item);

                }

            }
        };

    }
}
