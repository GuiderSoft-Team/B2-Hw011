import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainApp {
    private static final Collator TR_LANG=Collator.getInstance(new Locale("tr","TR"));
    public static void main(String[] args) {
        // 1
        System.out.println("Calisan personel adedi: "+Personel.calisanlar().size());

        // 2
        System.out.println("Erkek personel sayisi: "+Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('E')).count());
        System.out.println("Kadin personel sayisi: "+Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('K')).count());

        // 3
        System.out.println("Calisanlarin toplam maliyeti: "+Personel.calisanlar().stream().mapToDouble(Personel::maas).sum());

        // 4 a - b
        System.out.println("Kadin calisan sayisi: "+Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('K')).count());
        Personel.calisanlar().stream().filter(item->item.cinsiyet().equals('K')).forEach(System.out::println);

        // 5 a - b - c - d
        System.out.printf("Kadin personel maas ortalamasi: %5.2f \t",Personel.calisanlar().stream().filter(item->item.cinsiyet().equals('K')).mapToDouble(Personel::maas).average().getAsDouble());
        System.out.printf("Erkek personel maas ortalamasi: %5.2f \t",Personel.calisanlar().stream().filter(item->item.cinsiyet().equals('E')).mapToDouble(Personel::maas).average().getAsDouble());
        System.out.printf("Genel maas ortalamasi: %5.2f \n",Personel.calisanlar().stream().mapToDouble(Personel::maas).average().getAsDouble());

        // 6 a - b - c - d
        Personel.calisanlar().stream().sorted(Comparator.comparing(Personel::yas).reversed()).forEach(System.out::println);
        System.out.println("Yasi 35'ten buyuk kadin personel sayisi: "+Personel.calisanlar().stream().filter(item -> item.yas() > 35).filter(item -> item.cinsiyet().equals('K')).count());
        System.out.println("Yasi 50'den kucuk erkek personel sayisi: "+Personel.calisanlar().stream().filter(item -> item.yas() < 50).filter(item -> item.cinsiyet().equals('E')).count());
        System.out.printf("Personellerin yas ortalamasi: %5.2f \n",Personel.calisanlar().stream().mapToDouble(Personel::yas).average().getAsDouble());

        // 7
        Personel.calisanlar().stream().filter(item->item.cinsiyet().equals('K')).sorted(Comparator.comparing(Personel::adi,TR_LANG)).forEach(System.out::println);
        System.out.println();
        Personel.calisanlar().stream().filter(item->item.cinsiyet().equals('E')).sorted(Comparator.comparing(Personel::adi,TR_LANG)).forEach(System.out::println);
        System.out.println();

        // 8
        System.out.printf("Ismi S harfi ile baslayan personellerin maas toplami: %5.2f",Personel.calisanlar().stream().filter(item -> item.adi().startsWith("S")).mapToDouble(Personel::maas).sum());


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
