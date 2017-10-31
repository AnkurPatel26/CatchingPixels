package com.example.macadmin.catchingpixels;

import android.graphics.Color;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView PX, Base;
    TextView Score, Lives;
    Button Left, Right,playAgain;
    RelativeLayout main;
    Runnable refresh;
    boolean max;
    int H, W,s=0,l=3;
    float X,Y,BY,BX;
    int speed=100;
    float rotateDegree = 0;

  Random Rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PX = (ImageView) findViewById(R.id.Pixel);
        Base = (ImageView) findViewById(R.id.Base);
        Score = (TextView) findViewById(R.id.lblScore);
        Lives = (TextView) findViewById(R.id.lblLives);
        Left = (Button) findViewById(R.id.btnLeft);
        Right = (Button) findViewById(R.id.btnRight);
        main = (RelativeLayout) findViewById(R.id.activity_main);
        playAgain = (Button) findViewById(R.id.playAgain);

        Score.setText(String.valueOf(s));
        Lives.setText(String.valueOf(l));
        final Handler handler = new Handler();

        Right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BX = Base.getX();
                if(BX<950){
                BX +=100;
                Base.setX(BX);}
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s=0;
                l=3;
                speed = 100;
                Score.setText(String.valueOf(s));
                Lives.setText(String.valueOf(l));
                playAgain.setVisibility(View.GONE);

            }
        });

        Left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BX = Base.getX();
                if(BX > 40){
                BX -=100;
                Base.setX(BX);}
            }
        });

        refresh = new Runnable() {
            @Override

            public void run() {
                BX = Base.getX();
                BY = Base.getY();
                X = PX.getX();
                Y = PX.getY();


                    //scoreupdate
                    //if(l>0){}
                if (l>0) {

                    //going down
                    if (Y < 1620) {
                        Y += 30;
                        PX.setY(Y);
                        rotateDegree = rotateDegree + 20;
                        PX.setRotation(rotateDegree);
                    }

                    if (Y > 1600 && X > BX - 300 || Y > 1600 && X < BX + 300) {
                        s += 1;
                        Score.setText(String.valueOf(s));
                        PX.setY(0);
                        //l=l+1;

                        PX.setX(Rand.nextInt(1180));
                        speed-=10;


                    }
                    if (Y > 1600 && X < BX - 300 || Y > 1600 && X > BX + 300) {
                        PX.setY(0);
                        l -= 1;
                        s -= 1;
                        Score.setText(String.valueOf(s));
                        Lives.setText(String.valueOf(l));
                        PX.setX(Rand.nextInt(1180));




                    }
                }
                else if(l==0){
                    /*Toast.makeText(MainActivity.this, "Sorry!!! Game Over",
                            Toast.LENGTH_SHORT).show();*/
                    playAgain.setVisibility(View.VISIBLE);
                   handler.removeCallbacksAndMessages(null);
                    }

                handler.postDelayed(this,speed);
            }





        };

        handler.postDelayed(refresh, speed);
    }


}






