package edu.udel.iyannamm.penpals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSignature extends AppCompatActivity {
    private Button submit;
    private EditText suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_signature);

        submit = (Button)findViewById(R.id.submitSuggestion);
        suggestion = (EditText)findViewById(R.id.signature);

        // sets the users signature to whatever the EditText is holding
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.setSignature(suggestion.getText().toString());
            }
        });
    }
}
