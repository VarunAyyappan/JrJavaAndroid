package jrjava.cookieclicker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int count = 0;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        tv = new TextView(this);
        tv.setText("0");
        tv.setTextSize(40);
        tv.setLayoutParams(param);
        tv.setPadding(10, 100, 10, 150);
        layout.addView(tv);

        ImageButton btn = new ImageButton(this);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.cookie1);
        btn.setImageBitmap(bitmap);
        btn.setLayoutParams(param);
        btn.setBackgroundColor(0x00ffffff);
        btn.setOnClickListener(this);
        layout.addView(btn);

        /*
        button btn = new Button(this);
        btn.setText("Cookies");
        btn.setAllCaps(false);
        btn.setTextSize(60);
        btn.setLayoutParams(param);
        btn.setOnClickListener(this);
        layout.addView(btn);
        */

        this.setContentView(layout);
    }

    public void onClick(View v) {
        count++;
        tv.setText(String.valueOf(count));
        //Toast.makeText(this, "I am in onClick()", Toast.LENGTH_LONG).show();
    }

}
