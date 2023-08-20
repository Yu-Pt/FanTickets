package com.example.fantickets;

public class Match {
    public String idmatch, date, month, club1, club2, time, stadium, score1, score2, image1, image2;

    public Match(){

    }
    public Match(String idmatch, String date, String month, String club1, String club2, String time, String stadium, String score1, String score2, String image1, String image2){
        this.idmatch = idmatch;
        this.date = date;
        this.month = month;
        this.club1 = club1;
        this.club2 = club2;
        this.time = time;
        this.stadium = stadium;
        this.score1 = score1;
        this.score2 = score2;
        this.image1 = image1;
        this.image2 = image2;
    }
}
