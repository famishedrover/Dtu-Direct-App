package com.example.android.dtuguide;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by archi on 04-12-2016.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        ViewHolder holder;
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder=new ViewHolder();
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Word currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        holder.contact = (TextView) listItemView.findViewById(R.id.contact_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        holder.contact.setText(currentAndroidFlavor.getcontactinfo());

        // Find the TextView in the list_item.xml layout with the ID version_number
        holder.email = (TextView) listItemView.findViewById(R.id.email_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        holder.email.setText(currentAndroidFlavor.getemailid());

        holder.mobile = (TextView) listItemView.findViewById(R.id.mobile_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        holder.mobile.setText(currentAndroidFlavor.getnumber());


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    static class ViewHolder {
        TextView contact, email, mobile;
    }

}