package com.aemreunal.videorecorder.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aemreunal.videorecorder.R;


public class RootActivity extends Activity {

    public static final int REQUEST_VIDEO_CAPTURE = 1;

    private EditText durationTextField;
    private EditText jobNameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        durationTextField = (EditText) findViewById(R.id.editText);
        jobNameTextField = (EditText) findViewById(R.id.jobNameTextField);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    public void recordButtonTapped(View view) {
        dispatchTakeVideoIntent();
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        // Set duration
        int duration = Integer.parseInt(durationTextField.getText().toString());
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, duration);

/*
        // Set job name
        String jobName = jobNameTextField.getText().toString();
        if (jobName == null || jobName.equals("")) {
            jobName = "job";
        }
        takeVideoIntent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, jobName);
*/

        // Start activity
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            Toast.makeText(this, "Video recorded. URL: " + videoUri, Toast.LENGTH_LONG).show();
        }
    }
}
