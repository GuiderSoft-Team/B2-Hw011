import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {
        List<Personel> personel = Arrays.asList(
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
        System.out.printf("Çalışan personel adedi : "+personel.stream().count());
        System.out.println();
        long count=personel.stream().filter(i-> i.cinsiyet()=='E').count();
        System.out.println("Erkek çalışan :"+count);
        System.out.println("Kadın çalışan: "+personel.stream().filter(i->i.cinsiyet()=='K').count());
        System.out.println();
        double maliyet= personel.stream().mapToDouble(Personel::maas).sum();
        System.out.println("Toplam Maliyet : "+maliyet);
        List<Personel>KadinPersonel=personel.stream().filter(i->i.cinsiyet()=='K').toList();
        List<Personel>ErkekPersonel=personel.stream().filter(i->i.cinsiyet()=='E').toList();
        System.out.println("Kadın Personel Listesi : "+KadinPersonel);
        double KadinPersonelMaasOrtalamasi= KadinPersonel.stream()
                .mapToDouble(Personel::maas).average().getAsDouble();
        double ErkekPersonelinMaasOrtalamasi= ErkekPersonel.stream()
                .mapToDouble(Personel::maas).average().getAsDouble();
        double GenelOrtalama= personel.stream().mapToDouble(Personel::maas).average().getAsDouble();
        System.out.printf("Kadın Personel Maaş Ortalaması: %5.2f, " +
                "\n Erkek Personel Maaş Ortalaması: %5.2f \n," +
                " Genel Maaş Ortalması : %5.2f",KadinPersonelMaasOrtalamasi,ErkekPersonelinMaasOrtalamasi,GenelOrtalama);
        System.out.println();
        List<Personel> PersonelYas= personel.stream()
                .sorted(Comparator.comparing(Personel::yas,Comparator.reverseOrder())).toList();
        PersonelYas.forEach(System.out::println);
        System.out.println();
        PersonelYas.stream().filter(i->i.yas()>35).filter(i->i.cinsiyet()=='K').forEach(System.out::println);
        System.out.println();
        PersonelYas.stream().filter(i->i.yas()<50).filter(i->i.cinsiyet()=='E').forEach(System.out::println);
        System.out.println();
        System.out.printf("Tüm Çalışanların Yaş Ortalaması: %5.2f ",personel.stream().mapToDouble(Personel::yas)
                .average().getAsDouble());
        System.out.println();
        List<Personel>KadinlarIsimSiraliListe= personel.stream().filter(i->i.cinsiyet()=='K')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr","TR")))).toList();
        System.out.println(KadinlarIsimSiraliListe);
        List<Personel>ErkeklerIsimSiraliListe= personel.stream().filter(i->i.cinsiyet()=='E')
                .sorted(Comparator.comparing(Personel::adi, Collator.getInstance(new Locale("tr","TR")))).toList();
        System.out.println(ErkeklerIsimSiraliListe);
        System.out.println("İsmi S ile başlayan Çalışanlarınj Maaş Toplamı : "+personel.stream().filter(i->i.adi().charAt(0)=='S').mapToDouble(Personel::maas).sum());

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
