import java.text.Collator;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        List<Personel> calisan = Arrays.asList(
                new Personel(1, "Ahmet", 7500., 30, 'E'),
                new Personel(2, "Şahin", 3750., 45, 'E'),
                new Personel(3, "Şerife", 6500., 37, 'K'),
                new Personel(4, "Suna", 4900., 27, 'K'),
                new Personel(5, "Hakan", 6300., 23, 'E'),
                new Personel(6, "İlhan", 5250., 55, 'E'),
                new Personel(7, "Serkan", 4750., 47, 'E'),
                new Personel(8, "Songül", 8300., 19, 'K'),
                new Personel(9, "Ferhan", 11200., 34, 'E'),
                new Personel(10, "Osman", 13700., 41, 'E'),
                new Personel(11, "Kadriye", 11200., 33, 'K'),
                new Personel(12, "Fahriye", 9350., 42, 'K'),
                new Personel(13, "Sezen", 7775., 29, 'K'),
                new Personel(14, "Cumhur", 5800., 26, 'E'),
                new Personel(15, "Müzeyyen", 6950., 39, 'K'),
                new Personel(16, "Fatih", 6600., 56, 'E')
        );

        //1. Çalışan personel adedini bulup, ekranda gösteriniz.
        long toplamCalisan = calisan.stream().count();
        System.out.println("Toplam Calisan adedi : " + toplamCalisan);

        //2. Erkek ve kadın personel sayılarını buldurup, ekrana yazdırınız.
        System.out.println("personal erkek = " + calisan.stream().filter(item -> item.cinsiyet() == 'E').count());

        System.out.println("personal kadın = " + calisan.stream().filter(item -> item.cinsiyet() == 'K').count());


        //3. Bu çalışanların firmaya olan toplam maliyetini hesaplayıp, ekrana yazdırınız.

        //       4. Kadın çalışanların;
//        a. Listesini buldurunuz
//        b. Ekrana yazdırınız

//        5. Maaş ortalaması;
//        a. Kadın çalışanların maaş ortalaması
//        b. Erkek çalışanların maaş ortalaması
//        c. Genel ortalama nedir
//        d. Hepsini tek bir satıra formatlı şekilde yazdırınız
        System.out.println(" Erkek maas ortalamasi = " + calisan.stream()
                .filter(item -> item.cinsiyet() == 'E').mapToDouble(Personel::maas).average());

        System.out.println("Kadin maas ortalamasi = " + calisan.stream()
                .filter(item -> item.cinsiyet() == 'K').mapToDouble(Personel::maas).average());

        System.out.println("Erkek ve Kadin maas ortalamasi = " + calisan.stream()
                .mapToDouble(Personel::maas).average());

        System.out.println("-----------------------------");
//  6.)      Yaş;
//        a. Tüm çalışanları büyükten küçüğe **listeleyin** ve ekrana **yazdırın**
//        b. Yaşı 35'ten büyük kadınların sayısını bulup, yazdırın
//        c. Yaşı 50'den küçük erkeklerin sayısını bulup, yazdırın
//        d. Tüm çalışanların yaş ortalaması nedir? Formatlı olarak yazdırınız

        List<Personel> personelYasListesi = calisan.stream()
                .sorted(Comparator.comparing(Personel::yas, Comparator.reverseOrder())).toList();
        personelYasListesi.forEach(System.out::println);
        System.out.println("--------------------------------------");
        personelYasListesi.stream().filter(e -> e.yas() > 35).filter(k -> k.cinsiyet() == 'K')
                .forEach(System.out::println);
        System.out.println("---------------------------------------------------------");

        personelYasListesi.stream().filter(e -> e.yas() < 50).filter(e -> e.cinsiyet() == 'E')
                .forEach(System.out::println);

        System.out.println("----------------------------------------");
 //       7)Tüm çalışanların, önce kadınlar, sonra erkekler olacak şekilde isme göre sıralı listesini
        //   a. buldurunuz
        //   b. yazdırınız
        List<Personel> erkekIsimSiraliListe = calisan.stream().filter(e -> e.cinsiyet() == 'E')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance())).toList();
        erkekIsimSiraliListe.forEach(System.out::println);

        System.out.println("--------------------------------------------");

        List<Personel> kadinIsimSiraliListe = calisan.stream().filter(e -> e.cinsiyet() == 'K')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance())).toList();
        kadinIsimSiraliListe.forEach(System.out::println);
    }
}


   record Personel(Integer id, String adi, Double maas, Integer yas, Character cinsiyet) {
        public static List<Personel> calisanlar () {
            return List.of(
                    new Personel(1, "Ahmet", 7500., 30, 'E'),
                    new Personel(2, "Şahin", 3750., 45, 'E'),
                    new Personel(3, "Şerife", 6500., 37, 'K'),
                    new Personel(4, "Suna", 4900., 27, 'K'),
                    new Personel(5, "Hakan", 6300., 23, 'E'),
                    new Personel(6, "İlhan", 5250., 55, 'E'),
                    new Personel(7, "Serkan", 4750., 47, 'E'),
                    new Personel(8, "Songül", 8300., 19, 'K'),
                    new Personel(9, "Ferhan", 11200., 34, 'E'),
                    new Personel(10, "Osman", 13700., 41, 'E'),
                    new Personel(11, "Kadriye", 11200., 33, 'K'),
                    new Personel(12, "Fahriye", 9350., 42, 'K'),
                    new Personel(13, "Sezen", 7775., 29, 'K'),
                    new Personel(14, "Cumhur", 5800., 26, 'E'),
                    new Personel(15, "Müzeyyen", 6950., 39, 'K'),
                    new Personel(16, "Fatih", 6600., 56, 'E')
            );



   }
    }


