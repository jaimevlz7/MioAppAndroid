package Modelo;

/**
 * Created by aburr on 16/11/2017.
 */

public class FeedInfo {

    private String feed_publisher_name;
    private String feed_publisher_url;
    private String feed_lang;
    private String feed_start_date;
    private String feed_end_date;
    private String feed_version;

    public FeedInfo(String feed_publisher_name, String feed_publisher_url, String feed_lang, String feed_start_date, String feed_end_date, String feed_version) {
        this.feed_publisher_name = feed_publisher_name;
        this.feed_publisher_url = feed_publisher_url;
        this.feed_lang = feed_lang;
        this.feed_start_date = feed_start_date;
        this.feed_end_date = feed_end_date;
        this.feed_version = feed_version;
    }

    public String getFeed_publisher_name() {
        return feed_publisher_name;
    }

    public void setFeed_publisher_name(String feed_publisher_name) {
        this.feed_publisher_name = feed_publisher_name;
    }

    public String getFeed_publisher_url() {
        return feed_publisher_url;
    }

    public void setFeed_publisher_url(String feed_publisher_url) {
        this.feed_publisher_url = feed_publisher_url;
    }

    public String getFeed_lang() {
        return feed_lang;
    }

    public void setFeed_lang(String feed_lang) {
        this.feed_lang = feed_lang;
    }

    public String getFeed_start_date() {
        return feed_start_date;
    }

    public void setFeed_start_date(String feed_start_date) {
        this.feed_start_date = feed_start_date;
    }

    public String getFeed_end_date() {
        return feed_end_date;
    }

    public void setFeed_end_date(String feed_end_date) {
        this.feed_end_date = feed_end_date;
    }

    public String getFeed_version() {
        return feed_version;
    }

    public void setFeed_version(String feed_version) {
        this.feed_version = feed_version;
    }

}
