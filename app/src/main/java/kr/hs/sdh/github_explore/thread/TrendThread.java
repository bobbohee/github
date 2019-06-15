package kr.hs.sdh.github_explore.thread;

import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import kr.hs.sdh.github_explore.listview.TrendListview;

public class TrendThread implements Runnable {

    private String since = "daily";
    private String language = "";
    private String url;
    private boolean flag;
    private ArrayList<TrendListview> arrayList;
    private Handler handler;

    public TrendThread() { }

    public TrendThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        flag = true;
        arrayList = new ArrayList<>();

        while(flag) {
            try {
                url = "https://github.com/trending/" + language + "?since=" + since;
                Document doc = Jsoup.connect(url).get();
                Elements articles = doc.getElementsByTag("article");
                flag = false;

                for(Element article: articles) {
                    TrendListview trendListview = new TrendListview();

                    Elements h1s = article.getElementsByTag("h1");
                    Elements ps = article.getElementsByTag("p");

                    for(Element h1: h1s) {
                        Elements as = h1.getElementsByTag("a");

                        for (Element a: as) {
                            String title[] = a.text().split("/");
                            String developer = title[0] + " / ";
                            String project = title[1];

                            trendListview.setDeveloper(developer);
                            trendListview.setProject(project);
                        }
                    }

                    for(Element p: ps) {
                        String description = p.text();

                        trendListview.setDescription(description);
                    }

                    arrayList.add(trendListview);
                }
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        Message msg = new Message();
        msg.obj = arrayList;
        handler.sendMessage(msg);
    }

    public void setSince(String since) {
        this.since = since;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
