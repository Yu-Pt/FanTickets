package com.example.fantickets;

public class Ticket {
    public String idticket, number, date, time, club1, club2, stadium, sector, tribuna, rad, mesto, cost, qr;

    public Ticket(){}

    public Ticket(String idticket, String number, String date, String time, String club1, String club2, String stadium, String sector, String tribuna, String rad, String mesto, String cost, String qr){
        this.idticket = idticket;
        this.number = number;
        this.date = date;
        this.time = time;
        this.club1 = club1;
        this.club2 = club2;
        this.stadium = stadium;
        this.sector = sector;
        this.tribuna = tribuna;
        this.rad = rad;
        this.mesto = mesto;
        this.cost = cost;
        this.qr = qr;

    }
}
