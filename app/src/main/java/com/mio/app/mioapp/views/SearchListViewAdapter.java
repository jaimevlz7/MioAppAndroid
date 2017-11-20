package com.mio.app.mioapp.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mio.app.mioapp.R;
import com.mio.app.mioapp.model.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Japo on 17/09/17.
 */

public class SearchListViewAdapter extends ArrayAdapter<Tweet> {

    private Context context;
    private List<Tweet> imageList;

    //Constructor
    public SearchListViewAdapter(Context _context, int resource, ArrayList<Tweet> _imageList) {
        super(_context, resource, _imageList);
        context = _context;
        imageList = _imageList;
    }



    public View getView(int position, View convertView, ViewGroup parent){

        //get the image we are displaying
        //Imagen imagenTemp = imageList.get(position);

        //get the inflater and inflate the XML laysout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //conditionally inflate either standard or special template
        View view;
        view = inflater.inflate(R.layout.tweeter_feed_layout, null);

        //Conect Code with layout objects
        ImageView imgLoad = (ImageView) view.findViewById(R.id.authorImg_load);
        TextView authorLoad = (TextView) view.findViewById(R.id.author_load);
        TextView dateLoad = (TextView) view.findViewById(R.id.date_load);
        TextView contentLoad = (TextView) view.findViewById(R.id.content_load);

        /*
        //Receive Image path
        File imgFile = new  File(imageList.get(position).getImagenCargada());

        //Check that the file exists
        if(imgFile.exists()){

            //Set the image to a bitmap
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 1, out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            //Asing the bitmap to the imageView
            //imgLoad.setImageResource(myBitmap);
            imgLoad.setImageBitmap(decoded);
        }
        */


        //Set the rest of the texts
        contentLoad.setText(imageList.get(position).getContent());
        dateLoad.setText(imageList.get(position).getDate());
        authorLoad.setText(imageList.get(position).getAuthor());


        return view;
    }


}
