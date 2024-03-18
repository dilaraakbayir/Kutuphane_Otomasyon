package com.dilara;

import java.util.*;
import java.util.stream.Collectors;

public class Kutuphane implements IKutuphane{

    private List<Kitap> kitaplar = new ArrayList<>();
    private List<Uye> uyeler = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void kitapEkle(){
        System.out.println("Eklemek İstediğiniz Kitap Türünü Giriniz");
        System.out.println("""
                        1- Bilim
                        2- Roman
                        3- Tarih
                """);
        int secim = scanner.nextInt();
        System.out.println("Kitap id sini giriniz");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Kitap başlığını giriniz");
        String baslik = scanner.nextLine();
        System.out.println("Yazar adını giriniz");
        String yazar = scanner.nextLine();
        System.out.println("Yayın yılını giriniz");
        String yayinYili = scanner.nextLine();
        System.out.println("Yayın evini giriniz");
        String yayinEvi = scanner.nextLine();

        if(secim==1){
            Kitap kitap = new KitapBilim(id,baslik,yazar,yayinYili,yayinEvi,EDurum.ODUNC_ALABILIR);
            kitaplar.add(kitap);
        }
        if (secim==2){
            Kitap kitap = new KitapRoman(id,baslik,yazar,yayinYili,yayinEvi,EDurum.ODUNC_ALABILIR);
            kitaplar.add(kitap);
        }
        if (secim==3){
            Kitap kitap = new KitapTarih(id,baslik,yazar,yayinYili,yayinEvi,EDurum.ODUNC_ALABILIR);
            kitaplar.add(kitap);
        }
        System.out.println("Kitap eklendi");
        durumuGuncelle();
    }

    @Override
    public List<Kitap> kitaplariListele(){
        kitaplar.forEach(System.out::println);
        return kitaplar.stream().collect(Collectors.toList());
    }

    @Override
    public void oduncVer() {
        List<Kitap> oduncVerilebilirListesi = kitaplar.stream().filter(x -> x.getDurum().equals(EDurum.ODUNC_ALABILIR)).collect(Collectors.toList());

        try {
            System.out.println("Ödünç Vermek İstediğiniz Kitabının id'sini girinizi");
            Long kitapId = scanner.nextLong();
            System.out.println("Ödünç Vermek İstediğiniz Üyenin no'sunu girinizi");
            Long uyeNo = scanner.nextLong();

            Optional<Kitap>  oduncVerilecekKitap = oduncVerilebilirListesi.stream().filter(x -> x.getId().equals(kitapId)).findFirst();
            oduncVerilecekKitap.get().setDurum(EDurum.ODUNC_VERILDI);

            Uye uye = uyeler.stream().filter(x->x.getUyeNo().equals(uyeNo)).findFirst().get();
            uye.getOduncKitaplar().add(oduncVerilecekKitap.get());

            System.out.println(oduncVerilecekKitap.get().getBaslik()+ " adlı kitap ödünç verildi.");
            durumuGuncelle();
            }catch(Exception e){
                System.out.println("Id si girilen kitap uygun değildir.");
            }



    }

    @Override
    public void iadeAl() {
        System.out.println("İade alınacak olan kitap idsini giriniz");
        Long iadeId = scanner.nextLong();
        System.out.println("İade alınacak olan üyenin no'sunu giriniz");
        Long uyeNo = scanner.nextLong();

        Kitap iadeKitap = kitaplar.stream().filter(x->x.getId().equals(iadeId)).findAny().get();
        iadeKitap.setDurum(EDurum.ODUNC_ALABILIR);

        Uye uye = uyeler.stream().filter(x->x.getUyeNo().equals(uyeNo)).findAny().get();
        uye.getOduncKitaplar().remove(iadeKitap);
        System.out.println(uye.getAd()+" adlı kullanıcıdan"+ iadeKitap.getBaslik()+" adlı kitap iade alındı.");
        durumuGuncelle();
    }

    @Override
    public void durumuGuncelle() {
        System.out.println("Durum güncellendi.");
    }

    @Override
    public void uyeKaydet() {
        System.out.println("Lütfen üye no giriniz ");
        Long uyeNo = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Lütfen üye adını giriniz");
        String ad = scanner.nextLine();
        System.out.println("Lütfen üye soyadını giriniz");
        String soyad = scanner.nextLine();
        System.out.println("Lütfen üye telefon numarasını giriniz");
        String telNo = scanner.nextLine();
        Uye uye = new Uye(uyeNo,ad,soyad,telNo);
        uyeler.add(uye);
        System.out.println("Üye Kaydedildi");


    }

    @Override
    public List<Uye> uyeleriListele(){
        uyeler.forEach(System.out::println);
        return uyeler.stream().collect(Collectors.toList());
    }

    @Override
    public void uyeSil() {
        System.out.println("Silmek istediğiniz üyenin no'sunu giriniz.");
        Long silinecekUyeNo = scanner.nextLong();
        Uye silinecekUye = uyeler.stream().filter(x->x.getUyeNo().equals(silinecekUyeNo)).findAny().get();
        uyeler.remove(silinecekUye);
        System.out.println(silinecekUye.getAd() + " adlı üye silindi.");
        durumuGuncelle();
    }

    public void kutuphaneApp(){
        int secim;
        do {
            secim = kutuphaneMenu();
            switch (secim) {
                case 1:
                    uyeKaydet();
                    break;
                case 2:
                    uyeSil();
                    break;
                case 3: uyeleriListele();
                    break;
                case 4:
                    kitapEkle();
                    break;
                case 5:
                    kitaplariListele();
                    break;
                case 6:
                    oduncVer();
                    break;
                case 7:
                    iadeAl();
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

    public int kutuphaneMenu(){ //kütüphane görevlisinin kullandığı sistem
        System.out.println("--- Kutuphane Otomasyonuna Hosgeldiniz ---");
        System.out.println("Lütfen Yapmak İstediğiniz İşlemi Seçiniz");
        System.out.println("""
                    1- Üye Kaydet
                    2- Üye Sil
                    3- Üyeleri Listele
                    4- Kitap Ekle
                    5- Kitapları Listele
                    6- Kitap Ödünç Ver
                    7- Kitap İade Al
                    0- C I K I S
                """);
        int secim = scanner.nextInt();
        scanner.nextLine();
        return secim;
    }
}
