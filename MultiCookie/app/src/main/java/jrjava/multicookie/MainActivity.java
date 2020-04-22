package jrjava.multicookie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout layout;
    private TextView tv;
    private ImageButton[] btns;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        count = 10;
        tv = new TextView(this);
        tv.setText(String.valueOf(count));
        tv.setTextSize(40);
        tv.setLayoutParams(param);
        tv.setPadding(10, 100, 10, 150);
        layout.addView(tv);

        btns = new ImageButton[4];
        int[] resIds = {R.drawable.cookie1, R.drawable.cookie2, R.drawable.cookie3, R.drawable.cookie4};

        for(int i=0; i<btns.length; i++) {
            btns[i] = new ImageButton(this);
            btns[i].setImageBitmap(BitmapFactory.decodeResource(this.getResources(), resIds[i]));
            btns[i].setLayoutParams(param);
            btns[i].setBackgroundColor(0x00ffffff);
            btns[i].setOnClickListener(this);
        }

        layout.addView(btns[0]);
        layout.addView(btns[2]);


        this.setContentView(layout);
    }

    public void onClick(View v) {
       if(v.equals(btns[0]))
           count--;
       else if(v.equals(btns[3]))
           count++;
       else if(v.equals(btns[1]))
           count+=2;

        tv.setText(String.valueOf(count));

        layout.removeAllViews();
        layout.addView(tv);

        ImageButton[] b = btns;
        int rand = (int)(Math.random()*b.length);
        layout.addView(b[rand]);

        b[rand] = b[b.length-1];
        rand = (int)(Math.random()*b.length);
        layout.addView(b[rand]);
    }

}
