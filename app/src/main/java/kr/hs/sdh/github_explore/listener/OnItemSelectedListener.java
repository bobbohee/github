package kr.hs.sdh.github_explore.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private String since;
    private ArrayAdapter arrayAdapter;

    public OnItemSelectedListener() {

    }

    public OnItemSelectedListener(ArrayAdapter arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sinceCode = arrayAdapter.getItem(position) + "";

        switch (sinceCode) {
            case "today":
                since = "daily";
                break;
            case "this week":
                since = "weekly";
                break;
            case "this month":
                since = "monthly";
                break;
        }

        Toast.makeText(parent.getContext(), since, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
