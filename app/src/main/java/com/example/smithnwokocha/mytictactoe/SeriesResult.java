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
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
// this activity shows the score board //
public class SeriesResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_result);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        String player1Wins = String.valueOf(intent.getExtras().getInt("Player 1 Wins"));
        String player2Wins = String.valueOf(intent.getExtras().getInt("Player 2 Wins"));
        String draws = String.valueOf(intent.getExtras().getInt("Draws"));
        String player1Name = intent.getExtras().getString("Player 1 Name");
        String player2Name = intent.getExtras().getString("Player 2 Name");
        TextView player1NameView = (TextView) findViewById(R.id.p1name);
        TextView player2NameView = (TextView) findViewById(R.id.p2name);
        TextView player1WinsView = (TextView) findViewById(R.id.p1wins);
        TextView player2WinsView = (TextView) findViewById(R.id.p2wins);
        TextView drawsView = (TextView) findViewById(R.id.draws);


        if(Integer.parseInt(player1Wins)>Integer.parseInt(player2Wins)){
            player1NameView.setTextColor(Color.GREEN);
            player2NameView.setTextColor(Color.RED);
        }
        else if(Integer.parseInt(player1Wins)<Integer.parseInt(player2Wins)){
            player2NameView.setTextColor(Color.GREEN);
            player1NameView.setTextColor(Color.RED);
        }
        else
        {
            player2NameView.setTextColor(Color.YELLOW);
            player1NameView.setTextColor(Color.YELLOW);
        }
        player1NameView.setText(player1Name);
        player2NameView.setText(player2Name);
        player1WinsView.setText(player1Wins);
        player2WinsView.setText(player2Wins);
        drawsView.setText(draws);

    }

    public void onClickContinue(View view){
        Intent intent = new Intent(this,MainActivity.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
            finish();
        }
    }
}
