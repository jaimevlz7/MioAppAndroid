package com.mio.app.mioapp.model;

/**
 * Created by Japo on 16/11/17.
 */

public class Tweet {
    String author, date, content, authorImageUrl;

    public Tweet(String _author, String _date, String _content, String _authorImageUrl){
        author = _author;
        date = _date;
        content = _content;
        authorImageUrl = _authorImageUrl;
    }

    public String getAuthor(){
        return author;
    }

    public String getContent(){
        return content;
    }

    public String getDate(){
        return date;
    }

    public String getAuthorImageUrl(){
        return authorImageUrl;
    }
}
