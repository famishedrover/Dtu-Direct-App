package com.example.android.dtuguide;



public class Word {
    private String mcontactinfo;
    private String memailid;
    private String mnumber;

    public Word(String contactinfo,String emailid,String number){
        mcontactinfo=contactinfo;
        memailid=emailid;
        mnumber=number;
    }

    public String getcontactinfo(){
        return mcontactinfo;
    }
    public String getemailid(){
        return memailid;
    }
    public String getnumber(){
        return mnumber;
    }
}
