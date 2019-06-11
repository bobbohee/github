package kr.hs.sdh.github_explore.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private String since = "daily";
    private String language = "";
    private ArrayAdapter arrayAdapter;

    public OnItemSelectedListener() {

    }

    public OnItemSelectedListener(ArrayAdapter arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = arrayAdapter.getItem(position) + "";

        switch (item) {
            case "today":
                since = "daily";
                break;
            case "this week":
                since = "weekly";
                break;
            case "this month":
                since = "monthly";
                break;
            case "All":
                language = "";
                break;
            case "Unknown":
                language = "unknown";
                break;
            case "HTML":
                language = "html";
                break;
            case "Java":
                language = "java";
                break;
            case "JavaScript":
                language = "javascript";
                break;
            case "Kotlin":
                language = "kotlin";
                break;
            case "PHP":
                language = "php";
                break;
            case "TypeScript":
                language = "typescript";
                break;
            case "Vue":
                language = "vue";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
