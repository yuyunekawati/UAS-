package com.example.kamusbahasasasak;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Kamus {

    public static final String KATAKERJA="KATA KERJA";
    public static final String KATASIFAT="KATA SIFAT";
    public static final String KATABENDA="KATA BENDA";


    private String id;
    private Date tanggal;
    private String kata;
    private String arti;
    private String jenis;
    private String contoh;


    public Kamus() {
        this.id = UUID.randomUUID().toString();
        this.tanggal = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getContoh() {
        return contoh;
    }

    public void setContoh(String contoh) {
        this.contoh = contoh;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }




    public static Kamus fromJSONObject(JSONObject obj) {
        Kamus tr = new Kamus();
        try {
            tr.setId(obj.getString("id"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setArti(obj.getString("arti"));
            tr.setContoh(obj.getString("contoh"));
            tr.setKata(obj.getString("kata"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id",this.id);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("arti",this.arti);
            jsonObj.put("kata",this.contoh);
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("contoh",this.contoh);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
