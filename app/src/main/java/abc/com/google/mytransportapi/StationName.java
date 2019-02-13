package abc.com.google.mytransportapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StationName extends AppCompatActivity {
EditText editText;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_name);
        editText=findViewById(R.id.editText);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(StationName.this,MainActivity.class);
                in.putExtra("stname",editText.getText().toString());
                startActivity(in);
            }
        });
    }
}
