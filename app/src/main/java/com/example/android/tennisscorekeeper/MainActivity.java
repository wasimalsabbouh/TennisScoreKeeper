package com.example.android.tennisscorekeeper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private byte player1NumOfGames,
            player1NumOfSets,
            player1NumOfPoints,
            player2NumOfGames,
            player2NumOfSets,
            player2NumOfPoints;

    private boolean reset,
            player1fault,
            player2fault;

    private int typeOfGame = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button player1ScoreButton = (Button) findViewById(R.id.player1_score_button);

        player1ScoreButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                player1AddingPointProcess();

                if (!player1fault) {
                    Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);
                    player1FaultButton.setBackgroundResource(R.drawable.default_button_design);
                }
                if (player2fault) {
                    Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);
                    player2FaultButton.setBackgroundResource(R.drawable.default_button_design);
                    player2fault = false;
                }
            }

        });


        Button player2ScoreButton = (Button) findViewById(R.id.player2_score_button);

        player2ScoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                player2AddingPointProcess();
                if (!player2fault) {
                    Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);
                    player2FaultButton.setBackgroundResource(R.drawable.default_button_design);
                }
                if (player1fault) {
                    Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);
                    player1FaultButton.setBackgroundResource(R.drawable.default_button_design);
                    player1fault = false;
                }
            }
        });

        final Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);

        player1FaultButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (player1fault) {
                    player2AddingPointProcess();
                    player1fault = false;
                    player1FaultButton.setBackgroundResource(R.drawable.default_button_design);
                    Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);
                    player2FaultButton.setBackgroundResource(R.drawable.default_button_design);
                } else {
                    player1fault = true;
                    player1FaultButton.setBackgroundColor(0xFF8c0002);
                }
            }
        });

        final Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);

        player2FaultButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (player2fault) {
                    player1AddingPointProcess();
                    player2fault = false;

                    player2FaultButton.setBackgroundResource(R.drawable.default_button_design);

                    Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);
                    player1FaultButton.setBackgroundResource(R.drawable.default_button_design);

                } else {
                    player2fault = true;
                    player2FaultButton.setBackgroundColor(0xFF8c0002);
                }
            }
        });

        final Button type3SetsType = (Button) findViewById(R.id.radioButton_3_sets);

        type3SetsType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                typeOfGame = 3;

                colorOfSets();


            }
        });

        final Button type5SetsType = (Button) findViewById(R.id.radioButton_5_sets);

        type5SetsType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                typeOfGame = 5;

                colorOfSets();

            }
        });


        final Button startAndResetButton = (Button) findViewById(R.id.startAndReset_button);

        startAndResetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RadioButton radioButton3Sets = (RadioButton) findViewById(R.id.radioButton_3_sets);
                RadioButton radioButton5Sets = (RadioButton) findViewById(R.id.radioButton_5_sets);


                if (!reset) {
                    reset = true;
                    if (typeOfGame == 3) {
                        radioButton5Sets.setEnabled(false);

                    } else {
                        radioButton3Sets.setEnabled(false);
                    }

                    buttonsActiveDeactivating(true);
                    startAndResetButton.setText(R.string.reset);
                } else {

                    reset = false;

                    resetAllValues();
                    buttonsActiveDeactivating(false);

                    radioButton3Sets.setEnabled(true);
                    radioButton5Sets.setEnabled(true);
                    startAndResetButton.setText(R.string.start);

                }
            }
        });

        buttonsActiveDeactivating(false);

    }

    private void colorOfSets() {
        final Button type3SetsType = (Button) findViewById(R.id.radioButton_3_sets);
        final Button type5SetsType = (Button) findViewById(R.id.radioButton_5_sets);

        if (typeOfGame == 3) {
            type3SetsType.setTextColor(0xFF28446c);
            type5SetsType.setTextColor(Color.WHITE);
        } else {
            type5SetsType.setTextColor(0xFF28446c);
            type3SetsType.setTextColor(Color.WHITE);
        }
    }

    private void buttonsActiveDeactivating(boolean active) {
        Button player1ScoreButton = (Button) findViewById(R.id.player1_score_button);
        Button player2ScoreButton = (Button) findViewById(R.id.player2_score_button);
        Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);
        Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);
        player1ScoreButton.setEnabled(active);
        player2ScoreButton.setEnabled(active);
        player1FaultButton.setEnabled(active);
        player2FaultButton.setEnabled(active);
    }


    private void resetAllValues() {

        player1NumOfGames = 0;
        player1NumOfSets = 0;
        player1NumOfPoints = 0;
        player2NumOfGames = 0;
        player2NumOfSets = 0;
        player2NumOfPoints = 0;
        player1fault = false;

        TextView leftPointCount = (TextView) findViewById(R.id.left_point_count);
        TextView leftGamesCount = (TextView) findViewById(R.id.left_games_count);
        TextView leftSetsCount = (TextView) findViewById(R.id.left_sets_count);
        leftPointCount.setText(R.string.doubleZeros);
        leftGamesCount.setText(R.string.zero);
        leftSetsCount.setText(R.string.zero);
        TextView rightPointCount = (TextView) findViewById(R.id.right_point_count);
        TextView rightGamesCount = (TextView) findViewById(R.id.right_games_count);
        TextView rightSetsCount = (TextView) findViewById(R.id.right_sets_count);
        rightPointCount.setText(R.string.doubleZeros);
        rightGamesCount.setText(R.string.zero);
        rightSetsCount.setText(R.string.zero);
        Button player1FaultButton = (Button) findViewById(R.id.player1_fault_button);
        player1FaultButton.setBackgroundResource(R.drawable.default_button_design);
        Button player2FaultButton = (Button) findViewById(R.id.player2_fault_button);
        player2FaultButton.setBackgroundResource(R.drawable.default_button_design);
        rightPointCount.setTextSize(50);
        leftPointCount.setTextSize(50);

    }

    private void resetPointCounters() {
        TextView leftPointCount = (TextView) findViewById(R.id.left_point_count);
        TextView rightPointCount = (TextView) findViewById(R.id.right_point_count);
        leftPointCount.setText(R.string.doubleZeros);
        rightPointCount.setText(R.string.doubleZeros);
        player1NumOfPoints = 0;
        player2NumOfPoints = 0;
    }


    private void player1AddingPointProcess() {

        player1fault = false;
        TextView leftPointCount = (TextView) findViewById(R.id.left_point_count);
        TextView leftGamesCount = (TextView) findViewById(R.id.left_games_count);
        TextView leftSetsCount = (TextView) findViewById(R.id.left_sets_count);

        TextView rightPointCount = (TextView) findViewById(R.id.right_point_count);


        switch (player1NumOfPoints) {
            case 0:
                player1NumOfPoints = 15;
                leftPointCount.setText(String.valueOf(player1NumOfPoints));
                break;
            case 15:
                player1NumOfPoints = 30;
                leftPointCount.setText(String.valueOf(player1NumOfPoints));
                break;
            case 30:
                player1NumOfPoints = 40;
                leftPointCount.setText(String.valueOf(player1NumOfPoints));

                if (player1NumOfPoints == 40 && player2NumOfPoints == 40) {
                    player1NumOfPoints = 50;
                    player2NumOfPoints = 50;
                    Context context = getApplicationContext();
                    CharSequence text = "DEUCE";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                }

                break;
            case 40:
                resetPointCounters();
                if (player1NumOfGames < 6 && player1NumOfSets < typeOfGame) {
                    player1NumOfGames += 1;
                    leftGamesCount.setText(String.valueOf(player1NumOfGames));
                }
                if (player1NumOfGames == 6 && player1NumOfSets < typeOfGame) {
                    player1NumOfSets += 1;
                    player1NumOfGames = 0;
                    leftGamesCount.setText(String.valueOf(player1NumOfGames));
                    leftSetsCount.setText(String.valueOf(player1NumOfSets));
                }
                if (player1NumOfSets == typeOfGame) {
                    Context context = getApplicationContext();
                    CharSequence text = "PLAYER 1 WINS!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                    resetAllValues();
                    buttonsActiveDeactivating(false);
                }
                player1NumOfPoints = 0;
                break;
            case 50:
                player1NumOfPoints = 60;
                leftPointCount.setTextSize(46);
                leftPointCount.setText(R.string.deuce_point);
                break;
            case 60:
                resetPointCounters();
                if (player1NumOfGames < 6 && player1NumOfSets < typeOfGame) {
                    player1NumOfGames += 1;
                    leftGamesCount.setText(String.valueOf(player1NumOfGames));
                    leftPointCount.setTextSize(50);
                    rightPointCount.setTextSize(50);
                }

                if (player1NumOfGames == 6 && player1NumOfSets < typeOfGame) {
                    player1NumOfSets += 1;
                    player1NumOfGames = 0;
                    leftGamesCount.setText(String.valueOf(player1NumOfGames));
                    leftSetsCount.setText(String.valueOf(player1NumOfSets));
                    leftPointCount.setTextSize(50);
                    rightPointCount.setTextSize(50);
                }

                if (player1NumOfSets == typeOfGame) {
                    Context context = getApplicationContext();
                    CharSequence text = "PLAYER 1 WINS";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                    resetAllValues();
                    buttonsActiveDeactivating(false);
                    leftPointCount.setTextSize(50);
                    rightPointCount.setTextSize(50);
                }
                player1NumOfPoints = 0;

        }

        if (typeOfGame == 3 && (player1NumOfSets - player2NumOfSets) == 2) {
            Context context = getApplicationContext();
            CharSequence text = "PLAYER 1 WINS";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 330);
            toast.show();
            resetAllValues();
            buttonsActiveDeactivating(false);
        } else if (typeOfGame == 5 && (player1NumOfSets - player2NumOfSets) == 3) {
            Context context = getApplicationContext();
            CharSequence text = "PLAYER 1 WINS";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 330);
            toast.show();
            resetAllValues();
            buttonsActiveDeactivating(false);
        }
    }


    private void player2AddingPointProcess() {

        player2fault = false;
        TextView rightPointCount = (TextView) findViewById(R.id.right_point_count);
        TextView rightGamesCount = (TextView) findViewById(R.id.right_games_count);
        TextView rightSetsCount = (TextView) findViewById(R.id.right_sets_count);
        TextView leftPointCount = (TextView) findViewById(R.id.left_point_count);
        switch (player2NumOfPoints) {
            case 0:
                player2NumOfPoints = 15;
                rightPointCount.setText(String.valueOf(player2NumOfPoints));
                break;
            case 15:
                player2NumOfPoints = 30;
                rightPointCount.setText(String.valueOf(player2NumOfPoints));
                break;
            case 30:
                player2NumOfPoints = 40;
                rightPointCount.setText(String.valueOf(player2NumOfPoints));
                if (player2NumOfPoints == 40 && player1NumOfPoints == 40) {
                    player2NumOfPoints = 50;
                    player1NumOfPoints = 50;
                    Context context = getApplicationContext();
                    CharSequence text = "DEUCE";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                }

                break;
            case 40:
                resetPointCounters();
                if (player2NumOfGames < 6 && player2NumOfSets < typeOfGame) {
                    player2NumOfGames += 1;
                    rightGamesCount.setText(String.valueOf(player2NumOfGames));
                }
                if (player2NumOfGames == 6 && player2NumOfSets < typeOfGame) {
                    player2NumOfSets += 1;
                    player2NumOfGames = 0;
                    rightGamesCount.setText(String.valueOf(player2NumOfGames));
                    rightSetsCount.setText(String.valueOf(player2NumOfSets));
                }
                if (player2NumOfSets == typeOfGame) {
                    Context context = getApplicationContext();
                    CharSequence text = "PLAYER 2 WINS";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                    resetAllValues();
                    buttonsActiveDeactivating(false);
                }
                player2NumOfPoints = 0;
                break;
            case 50:
                player2NumOfPoints = 60;
                rightPointCount.setTextSize(46);
                rightPointCount.setText(R.string.deuce_point);
                break;
            case 60:
                resetPointCounters();
                if (player2NumOfGames < 6 && player2NumOfSets < typeOfGame) {
                    player2NumOfGames += 1;
                    rightGamesCount.setText(String.valueOf(player2NumOfGames));
                    rightPointCount.setTextSize(50);
                    leftPointCount.setTextSize(50);
                }
                if (player2NumOfGames == 6 && player2NumOfSets < typeOfGame) {
                    player2NumOfSets += 1;
                    player2NumOfGames = 0;
                    rightGamesCount.setText(String.valueOf(player2NumOfGames));
                    rightSetsCount.setText(String.valueOf(player2NumOfSets));
                    rightPointCount.setTextSize(50);
                    leftPointCount.setTextSize(50);
                }
                if (player2NumOfSets == typeOfGame) {
                    Context context = getApplicationContext();
                    CharSequence text = "PLAYER 2 WINS";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.TOP, 0, 330);
                    toast.show();
                    resetAllValues();
                    buttonsActiveDeactivating(false);
                    rightPointCount.setTextSize(50);
                    leftPointCount.setTextSize(50);
                }
                player2NumOfPoints = 0;
        }

        if (typeOfGame == 3 && (player2NumOfSets - player1NumOfSets) == 2) {
            Context context = getApplicationContext();
            CharSequence text = "PLAYER 2 WINS";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 330);
            toast.show();
            resetAllValues();
            buttonsActiveDeactivating(false);
        } else if (typeOfGame == 5 && (player2NumOfSets - player1NumOfSets) == 3) {
            Context context = getApplicationContext();
            CharSequence text = "PLAYER 2 WINS";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 330);
            toast.show();
            resetAllValues();
            buttonsActiveDeactivating(false);
        }
    }

}
