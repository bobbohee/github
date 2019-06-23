package kr.hs.sdh.github_explore.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import kr.hs.sdh.github_explore.listview.UserCardListView;
import kr.hs.sdh.github_explore.listview.UserRepoListView;

public class UserThread implements Runnable {

    private Context context;
    private Handler handler;
    private String url;
    private String user;

    private String fullName = "";
    private String userName = "";
    private String organization = "";
    private String location = "";
    private String mail = "";
    private String link = "";

    public UserThread() {
    }

    public UserThread(Handler handler, String user) {
        this.handler = handler;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            url = "https://github.com/" + user;
            Document doc = Jsoup.connect(url).get();

            UserCardListView userCardListView = new UserCardListView();

            // not require -> fullName
            Elements vcardFullnames = doc.getElementsByClass("vcard-fullname");
            fullName = vcardFullnames.size() > 0 ? vcardFullnames.get(0).text() : "";
            userCardListView.setFullName(fullName);

            // require -> userName
            Element vcardUsername = doc.getElementsByClass("vcard-username").get(0);
            userName = vcardUsername.text();
            userCardListView.setUserName(userName);

            Element vcardDetails = doc.getElementsByClass("vcard-details").get(0);
            Elements itemprops = vcardDetails.getElementsByAttribute("itemprop");

            for (Element itemprop : itemprops) {
                String item = itemprop.attr("itemprop");
                switch (item) {
                    case "worksFor":
                        organization = itemprop.getElementsByTag("div").get(0).text();
                        userCardListView.setOrganization(organization);
                        break;
                    case "homeLocation":
                        location = itemprop.getElementsByTag("span").get(0).text();
                        userCardListView.setLocation(location);
                        break;
                    case "email":
                        mail = itemprop.getElementsByTag("a").get(0).text();
                        userCardListView.setMail(mail);
                        break;
                    case "url":
                        link = itemprop.getElementsByTag("a").get(0).text();
                        userCardListView.setLink(link);
                        break;
                }
            }

            Elements pinnedItems = doc.getElementsByClass("pinned-item-list-item-content");
            for (Element pinnedItem : pinnedItems) {
                UserRepoListView userRepoListView = new UserRepoListView();

                // require -> repository
                Element a = pinnedItem.getElementsByTag("a").get(0);
                String repository = a.text();
                userRepoListView.setRepository(repository);

                // not require -> description
                Elements ps = pinnedItem.getElementsByClass("pinned-item-desc");
                String description = ps.size() > 0 ? ps.get(0).text() : "";
                userRepoListView.setDescription(description);

                // not require -> language
                Elements spans = pinnedItem.getElementsByClass("d-inline-block");
                String language = spans.size() > 0 ? spans.get(0).getElementsByTag("span").get(2).text() : "";
                userRepoListView.setLanguage(language);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = new Message();
        handler.sendMessage(msg);
    }

}