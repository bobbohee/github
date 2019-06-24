package kr.hs.sdh.github_explore.thread;

import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import kr.hs.sdh.github_explore.listview.TrendListView;

public class TrendThread implements Runnable {

    private Handler handler;
    private ArrayList<TrendListView> arrayList;

    private String since = "daily";
    private String language = "";
    private String url;
    private boolean flag;

    public TrendThread() {
    }

    public TrendThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        flag = true;
        arrayList = new ArrayList<>();

        while (flag) {
            try {
                url = "https://github.com/trending/" + language + "?since=" + since;
                Document doc = Jsoup.connect(url).get();
                Elements articles = doc.getElementsByTag("article");
                flag = false;

                for (Element article : articles) {
                    TrendListView trendListView = new TrendListView();

                    // require -> developer, repository
                    Element h1 = article.getElementsByTag("h1").get(0);
                    String project[] = h1.getElementsByTag("a").get(0).text().split("/");
                    String developer = project[0] + "/";
                    String repository = project[1];
                    trendListView.setDeveloper(developer);
                    trendListView.setRepository(repository);

                    // not require -> description
                    Elements ps = article.getElementsByTag("p");
                    String description = ps.size() > 0 ? ps.get(0).text() : "";
                    trendListView.setDescription(description);

                    // not require -> star, share
                    Element div = article.getElementsByTag("div").get(1);
                    Elements spans = div.getElementsByTag("span");
                    Elements as = div.getElementsByTag("a");
                    String star = as.size() > 0 ? as.get(0).text() : "";
                    String share = as.size() > 1 ? as.get(1).text() : "";
                    trendListView.setStar(star);
                    trendListView.setShare(share);

                    // not require -> language
                    String repoLanguage = spans.size() > 2 ? spans.get(2).text() : "";
                    trendListView.setLanguage(repoLanguage);

                    trendListView.setSince(since);
                    arrayList.add(trendListView);
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