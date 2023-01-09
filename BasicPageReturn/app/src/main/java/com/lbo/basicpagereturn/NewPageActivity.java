package com.lbo.basicpagereturn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        Button btnReturn = (Button)findViewById(R.id.button_return);
        EditText etInput = (EditText)findViewById(R.id.edittext_input);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra("value", etInput.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
    }
}