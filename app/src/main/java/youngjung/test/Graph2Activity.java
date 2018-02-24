package youngjung.test;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import youngjung.test.ui.base.Demobase;

/**
 * Created by HANSUNG on 2018-02-14.
 */

public class Graph2Activity extends Demobase {
    private HorizontalBarChart chart_ho;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart_ho = findViewById(R.id.chart_horizontal);
        chart_ho.setBackgroundColor(Color.WHITE);
        // mChart.setHighlightEnabled(false);

        chart_ho.setDrawBarShadow(false);

        chart_ho.setDrawValueAboveBar(true);

        chart_ho.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart_ho.setMaxVisibleValueCount(0);

        // scaling can now only be done on x- and y-axis separately
        chart_ho.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        chart_ho.setDrawBarShadow(true);

        //클릭금지
        chart_ho.setClickable(false);
        chart_ho.setDrawGridBackground(true);

        XAxis xl = chart_ho.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);

        setData(1, 100);
        chart_ho.setFitBars(true);
        chart_ho.animateY(2500);

        chart_ho.getLegend().setEnabled(false);

        chart_ho.setDoubleTapToZoomEnabled(false);
    }

    private void setData(int count, float range) {

        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            //float val = (float) (Math.random() * range);
            float val = (float)(70);
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }

        BarDataSet set1;

        if (chart_ho.getData() != null &&
                chart_ho.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)chart_ho.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            chart_ho.getData().notifyDataChanged();
            chart_ho.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");

            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(barWidth);
            chart_ho.setData(data);
        }
    }

    protected RectF mOnValueSelectedRectF = new RectF();
}
