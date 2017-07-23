package app.gwt.sample.shared;


import java.io.Serializable;

public class Log implements Serializable {

    private int id;
    private  String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

