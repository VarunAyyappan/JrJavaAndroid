package jrjava.helloworld;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView[] tvs = new TextView[5];
        String[] texts = {"Hello", "World!", "I am", "in", "Android Class."};
        int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00, 0xff00ffff};
        for(int i=0; i<tvs.length; i++) {
            tvs[i] = new TextView(this);
            tvs[i].setText(texts[i]);
            tvs[i].setTextSize(40);
            tvs[i].setPadding(10, 100, 10, 100);
            tvs[i].setTextColor(colors[i]);
            tvs[i].setLayoutParams(param);
            layout.addView(tvs[i]);
        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setContentView(layout);
    }
}
