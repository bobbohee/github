package kr.hs.sdh.github_explore.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
    private TextView txtMenuExplore;
    private TextView txtMenuUser;
    private Spinner spinSince;
    private Spinner spinLanguage;
    private ListView listView;
    private RelativeLayout relHeader;
    private LinearLayout linHamburger;
    private LinearLayout linMenu;
    private LinearLayout linMain;
    private LinearLayout linUser;
    private LinearLayout linTrend;
    private Button btnGoUser;

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
        txtMenuExplore = (TextView) findViewById(R.id.txt_menu_explore);
        txtMenuUser = (TextView) findViewById(R.id.txt_menu_user);
        spinSince = (Spinner) findViewById(R.id.since_spinner);
        spinLanguage = (Spinner) findViewById(R.id.language_spinner);
        listView = (ListView) findViewById(R.id.list_view);
        relHeader = (RelativeLayout) findViewById(R.id.rel_header);
        linHamburger = (LinearLayout) findViewById(R.id.lin_hamburger);
        linMenu = (LinearLayout) findViewById(R.id.lin_menu);
        linMain = (LinearLayout) findViewById(R.id.lin_main);
        linUser = (LinearLayout) findViewById(R.id.lin_user);
        linTrend = (LinearLayout) findViewById(R.id.lin_trend);
        btnGoUser = (Button) findViewById(R.id.btn_go_user);

        trendAdapter = new TrendAdapter(this);

        sinceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.since, android.R.layout.simple_spinner_dropdown_item);
        languageArrayAdapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_dropdown_item);
        spinSince.setAdapter(sinceArrayAdapter);
        spinLanguage.setAdapter(languageArrayAdapter);

        clickListener = new OnClickListener(relHeader, linMenu, linMain, linUser, linTrend);
        sinceItemSelected = new OnItemSelectedListener(handler, sinceArrayAdapter);
        languageItemSelected = new OnItemSelectedListener(handler, languageArrayAdapter);

        btnGoUser.setOnClickListener(clickListener);
        txtMenuExplore.setOnClickListener(clickListener);
        txtMenuUser.setOnClickListener(clickListener);
        linHamburger.setOnClickListener(clickListener);
        spinSince.setOnItemSelectedListener(sinceItemSelected);
        spinLanguage.setOnItemSelectedListener(languageItemSelected);
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