package kr.hs.sdh.github_explore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.listview.TrendListview;

public class TrendAdapter extends BaseAdapter {

    private ArrayList<TrendListview> arrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context CONTEXT = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_trend, parent, false);
        }

        TextView txtDeveloper = (TextView) convertView.findViewById(R.id.txt_developer);
        TextView txtProject = (TextView) convertView.findViewById(R.id.txt_project);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txt_description);

        TrendListview trendListview = arrayList.get(position);

        txtDeveloper.setText(trendListview.getDeveloper());
        txtProject.setText(trendListview.getProject());
        txtDescription.setText(trendListview.getDescription());

        return convertView;
    }

}
