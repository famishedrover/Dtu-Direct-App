package com.angryziber.android.dtuguide;

/**
 * Created by muditverma on 07/12/16.
 */

public class Results {

    private String refer , link , date, count;

    public Results () {

    }

    public Results(String count, String date, String link, String refer) {
        this.count = count;
        this.date = date;
        this.link = link;
        this.refer = refer;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }
}
