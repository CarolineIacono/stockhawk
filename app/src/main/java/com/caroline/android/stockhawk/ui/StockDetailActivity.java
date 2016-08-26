package com.caroline.android.stockhawk.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.caroline.android.stockhawk.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by carolinestewart on 8/11/16.
 */
public class StockDetailActivity extends AppCompatActivity {

    private View errorMessage;
    private LineGraphSeries lineGraph;

    private boolean isLoaded = false;
    private String companySymbol;
    private String companyName;
    private ArrayList<String> labels;
    private ArrayList<Float> values;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        errorMessage = findViewById(R.id.error_message);

        companySymbol = getIntent().getStringExtra("symbol");
        if (savedInstanceState == null) {
            downloadStockDetails();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isLoaded) {
            outState.putString("company_name", companyName);
            outState.putStringArrayList("labels", labels);

            float[] valuesArray = new float[values.size()];
            for (int i = 0; i < valuesArray.length; i++) {
                valuesArray[i] = values.get(i);
            }
            outState.putFloatArray("values", valuesArray);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("company_name")) {
            isLoaded = true;

            companyName = savedInstanceState.getString("company_name");
            labels = savedInstanceState.getStringArrayList("labels");
            values = new ArrayList<>();

            float[] valuesArray = savedInstanceState.getFloatArray("values");
            for (float f : valuesArray) {
                values.add(f);
            }
            onDownloadCompleted();
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return false;
        }
    }

    private void downloadStockDetails() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://chartapi.finance.yahoo.com/instrument/1.0/" + companySymbol + "/chartdata;type=quote;range=1y/json")
                .build();

        client.newCall(request).enqueue(new Callback() {


            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        if (result.startsWith("finance_charts_json_callback( ")) {
                            result = result.substring(29, result.length() - 2);
                        }

                        // Parse JSON
                        JSONObject object = new JSONObject(result);
                        companyName = object.getJSONObject("meta").getString("Company-Name");
                        labels = new ArrayList<>();
                        values = new ArrayList<>();
                        JSONArray series = object.getJSONArray("series");
                        for (int i = 0; i < series.length(); i++) {

                            //get the date and format it for readability
                            JSONObject seriesItem = series.getJSONObject(i);
                            String dateString = seriesItem.getString("Date");

                            //add date to label array and close number to value array
                            labels.add(dateString);
                            values.add(Float.parseFloat(seriesItem.getString("close")));
                        }
                        onDownloadCompleted();

                    } catch (Exception e) {
                        onDownloadFailed();
                        e.printStackTrace();
                    }
                } else {
                    onDownloadFailed();
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                onDownloadFailed();
            }
        });
    }


    private void onDownloadCompleted() {
        StockDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTitle(companyName);


                GraphView graph = (GraphView) findViewById(R.id.linechart);
                LineGraphSeries<DataPoint> lineGraphSeries;
                DataPoint[] dataPoints = new DataPoint[labels.size()];
                //graph.getGridLabelRenderer().setNumHorizontalLabels(4);



                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                try {
                    for (int i = 0; i < labels.size(); i++) {
                        dataPoints[i] = new DataPoint(sdf.parse(labels.get(i)).getTime(), values.get(i));


                    }
                    lineGraphSeries = new LineGraphSeries<>(dataPoints);
                    graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(StockDetailActivity.this));
//                    graph.getViewport().setScrollable(true);
                    graph.addSeries(lineGraphSeries);

                    //graph.getGridLabelRenderer().setHumanRounding(false);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void onDownloadFailed() {
        StockDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                errorMessage.setVisibility(View.VISIBLE);
                setTitle(R.string.error);
            }
        });
    }
}
