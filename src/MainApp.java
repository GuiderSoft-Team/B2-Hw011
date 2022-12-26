import java.text.Collator;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        List<Personel> personels = new ArrayList<>(Personel.calisanlar());
        System.out.println("Personel Sayısı         : " + personels.stream().count());
        long erkekler = personels.stream().filter(personel -> personel.cinsiyet().equals('E')).count();
        System.out.println("Erkek Personel Sayısı   : " + erkekler);
        long kadınlar = personels.stream().filter(personel -> personel.cinsiyet().equals('K')).count();
        System.out.println("Kadın Personel Sayısı   : " + kadınlar);
        System.out.println("Personel Maaş Maliyeti  : " + personels.stream().mapToDouble(personel -> personel.maas()).sum());
        List<Personel> kadınPersonelListesi = personels.stream().filter(personel -> personel.cinsiyet().equals('K')).toList();
        System.out.println("Kadın Personel Listesi : " + kadınPersonelListesi);

        double personelMaasOrtalaması = personels.stream().mapToDouble(personel -> personel.maas()).average().getAsDouble();
        double kadınMaasOrtalaması = personels.stream().filter(personel -> personel.cinsiyet().equals('K'))
                .mapToDouble(kadınMaas -> kadınMaas.maas()).average().getAsDouble();
        double erkekMaasOrtalaması = personels.stream()
                .filter(personel -> personel.cinsiyet().equals('E'))
                .mapToDouble(erkekMaas -> erkekMaas.maas()).average().getAsDouble();

        System.out.printf("Personel Maaş Ortalaması :[%5.2f];Kadın Personel Maaş Ortalaması: [%5.2f];" +
                "Erkek Personel Maaş Ortalaması:[%5.2f]", personelMaasOrtalaması, kadınMaasOrtalaması, erkekMaasOrtalaması);
        System.out.println();
        System.out.print("Personelin büyükten küçüğe yaş sıralaması : \n");
        personels.stream()
                .sorted(Comparator.comparing(Personel::yas)
                        .reversed()).toList().forEach(System.out::println);
        System.out.println();
        System.out.print("Yaşı 35'ten büyük kadınlar : ");
        System.out.println(personels.stream().filter(item -> item.cinsiyet().equals('K'))
                .toList().stream().filter(item -> item.yas() > 35).count());

        kadınPersonelListesi.stream().filter(item -> item.yas() > 35).toList().forEach(System.out::println);
        System.out.println();
        System.out.print("Yaşı 50'den küçük erkekler :");
        System.out.println(personels.stream().filter(erkek -> erkek.cinsiyet().equals('E')).toList()
                .stream().filter(erkek -> erkek.yas() < 50).count());
        personels.stream().filter(erkek -> erkek.cinsiyet().equals('E')).toList()
                .stream().filter(erkek -> erkek.yas() < 50).forEach(System.out::println);

        double personelYasOrtalaması = personels.stream().mapToDouble(personel -> personel.yas()).average().getAsDouble();

        System.out.printf("Tüm Personelin Yaş Ortalaması :%5.2f ", personelYasOrtalaması);
        System.out.println();
        System.out.println();
        System.out.println("Kadınlar önce Erkekler sonra Tüm liste :");
        personels.stream().sorted(Comparator.comparing(Personel::cinsiyet).reversed()
                        .thenComparing(Personel::adi, Collator.getInstance()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("İsmi S ile başlayan personelin maaş toplmaı : " + personels.stream().filter(item -> item.adi()
                        .startsWith("S")).toList()
                .stream().mapToDouble(item -> item.maas()).sum());

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
}

