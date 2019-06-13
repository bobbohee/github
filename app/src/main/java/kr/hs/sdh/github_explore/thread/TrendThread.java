package kr.hs.sdh.github_explore.thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TrendThread implements Runnable {

    private String since = "daily";
    private String language = "";
    private String url;
    private boolean flag;

    public TrendThread() { }

    @Override
    public void run() {
        flag = true;
        while(flag) {
            try {
                url = "https://github.com/trending/" + language + "?since=" + since;
                Document doc = Jsoup.connect(url).get();
                Elements articles = doc.getElementsByTag("article");
                flag = false;

                for(Element article: articles) {
                    Elements h1s = article.getElementsByTag("h1");
                    Elements ps = article.getElementsByTag("p");

                    for(Element h1: h1s) {
                        Elements as = h1.getElementsByTag("a");

                        for (Element a: as) {
                            String title = a.text();
                        }
                    }

                    for(Element p: ps) {
                        String description = p.text();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
            }
        }
    }

    public void setSince(String since) {
        this.since = since;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
