package com.example.bp3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class activity_event extends AppCompatActivity {
    ArrayList<String> ar = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        setContentView(R.layout.activity_event);
        Button button = (Button)(findViewById(R.id.button));


        button.setOnClickListener(v -> {
            Connectionclass conn = new Connectionclass();

            String model = "eventsoort";
            String models = "eventSoorts";
            int index = 0;
            ar = conn.getConnection(context,model,models,index);
            System.out.println(ar.get(1));
            System.out.println("Done");
        });

    }
}
