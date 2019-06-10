package kr.hs.sdh.github_explore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.listener.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    private Spinner sinceSpinner;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    public void setViews() {
        sinceSpinner = (Spinner) findViewById(R.id.since_spinner);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.since, android.R.layout.simple_spinner_dropdown_item);

        OnItemSelectedListener itemSelected = new OnItemSelectedListener(arrayAdapter);

        sinceSpinner.setAdapter(arrayAdapter);
        sinceSpinner.setOnItemSelectedListener(itemSelected);
    }

}
