package com.example.charttest;

import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * @Author:sdh
 * @Description: 图表工具类（基于MPAdroid）
 * @Date:2020/10/12 16:54
 **/
public class ChartUtil {

    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线

    ChartUtil(LineChart lineChart){
        this.lineChart = lineChart;

    }
    /**
     *展示图表
     * @param dataList 数据
     * @param name 曲线名称
     * @param color 曲线颜色
     * @param drawable 曲线背景
     **/
    public void showLineChart(ArrayList<Entry> dataList, String name, int color, Drawable drawable){

        LineDataSet lineDataSet = new LineDataSet(dataList,name);
        initLineDataSet(lineDataSet,color,LineDataSet.Mode.CUBIC_BEZIER);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        if (drawable != null){
            //设置曲线背景渐变
            setChartFillDrawable(drawable, lineDataSet);
        }
    }

    /**
     * 增加一条曲线
     * @param dataList 曲线的数据
     * @param name 曲线的名称
     * @param color 曲线的颜色
     * @param drawable
     **/
    public void addLine(ArrayList<Entry> dataList, String name, int color, Drawable drawable){
        LineDataSet lineDataSet = new LineDataSet(dataList, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.CUBIC_BEZIER);
        if (drawable != null){
            //设置曲线背景渐变
            setChartFillDrawable(drawable, lineDataSet);
        }
        lineChart.getLineData().addDataSet(lineDataSet);
        lineChart.invalidate();
    }

    /**
     * 初始化图表
     **/
    public void initChart(){
        //设置标题
        Description description = new Description();
        //        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);
        //是否绘制网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(false);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //是否显示右边Y轴
        rightYaxis.setEnabled(false);
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        //禁掉X Y轴自己的网格线
        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);
        //设置X Y轴网格线为虚线
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }

    /**
     * 初始化曲线
     * @param lineDataSet 线条
     * @param color 线条颜色
     * @param mode 线条样式
     * **/
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode){
        //是否显示线条的值
        lineDataSet.setDrawValues(false);
        //是否显示线条的点
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }

    }

    /**
     * 设置线条填充背景颜色
     * @param drawable 布局参数
     * @param lineDataSet 曲线
     **/
    private void setChartFillDrawable(Drawable drawable,LineDataSet lineDataSet){
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            //LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }
}
