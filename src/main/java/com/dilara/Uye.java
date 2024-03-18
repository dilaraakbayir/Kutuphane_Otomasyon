package com.dilara;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Uye implements IUye{
    private Long uyeNo ;
    private String ad ;
    private String soyad ;
    private String telNo;
    List<Kitap> oduncKitaplar = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Kutuphane kutuphane = new Kutuphane();

    public Uye(Long uyeNo, String ad, String soyad, String telNo) {
        this.uyeNo=uyeNo;
        this.ad = ad;
        this.soyad = soyad;
        this.telNo = telNo;
    }


    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Long getUyeNo() {
        return uyeNo;
    }

    public void setUyeNo(Long uyeNo) {
        this.uyeNo = uyeNo;
    }

    public List<Kitap> getOduncKitaplar() {
        return oduncKitaplar;
    }

    public void setOduncKitaplar(List<Kitap> oduncKitaplar) {
        this.oduncKitaplar = oduncKitaplar;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Uye{");
        sb.append(", ad='").append(ad).append('\'');
        sb.append(", soyad='").append(soyad).append('\'');
        sb.append(", telNo='").append(telNo).append('\'');
        sb.append(", uyeNo=").append(uyeNo);
        sb.append(", oduncKitaplar=").append(oduncKitaplar);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void oduncAl() {
       List <Kitap> kitaplar = kutuphane.kitaplariListele();
        List<Kitap> oduncVerilebilirListesi = kitaplar.stream().filter(x -> x.getDurum().equals(EDurum.ODUNC_ALABILIR)).collect(Collectors.toList());

        try {
            System.out.println("Ödünç Almak İstediğiniz Kitabının Adını Giriniz");
            String kitapAd = scanner.nextLine();

            Optional<Kitap>  oduncVerilecekKitap = oduncVerilebilirListesi.stream().filter(x -> x.getId().equals(kitapAd)).findFirst();
            oduncVerilecekKitap.get().setDurum(EDurum.ODUNC_VERILDI);

            List<Uye> uyeler = kutuphane.uyeleriListele();
            Uye uye = uyeler.stream().filter(x->x.getUyeNo().equals(uyeNo)).findFirst().get();
            uye.getOduncKitaplar().add(oduncVerilecekKitap.get());

            System.out.println(oduncVerilecekKitap.get().getBaslik()+ " adlı kitap ödünç alındı.");
            kutuphane.durumuGuncelle();
        }catch(Exception e){
            System.out.println("Adı girilen kitap uygun değildir.");
        }



    }

    @Override
    public void iadeEt() {
        List <Kitap> kitaplar = kutuphane.kitaplariListele();
        System.out.println("İade edilecek olan kitabın adını giriniz");
        String kitapAdi = scanner.nextLine();

        Kitap iadeKitap = kitaplar.stream().filter(x->x.getId().equals(kitapAdi)).findAny().get();
        iadeKitap.setDurum(EDurum.ODUNC_ALABILIR);

        List<Uye> uyeler = kutuphane.uyeleriListele();
        Uye uye = uyeler.stream().filter(x->x.getUyeNo().equals(uyeNo)).findAny().get();
        uye.getOduncKitaplar().remove(iadeKitap);
        System.out.println(iadeKitap.getBaslik()+" adlı kitap iade edildi.");
        kutuphane.durumuGuncelle();

    }
    public void uyeApp(){
        int secim;
        do {
            secim = uyeMenu();
            switch (secim) {
                case 1:
                    kutuphane.kitaplariListele();
                    break;
                case 2:
                    oduncAl();
                    break;
                case 3:
                    iadeEt();
                    break;
                case 0:
                    System.out.println("Çıkış Yapılıyor");
                    break;
                default:
                    System.out.println("Lütfen doğru bir seçim yapınız");
                    break;
            }
        }while (secim!=0);

    }

    private void uyeGirisi() {
        System.out.println("Üye numaranızı giriniz");
        Long uyeNo = scanner.nextLong();
        List<Uye> uyeler = kutuphane.uyeleriListele();
        Optional<Uye> uye = uyeler.stream().filter(x->x.getUyeNo().equals(uyeNo)).findAny();
        if(uye.isEmpty())
            System.out.println("Üye numarasını tekrar giriniz.");
        System.out.println("Hoşgeldin "+uye.get().getAd());
        uyeMenu();

    }

    public int uyeMenu(){ //üyenin kullandığı sistem
        System.out.println("--- Kutuphane Üye Arayüzüne Hosgeldiniz ---");
        System.out.println("Lütfen Yapmak İstediğiniz İşlemi Seçiniz");
        System.out.println("""
                    1- Kitapları Listele
                    2- Kitap Ödünç Al
                    3- Kitap İade Et
                    0- C I K I S
                """);
        int secim = scanner.nextInt();
        scanner.nextLine();
        return secim;
    }

}
