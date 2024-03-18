package com.dilara;

public class KitapBilim extends Kitap{

    public KitapBilim(Long id , String baslik, String yazar, String yayinYili, String yayinEvi, EDurum durum) {
        super(id,baslik, yazar, yayinYili, yayinEvi, durum);
        String tur = "Bilim";
    }


}
