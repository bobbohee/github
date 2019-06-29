package kr.hs.sdh.github_explore.listview;

public class TrendListView {

    private String since;
    private String developer;
    private String repository;
    private String description;
    private String language;
    private String star;
    private String share;

    public String getSince() {
        return since;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getRepository() {
        return repository;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getStar() {
        return star;
    }

    public String getShare() {
        return share;
    }

    public void setSince(String since) { this.since = since; }

    public void setDeveloper(String developer) {
        this.developer = developer;
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

    public void setStar(String star) {
        this.star = star;
    }

    public void setShare(String share) {
        this.share = share;
    }

}
