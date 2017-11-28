package co.miniforge.corey.mediatracker.media_recycler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;
import co.miniforge.corey.mediatracker.ui_helpers.ThemeHelper;

import static co.miniforge.corey.mediatracker.MyListActivity.mediaExtra;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaDetailActivity extends AppCompatActivity {

    EditText title;
    EditText url;
    EditText description;
    Button save;
    Intent intent;
    Button changeThemeButton;

    ThemeHelper themeHelper;

    JSONObject jsonData;
    private MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        url = (EditText) findViewById(R.id.link);
        description = (EditText) findViewById(R.id.description);
        title = (EditText) findViewById(R.id.title);
        save = (Button) findViewById(R.id.save);

        intentData();
        bindData();

        saveOnClickListener();

    }

    private void intentData() {
        if (getIntent().hasExtra(MediaViewHolder.json)) {
            String json = getIntent().getStringExtra(MediaViewHolder.json);
            try {
                this.jsonData = new JSONObject(json);
                mediaItem = new MediaItem(this.jsonData);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void bindData() {
        url.setText(mediaItem.url);
        description.setText(mediaItem.description);
        title.setText(mediaItem.title);

        changeThemeButton = (Button) findViewById(R.id.themeButton);

        themeHelper = new ThemeHelper(getApplicationContext());
    }

    private void saveOnClickListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplication(), "hi", Toast.LENGTH_SHORT).show();
                mediaItem.description =  description.getText().toString();
                mediaItem.url = url.getText().toString();
                mediaItem.title = title.getText().toString();


                promptConfirmation();

            }
        });

        changeThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeHelper.darkThemeEnabled()) {

                    themeHelper.enableDarkTheme(false);
                }
                else{
                    themeHelper.enableDarkTheme(true);
                }

                themeHelper.themeTextView(title,description,url);
                themeHelper.themeBackground(title);
                themeHelper.themeBackground(description);
                themeHelper.themeBackground(url);



            }
        });
    }

    private void promptConfirmation() {
        //https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
//Make sure to put the code in the activity, the builder requires an activity to be passed in
//import android.support.v7.app for the alert dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Changes").setMessage("Are you sure you want to save these changes?");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Put the start activity with intent code here
                                intent = new Intent(getApplicationContext(), MyListActivity.class);

                intent.putExtra(mediaExtra, mediaItem.toJson().toString());
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing, unless you want this button to go back to
                // ListActivity without putting an intent extra
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

