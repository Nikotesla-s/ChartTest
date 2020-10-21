package com.example.charttest;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = (LineChart)findViewById(R.id.chart);
        ChartUtil chartUtil = new ChartUtil(lineChart);
        chartUtil.initChart();

        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * (5 + 1)) + 10;
            values1.add(new Entry(i, val));
        }

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * (5 + 1)) + 10;
            values2.add(new Entry(i, val));
        }
        Drawable drawable1 = getResources().getDrawable(R.drawable.fade_blue);
        Drawable drawable2 = getResources().getDrawable(R.drawable.fade_red);
        chartUtil.showLineChart(values1,"曲线1",Color.RED,drawable1);
        chartUtil.addLine(values2,"曲线2",Color.BLUE,drawable2);


    }




 /*   private void bindData(){
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * (5 + 1)) + 20;
            values.add(new Entry(i, val));
        }

        values1.add(new Entry(4,10));
        values1.add(new Entry(6,15));
        values1.add(new Entry(9,20));
        values1.add(new Entry(12,5));
        values1.add(new Entry(15,30));

        values2.add(new Entry(3,110));
        values2.add(new Entry(6,115));
        values2.add(new Entry(9,130));
        values2.add(new Entry(12,85));
        values2.add(new Entry(15,90));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
        LineDataSet set2;

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0){
            //表中有数据时
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //表中没有数据时
            //设置数据1
            set1 = new LineDataSet(values,"测试数据1");
            set1.setCircleColor(Color.BLACK);
            set1.setColor(Color.RED);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setDrawFilled(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return lineChart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            lineChart.setData(data);

            //设置数据2
        }
    }*/
}