import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        int calisanPersonelAdedi = Personel.calisanlar().size();
        System.out.println("Calisan Personel Adedi : "+calisanPersonelAdedi);
        long erkekCalisanSayi = Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('E')).count();
        System.out.println("Erkek Calisan Sayi : "+erkekCalisanSayi);
        long bayanCalisanSayi = Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('K')).count();
        System.out.println("Bayan Calisan Sayi : "+bayanCalisanSayi);
        double erkekMaasTotal = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('E'))
                .mapToDouble(Personel::maas).sum();
        System.out.println("Toplam Erkek Maas : "+erkekMaasTotal);
        double bayanMaasTotal = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K'))
                .mapToDouble(Personel::maas).sum();
        System.out.println("Toplam Bayan Maas : "+bayanMaasTotal);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Toplam Calisan Bayan Listesi");
        System.out.println("----------------------------------------------------------------");
        Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K')).forEach(System.out::println);

        double bayanCalisanMaasOrt = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K')).mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Bayan Calisanlarin Maas Ortalamasi : %5.2f",bayanCalisanMaasOrt);
        System.out.println();
        double erkekCalisanMaasOrt = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('E')).mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Erkek Calisanlarin Maas Ortalamasi : %5.2f",erkekCalisanMaasOrt);
        System.out.println();
        double genelMaasOrt = Personel.calisanlar()
                .stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Calisanlarin Maas Ortalamasi : %5.2f",genelMaasOrt);
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Büyükten Kücüge Yas Bazli Tüm Liste");
        System.out.println("-------------------------------------------------------------");
        Personel.calisanlar().stream().sorted(Comparator.comparing(Personel::yas).reversed()).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Yasi 35 den Kücük Bayan Liste");
        System.out.println("---------------------------------------------------------------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::yas)).filter(item -> item.yas() > 35).filter(item -> item.cinsiyet().equals('K')).forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Yasi 50 den Kücük erkeklerin Listesi");
        System.out.println("----------------------------------------------------------------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::yas)).filter(item-> item.yas()<50).filter(item -> item.cinsiyet().equals('E')).forEach(System.out::println);
        double toplamYasOrtalamasi = Personel.calisanlar()
                .stream().mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf("Toplam Calisanlarin Yas Ortalamasi : %5.2f",toplamYasOrtalamasi);
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Tüm Calisanlarin Önce Bayanlar olmak üzere Isme Göre Listesi");
        System.out.println("----------------------------------------------------------------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::cinsiyet).reversed()
                        .thenComparing(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR")))))
                .forEach(item-> System.out.println(item));

        System.out.println("----------------------------------------------------------------");
        double s = Personel.calisanlar()
                .stream().filter(item -> item.adi().startsWith("S")).mapToDouble(Personel::maas).sum();
        System.out.printf("Ismi S ile Baslayan Personelin Maas Toplami : %5.2f ",s);




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
