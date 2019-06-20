package kr.hs.sdh.github_explore.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.adapter.TrendAdapter;
import kr.hs.sdh.github_explore.listener.OnClickListener;
import kr.hs.sdh.github_explore.listener.OnItemSelectedListener;
import kr.hs.sdh.github_explore.listview.TrendListView;

public class MainActivity extends AppCompatActivity {

    private TextView txtTrending;
    private Spinner sinceSpinner;
    private Spinner languageSpinner;
    private ListView listView;
    private LinearLayout linearHamburger;
    private LinearLayout linearMenu;

    private String since;
    private String trending = "See what the GitHub community is most excited about ";

    private TrendAdapter trendAdapter;
    private ArrayList<TrendListView> arrayList;

    private ArrayAdapter sinceArrayAdapter;
    private ArrayAdapter languageArrayAdapter;

    private OnClickListener clickListener;
    private OnItemSelectedListener sinceItemSelected;
    private OnItemSelectedListener languageItemSelected;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            arrayList = (ArrayList<TrendListView>) msg.obj;
            trendAdapter.addItem(arrayList);
            listView.setAdapter(trendAdapter);
            setTrending(arrayList.get(0).getSince());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    public void setViews() {
        txtTrending = (TextView) findViewById(R.id.txt_trending);
        sinceSpinner = (Spinner) findViewById(R.id.since_spinner);
        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        listView = (ListView) findViewById(R.id.list_view);
        linearHamburger = (LinearLayout) findViewById(R.id.linear_hamburger);
        linearMenu = (LinearLayout) findViewById(R.id.linear_menu);

        trendAdapter = new TrendAdapter(this);

        sinceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.since, android.R.layout.simple_spinner_dropdown_item);
        languageArrayAdapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_dropdown_item);
        sinceSpinner.setAdapter(sinceArrayAdapter);
        languageSpinner.setAdapter(languageArrayAdapter);

        clickListener = new OnClickListener(this, linearMenu);
        sinceItemSelected = new OnItemSelectedListener(handler, sinceArrayAdapter);
        languageItemSelected = new OnItemSelectedListener(handler, languageArrayAdapter);

        linearHamburger.setOnClickListener(clickListener);
        sinceSpinner.setOnItemSelectedListener(sinceItemSelected);
        languageSpinner.setOnItemSelectedListener(languageItemSelected);
    }

    public void setTrending(String since) {
        switch (since) {
            case "daily":
                this.since = "today";
                break;
            case "weekly":
                this.since = "this week";
                break;
            case "monthly":
                this.since = "this month";
                break;
        }

        txtTrending.setText(trending + this.since);
    }

}