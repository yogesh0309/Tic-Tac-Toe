package com.yogesh.dell.connect3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameActive = true;
    int change = 0;
    int[][] checkCond = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void cameIn(View view) {

        ImageView image = (ImageView) view;
        image.setTranslationY(-1000f);
        int Tapped = Integer.parseInt(image.getTag().toString());

        if (gameState[Tapped] == 2 && gameActive) {
            gameState[Tapped] = change;
            if (change == 0) {
                image.setImageResource(R.drawable.red);
                change = 1;
            } else {
                image.setImageResource(R.drawable.yellow);
                change = 0;
            }
            image.animate().translationYBy(1000f).setDuration(300);

            for(int[] winner:checkCond){
                if(gameState[winner[0]]== gameState[winner[1]] && gameState[winner[1]]==gameState[winner[2]] &&
                        gameState[winner[0]] != 2){

                    gameActive = false;
                    String winnerM = "Yellow";

                    if(gameState[winner[0]]==0){
                        winnerM = "Red";
                    }
                    TextView winnermsg = (TextView)findViewById(R.id.myText);
                    winnermsg.setText(winnerM + " Has Won!!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                    layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameOver=true;
                    for(int game:gameState) {
                        if (game == 2){ gameOver = false;}
                    }
                    if(gameOver) {
                        TextView winnermsg = (TextView) findViewById(R.id.myText);
                        winnermsg.setText("It's A Draw!!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }
    public void playagain(View view){
        gameActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.setVisibility(View.INVISIBLE);
        change=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLay);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
