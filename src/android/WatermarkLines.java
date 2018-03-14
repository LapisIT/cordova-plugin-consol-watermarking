package com.yarris.cordova.consol.watermarking;

/**
 * Created by gota on 14/03/18.
 */

public class WatermarkLines {
    String id;
    String address;
    String name;
    String lat;
    String lng;
    String date;
    String time;

    public WatermarkLines(String id, String address, String name, String lat, String lng, String date, String time) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.lat = lat +(char) 0x00B0+" S";
        this.lng = lng +(char) 0x00B0+" E";
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "WatermarkLines{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
