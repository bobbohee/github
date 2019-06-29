package kr.hs.sdh.github_explore.thread;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import kr.hs.sdh.github_explore.listview.UserCardListView;
import kr.hs.sdh.github_explore.listview.UserRepoListView;

public class UserThread implements Runnable {

    private String url;
    private String user;

    private String fullName = "";
    private String userName = "";
    private String organization = "";
    private String location = "";
    private String mail = "";
    private String link = "";

    private Handler userCardHandler;
    private Handler userRepoHandler;

    private UserCardListView userCardListView;
    private ArrayList<UserRepoListView> arrayList;

    public UserThread() {
    }

    public UserThread(Handler userCardHandler, Handler userRepoHandler, String user) {
        this.userCardHandler = userCardHandler;
        this.userRepoHandler = userRepoHandler;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            url = "https://github.com/" + user;
            Document doc = Jsoup.connect(url).get();

            userCardListView = new UserCardListView();

            // require -> userPhoto
            Element uPhoto = doc.getElementsByClass("u-photo").get(0);
            Element img = uPhoto.getElementsByTag("img").get(0);
            String src = img.attr("src");
            InputStream is = (InputStream) new URL(src).getContent();
            Drawable userPhoto = Drawable.createFromStream(is, "src name");
            userCardListView.setUserPhoto(userPhoto);

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
                    case "url":
                        link = itemprop.getElementsByTag("a").get(0).text();
                        userCardListView.setLink(link);
                        break;
                }
            }

            Message userCardMsg = new Message();
            userCardMsg.obj = userCardListView;
            userCardHandler.sendMessage(userCardMsg);

            arrayList = new ArrayList<>();
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

                arrayList.add(userRepoListView);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Message userRepoMsg = new Message();
        userRepoMsg.obj = arrayList;
        userRepoHandler.sendMessage(userRepoMsg);
    }

}