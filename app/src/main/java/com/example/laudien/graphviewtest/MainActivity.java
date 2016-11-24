package com.example.laudien.graphviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    Button btn_add;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = (GraphView) findViewById(R.id.graph_view);
        series = new LineGraphSeries<>();
        graphView.addSeries(series);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        counter = 0;
    }

    @Override
    public void onClick(View view) {
        counter++;
        int rand = new Random().nextInt(counter);
        series.appendData(new DataPoint(counter, rand), false, 100);
        graphView.onDataChanged(false, false);
        Log.i("MainActivity", "A new point was added!");
    }
}
