import java.text.Collator;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        List<Personel> calisanlar = new ArrayList<>(Personel.calisanlar());
        List<Personel> kadinPersonels = calisanlar.stream().filter(item -> item.cinsiyet() == 'K').toList();
        List<Personel> erkekPersonels = calisanlar.stream().filter(item -> item.cinsiyet() == 'E').toList();

        //1. Çalışan personel adedini bulup, ekranda gösteriniz
        long count = calisanlar.stream().count();
        System.out.println("Çalışan personel adedi : " + count);
        
        //2. Erkek ve kadın personel sayılarını buldurup, ekrana yazdırınız.
        long erkek = erkekPersonels.stream().count();
        long kadin = kadinPersonels.stream().count();
        System.out.println("Erkek calisan sayisi : " + erkek);
        System.out.println("Kadin calisan sayisi : " + kadin);

        //3. Bu çalışanların firmaya olan toplam maliyetini hesaplayıp, ekrana yazdırınız.
        double maliyet = calisanlar.stream().mapToDouble(Personel::maas).sum();
        System.out.printf("Toplam maliyet : %5.2f\n\n" , maliyet);

        //4. Kadın çalışanların listesini buldurunuz ve ekrana yazdırınız.
        kadinPersonels.forEach(System.out::println);
        System.out.println();

        //5. Maaş ortalaması
        double kadinOrtalama = kadinPersonels.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double erkekOrtalama = erkekPersonels.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double ortalama = calisanlar.stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Kadın çalışanların maaş ortalaması : %5.2f\nErkek çalışanların maaş ortalaması : %5.2f\n" +
                "Genel ortalama : %5.2f\n\n", kadinOrtalama, erkekOrtalama,ortalama);

        //6. Yaş;
        //    a. Tüm çalışanları büyükten küçüğe **listeleyin** ve ekrana **yazdırın**
        List<Personel> reverseAge = calisanlar.stream().sorted(Comparator.comparing(Personel::yas).reversed()).toList();
        reverseAge.forEach(System.out::println);
        System.out.println();

        //b. Yaşı 35'ten büyük kadınların sayısını bulup, yazdırın.
        long count1 = kadinPersonels.stream().filter(item -> item.yas() > 35).count();
        System.out.printf("Yaşı 35'ten büyük kadınların sayısı : %d\n" , count1);

        //c. Yaşı 50'den küçük erkeklerin sayısını bulup, yazdırın
        long count2 = erkekPersonels.stream().filter(item -> item.yas() < 50).count();
        System.out.printf("Yaşı 50'den kücük erkeklerin sayısı : %d\n" , count2);

        //d. Tüm çalışanların yaş ortalaması nedir? Formatlı olarak yazdırınız
        double yasOrtalama = calisanlar.stream().mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf("Tüm çalışanların yaş ortalaması : %5.2f\n\n" , yasOrtalama);

        //7. Tüm çalışanların, önce kadınlar, sonra erkekler olacak şekilde isme göre sıralı listesini buldurunuz yazdırınız.
        List<Personel> personels = calisanlar.stream().sorted(Comparator.comparing(Personel::cinsiyet).reversed()
                .thenComparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR")))).toList();
        personels.forEach(System.out::println);
        System.out.println();

        //8. İsmi S ile başlayan çalışanların maaş toplamını ekrana yazdırınız.
        double sum = calisanlar.stream().filter(item -> item.adi().charAt(0) == 'S').mapToDouble(Personel::maas).sum();
        System.out.println("İsmi S ile başlayan çalışanların maaş toplamı : " + sum);

    }
}

