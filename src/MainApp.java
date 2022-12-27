import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
//Çalışan personel adedini bulup, ekranda gösteriniz.
        long toplamPersonel = Personel.calisanlar().size();
        System.out.println(toplamPersonel);
        System.out.println();

//Erkek ve kadın personel sayılarını buldurup, ekrana yazdırınız.

        long erkekPersonel = (int) Personel.calisanlar().stream().filter(item -> item.cinsiyet() == 'E').count();
        long kadinPersonel = (int) Personel.calisanlar().stream().filter(item -> item.cinsiyet() == 'K').count();
        System.out.println("Erkek personel sayısı: " + erkekPersonel);
        System.out.println(" Kadın personel sayısı: " + kadinPersonel);
        System.out.println();

//Bu çalışanların firmaya olan toplam maliyetini hesaplayıp, ekrana yazdırınız.
        double toplamMaliyet = Personel.calisanlar().stream().mapToDouble(Personel::maas).sum();
        System.out.println("Toplam maliyet: " + toplamMaliyet);
        System.out.println();

 //Kadın çalışanların; a. Listesini buldurunuz b. Ekrana yazdırınız
        List<Personel> kadinCalisanListesi = Personel.calisanlar().stream().filter(item -> item.cinsiyet() == 'K').toList();
        System.out.println(kadinCalisanListesi);
        System.out.println();

//Maaş ortalaması; a. Kadın çalışanların maaş ortalaması b. Erkek çalışanların maaş ortalaması
//c. Genel ortalama nedir d. Hepsini tek bir satıra formatlı şekilde yazdırınız

        double kadinCalisanMaasOrtalamasi = Personel.calisanlar().stream().filter(item -> item.cinsiyet() == 'K')
                .mapToDouble(Personel::maas).sum() / kadinPersonel;
        double erkekCalisanMaasOrtalamasi = Personel.calisanlar().stream().filter(item -> item.cinsiyet() == 'E')
                .mapToDouble(Personel::maas).sum() / erkekPersonel;
        double genelOrtalama = Personel.calisanlar().stream().mapToDouble(Personel::maas).sum() / toplamPersonel;
        System.out.printf("Kadın maaş ortalaması: %5.2f Erkek Maaş Ortalaması: %5.2f Toplam Ortalama: %5.2f",
                kadinCalisanMaasOrtalamasi, erkekCalisanMaasOrtalamasi, genelOrtalama);
        System.out.println();
//Yaş; a. Tüm çalışanları büyükten küçüğe listeleyin ve ekrana yazdırın b. Yaşı 35'ten büyük kadınların
//sayısını bulup, yazdırın c. Yaşı 50'den küçük erkeklerin sayısını bulup, yazdırın
//d. Tüm çalışanların yaş ortalaması nedir? Formatlı olarak yazdırınız

        List<Personel> yasSiralamasi = Personel.calisanlar().stream().sorted(Comparator.comparing(Personel::yas)
                        .reversed()).toList();
        System.out.println("Yaşa göre sıralı liste" + yasSiralamasi);
        System.out.println("Yaşı 35'ten büyük kadınların sayısı: " + Personel.calisanlar().stream()
                .filter(item -> item.cinsiyet() == 'K' && item.yas() > 35).count());
        System.out.println("Yaşı 50'den küçük erkeklerin sayısı: " + Personel.calisanlar().stream()
                .filter(item -> item.cinsiyet() == 'E' && item.yas() < 50).count());
        double tümCalisanlarYasOrtalamasi = Personel.calisanlar().stream().mapToDouble(Personel::yas).sum() / toplamPersonel;
        System.out.printf("Tüm çalışanların yaş ortalaması: %5.2f", tümCalisanlarYasOrtalamasi);
        System.out.println();

//Tüm çalışanların, önce kadınlar, sonra erkekler olacak şekilde isme göre sıralı listesini a. buldurunuz b. yazdırınız
        List<Personel> ismeGoreListe = Personel.calisanlar().stream().sorted(Comparator.comparing(Personel::cinsiyet)
                        .reversed().thenComparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR"))))
                .toList();
        System.out.println("Personel isme göre sıralı liste :" + ismeGoreListe);
        System.out.println();

//İsmi S ile başlayan çalışanların maaş toplamını ekrana yazdırınız.
        System.out.println("S ile başlayanların toplam maaşı: " + Personel.calisanlar().stream()
                .filter(item -> item.adi().startsWith("S")).mapToDouble(Personel::maas).sum());
       }
    }

record Personel(Integer id, String adi, Double maas, Integer yas,Character cinsiyet){
    public static List<Personel> calisanlar(){
        return List.of(
                new Personel(1,"Ahmet",7500.,30,'E'),
                new Personel(2,"Şahin",3750.,45,'E'),
                new Personel(3,"Şerife",6500.,37,'K'),
                new Personel(4,"Suna",4900.,27,'K'),
                new Personel(5,"Hakan",6300.,23,'E'),
                new Personel(6,"İlhan",5250.,55,'E'),
                new Personel(7,"Serkan",4750.,47,'E'),
                new Personel(8,"Songül",8300.,19,'K'),
                new Personel(9,"Ferhan",11200.,34,'E'),
                new Personel(10,"Osman",13700.,41,'E'),
                new Personel(11,"Kadriye",11200.,33,'K'),
                new Personel(12,"Fahriye",9350.,42,'K'),
                new Personel(13,"Sezen",7775.,29,'K'),
                new Personel(14,"Cumhur",5800.,26,'E'),
                new Personel(15,"Müzeyyen",6950.,39,'K'),
                new Personel(16,"Fatih",6600.,56,'E')
        );




    }
}
