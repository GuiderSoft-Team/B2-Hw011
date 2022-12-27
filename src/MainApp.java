import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {
        long count = Personel.calisanlar()
                .stream()
                .count();
        System.out.println("Toplam personel adedi : "+count);
        System.out.println("----------------------------------------------------------------");

        long erkekPersonel = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.cinsiyet().equals('E'))
                .count();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Toplam erkek personel adedi: "+erkekPersonel);

        long kadinPersonel = Personel.calisanlar()
                .stream().
                filter(calisan -> calisan.cinsiyet().equals('K'))
                .count();
        System.out.println("Toplam kadin personel adedi: "+kadinPersonel);
        System.out.println("----------------------------------------------------------------");

        double toplamMaliyet = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::maas)
                .sum();
        System.out.println("Calisanlarin firmaya toplam maliyeti : "+toplamMaliyet);
        System.out.println("----------------------------------------------------------------");

        Personel.calisanlar()
                .stream().
                filter(calisan -> calisan.cinsiyet().equals('K'))
                .toList().
                forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        double kadinMaasOrtalamasi = Personel.calisanlar()
                .stream().filter(calisan -> calisan.cinsiyet().equals('K'))
                .mapToDouble(calisan -> calisan.maas())
                .average()
                .getAsDouble();
        double erkekMaasOrtalamasi = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.cinsiyet().equals('E'))
                .mapToDouble(calisan -> calisan.maas())
                .average()
                .getAsDouble();
        double genelMaasOrtalamasi = Personel.calisanlar()
                .stream()
                .mapToDouble(calisan -> calisan.maas())
                .average().
                getAsDouble();
        System.out.printf("Kadinlarin maas ortalamasi : %5.2f\nErkeklerin maas ortalamasi : %5.2f\nGenel maas ortalamasi : %5.2f",kadinMaasOrtalamasi,erkekMaasOrtalamasi,genelMaasOrtalamasi);
        System.out.println();
        System.out.println("----------------------------------------------------------------");

        System.out.println("Personelin yasa göre siralamasi: \n");
        Personel.calisanlar()
                .stream()
                .sorted(Comparator.comparing(Personel :: yas))
                .forEach(calisan -> System.out.println(calisan));

        System.out.println("----------------------------------------------------------------");

        long kadinYas35Uzeri = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.yas() > 35)
                .count();
        System.out.println("35 yasin üzerinde kadinlarin sayisi : "+kadinYas35Uzeri);
        long erkekYas50Altinda = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.yas() > 50)
                .count();
        System.out.println("50 yasin altinda erkeklerin sayisi : "+erkekYas50Altinda);
        System.out.println("----------------------------------------------------------------");

        double calisanlarinYasOrtalamasi = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::yas)
                .average()
                .getAsDouble();
        System.out.printf("Calisanlarin yas ortalamasi : %5.2f\n",calisanlarinYasOrtalamasi);

        System.out.println("----------------------------------------------------------------");

        List<Personel> kadinPersonelSirali = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.cinsiyet().equals('K'))
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR"))))
                .toList();

        List<Personel> erkekPersonelSirali = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.cinsiyet().equals('E'))
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR"))))
                .toList();

        System.out.println("Kadinlarin isimlerine göre siralamasi: \n");
        kadinPersonelSirali.forEach(System.out::println);
        System.out.println();
        System.out.println("Erkeklerin isimlerine göre siralamasi: \n");
        erkekPersonelSirali.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        double toplamMaasIsimBasiS = Personel.calisanlar()
                .stream()
                .filter(calisan -> calisan.adi().startsWith("S"))
                .mapToDouble(Personel::maas).sum();

        System.out.println("Isimleri S ile baslayanlarin toplam maasi "+toplamMaasIsimBasiS);

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
