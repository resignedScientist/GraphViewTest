package com.example.laudien.graphviewtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    Viewport viewPort;
    Button btn_add;
    CheckBox cb_scrollable, cb_scalable;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = (GraphView) findViewById(R.id.graph_view);
        viewPort = graphView.getViewport();
        viewPort.setXAxisBoundsManual(true);
        viewPort.setMaxX(100);
        viewPort.setMinX(0);
        series = new LineGraphSeries<>();
        series.setColor(Color.RED);
        graphView.addSeries(series);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        cb_scrollable = (CheckBox) findViewById(R.id.cb_scrollable);
        cb_scrollable.setOnCheckedChangeListener(this);
        cb_scalable = (CheckBox) findViewById(R.id.cb_scalable);
        cb_scalable.setOnCheckedChangeListener(this);
        counter = 0;
    }

    @Override
    public void onClick(View view) {
        counter++;
        int rand = new Random().nextInt(counter);
        series.appendData(new DataPoint(counter, (rand + counter) * counter), true, 500);
        graphView.onDataChanged(false, false);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_scrollable:
                viewPort.setScrollable(b);
                break;
            case R.id.cb_scalable:
                viewPort.setScalable(b);
                break;
        }
    }
}
