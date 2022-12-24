import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {

        List<Personel>calisanlar=Personel.calisanlar();
        System.out.println("çalışan personel adedi: "+calisanlar.stream().count());
        System.out.printf("çalışan kadın personel sayısı:%d\n " +
                        "çalışan erkek personel sayısı:%d",
                calisanlar.stream().filter(item->item.cinsiyet().equals('K')).count(),
                calisanlar.stream().filter(item->item.cinsiyet().equals('E')).count());
        System.out.println();
        System.out.println("çalışanların toplam maliyeti: "
        +calisanlar.stream().mapToDouble(Personel::maas).sum());
        List<Personel>kadincalisanlar=calisanlar.stream().filter(item->item.cinsiyet().equals('K')).toList();
        List<Personel>erkekcalisanlar=calisanlar.stream().filter(item->item.cinsiyet().equals('E')).toList();
        System.out.println(kadincalisanlar);
        double kavrg = kadincalisanlar.stream().mapToDouble(Personel::maas).average().getAsDouble();
        double eavrg= calisanlar.stream().filter(item->item.cinsiyet().equals('E'))
                .mapToDouble(Personel::maas).average().getAsDouble();
        double gavrg=kavrg+eavrg;

        System.out.printf("İş bu işyerinde çalışan kadınların maaş ort:%f, erkeklerin maaş ort:%f" +
                " genel ort:%f",kavrg,eavrg,gavrg);

        //çalişanları yaş kriterine göre büyükten küçüğe sıralanışı
        calisanlar.stream().sorted(Comparator.comparing(Personel::yas).reversed())
                .forEach(System.out::println);

        System.out.println();
        //yaşi 35 ten büyük olanların yazılışı
        kadincalisanlar.stream().filter(item->item.yas()>35).forEach(System.out::println);
        System.out.println();

        //yasi 50 den büyük erkeklerin listesi
        erkekcalisanlar.stream().filter(item->item.yas()>50).forEach(System.out::println);

        //tüm çalişanların yaş ort
        double ageaverage = calisanlar.stream().mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf("tüm çalişanların yaş ortalaması:%f\n",ageaverage);
        System.out.println();
        calisanlar.stream()
                .sorted((Comparator.comparing((Personel p)->p.cinsiyet().equals('E'))
                        .thenComparing((Personel p)->p.adi())))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println();

        double s = calisanlar.stream().filter(item -> item.adi().startsWith("S")).mapToDouble(Personel::maas).sum();
        System.out.println("İsmi -S- ile başlayan işçilerin maaş toplamı:"+s);
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
