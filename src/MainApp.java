import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {
        List<Personel> calisanlar = Personel.calisanlar();
        long personelAdedi = calisanlar.stream().count();
        System.out.println("Çalışan Personel Sayısı : "+personelAdedi);
        System.out.println("-------------------");
        long erkekPersonel = calisanlar.stream().filter(p -> p.cinsiyet().equals('E')).count();
        long kadinPersonel = calisanlar.stream().filter(p -> p.cinsiyet().equals('K')).count();
        System.out.printf("Kadın Personel Adedi : %d\tErkek Personel Adedi: %d\n",kadinPersonel,erkekPersonel);
        System.out.println("----------------------------");
        double toplamMaliyet = calisanlar.stream().mapToDouble(Personel::maas).sum();
        System.out.println("Toplam Maliyet : "+toplamMaliyet);
        System.out.println("-----------------------------------");
        List<Personel> kadinCalisanlar = calisanlar
                .stream()
                .filter(p -> p.cinsiyet().equals('K'))
                .toList();
        kadinCalisanlar.forEach(System.out::println);
        System.out.println("---------------------------");
        double genelOrtalama = calisanlar.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double kadinCalisanMaasOrtalama = calisanlar.stream().filter(p -> p.cinsiyet().equals('K')).mapToDouble(Personel::maas).average().getAsDouble();
        double erkekCalisanMaasOrtalama = calisanlar.stream().filter(p -> p.cinsiyet().equals('E')).mapToDouble(Personel::maas).average().getAsDouble();
        System.out.println("Maaş ortalamaları:");
        System.out.printf(
                "\tGenel Maaş Ortalaması : %5.2f\n\tKadın Çalışan Maaş Ortalaması : %5.2f\n\tErkek Çalışan Maaş Ortalaması : %5.2f\n",
                genelOrtalama,
                kadinCalisanMaasOrtalama,
                erkekCalisanMaasOrtalama
        );
        System.out.println("---------------------------------");
        List<Personel> yasaGoreTerstenSiraliListe = calisanlar
                .stream()
                .sorted(Comparator.comparing(Personel::yas).reversed()).toList();
        yasaGoreTerstenSiraliListe.forEach(System.out::println);
        System.out.printf("---------------------------\n");
        System.out.println("Yaşı 35'ten büyük kadın sayısı : "+calisanlar.stream().filter(p->p.yas()>35&p.cinsiyet().equals('K')).count());
        long e50 = calisanlar
                .stream()
                .filter(p -> p.cinsiyet().equals('E'))
                .filter(p -> p.yas() < 50)
                .count();
        System.out.println("Yaşı 50'den küçük erkek çalışan sayısı : "+e50);
        System.out.println("-------------------------------");
        double yasOrtalamasi = calisanlar
                .stream()
                .mapToDouble(Personel::yas)
                .average()
                .getAsDouble();
        System.out.printf("Tüm çalışanların yaş ortalaması %5.2f olarak hesaplanmıştır.\n",yasOrtalamasi);
        System.out.println("--------------------------------");
        calisanlar
                .stream()
                .sorted(
                        Comparator
                                .comparing(Personel::cinsiyet)
                                .reversed()
                                .thenComparing(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr","TR"))))
                )
                .toList().forEach(System.out::println);
        System.out.println("----------------------------");
        double sMaasToplami = calisanlar
                .stream()
                .filter(p -> p.adi().startsWith("S"))
                .mapToDouble(Personel::maas)
                .sum();
        System.out.println("İsmi S ile başlayan personelin maaş toplamı : "+sMaasToplami);

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
