import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {


    public static void main(String[] args) {

        //1.
        List<Personel> personel = Personel.calisanlar();
        System.out.printf("Toplam %d Personel bulunmaktadır.",personel.stream().count());
        System.out.println();
        //2

        System.out.printf("Toplam %d kadın personel bulunmaktadır.",
                personel.stream().filter(personel1 -> personel1.cinsiyet().equals('K')).count());
        System.out.println();

        System.out.printf("Toplam %d Erkek personel bulunmaktadır.",
                personel.stream().filter(personel1 -> personel1.cinsiyet().equals('E')).count());

        System.out.println();
        //3.
        double sum=personel.stream().mapToDouble(Personel::maas).sum();
        System.out.println("çalışanların firmaya olan toplam maliyeti: " +sum);
        System.out.println();
        //4
        personel.stream().filter(personel1 -> personel1.cinsiyet().equals('K'))
                .forEach(personel1 -> System.out.println(personel1));
        System.out.println();
        //5.
        double ortalama=personel.stream().filter(personel1 -> personel1.cinsiyet()=='K')
                .mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf(" Kadın Personel maaş ortalaması  :  %5.2f ",ortalama);
        System.out.println();
        double ortalama1 =personel.stream().filter(personel1 -> personel1.cinsiyet()=='E')
                .mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf(" Erkek Personel maaş ortalaması  :  %5.2f ",ortalama);
        System.out.println();
        double ortalama2 =personel.stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf(" Tüm personelin maaş ortalaması  :  %5.2f ",ortalama);
        System.out.println();

        //6.
        personel.stream().sorted(Comparator.comparing(Personel::yas).reversed())
                .forEach(System.out::println); // yaşı büyükten küçüğe sıralama
        System.out.println();

      long yas35K = personel.stream().filter(personel1 -> personel1.yas()>35
              & personel1.cinsiyet().equals('K')).count();
        System.out.println("  Yaşı 35'ten büyük kadınların sayısı :  "+yas35K);

        long yas50E= personel.stream().filter(personel1 -> personel1.yas()<50
                & personel1.cinsiyet()=='E').count();
        System.out.println("Yaşı 50'den küçük erkeklerin sayısı :"+yas50E);

        double ortalama3 =personel.stream().mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf(" Tüm çalışanların yaş ortalaması  :  %5.2f ",ortalama);
        System.out.println();
        System.out.println();
        //7
        personel.stream().sorted((Comparator.comparing((Personel personel1)->personel1.cinsiyet()
                                .equals('K')).reversed()
                        .thenComparing((Personel personel1)->personel1.adi()
                                ,Collator.getInstance(new Locale("tr","TR")))))
                .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println();
      //8
        double ortalama4 =personel.stream().
                filter(personel1 -> personel1.adi().startsWith("S"))
                .mapToDouble(Personel::maas).sum();
        System.out.printf("İsmi S ile başlayan çalışanların maaş toplamı :  %5.2f",ortalama4);




              {

        }



    } {










    }


}

record Personel(Integer id, String adi, Double maas, Integer yas,Character cinsiyet) {
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
