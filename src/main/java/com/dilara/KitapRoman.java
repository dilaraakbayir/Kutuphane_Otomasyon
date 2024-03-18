package com.dilara;

public class KitapRoman extends Kitap{


    public KitapRoman(Long id, String baslik, String yazar, String yayinYili, String yayinEvi, EDurum durum) {
        super(id,baslik, yazar, yayinYili, yayinEvi, durum);
        String tur = "Roman";
    }
}
