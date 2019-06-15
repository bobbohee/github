package kr.hs.sdh.github_explore.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.adapter.TrendAdapter;
import kr.hs.sdh.github_explore.listener.OnItemSelectedListener;
import kr.hs.sdh.github_explore.listview.TrendListview;

public class MainActivity extends AppCompatActivity {

    private Spinner sinceSpinner;
    private Spinner languageSpinner;
    private ListView listView;

    private ArrayList<TrendListview> arrayList;
    private TrendAdapter trendAdapter;
    private OnItemSelectedListener sinceItemSelected;
    private OnItemSelectedListener languageItemSelected;

    private ArrayAdapter sinceArrayAdapter;
    private ArrayAdapter languageArrayAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            arrayList = (ArrayList<TrendListview>) msg.obj;
            trendAdapter.addItem(arrayList);
            listView.setAdapter(trendAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    public void setViews() {
        sinceSpinner = (Spinner) findViewById(R.id.since_spinner);
        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        listView = (ListView) findViewById(R.id.list_view);

        trendAdapter = new TrendAdapter();

        sinceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.since, android.R.layout.simple_spinner_dropdown_item);
        languageArrayAdapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_dropdown_item);
        sinceSpinner.setAdapter(sinceArrayAdapter);
        languageSpinner.setAdapter(languageArrayAdapter);

        sinceItemSelected = new OnItemSelectedListener(handler, sinceArrayAdapter);
        languageItemSelected = new OnItemSelectedListener(handler, languageArrayAdapter);
        sinceSpinner.setOnItemSelectedListener(sinceItemSelected);
        languageSpinner.setOnItemSelectedListener(languageItemSelected);
    }

}