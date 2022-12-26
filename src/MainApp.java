import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {
//        Çalışan personel adedini bulup, ekranda gösteriniz.
        int totalPersonal = Personel.calisanlar().size();
        System.out.println(totalPersonal);


//        Erkek ve kadın personel sayılarını buldurup, ekrana yazdırınız.

        long menCount =  Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'E')
                .count();
        long womenCount =  Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'K')
                .count();
        System.out.println("Erkek personel sayısı: " + menCount + " Kadın personel sayısı: " + womenCount);


//        Bu çalışanların firmaya olan toplam maliyetini hesaplayıp, ekrana yazdırınız.
        int sumWage = (int) Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::maas)
                .sum();
        System.out.println("Toplam maliyet: " + sumWage);

//        Kadın çalışanların; a. Listesini buldurunuz b. Ekrana yazdırınız

        List<Personel> womenList = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'K')
                .toList();
        System.out.println(womenList);

//        Maaş ortalaması; a. Kadın çalışanların maaş ortalaması b. Erkek çalışanların maaş ortalaması
//        c. Genel ortalama nedir d. Hepsini tek bir satıra formatlı şekilde yazdırınız

        double averageWageWomen = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'K')
                .mapToDouble(Personel::maas).sum() / womenCount;
        double averageWageMen = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'E')
                .mapToDouble(Personel::maas).sum() / menCount;
        double averageWageTotal = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::maas).sum() / totalPersonal;
        System.out.printf("Kadın maaş ortalaması: %5.2f Erkek Maaş Ortalaması: %5.2f Toplam Ortalama: %5.2f", averageWageWomen, averageWageMen, averageWageTotal);
        System.out.println();
//        Yaş; a. Tüm çalışanları büyükten küçüğe listeleyin ve ekrana yazdırın b. Yaşı 35'ten büyük kadınların
//        sayısını bulup, yazdırın c. Yaşı 50'den küçük erkeklerin sayısını bulup, yazdırın
//        d. Tüm çalışanların yaş ortalaması nedir? Formatlı olarak yazdırınız

        List<Personel> sortedAgePersonal = Personel.calisanlar()
                .stream()
                .sorted(Comparator.comparing(Personel::yas).reversed())
                .toList();
        System.out.println("Yaşa göre sıralı liste" + sortedAgePersonal);
        System.out.println("Yaşı 35'ten büyük kadınların sayısı: " + Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'K' && i.yas() > 35)
                .count());
        System.out.println("Yaşı 50'den küçük erkeklerin sayısı: " + Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'E' && i.yas() < 50)
                .count());
        double aveAgeTotal = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::yas).sum() / totalPersonal;
        System.out.printf("Tüm çalışanların yaş ortalaması: %5.2f", aveAgeTotal);
        System.out.println();

//        Tüm çalışanların, önce kadınlar, sonra erkekler olacak şekilde isme göre sıralı listesini a. buldurunuz b. yazdırınız
        List<Personel> sortedNamePersonal = Personel.calisanlar()
                .stream()
                .sorted(Comparator.comparing(Personel::cinsiyet)
                        .reversed().thenComparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR"))))
                .toList();
        System.out.println("Personel isme göre sıralı liste :" + sortedNamePersonal);

//        İsmi S ile başlayan çalışanların maaş toplamını ekrana yazdırınız.
        System.out.println("S ile başlayanların toplam maaşı: " + Personel.calisanlar()
                .stream()
                .filter(i -> i.adi().startsWith("S"))
                .mapToDouble(Personel::maas)
                .sum());

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
