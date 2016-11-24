package com.example.laudien.graphviewtest;

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
    Button btn_add, btn_generate, btn_reset;
    CheckBox cb_scrollable, cb_scalable, cb_background;
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
        graphView.addSeries(series);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_generate = (Button) findViewById(R.id.btn_generate);
        btn_generate.setOnClickListener(this);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this);
        cb_scrollable = (CheckBox) findViewById(R.id.cb_scrollable);
        cb_scrollable.setOnCheckedChangeListener(this);
        cb_scalable = (CheckBox) findViewById(R.id.cb_scalable);
        cb_scalable.setOnCheckedChangeListener(this);
        cb_background = (CheckBox) findViewById(R.id.cb_background);
        cb_background.setOnCheckedChangeListener(this);
        counter = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                counter++;
                int rand = new Random().nextInt(counter);
                series.appendData(new DataPoint(counter, (rand + counter) * counter), true, 500);
                graphView.onDataChanged(false, false);
                break;
            case R.id.btn_generate:
                onClick(btn_reset);
                for (int i = 0; i < 100; i++)
                    onClick(btn_add);
                break;
            case R.id.btn_reset:
                series.resetData(new DataPoint[]{});
                counter = 0;
                break;
        }
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
            case R.id.cb_background:
                series.setDrawBackground(b);
                graphView.onDataChanged(false, false);
                break;
        }
    }
}
