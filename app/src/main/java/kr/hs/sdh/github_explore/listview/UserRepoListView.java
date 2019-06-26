package kr.hs.sdh.github_explore.listview;

public class UserRepoListView {

    private String repository = "";
    private String description = "";
    private String language = "";

    public String getRepository() {
        return repository;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
