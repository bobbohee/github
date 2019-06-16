package kr.hs.sdh.github_explore.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.sdh.github_explore.R;
import kr.hs.sdh.github_explore.listview.TrendListView;

public class TrendAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TrendListView> arrayList = new ArrayList<>();

    public TrendAdapter() {
    }

    public TrendAdapter(Context context) {
        this.context = context;
    }


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
        TextView txtRepository = (TextView) convertView.findViewById(R.id.txt_repository);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
        TextView txtLanguage = (TextView) convertView.findViewById(R.id.txt_language);
        TextView txtStar = (TextView) convertView.findViewById(R.id.txt_star);
        TextView txtShare = (TextView) convertView.findViewById(R.id.txt_share);
        ImageView imgLanguage = (ImageView) convertView.findViewById(R.id.img_language);

        TrendListView trendListview = arrayList.get(position);

        txtDeveloper.setText(trendListview.getDeveloper());
        txtRepository.setText(trendListview.getRepository());
        txtDescription.setText(trendListview.getDescription());
        txtLanguage.setText(trendListview.getLanguage());
        txtStar.setText(trendListview.getStar());
        txtShare.setText(trendListview.getShare());
        imgLanguage.setImageDrawable(setImgLanguage(trendListview.getLanguage()));

        return convertView;
    }

    public void addItem(ArrayList<TrendListView> arrayList) {
        this.arrayList = arrayList;
    }

    public Drawable setImgLanguage(String language) {
        Drawable imgLanguage;

        switch (language) {
            case "":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_unknown);
                break;
            case "HTML":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_html);
                break;
            case "Java":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_java);
                break;
            case "JavaScript":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_javascript);
                break;
            case "Kotlin":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_kotlin);
                break;
            case "PHP":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_php);
                break;
            case "TypeScript":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_typescript);
                break;
            case "Vue":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_vue);
                break;
            case "Python":
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_python);
                break;
            default:
                imgLanguage = ContextCompat.getDrawable(context, R.drawable.oval_other);
                break;
        }

        return imgLanguage;
    }

}
