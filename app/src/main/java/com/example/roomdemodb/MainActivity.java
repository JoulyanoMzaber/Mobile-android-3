package com.example.roomdemodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText note;
    Button addText;
    Button destroyer;
    List<Data> allNotes = null;
    String TAG = "--";
    LinearLayout textList;
    DataDB dataDB;

    // deletes all the notes from the database and removes them from the screen
    public void destroy(View view) {
        dataDB.dataDAO().deleteAll();
        textList.removeAllViewsInLayout();
        setListUp();
    }

    // sets up the UI and listeners for the activity, retrieves and displays any existing
    // notes stored in the database, and adds new notes to the database and the screen when
    // the addText Button is clicked
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataDB = DataDB.getInstance(this);
        note = findViewById(R.id.noteText);
        addText = findViewById(R.id.textInput);
        textList = (LinearLayout) findViewById(R.id.listContainer);
        destroyer = findViewById(R.id.destroyer);
        setListUp();

        destroyer.setOnClickListener(this::destroy);
        addText.setOnClickListener((View view) -> {
            String text = note.getText().toString();
            if (!text.isEmpty())
            {
                text = text.trim();
                Data data = new Data(text);
                //Use our DataDB object to pass in the new data object and save it.
                dataDB.dataDAO().insert(data);
                //clear textBox
                note.setText("");

                // list and adds each note to the textList LinearLayout by creating a new TextView
                // object and setting its text, font size, and alignment,
                // and then adding it to the textList using the addView() method
                for( Data item: allNotes)
                {
                    allNotes.add(data);
                    TextView note = new TextView(this);
                    note.setText(item.data);
                    note.setTextSize(20);
                    note.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textList.addView(note);
                }
            }
        });
    }


    // displays all the notes stored in the database on the screen
    public void setListUp() {
        allNotes = dataDB.dataDAO().findAllData();

        for( Data item: allNotes)
        {
            TextView note = new TextView(this);
            note.setText(item.data);
            note.setTextSize(20);
            note.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textList.addView(note);
        }
    }
}