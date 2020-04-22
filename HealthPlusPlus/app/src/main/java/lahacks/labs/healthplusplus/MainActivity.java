package lahacks.labs.healthplusplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONTokener;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Varun on 3/24/2018.
 */

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Profile prof;
    protected String query;
    private String excercise, calories;
    private EditText input;
    private List<ExerciseItem> exerciseItems;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prof = (Profile) new User();
        prof.setAge(15);
        prof.setGender("male");
        prof.setHeight(67/2.54);
        prof.setWeight(135*0.45);

        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        input = (EditText) findViewById(R.id.enteredText);
        exerciseItems = new ArrayList<ExerciseItem>();
        list = (ListView) findViewById(R.id.exerciseList);
    }

    protected void setUser(User user) {
        prof = (Profile) user;
    }

    public void enter(View v) {
        if(v.getId() == R.id.enter) {
            query = input.getText().toString();
            System.out.println(query);
            post();
        }
    }

    private void updateList() {
        exerciseItems.add(new ExerciseItem(excercise, (int)(Double.parseDouble(calories))));
        list.setAdapter(new ExerciseItemAdapter(this, exerciseItems));
    }

    private void post() {
        String url = "https://trackapi.nutritionix.com/v2/natural/exercise";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        //Log.d("Response", response);
                        try {
                            readJson(response);
                        }
                        catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        if(error.getMessage() != null)
                            Log.d("Error.Response", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                String q = "";
                params.put("query", q + query + q);
                params.put("gender", q + String.valueOf(prof.getGender()) + q);
                params.put("weight_kg", String.valueOf(prof.getWeight()));
                params.put("height_cm", String.valueOf(prof.getHeight()));
                params.put("age",String.valueOf(prof.getAge()));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                //params.put("Content-Type", "application/json");
                params.put("x-app-id", "bafc9805");
                params.put("x-app-key", "8924662476b42e67193db2039f77a9df");
                //params.put("x-remote-user-id", "0");
                return params;
            }


        };
        queue.add(postRequest);
    }

    public void readJson(String response) throws IOException {

        String name = "";
        JsonReader in = new JsonReader(new StringReader(response));
        in.setLenient(true);
        in.beginObject();
        in.nextName();
        in.beginArray();

        while(in.hasNext()) {
            in.beginObject();
            while(in.hasNext()) {
                name = in.nextName();

                if(name.equals("nf_calories")){
                    if (in.peek() == JsonToken.NULL)
                        in.nextNull();
                    else {
                        calories = in.nextString();
                    }
                }
                else if(name.equals("name")){
                    if (in.peek() == JsonToken.NULL)
                        in.nextNull();
                    else
                        excercise = in.nextString();
                }
                else
                    in.skipValue();
            }
        }

        updateList();

    }
}
