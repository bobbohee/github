package kr.hs.sdh.github_explore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.listener.OnItemSelectedListener;
import kr.hs.sdh.github_explore.listview.TrendListview;
import kr.hs.sdh.github_explore.thread.TrendThread;

public class MainActivity extends AppCompatActivity {

    private Spinner sinceSpinner;
    private Spinner languageSpinner;

    private ArrayAdapter sinceArrayAdapter;
    private ArrayAdapter languageArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setTrend();
    }

    public void setViews() {
        sinceSpinner = (Spinner) findViewById(R.id.since_spinner);
        languageSpinner = (Spinner) findViewById(R.id.language_spinner);

        sinceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.since, android.R.layout.simple_spinner_dropdown_item);
        languageArrayAdapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_dropdown_item);

        sinceSpinner.setAdapter(sinceArrayAdapter);
        languageSpinner.setAdapter(languageArrayAdapter);

        OnItemSelectedListener sinceItemSelected = new OnItemSelectedListener(sinceArrayAdapter);
        OnItemSelectedListener languageItemSelected = new OnItemSelectedListener(languageArrayAdapter);

        sinceSpinner.setOnItemSelectedListener(sinceItemSelected);
        languageSpinner.setOnItemSelectedListener(languageItemSelected);
    }

    public void setTrend() {
        ArrayList<TrendListview> arrayList = new TrendThread().getArrayList();
    }

}