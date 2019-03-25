package com.example.bp3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_event extends AppCompatActivity {
    ArrayList<String> ar = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        setContentView(R.layout.activity_event);
        Button button = (Button)(findViewById(R.id.button));
        Button button2 = (Button)findViewById(R.id.button2);
        TextView txt = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(v -> {
            Connectionclass conn = new Connectionclass();

            String model = "eventsoort";
            String models = "eventSoorts";
            int index = 0;
            conn.getConnection(context,model,models,index);
            ar = conn.deVelden;
            System.out.println(ar.size());

            String x = ar.get(1);
            System.out.println(x);
            System.out.println("Done");
        });

        button2.setOnClickListener(v -> {
            System.out.println(ar.size());
            String x = ar.get(1);
            System.out.println(x);
        });

    }
}
