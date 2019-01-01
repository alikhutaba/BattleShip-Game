package com.example.khuta.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button Play_B ;
    private RadioGroup radioGroup;
    private String difficult = "EASY" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Play_B = (Button)findViewById(R.id.Play_Button);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        Play();

    }

    public void Play(){

        Play_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                difficult = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                Intent intent = new Intent(MainActivity.this , GameActivity.class);
                intent.putExtra("difficult" , difficult);
                startActivityForResult(intent , 1);
            }
        });
    }
}
