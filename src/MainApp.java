import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {

        List<Personel> personalList =  Personel.calisanlar();

        System.out.println("personal adedi = " + personalList.stream().count());

        System.out.println("personal erkek = " + personalList.stream().filter(item -> item.cinsiyet() == 'E').count());

        System.out.println("personal kadın = " + personalList.stream().filter(item -> item.cinsiyet() == 'K').count());

        System.out.println("personal erkek maas toplami = " + personalList.stream()
                .filter(item -> item.cinsiyet() == 'E').mapToDouble(Personel::maas).sum());

        System.out.println("personal kadin maas toplami = " + personalList.stream()
                .filter(item -> item.cinsiyet() == 'K').mapToDouble(Personel::maas).sum());

        System.out.println("personal erkek ve kadin maas toplami = " + personalList.stream()
                .mapToDouble(Personel::maas).sum());

        System.out.println("-".repeat(50));

        List<Personel> personelKadin = personalList.stream().filter(item -> item.cinsiyet() == 'K').toList();
        personelKadin.forEach(System.out::println);

        System.out.println("-".repeat(50));

        List<Personel> personelErkek = personalList.stream().filter(item -> item.cinsiyet() == 'E').toList();
        personelErkek.forEach(System.out::println);

        System.out.println("-".repeat(50));

        double averageKadinMaas = personelKadin.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double averageErkekMaas = personelErkek.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double averageGenelMaas = personalList.stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Kadin personel maas ortalamasi : %5.2f,\nErkek personel maas ortalamasi : " +
                "%5.2f,\nTüm personel maas ortalamasi : %5.2f\n", averageKadinMaas, averageErkekMaas, averageGenelMaas);

        System.out.println("-".repeat(50));

        List<Personel> personelYasListesi = personalList.stream()
                .sorted(Comparator.comparing(Personel::yas, Comparator.reverseOrder())).toList();
        personelYasListesi.forEach(System.out::println);

        System.out.println("-".repeat(50));

        personelYasListesi.stream().filter(e -> e.yas() > 35).filter(k -> k.cinsiyet() == 'K')
                .forEach(System.out::println);

        System.out.println("-".repeat(50));

        personelYasListesi.stream().filter(e -> e.yas() < 50).filter(e -> e.cinsiyet() == 'E')
                .forEach(System.out::println);

        System.out.println("-".repeat(50));

        System.out.printf("Tüm personel yas ortalaması : %5.2f\n", personalList.stream()
                .mapToDouble(Personel::yas).average().getAsDouble());

        System.out.println("-".repeat(50));

        List<Personel> erkekIsimSiraliListe = personalList.stream().filter(e -> e.cinsiyet() == 'E')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance())).toList();
        erkekIsimSiraliListe.forEach(System.out::println);

        System.out.println("-".repeat(50));

        List<Personel> kadinIsimSiraliListe = personalList.stream().filter(e -> e.cinsiyet() == 'K')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance())).toList();
        kadinIsimSiraliListe.forEach(System.out::println);

        System.out.println("-".repeat(50));

        System.out.println("Ismi 'S' ile başlayanlar maas toplamı : " + personalList.stream()
                .filter(e -> e.adi().charAt(0) == 'S').mapToDouble(Personel::maas).sum());


    }
}

record Personel(Integer id, String adi, Double maas, Integer yas, Character cinsiyet) {
    public static List<Personel> calisanlar() {
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
