package com.teoandjohn.facevents;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainView extends AppCompatActivity {

    /*  Microsoft Emotion API keys
    Endpoint: https://westus.api.cognitive.microsoft.com/emotion/v1.0
Key 1: not commited - get your own
Key 2: not commited - get your own
     */


    //Declare variables related to the view
    private Button selfieButton;
    private TextView eventResults;
    private TextView emotionResults;

    // The URI of photo taken with camera
    private Uri mUriPhotoTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);

        selfieButton = findViewById(R.id.selfieButton);
        emotionResults = findViewById(R.id.emotionsTextView);
        eventResults = findViewById(R.id.eventsTextView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               // startActivity(intent); //mention here the permission given in AndroidManifest.XML

                //code taken from Cognitive Emotion Android Github project: SelectImageActivity.java line 105
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {

                    // Save the photo taken to a temporary file.
                    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file = null;
                    try {
                        file = File.createTempFile("IMG_", ".jpg", storageDir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mUriPhotoTaken = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);
                        startActivity(intent); //mention here the permission given in AndroidManifest.XML

                    //future implementation will consist of CameraActivity
                }

            }
        };
        selfieButton.setOnClickListener(listener);
    }
}
