package com.dilara;

public class KitapTarih extends Kitap{

    public KitapTarih(Long id, String baslik, String yazar, String yayinYili, String yayinEvi, EDurum durum) {
        super(id, baslik, yazar, yayinYili, yayinEvi, durum);
        String tur = "Tarih";
    }
}
