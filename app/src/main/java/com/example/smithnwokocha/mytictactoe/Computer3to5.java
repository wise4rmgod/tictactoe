package com.example.smithnwokocha.mytictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Computer3to5 extends AppCompatActivity {
    final static int BY_3 = 1;
    final static int BY_4 = 2;
    final static int BY_5 = 3;
    Button by5, by4, by3, vsHuman, vsComputer, beX, beO, random;

    Animation moveInRight, moveInLeft, moveOutRight, moveOutLeft, moveUp, fadeIn, fadeOut;
    int type;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer3to5);


        by3 = findViewById(R.id.by3);
        by4 = findViewById(R.id.by4);
        by5 = findViewById(R.id.by5);
        beX = findViewById(R.id.x);
        beO = findViewById(R.id.o);

        moveInRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
        moveInLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
        moveOutRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out);
        moveOutLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out);
        moveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_up);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        by3.startAnimation(moveInLeft);
        by4.startAnimation(moveInRight);
        by5.startAnimation(moveInLeft);

        by3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = BY_3;
                showVersus();
            }
        });

        by4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = BY_4;
                showVersus();
            }
        });

        by5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = BY_5;
                showVersus();
            }
        });


        beO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == BY_5)
                    intent = new Intent(Computer3to5.this, Main5x5.class);
                else if (type == BY_4)
                    intent = new Intent(Computer3to5.this, Computergame4x4.class);
                else
                    intent = new Intent(Computer3to5.this, PlayGameWithComputer.class);
                intent.putExtra("playerTurn", 2);
                startActivity(intent);

            }
        });

        beX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == BY_5)
                    intent = new Intent(Computer3to5.this, Main5x5.class);
                else if (type == BY_4)
                    intent = new Intent(Computer3to5.this, Computergame4x4.class);
                else
                    intent = new Intent(Computer3to5.this, PlayGameWithComputer.class);
                intent.putExtra("playerTurn", 1);
                startActivity(intent);
            }
        });
    }

 /**   private void showXO() {
        beO.startAnimation(moveUp);
        beX.startAnimation(moveUp);
        disappearAnim(moveOutLeft, vsComputer);
        beO.setVisibility(View.VISIBLE);
        beX.setVisibility(View.VISIBLE);
    }
**/
    private void showVersus() {
        disappearAnim(moveOutLeft, by3);
        disappearAnim(moveOutRight, by4);
        disappearAnim(moveOutLeft, by5);
        beO.startAnimation(moveUp);
        beX.startAnimation(moveUp);
        beO.setVisibility(View.VISIBLE);
        beX.setVisibility(View.VISIBLE);

    }

    private void disappearAnim(Animation animation, final View button) {

        button.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setVisibility(View.GONE);
            }
        });
        button.setVisibility(View.GONE);
        animation.setAnimationListener(null);
    }
}
