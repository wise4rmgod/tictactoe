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

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

// this is the first activity with buttons to enable the user navigate the app and play the game//
public class MainActivity extends AppCompatActivity {
    final Context context = this;
    ImageView image;
    Animation  fadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        image = findViewById(R.id.imageView);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        image.startAnimation(fadeIn);
    }
    public void playTwoPGame(View view) {
        Intent intent = new Intent(this,Human3x3and5x5.class);
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }

    }

       public void playSinglePGame(View view) {
        Intent intent = new Intent(this, Computer3to5.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void about(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("About Me");

        // set dialog message
        alertDialogBuilder
                .setMessage("The app is created by Nwokocha Wisdom Maduabuchi" + " " + "E-mail:Wise4rmgod@gmail.com"+" "+ "Phone:07039120539")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity

                    }
                })
                .setNegativeButton("",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }



    public void howtoplay(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("How To Play Tic Tac Toe....");

        // set dialog message
        alertDialogBuilder
                .setMessage("Tic-tac-toe (also known as noughts and crosses or Xs and Os) is a android game for single and two players, " +
                        "X and O," +
                        " who take turns marking the spaces in a 3x3,4x4,5x5 grid."+"The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity

                    }
                })
                .setNegativeButton("",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

   public void quit(View view){
        finish();
        System.exit(0);
   }
}
