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

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
// this activity ask for the user name and number of games  for the human to system //
public class PlayerNameWithComputer4x4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_with_computer4x4);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void submitName(View view) {
        EditText player1NameBox = (EditText) findViewById(R.id.player1);

        EditText numberOfGames = (EditText) findViewById(R.id.numberOfG);
        String player1NameText = player1NameBox.getText().toString();

        String number = numberOfGames.getText().toString();

        if (number.length() > 3) {
            number = "999";
            numberOfGames.setText(R.string.default_max_games);
        }
        if (number.equals("") || Integer.parseInt(number) <= 0) {
            numberOfGames.setText(R.string.default_min_games);
            number = "1";
        }

//        if(player1NameText.equals("")||player2NameText.equals("")){
//            toastMessage("Please enter names of both the players");
//            return;
//        }
        if (player1NameText.equals("")) {
            player1NameText = "Human";
            player1NameBox.setText("Human");
        }

        Intent intent = new Intent(this, Computergame4x4.class);
        intent.putExtra("Player 1", player1NameText);
        intent.putExtra("Number", number);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            finish();
        }

    }
}
