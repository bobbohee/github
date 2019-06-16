package kr.hs.sdh.github_explore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
        int languageXml = 0;

        switch (language) {
            case "":
                languageXml = R.drawable.oval_unknown;
                break;
            case "C":
                languageXml = R.drawable.oval_c;
                break;
            case "C++":
                languageXml = R.drawable.oval_cpp;
                break;
            case "C#":
                languageXml = R.drawable.oval_cs;
                break;
            case "CSS":
                languageXml = R.drawable.oval_css;
                break;
            case "Dart":
                languageXml = R.drawable.oval_dart;
                break;
            case "Go":
                languageXml = R.drawable.oval_go;
                break;
            case "HTML":
                languageXml = R.drawable.oval_html;
                break;
            case "Java":
                languageXml = R.drawable.oval_java;
                break;
            case "JavaScript":
                languageXml = R.drawable.oval_javascript;
                break;
            case "Jupyter Notebook":
                languageXml = R.drawable.oval_jupyter_notebook;
                break;
            case "Kotlin":
                languageXml = R.drawable.oval_kotlin;
                break;
            case "PHP":
                languageXml = R.drawable.oval_php;
                break;
            case "Python":
                languageXml = R.drawable.oval_python;
                break;
            case "Scala":
                languageXml = R.drawable.oval_scala;
                break;
            case "Shell":
                languageXml = R.drawable.oval_shell;
                break;
            case "TypeScript":
                languageXml = R.drawable.oval_typescript;
                break;
            case "Vue":
                languageXml = R.drawable.oval_vue;
                break;
            default:
                languageXml = R.drawable.oval_other;
                break;
        }

        Drawable languageDraw = ContextCompat.getDrawable(context, languageXml);
        return languageDraw;
    }

}
