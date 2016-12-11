package com.angryziber.android.dtuguide;

/**
 * Created by muditverma on 07/12/16.
 */

public class Syllabus {

    private String refer , link , count ;

    public Syllabus () {

    }

    public Syllabus(String count, String link, String refer) {
        this.count = count;
        this.link = link;
        this.refer = refer;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
