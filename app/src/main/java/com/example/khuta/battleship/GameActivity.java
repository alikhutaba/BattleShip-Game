package com.example.khuta.battleship;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;



public class GameActivity extends AppCompatActivity {


    private Game game;
    private String difficult;
    private TextView currentPlayerTurn;
    private TextView countOfHits;
    private TextView floatingShips;
    private TextView numberOfShots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setDifficult();
        game = new Game(difficult);
        GameView();
        GameLogic();

    }


    private void setDifficult(){

        Intent intent = getIntent();
        this.difficult = intent.getExtras().getString("difficult");
    }


    private void GameView(){

        /* Define Player Board */
        game.getPlayerBoard().boardView = (BoardView) findViewById(R.id.humanBoard);
        game.getPlayerBoard().boardView.setBoard(game.getPlayerBoard().getGridSize());
        game.getPlayerBoard().boardView.defineCoordinatesSize();
        game.getPlayerBoard().boardView.coordinatesOfPlayer1Ships = game.getPlayerBoard().readBoatCoordinates();

        /* Define computer Board */
        game.getComputerBoard().boardView = (BoardView) findViewById(R.id.computerBoard);
        game.getComputerBoard().boardView.setBoard(game.getComputerBoard().getGridSize());
        game.getComputerBoard().boardView.defineCoordinatesSize();
        game.getComputerBoard().boardView.coordinatesOfPlayer2Ships = game.getComputerBoard().readBoatCoordinates();

        // Define text views
        currentPlayerTurn = (TextView) findViewById(R.id.currentPlayerTurn);
        countOfHits = (TextView) findViewById(R.id.countOfHits);
        floatingShips = (TextView) findViewById(R.id.FloatingShips);
        numberOfShots = (TextView) findViewById(R.id.NumberOfShots);

        floatingShips.setText(String.valueOf(game.getComputerBoard().getNumberOfShipsFloating()));
        currentPlayerTurn.setText(game.getPlayerBoard().getTypeOfPlayer());
        countOfHits.setText(String.valueOf(game.getComputerBoard().getnumberOfHits()));
        numberOfShots.setText(String.valueOf(game.getComputerBoard().getNumberOfShots()));

    }


    private void GameLogic() {

        // Handles the instance where the player1 touches player2's board.
        game.getComputerBoard().boardView.addBoardTouchListener(new BoardView.BoardTouchListener() {

            /* After player taps on computers board */
            @Override
            public void onTouch(int x, int y) {

                if(game.getComputerBoard().grid[x][y] >= 0) {

                    if (!game.isGameOver()) {

                        // PLAYER SHOOTS AT COMPUTERS BOARD
                        if (game.shootsAt(game.getComputerBoard(), x, y)) {// Human hits a boat, paint red
                            toast("HIT");
                            floatingShips.setText(String.valueOf(game.getComputerBoard().getNumberOfShipsFloating()));
                            countOfHits.setText(String.valueOf(game.getComputerBoard().getnumberOfHits()));
                        }

                        game.getComputerBoard().shoots();
                        numberOfShots.setText(String.valueOf(game.getComputerBoard().getNumberOfShots()));
                        currentPlayerTurn.setText(game.getComputerBoard().getTypeOfPlayer());

                        Thread timer = new Thread() {
                            public void run() {
                                try {
                                    sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        timer.start();


                        currentPlayerTurn.setText(game.getPlayerBoard().getTypeOfPlayer());


                        // COMPUTER SHOOTS AT PLAYER'S 1 BOARD
                        int randomX = generateRandomCoordinate();
                        int randomY = generateRandomCoordinate();

                        if (game.shootsAt(game.getPlayerBoard(), randomX, randomY))
                            toast("Your boat has been shot!");



                    }
                    else
                        gameEnded();

                }
            }
        });
    }

    private int generateRandomCoordinate() {
        Random random = new Random();
        return random.nextInt(10);
    }


    private void gameEnded(){

        String winner = null;

        if(game.getPlayerBoard().getNumberOfShipsFloating() == 0)
            winner = game.getComputerBoard().getTypeOfPlayer();

        if(game.getComputerBoard().getNumberOfShipsFloating() == 0)
            winner = game.getPlayerBoard().getTypeOfPlayer();

        Intent intent = new Intent(GameActivity.this , GameResultActivity.class);
        intent.putExtra("winner" , winner);
        startActivity(intent);
        finish();
    }


    private void toast(String msg) {
        final Toast toast = Toast.makeText(GameActivity.this, msg, Toast.LENGTH_SHORT);
        toast.show();
        new CountDownTimer(500, 2000) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }










}
