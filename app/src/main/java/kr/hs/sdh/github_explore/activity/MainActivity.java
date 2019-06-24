package kr.hs.sdh.github_explore.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    private Spinner spinSince;
    private Spinner spinLanguage;
    private ListView listView;
    private EditText editUser;
    private TextView txtTrending;
    private TextView txtMenuExplore;
    private TextView txtMenuUser;
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

    private ArrayList<TrendListView> trendArrayList;

    private ArrayAdapter sinceArrayAdapter;
    private ArrayAdapter languageArrayAdapter;

    private OnClickListener menuClickListener;
    private OnClickListener userClickListener;
    private OnItemSelectedListener sinceItemSelected;
    private OnItemSelectedListener languageItemSelected;

    private Handler userHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    private Handler trendHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            trendArrayList = (ArrayList<TrendListView>) msg.obj;
            trendAdapter.addItem(trendArrayList);
            listView.setAdapter(trendAdapter);
            setTrending(trendArrayList.get(0).getSince());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    public void setViews() {
        editUser = (EditText) findViewById(R.id.edit_user);
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

        menuClickListener = new OnClickListener(linMenu, linMain, linUser, linTrend);
        userClickListener = new OnClickListener(this, userHandler, editUser, relHeader, linMenu, linMain, linUser, linTrend);
        sinceItemSelected = new OnItemSelectedListener(trendHandler, sinceArrayAdapter);
        languageItemSelected = new OnItemSelectedListener(trendHandler, languageArrayAdapter);

        btnGoUser.setOnClickListener(userClickListener);
        txtMenuExplore.setOnClickListener(menuClickListener);
        txtMenuUser.setOnClickListener(menuClickListener);
        linHamburger.setOnClickListener(menuClickListener);

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