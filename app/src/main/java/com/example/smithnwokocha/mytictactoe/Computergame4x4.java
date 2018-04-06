/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.smithnwokocha.mytictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class Computergame4x4 extends AppCompatActivity {
    int turn = 1;
    int win = 0;
    int gamov = 0;
    int flagEndGame=0;
    int flag;
    String displayTurn;
    GridLayout grid;
    Button playBoard[][] = new Button[4][4];
    Button tempBoard[][] = new Button[4][4];
    int boardMatrix[][] = new int[4][4];
    double probMatrix[][] = new double[4][4];
    TextView playerTurn;
    String player1Name;
    String player2Name;
    String numberText;
    int number;
    int moveNumber=1;
    int counter = 0;
    int player1Win = 0, player2Win = 0, draw = 0;
    int flipValue=0;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computergame4x4);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        playerTurn = (TextView) findViewById(R.id.player);
        builder = new AlertDialog.Builder(this);
        Intent intent = getIntent();
        player1Name = intent.getExtras().getString("Player 1");
        player2Name = "Computer";
        numberText = intent.getExtras().getString("Number");
        number = Integer.parseInt(numberText);
        grid = (GridLayout) findViewById(R.id.grid);
        displayTurn=player1Name + "'s turn (X)";
        playerTurn.setText(displayTurn);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                playBoard[i][j] = (Button) grid.getChildAt(4 * i + j);
                boardMatrix[i][j]=0;
            }
        }
        if(flipValue==1){
            computerPlay();
            turn=2;
        }


    }

    public void playmove(View view) {
        int index = grid.indexOfChild(view);
        int i = index / 4;
        int j = index % 4;
        flag = 0;
        if (turn == 1 && gamov == 0 && !(playBoard[i][j].getText().toString().equals("X")) && !(playBoard[i][j].getText().toString().equals("O"))) {


            if(flipValue==0){
                displayTurn=player2Name + "'s turn (O)";
                //  Log.v("BoardMatrix",String.valueOf(boardMatrix[0][0])+" "+String.valueOf(boardMatrix[0][1])+" "+String.valueOf(boardMatrix[0][2])+" "+String.valueOf(boardMatrix[1][0])+" "+String.valueOf(boardMatrix[1][1])+" "+String.valueOf(boardMatrix[1][2])+" "+String.valueOf(boardMatrix[2][0])+" "+String.valueOf(boardMatrix[2][1])+" "+String.valueOf(boardMatrix[2][2]));
                playerTurn.setText(displayTurn);
                playBoard[i][j].setText("X");
                boardMatrix[i][j]=1;
                turn = 2;
                moveNumber++;
                computerPlay();
                turn = 1;
                displayTurn=player1Name + "'s turn (X)";
                moveNumber++;
            }



        } else if (turn == 2 && gamov == 0 && !(playBoard[i][j].getText().toString().equals("X")) && !(playBoard[i][j].getText().toString().equals("O"))) {

            if(flipValue==1){
                displayTurn=player2Name + "'s turn (X)";
                playerTurn.setText(displayTurn);
                playBoard[i][j].setText("O");
                boardMatrix[i][j]=1;
                turn = 1;
                moveNumber++;
                computerPlay();
                displayTurn=player1Name + "'s turn (O)";
                turn = 2;
                moveNumber++;

            }

        }

        checkWin();
        if (gamov == 1) {
            if (win == 1) {
                builder.setMessage(player1Name + " wins!").setTitle("Game over");
                if(flagEndGame==0){
                    player1Win++;
                    counter++;
                }


            } else if (win == 2) {
                builder.setMessage(player2Name + " wins!").setTitle("Game over");
                if(flagEndGame==0){
                    player2Win++;
                    counter++;
                }

            }
            flagEndGame=1;
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    newGame(new View(getApplicationContext()));
                    if (counter == number) {
                        Intent intent = new Intent(getApplicationContext(), SeriesResult.class);
                        intent.putExtra("Player 1 Wins", player1Win);
                        intent.putExtra("Player 2 Wins", player2Win);
                        intent.putExtra("Draws", draw);
                        intent.putExtra("Player 1 Name", player1Name);
                        intent.putExtra("Player 2 Name", player2Name);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                            finish();
                        }

                    }
                }

            });
            AlertDialog dialog = builder.create();
            dialog.show();



        }
        if (gamov == 0) {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    if (!playBoard[i][j].getText().toString().equals("X") && !playBoard[i][j].getText().toString().equals("O")) {
                        flag = 1;
                        break;

                    }
                }
            }
            if (flag == 0) {
                builder.setMessage("It's a draw!").setTitle("Game over");
                if(flagEndGame==0){
                    counter++;
                    draw++;
                }
                flagEndGame=1;
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                        if (counter == number) {
                            Intent intent = new Intent(getApplicationContext(), SeriesResult.class);
                            intent.putExtra("Player 1 Wins", player1Win);
                            intent.putExtra("Player 2 Wins", player2Win);
                            intent.putExtra("Draws", draw);
                            intent.putExtra("Player 1 Name", player1Name);
                            intent.putExtra("Player 2 Name", player2Name);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                                finish();
                            }

                        }
                        else {
                            newGame(new View(getApplicationContext()));
                        }
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }


        }


    }
    int level=0;
    public void randomPlay(){
        int random = (int)(Math.random()*16);
        int i=random/4;
        int j=random%4;
        playBoard[i][j].setText("X");
        boardMatrix[i][j]=1;



    }
    public void computerPlay(){
        int currentTurn = turn;
        int currentMove = moveNumber;
        int i=0,j=0;
        int moveChoice=0;
        int flag=0;
        int flagGameNotOver=0;

        int counter=0;
        double sum=0;
        if(turn==1){
            turn=2;
        }
        else{
            turn=1;
        }
        for(int c=0;c<16;c++){
            i=c/4;
            j=c%4;
            //  tempBoard[i][j].setText(playBoard[i][j].getText().toString());
            probMatrix[i][j]=0;
        }

        for(int c=0; c<16;c++) {
            i = c / 4;
            j = c % 4;
            if (boardMatrix[i][j] == 0) {
                flagGameNotOver=1;
                // Log.e("INCP", "I got "+String.valueOf(i)+" "+String.valueOf(j));
                boardMatrix[i][j] = 1;
                if (flipValue == 1)
                    playBoard[i][j].setText("X");
                else
                    playBoard[i][j].setText("O");
                if (checkWinComp() == 2 && flipValue == 0) {
                    flag=1;
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j] = 0;
                    break;
                } else if (checkWinComp() == 2 && flipValue == 1) {
                    flag=1;
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j] = 0;
                    break;
                }
                if (checkWinComp() == 1 && flipValue == 1) {
                    playBoard[i][j].setText(" ");
                    //  Log.v("CP","I came to the first if");
                    boardMatrix[i][j] = 0;
                    continue;
                } else if (checkWinComp() == 1 && flipValue == 0) {
                    playBoard[i][j].setText(" ");
                    //   Log.v("CP","I came to the second if");
                    boardMatrix[i][j] = 0;
                    continue;

                } else {
                    level++;
                    probMatrix[i][j]=computerAnalyze();
                    //    double value = computerAnalyze();
//                    sum+=value;
//                    counter++;
                    level--;
                    //   Log.v("CP","Analysis has been done! " + String.valueOf(i)+" "+String.valueOf(j));

                }
                playBoard[i][j].setText(" ");
                boardMatrix[i][j] = 0;
                //probMatrix[i][j]=sum;

            }
        }
        if(flagGameNotOver==0){
            return;
        }
        double maxProb=0;
        if(flag==0){
            for(int p=0;p<4;p++){
                for(int q=0;q<4;q++){
                    if(maxProb<probMatrix[p][q]){
                        maxProb=probMatrix[p][q];
                    }
                }
            }
            for(int p=0;p<4;p++){
                for(int q=0;q<4;q++){
                    if(maxProb==probMatrix[p][q] && boardMatrix[p][q]==0){
                        moveChoice=5*p+q;
                        break;
                    }
                }
            }
        }
        else{
            moveChoice=4*i+j;
        }
        turn = currentTurn;
        moveNumber = currentMove;
        int xCoord=moveChoice/4;
        int yCoord=moveChoice%4;
        boardMatrix[xCoord][yCoord]=1;
        if(flipValue==0){
            playBoard[xCoord][yCoord].setText("O");
            displayTurn=player1Name+"'s turn (X)";
            playerTurn.setText(displayTurn);
        }
        else{
            playBoard[xCoord][yCoord].setText("X");
            displayTurn=player1Name+"'s turn (O)";
            playerTurn.setText(displayTurn);
        }

        //  Log.v("CP","I have moved!!! "+ String.valueOf(xCoord)+" "+String.valueOf(yCoord)+" "+String.valueOf(boardMatrix[xCoord][yCoord])+" "+String.valueOf(probMatrix[xCoord][yCoord]));
        //  Log.v("CP",String.valueOf(probMatrix[0][0])+" "+String.valueOf(probMatrix[0][1])+" "+String.valueOf(probMatrix[0][2])+" "+String.valueOf(probMatrix[1][0])+" "+String.valueOf(probMatrix[1][1])+" "+String.valueOf(probMatrix[1][2])+" "+String.valueOf(probMatrix[2][0])+" "+String.valueOf(probMatrix[2][1])+" "+String.valueOf(probMatrix[2][2]));

    }

    public double computerAnalyze() {
        double sum=0;
        int counter=0;
        int flagCheckGameNotOver=0;
        for(int c=0;c<16;c++){
            int i=c/4;
            int j=c%4;

            if(boardMatrix[i][j]==0){
                flagCheckGameNotOver=1;
                boardMatrix[i][j]=1;

                if(turn==1)
                    playBoard[i][j].setText("X");
                else
                    playBoard[i][j].setText("O");
                if(checkWinComp()==2 && flipValue==0){
                    sum=1;
                    //    Log.v("INCA","First If "+String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(level)+" "+String.valueOf(turn));
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j]=0;

                    return sum;
                }
                else if(checkWinComp()==2 && flipValue==1){
                    sum=1;
                    //    Log.v("INCA","Second If "+String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(level)+" "+String.valueOf(turn));
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j]=0;

                    return sum;
                }
                else if(checkWinComp()==1 && flipValue==1){
                    sum=0;
                    //    Log.v("INCA","Third Iff "+String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(level)+" "+String.valueOf(turn));
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j]=0;

                    return sum;
                }
                else if(checkWinComp()==1 && flipValue==0){
                    sum=0;
                    //    Log.v("INCA","Fourth If "+String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(level)+" "+String.valueOf(turn));
                    playBoard[i][j].setText(" ");
                    boardMatrix[i][j]=0;

                    return sum;
                }
                else {
                    //    Log.v("INCA","In Else "+String.valueOf(i)+" "+String.valueOf(j)+" "+String.valueOf(level)+" "+String.valueOf(turn));
                    counter++;
                    if(turn==1){
                        turn=2;
                    }
                    else{
                        turn=1;
                    }
                    level++;
                    double value=computerAnalyze();
                    level--;
                    sum+=value;
                    //   Log.v("INCA",String.valueOf(sum));

                }
                playBoard[i][j].setText(" ");
                boardMatrix[i][j]=0;
                if(turn==1){
                    turn=2;
                }
                else{
                    turn=1;
                }
            }

        }
        //  Log.v("SUMC",String.valueOf(sum)+" "+String.valueOf(counter));
        if(flagCheckGameNotOver==0){
            return 0.5;
        }
        double average = ((double) sum)/ ((double) counter);
        return average;
    }

    public void newGame(View view) {

        win = 0;
        gamov = 0;
        turn=1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                playBoard[i][j].setText(" ");
                playBoard[i][j].setTextColor(Color.WHITE);
                boardMatrix[i][j]=0;
            }
        }

        if(flipValue==0){
            if(flagEndGame==1){
                flipValue=1;
                displayTurn=player2Name + "'s turn (X)";
                playerTurn.setText(displayTurn);
            }
            else{
                displayTurn=player1Name + "'s turn (X)";
                playerTurn.setText(displayTurn);
            }


        }
        else if(flipValue==1 ){
            if(flagEndGame==1){
                flipValue=0;
                displayTurn=player1Name + "'s turn (X)";
                playerTurn.setText(displayTurn);
            }
            else{
                displayTurn=player2Name + "'s turn (X)";
                playerTurn.setText(displayTurn);
            }



        }
        flagEndGame=0;
        if(flipValue==1){
            //    Log.e("INNEW","I am here to create a new game with computer x!");
            randomPlay();
            //    Log.e("INNEW","I am out!");
            turn=2;
        }
    }

    public void checkWin() {
        for (int i = 0; i < 5; i++) {
            if (playBoard[i][0].getText().toString().equals(playBoard[i][1].getText().toString()) && playBoard[i][0].getText().toString().equals(playBoard[i][2].getText().toString())  && playBoard[i][0].getText().toString().equals(playBoard[i][3].getText().toString()) ) {
                if (playBoard[i][0].getText().toString().equals("X")) {
                    gamov = 1;
                    if(flipValue==0)
                        win = 1;
                    else if(flipValue==1)
                        win=2;


                } else if (playBoard[i][0].getText().toString().equals("O")) {
                    gamov = 1;
                    if(flipValue==0)
                        win = 2;
                    else if(flipValue==1)
                        win=1;

                }
                if (!playBoard[i][0].getText().toString().equals(" ")) {
                    playBoard[i][0].setTextColor(Color.RED);
                    playBoard[i][1].setTextColor(Color.RED);
                    playBoard[i][2].setTextColor(Color.RED);
                    playBoard[i][3].setTextColor(Color.RED);


                }

            }
            if (playBoard[0][i].getText().toString().equals(playBoard[1][i].getText().toString()) && playBoard[0][i].getText().toString().equals(playBoard[2][i].getText().toString()) && playBoard[0][i].getText().toString().equals(playBoard[3][i].getText().toString()) ) {
                if (playBoard[0][i].getText().toString().equals("X")) {
                    gamov = 1;
                    if(flipValue==0)
                        win = 1;
                    else if(flipValue==1)
                        win=2;


                } else if (playBoard[0][i].getText().toString().equals("O")) {
                    gamov = 1;
                    if(flipValue==0)
                        win = 2;
                    else if(flipValue==1)
                        win=1;

                }
                if (!playBoard[0][i].getText().toString().equals(" ")) {
                    playBoard[0][i].setTextColor(Color.RED);
                    playBoard[1][i].setTextColor(Color.RED);
                    playBoard[2][i].setTextColor(Color.RED);
                    playBoard[3][i].setTextColor(Color.RED);

                }

            }


        }
        if (playBoard[0][0].getText().toString().equals(playBoard[1][1].getText().toString()) && playBoard[0][0].getText().toString().equals(playBoard[2][2].getText().toString()) && playBoard[0][0].getText().toString().equals(playBoard[3][3].getText().toString()) ) {
            if (playBoard[0][0].getText().toString().equals("X")) {
                gamov = 1;
                if(flipValue==0)
                    win = 1;
                else if(flipValue==1)
                    win=2;


            } else if (playBoard[0][0].getText().toString().equals("O")) {
                gamov = 1;
                if(flipValue==0)
                    win = 2;
                else if(flipValue==1)
                    win=1;

            }
            if (!playBoard[0][0].getText().toString().equals(" ")) {
                playBoard[0][0].setTextColor(Color.RED);
                playBoard[1][1].setTextColor(Color.RED);
                playBoard[2][2].setTextColor(Color.RED);
                playBoard[3][3].setTextColor(Color.RED);

            }


        }
        if (playBoard[0][3].getText().toString().equals(playBoard[1][3].getText().toString()) && playBoard[0][3].getText().toString().equals(playBoard[2][2].getText().toString()) && playBoard[0][3].getText().toString().equals(playBoard[3][1].getText().toString())  && playBoard[0][3].getText().toString().equals(playBoard[3][0].getText().toString()) ) {
            if (playBoard[0][3].getText().toString().equals("X")) {
                gamov = 1;
                if(flipValue==0)
                    win = 1;
                else if(flipValue==1)
                    win=2;


            } else if (playBoard[0][3].getText().toString().equals("O")) {
                gamov = 1;
                if(flipValue==0)
                    win = 2;
                else if(flipValue==1)
                    win=1;

            }
            if (!playBoard[3][0].getText().toString().equals(" ")) {
                playBoard[3][0].setTextColor(Color.RED);
                playBoard[3][1].setTextColor(Color.RED);
                playBoard[2][2].setTextColor(Color.RED);
                playBoard[1][3].setTextColor(Color.RED);

            }


        }
    }

    public int checkWinComp() {
        for (int i = 0; i < 5; i++) {
            if (playBoard[i][0].getText().toString().equals(playBoard[i][1].getText().toString()) && playBoard[i][0].getText().toString().equals(playBoard[i][2].getText().toString()) && playBoard[i][0].getText().toString().equals(playBoard[i][3].getText().toString()) ) {
                if (playBoard[i][0].getText().toString().equals("X")) {

                    if(flipValue==0)
                        return 1;
                    else if(flipValue==1)
                        return 2;


                } else if (playBoard[i][0].getText().toString().equals("O")) {

                    if(flipValue==0)
                        return 2;
                    else if(flipValue==1)
                        return 1;

                }


            }
            if (playBoard[0][i].getText().toString().equals(playBoard[1][i].getText().toString()) && playBoard[0][i].getText().toString().equals(playBoard[2][i].getText().toString())  && playBoard[0][i].getText().toString().equals(playBoard[3][i].getText().toString()) ) {
                if (playBoard[0][i].getText().toString().equals("X")) {

                    if(flipValue==0)
                        return 1;
                    else if(flipValue==1)
                        return 2;


                } else if (playBoard[0][i].getText().toString().equals("O")) {

                    if(flipValue==0)
                        return 2;
                    else if(flipValue==1)
                        return 1;

                }


            }


        }
        if (playBoard[0][0].getText().toString().equals(playBoard[1][1].getText().toString()) && playBoard[0][0].getText().toString().equals(playBoard[2][2].getText().toString()) && playBoard[0][0].getText().toString().equals(playBoard[3][3].getText().toString()) ) {
            if (playBoard[0][0].getText().toString().equals("X")) {

                if(flipValue==0)
                    return 1;
                else if(flipValue==1)
                    return 2;


            } else if (playBoard[0][0].getText().toString().equals("O")) {

                if(flipValue==0)
                    return 2;
                else if(flipValue==1)
                    return 1;

            }



        }

        if (playBoard[0][3].getText().toString().equals(playBoard[1][3].getText().toString()) && playBoard[0][3].getText().toString().equals(playBoard[2][2].getText().toString()) && playBoard[0][4].getText().toString().equals(playBoard[3][1].getText().toString()) && playBoard[0][3].getText().toString().equals(playBoard[3][1].getText().toString()) ) {
            if (playBoard[0][3].getText().toString().equals("X")) {
                if(flipValue==0)
                    return 1;
                else if(flipValue==1)
                    return 2;


            } else if (playBoard[0][3].getText().toString().equals("O")) {

                if(flipValue==0)
                    return 2;
                else if(flipValue==1)
                    return 1;

            }



        }
        return 0;
    }
}
