
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainApp{
    public static void main(String[] args) {
       List<Personel> emekciler=Personel.calisanlar();

        System.out.printf("%d adet personel vardır.\n",emekciler.stream().count());

        System.out.println("*****************");

        System.out.printf("%d adet kadın personel vardır\n",emekciler.stream()
                .filter(k->k.cinsiyet().equals('K')).count());
        System.out.printf("%d adet erkek personel vardır.\n",emekciler.stream()
                .filter(e->e.cinsiyet().equals('E')).count());

        System.out.println("*****************");

        double maliyet= emekciler.stream().mapToDouble(Personel::maas).sum();
        System.out.printf("Çalışanların toplam maliyeti:%5.2f\n",maliyet);

        System.out.println("*****************");

        emekciler.stream().filter(k->k.cinsiyet().equals('K')).forEach(System.out::println);

        System.out.println("*****************");

        double kadinMaasOrt = emekciler.stream().filter(k -> k.cinsiyet().equals('K'))
                .mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Kadın maaş ortalaması:%5.2f\n",kadinMaasOrt);

        double erkekMaasOrt = emekciler.stream().filter(e -> e.cinsiyet().equals('E'))
                .mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Erkek maaş ortalaması:%5.2f\n",erkekMaasOrt);

        double genelOrtalama = emekciler.stream()
                .mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Genel maaş ortalaması:%5.2f\n",genelOrtalama);

        System.out.println("*****************");

        emekciler.stream().sorted(Comparator.comparing(Personel::yas).reversed())
                .forEach(System.out::println);

        long kCount = emekciler.stream().filter(k -> k.yas() > 35 & k.cinsiyet().equals('K')).count();
        System.out.println("35'ten büyük kadınların sayısı:"+kCount);

        long eCount = emekciler.stream().filter(e -> e.yas() < 50 & e.cinsiyet().equals('E')).count();
        System.out.println("50'den küçük erkeklerin sayısı:"+eCount);

        double yasOrt = emekciler.stream()
                .mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf("Tüm çalışanların yaş ortalaması:%5.2f\n",yasOrt);

        System.out.println("*****************");

        emekciler.stream()
                .sorted((Comparator.comparing((Personel s)->s.cinsiyet().equals('K')).reversed()
                        .thenComparing((Personel s)->s.adi(),Collator.getInstance(new Locale("tr","TR")))))
                .collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("*****************");

        double sMaas = emekciler.stream().filter(s -> s.adi().startsWith("S"))
                .mapToDouble(Personel::maas).sum();
        System.out.printf("İsmi S ile başlayan çalışanların maaş toplamı:%5.2f",sMaas);


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
