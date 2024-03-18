package com.dilara;

import java.util.List;

public interface IKutuphane {
    void kitapEkle();
    List<Kitap> kitaplariListele();
    void oduncVer();
    void iadeAl();
    void durumuGuncelle();
    void uyeKaydet();
    void uyeSil();
    List<Uye> uyeleriListele();


}
