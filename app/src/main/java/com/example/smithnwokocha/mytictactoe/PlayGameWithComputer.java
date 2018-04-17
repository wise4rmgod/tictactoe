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

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

//   this activity handles the function for human to play with the system  //
public class PlayGameWithComputer extends AppCompatActivity {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    TextView status, notifyTv;
    LinearLayout notify;
    boolean computerTurn = false;
    boolean isPlay = true;
    String playerSign = "O";
    String computerSign = "X";
    String win;
    Animation animation, animation2;
    MediaPlayer player;
    TextView playerOne, playerTwo, tie, playerOneStat, playerTwoStat, tieStat;
    Button levelOne, levelTwo, levelThree;
    int level = 2;
    int playerTurn;
    SharedPreferences pref;
    ImageButton soundBtn;
    boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_with_computer);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnOne = findViewById(R.id.btn1);
        btnTwo = findViewById(R.id.btn2);
        btnThree = findViewById(R.id.btn3);
        btnFour = findViewById(R.id.btn4);
        btnFive = findViewById(R.id.btn5);
        btnSix = findViewById(R.id.btn6);
        btnSeven = findViewById(R.id.btn7);
        btnEight = findViewById(R.id.btn8);
        btnNine = findViewById(R.id.btn9);
        status = findViewById(R.id.status);
        notify = findViewById(R.id.notify);
        notifyTv = findViewById(R.id.notifyTv);

        playerOne = findViewById(R.id.player_one);
        playerTwo = findViewById(R.id.player_two);
        tie = findViewById(R.id.tie);
        playerOneStat = findViewById(R.id.player_one_stat);
        playerTwoStat = findViewById(R.id.player_two_stat);
        tieStat = findViewById(R.id.tie_stat);

        levelOne = findViewById(R.id.level_one);
        levelTwo = findViewById(R.id.level_two);
        levelThree = findViewById(R.id.level_three);

        Animation logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        logoAnim.setRepeatMode(Animation.REVERSE);

        Intent intent = getIntent();
        playerTurn = intent.getIntExtra("playerTurn", 0);

        switch (playerTurn) {
            case 0:
                Random random = new Random();
                int rand = random.nextInt(10);
                computerTurn = rand % 2 == 1;
                break;
            case 1:
                computerTurn = false;
                break;
            default:
                computerTurn = true;
                break;
        }

        if (!computerTurn) {
            playerSign = "X";
            computerSign = "O";
            playerPlay();
        } else {
            removeClickListener();
            computerPlay();
        }

        playerOne.setText(R.string.computer);
        playerTwo.setText(R.string.player);

        pref = this.getSharedPreferences("leaderboard3", MODE_PRIVATE);
        int pOneScore = pref.getInt("pOneScore", 0);
        int pTwoScore = pref.getInt("pTwoScore", 0);
        int pTie = pref.getInt("pTie", 0);
        playerOneStat.setText(String.valueOf(pOneScore));
        playerTwoStat.setText(String.valueOf(pTwoScore));
        tieStat.setText(String.valueOf(pTie));

        Dialog dialog = new Dialog(this, R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.notify);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);

        player = MediaPlayer.create(this, R.raw.play);
        player.setLooping(false); // Set looping
        player.setVolume(100, 100);

        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                player.seekTo(0);
                player.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                player.seekTo(0);
                player.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        };

        animation.setAnimationListener(animationListener);

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notify.setAnimation(null);
                notify.setVisibility(View.GONE);
            }
        });




        levelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                reset();
                levelOne.setBackgroundColor(getResources().getColor(R.color.btn));
                levelOne.setTextColor(getResources().getColor(R.color.black));
                levelTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelTwo.setTextColor(getResources().getColor(R.color.white));
                levelThree.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelThree.setTextColor(getResources().getColor(R.color.white));
            }
        });

        levelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 2;
                reset();
                levelOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelOne.setTextColor(getResources().getColor(R.color.white));
                levelTwo.setBackgroundColor(getResources().getColor(R.color.btn));
                levelTwo.setTextColor(getResources().getColor(R.color.black));
                levelThree.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelThree.setTextColor(getResources().getColor(R.color.white));
            }
        });

        levelThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 3;
                reset();
                levelOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelOne.setTextColor(getResources().getColor(R.color.white));
                levelTwo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                levelTwo.setTextColor(getResources().getColor(R.color.white));
                levelThree.setBackgroundColor(getResources().getColor(R.color.btn));
                levelThree.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    boolean proTacticalPlay() {
        int p = 0;
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", getPackageName()));
            if (!button.getText().equals(""))
                p++;
            if (i == 9) {
                if (p == 0) {
                    btnFive.setText(computerSign);
                    btnFive.startAnimation(animation);
                    playerPlay();
                    return true;
                }
                if (p == 1) {
                    while (true) {
                        Random random = new Random();
                        int rand = random.nextInt(4) + 1;
                        int q = (rand == 1) ? 1 : (rand == 2) ? 3 : (rand == 3) ? 7 : (rand == 4) ? 5 : 9;
                        Button buttonPlay = findViewById(getResources().getIdentifier("btn" + q, "id", getPackageName()));
                        if (buttonPlay.getText().equals("")) {
                            buttonPlay.setText(computerSign);
                            playerPlay();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean tacticalPlay() {
        for (int i = 1; i < 10; i++) {
            int no = 0, yes = 0;

            if (i == 1 || i == 2 || i == 3) {
                for (int j = 0; j < 7; j++) {
                    int k = i + j;
                    Button button = findViewById(getResources().getIdentifier("btn" + k, "id", this.getPackageName()));
                    if (button.getText().equals(computerSign))
                        yes++;
                    if (button.getText().equals(""))
                        no++;
                    if (no == 2 && yes == 1) {
                        for (int q = 0; q < 3; q++) {
                            int p = i + q;
                            Button buttonPlay = findViewById(getResources().getIdentifier("btn" + p, "id", this.getPackageName()));
                            if (buttonPlay.getText().equals("")) {
                                buttonPlay.setText(computerSign);
                                buttonPlay.startAnimation(animation);
                                return true;
                            }
                            q += 2;
                        }
                    }
                    j += 2;
                }
            }

            no = yes = 0;

            if (i == 1 || i == 4 || i == 7) {
                for (int j = 0; j < 3; j++) {
                    int k = i + j;
                    Button button = findViewById(getResources().getIdentifier("btn" + k, "id", this.getPackageName()));
                    if (button.getText().equals(computerSign))
                        yes++;
                    if (button.getText().equals(""))
                        no++;
                    if (yes == 1 && no == 2) {
                        for (int q = 0; q < 3; q++) {
                            int p = i + q;
                            Button buttonPlay = findViewById(getResources().getIdentifier("btn" + p, "id", this.getPackageName()));
                            if (buttonPlay.getText().equals("")) {
                                buttonPlay.setText(computerSign);
                                buttonPlay.startAnimation(animation);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    void randomPlay() {
        while (true) {
            Random random = new Random();
            int rand = random.nextInt(9) + 1;
            Button button = findViewById(getResources().getIdentifier("btn" + rand, "id", getPackageName()));
            if (button.getText().equals("")) {
                button.setText(computerSign);
                button.startAnimation(animation);
                break;
            }
        }
    }

    boolean playToWin() {
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", this.getPackageName()));
            if (button.getText() == "") {
                button.setText(computerSign);
                if (checkWin(computerSign)) {
                    button.startAnimation(animation);
                    setNotify(win, 1);
                    return true;
                } else {
                    button.setText("");
                }
            }
        }
        return false;
    }

    boolean playToBlock() {
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", this.getPackageName()));
            if (button.getText() == "") {
                button.setText(playerSign);
                if (checkWin(playerSign)) {
                    button.setText(computerSign);
                    button.startAnimation(animation);
                    return true;
                } else {
                    button.setText("");
                }
            }
        }
        return false;
    }

    void removeClickListener() {
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", getPackageName()));
            button.setOnClickListener(null);
        }
    }

    void playerPlay() {

        if (!isPlay)
            return;

        status.setText(R.string.player_turn);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnOne.getText() == "") {
                    btnOne.startAnimation(animation);
                    btnOne.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnTwo.getText() == "") {
                    btnTwo.startAnimation(animation);
                    btnTwo.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnThree.getText() == "") {
                    btnThree.startAnimation(animation);
                    btnThree.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnFour.getText() == "") {
                    btnFour.startAnimation(animation);
                    btnFour.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnFive.getText() == "") {
                    btnFive.startAnimation(animation);
                    btnFive.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSix.getText() == "") {
                    btnSix.startAnimation(animation);
                    btnSix.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSeven.getText() == "") {
                    btnSeven.startAnimation(animation);
                    btnSeven.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEight.getText() == "") {
                    btnEight.startAnimation(animation);
                    btnEight.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnNine.getText() == "") {
                    btnNine.startAnimation(animation);
                    btnNine.setText(playerSign);
                    removeClickListener();
                    if (checkWin(playerSign)) {
                        setNotify(win, 2);
                        return;
                    }
                    computerPlay();
                }
            }
        });
    }

    void computerPlay() {

        if (!isPlay)
            return;

        switch (level) {
            case 1:
                computerPlayEasy();
                break;
            case 2:
                computerPlayMedim();
                break;
            case 3:
                computerPlayHard();
                break;
        }

    }

    void computerPlayHard() {

        status.setText(R.string.computer_turn);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (proTacticalPlay())
                            return;
                        if (playToWin())
                            return;
                        if (!playToBlock())
                            if (!tacticalPlay())
                                randomPlay();
                        if (!checkWin(computerSign))
                            playerPlay();

                    }
                });
            }
        }).start();
    }

    void computerPlayMedim() {

        status.setText(R.string.computer_turn);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!playToWin()) {
                            if (!playToBlock())
                                if (!tacticalPlay())
                                    randomPlay();
                            if (!checkWin(computerSign))
                                playerPlay();
                        }
                    }
                });
            }
        }).start();
    }

    void computerPlayEasy() {

        status.setText(R.string.computer_turn);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!playToWin())
                            randomPlay();
                        if (!checkWin(computerSign))
                            playerPlay();
                    }
                });
            }
        }).start();
    }

    boolean checkWin(String sign) {

        if (!isPlay)
            return false;

        return
                areEqual(btnOne.getText().toString(), btnTwo.getText().toString(), btnThree.getText().toString(), sign) ||
                        areEqual(btnFour.getText().toString(), btnFive.getText().toString(), btnSix.getText().toString(), sign) ||
                        areEqual(btnSeven.getText().toString(), btnEight.getText().toString(), btnNine.getText().toString(), sign) ||
                        areEqual(btnOne.getText().toString(), btnFour.getText().toString(), btnSeven.getText().toString(), sign) ||
                        areEqual(btnTwo.getText().toString(), btnFive.getText().toString(), btnEight.getText().toString(), sign) ||
                        areEqual(btnThree.getText().toString(), btnSix.getText().toString(), btnNine.getText().toString(), sign) ||
                        areEqual(btnOne.getText().toString(), btnFive.getText().toString(), btnNine.getText().toString(), sign) ||
                        areEqual(btnThree.getText().toString(), btnFive.getText().toString(), btnSeven.getText().toString(), sign) ||
                        gameOver();
    }

    boolean areEqual(String a, String b, String c, String sign) {
        Boolean check = (sign.equals(a) && sign.equals(b) && sign.equals(c));
        if (check && sign.equals(computerSign)) {
            win = "Computer wins";
        }
        if (check && sign.equals(playerSign)) {
            win = "Player wins";
        }
        return check;
    }

    boolean gameOver() {

        int p = 0;
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", getPackageName()));
            if (!button.getText().equals(""))
                p++;
        }
        if (p == 9) {
            win = "It's a tie";
            setNotify(win, 0);
            isPlay = false;
        }
        return false;
    }

    void setNotify(String win, int player) {
        status.setText(win);
        notifyTv.setText(win);
        notify.setVisibility(View.VISIBLE);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        notify.setAnimation(animation2);

        if (player == 0) {
            int stat = Integer.parseInt(tieStat.getText().toString()) + 1;
            tieStat.setText(String.valueOf(stat));
            pref.edit().putInt("pTie", stat).apply();
        } else if (player == 1) {
            int stat = Integer.parseInt(playerOneStat.getText().toString()) + 1;
            playerOneStat.setText(String.valueOf(stat));
            pref.edit().putInt("pOneScore", stat).apply();
        } else if (player == 2) {
            int stat = Integer.parseInt(playerTwoStat.getText().toString()) + 1;
            playerTwoStat.setText(String.valueOf(stat));
            pref.edit().putInt("pTwoScore", stat).apply();
        }
    }

    void reset() {
        isPlay = true;
        notify.setAnimation(null);
        notify.setVisibility(View.GONE);
        for (int i = 1; i < 10; i++) {
            Button button = findViewById(getResources().getIdentifier("btn" + i, "id", getPackageName()));
            button.setText("");
        }

        switch (playerTurn) {
            case 0:
                Random random = new Random();
                int rand = random.nextInt(10);
                computerTurn = rand % 2 == 1;
                break;
            case 1:
                computerTurn = false;
                break;
            default:
                computerTurn = true;
                break;
        }

        if (!computerTurn) {
            playerSign = "X";
            computerSign = "O";
            playerPlay();
        } else {
            removeClickListener();
            computerPlay();
        }
    }
}