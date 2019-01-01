package com.example.khuta.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameResultActivity extends AppCompatActivity {

    private Button playAgain;
    private TextView winner;
    private String win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        playAgain = (Button)findViewById(R.id.playAgain);
        winner = (TextView)findViewById(R.id.winner);

        Bundle bundle = getIntent().getExtras();
        win = bundle.getString("winner");

        showWinner();
        playAgain();

    }

    private  void showWinner(){

        if(win.equals("player"))
            winner.setText("Win");
        else
            winner.setText("Loose");

    }


    private void playAgain(){

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
