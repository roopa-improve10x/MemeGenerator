package com.example.memegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Spinner templatesSp;
    private EditText topTxt;
    private EditText bottomTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupSpinner();
        handleGenerateClick();
    }

    private void setupSpinner() {
        String [] templateNames = getResources().getStringArray(R.array.templates);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, templateNames);
        templatesSp.setAdapter(adapter);
    }

    private void initViews() {
        templatesSp = findViewById(R.id.templates_sp);
        topTxt = findViewById(R.id.top_txt);
        bottomTxt = findViewById(R.id.bottom_txt);
    }

    private void handleGenerateClick() {
        Button generateBtn = findViewById(R.id.generate_btn);
        generateBtn.setOnClickListener(view -> {
            String memeUrl = generateMemeUrl(getTemplateText(), getTopText(), getBottomText());
            // Todo: send url to the next activity
            Intent intent = new Intent(this, MemeActivity.class);
            intent.putExtra(MemeActivity.URL_EXTRA, memeUrl);
            startActivity(intent);
        });
    }

    private String getTemplateText() {
        return templatesSp.getSelectedItem().toString();
    }

    private String getTopText() {
        return topTxt.getText().toString();
    }

    private String getBottomText() {
        return bottomTxt.getText().toString();
    }

    private String generateMemeUrl(String template, String topText, String bottomText) {
        return "https://apimeme.com/meme?meme="+template+"&top="+topText+"&bottom=" + bottomText;
    }
}