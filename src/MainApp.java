import java.util.List;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        int calisanPersonelSayisi = Personel.calisanlar().size();
        System.out.println("Çalışan personel sayısı : "+calisanPersonelSayisi);
        long erkekPersonelSayisi = Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('E')).count();
        System.out.println("Erkek personel sayısı : "+erkekPersonelSayisi);
        long kadinCalisanSayisi = Personel.calisanlar().stream().filter(item -> item.cinsiyet().equals('K')).count();
        System.out.println("Kadın personel sayısı : "+kadinCalisanSayisi);
        double erkekMaasTotal = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('E'))
                .mapToDouble(Personel::maas).sum();
        System.out.println("Erkeklerin personel maaliyet toplamı : "+erkekMaasTotal);
        double kadinMaasTotal = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K'))
                .mapToDouble(Personel::maas).sum();
        System.out.println("Kadınların personel maaliyet toplamı : "+kadinMaasTotal);
        System.out.println("");
        System.out.println("Toplam kadın personel sayısı");
        System.out.println("--------");
        Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K')).forEach(System.out::println);
        double kadinMaasOrt = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('K')).mapToDouble(Personel::maas).average().getAsDouble();
        System.out.println();
        System.out.printf("Kadın personelin maaş ortalaması : %5.2f",kadinMaasOrt);
        System.out.println();
        double erkekMaasOrt = Personel.calisanlar()
                .stream().filter(item -> item.cinsiyet().equals('E')).mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Erkek personelin maaş ortalaması : %5.2f",erkekMaasOrt);
        System.out.println();
        double genelMaasOrt = Personel.calisanlar()
                .stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Genel personel maaş ortalaması : %5.2f",genelMaasOrt);
        System.out.println();
        System.out.println("--------");
        System.out.println("Büyükten küçük yaşa göre sıralanan liste");
        System.out.println("---------");
        Personel.calisanlar().stream().sorted(Comparator.comparing(Personel::yas).reversed()).forEach(System.out::println);
        System.out.println("---------");
        System.out.println("35 yaşından küçük kadın personel listesi");
        System.out.println("---------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::yas)).filter(item -> item.yas() > 35).filter(item -> item.cinsiyet().equals('K')).forEach(System.out::println);
        System.out.println("----------");
        System.out.println("50 yaşından küçük erkek personel listesi");
        System.out.println("----------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::yas)).filter(item-> item.yas()<50).filter(item -> item.cinsiyet().equals('E')).forEach(System.out::println);
        double totalYasOrtalamasi = Personel.calisanlar()
                .stream().mapToDouble(Personel::yas).average().getAsDouble();
        System.out.printf("Personelin genel yaş ortalaması : %5.2f",totalYasOrtalamasi);
        System.out.println();
        System.out.println("-----------");
        System.out.println("Kadın çalışanların isimleri başta olmak üzere alfabetik sıralanmış isim listesi");
        System.out.println("------------");
        Personel.calisanlar()
                .stream().sorted(Comparator.comparing(Personel::cinsiyet).reversed()
                        .thenComparing(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR")))))
                .forEach(item-> System.out.println(item));
        System.out.println("------------");
        double s = Personel.calisanlar()
                .stream().filter(item -> item.adi().startsWith("S")).mapToDouble(Personel::maas).sum();
        System.out.printf("İsmi 'S' ile başlayan personelin maaşlarının toplamı : %5.2f ",s);
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
