package com.example.android.dtuguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Administration", "mail@dce.edu", "01127871018"));
        words.add(new Word("Vice Chancellor", "dtu@dce.nic.in", "01127882284"));
        words.add(new Word("Registrar DTU", "Registrardtu@gmail.com", "9968830414"));
        words.add(new Word("Central Library", "rkshukla@dce.edu", "01127871419"));
        words.add(new Word("Board of Discipline", "narendrakumar@dce.edu.in", "9999755929"));
        words.add(new Word("Hostel Office", "hostels@dce.edu", "01127871045"));
        words.add(new Word("Training & Placement", "placements@dce.ac.in", "9868098410"));
        words.add(new Word("BTech Admissions", "jacdelhi2016@gmail.com", "9599384990"));
        words.add(new Word("Examination Cell", "vishalverma@dce.ac.in", "01127871029"));
        words.add(new Word("Computer Center Head", "opverma@dce.ac.in", "9910050177"));
        words.add(new Word("Post Graduate Admissions", "pgadmissions2016@gmail.com", "01127871018"));
        words.add(new Word("Recruitment Branch", "recruitment@dtu.ac.in", "01127294669"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

    }
}
