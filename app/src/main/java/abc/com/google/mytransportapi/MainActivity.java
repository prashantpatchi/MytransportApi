package abc.com.google.mytransportapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView textView,textView2;
    RequestQueue queue;
    String stname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        queue = Volley.newRequestQueue(this);
        stname=getIntent().getExtras().getString("stname");
        String url ="http://transport.opendata.ch/v1/stationboard?station="+stname+"&limit=10";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("JsonResponce",response.toString());
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject jj=jsonObject.getJSONObject("station");
                    String id=jj.getString("id");
                    String name=jj.getString("name");
                    JSONObject jsonObject1=jj.getJSONObject("coordinate");
                    double x=jsonObject1.getDouble("x");
                    double y=jsonObject1.getDouble("y");
                    textView.setText(id+name+"\n X="+x+"\nY:"+y);
                    //work from Json Array
                    JSONArray jsonArray=jsonObject.getJSONArray("stationboard");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jj1=jsonArray.getJSONObject(i);
                        JSONObject jj2=jj1.getJSONObject("stop");
                        String deptime=jj2.getString("departure");
                        String plateform=jj2.getString("platform");
                   textView2.append("departure:="+deptime+"\n plateform"+plateform);


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(stringRequest);
    }
}
