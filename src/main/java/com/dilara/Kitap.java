package com.dilara;

import java.util.Random;
import java.util.UUID;

public abstract class Kitap {
    private Long id ;
    private String ISBN;
    private String baslik;
    private String yazar;
    private String yayinYili;
    private String yayinEvi;
    private EDurum durum;

    public Kitap(Long id,String baslik, String yazar, String yayinYili, String yayinEvi, EDurum durum) {
        this.id = id;
        this.ISBN = UUID.randomUUID().toString();
        this.baslik = baslik;
        this.yazar = yazar;
        this.yayinYili = yayinYili;
        this.yayinEvi = yayinEvi;
        this.durum = durum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Kitap{");
        sb.append("id=").append(id);
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", baslik='").append(baslik).append('\'');
        sb.append(", yazar='").append(yazar).append('\'');
        sb.append(", yayinYili='").append(yayinYili).append('\'');
        sb.append(", yayinEvi='").append(yayinEvi).append('\'');
        sb.append(", durum=").append(durum);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getYayinYili() {
        return yayinYili;
    }

    public void setYayinYili(String yayinYili) {
        this.yayinYili = yayinYili;
    }

    public String getYayinEvi() {
        return yayinEvi;
    }

    public void setYayinEvi(String yayinEvi) {
        this.yayinEvi = yayinEvi;
    }

    public EDurum getDurum() {
        return durum;
    }

    public void setDurum(EDurum durum) {
        this.durum = durum;
    }
}
