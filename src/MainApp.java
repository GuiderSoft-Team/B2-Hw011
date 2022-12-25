import java.text.Collator;
import java.util.*;



public class MainApp {
    public static void main(String[] args) {

        long count = Personel.calisanlar().size();
        System.out.println("ÇALIŞAN PERSONEL SAYISI: "+count);

        long erkekPersonelSayisi = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'E')
                .count();
        System.out.println("----------\n"+"ERKEK PERSONEL SAYISI: "+erkekPersonelSayisi);

        long kadinPErsonelSayisi = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'K')
                .count();
        System.out.println("----------\n"+"KADIN PERSONEL SAYISI: "+ kadinPErsonelSayisi);

       double CalisanlarinMaliyeti = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::maas)
                .sum();
        System.out.println("----------\n"+"TUM CALIŞANLARI MALİYETİ: "+CalisanlarinMaliyeti);

        List<Personel> kadinPersoneller = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet().equals('K'))
                .toList();
        System.out.println("----------\n"+"KADIN PERSONELLER :");
        kadinPersoneller.forEach(System.out::println);

        double kadinCalisanMaasOrtalamasi = kadinPersoneller
                .stream()
                .mapToDouble(Personel::maas)
                .average()
                .orElse(0.0);

        double erkekCalisanMaasOrtalamasi = Personel.calisanlar()
                .stream()
                .filter(i -> i.cinsiyet() == 'E')
                .mapToDouble(Personel::maas)
                .average()
                .orElse(0.0);

        double tumCalisanlarMaasOrtalamasi = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::maas)
                .average()
                .orElse(0.0);

        System.out.printf("""
                        ----------
                        Kadın çalişanlar Maaş Ortalaması: %.2f
                        Erkek çalişanlar Maaş Ortalaması: %.2f
                        Tüm Çalişanlar Maaş Ortalaması  : %.2f
                        """,
                        kadinCalisanMaasOrtalamasi,
                        erkekCalisanMaasOrtalamasi,
                        tumCalisanlarMaasOrtalamasi);

        List<Personel> tumCalisanBuyuktenKucuge = Personel.calisanlar()
                .stream()
                .sorted(Comparator.comparing(Personel::yas).reversed())
                .toList();
        System.out.println("----------\n"+"TUM ÇALİSANLAR BÜYÜKTEN KÜÇÜĞE");
        tumCalisanBuyuktenKucuge.forEach(System.out::println);

        long buyuk35Kadinlar = kadinPersoneller
                .stream()
                .filter(i -> i.yas() < 35)
                .count();
        System.out.println("----------\n"+"35 YAŞINDAN BÜYÜK KADINLARIN SAYISI: "+buyuk35Kadinlar);

        long buyuk50YasErkek = Personel.calisanlar()
                .stream()
                .filter(i -> i.yas() < 50 & i.cinsiyet() == 'E')
                .count();
        System.out.println("----------\n"+"50 YAŞINDAN KÜÇÜK ERKEKLERİN SAYISI :"+buyuk50YasErkek );

        double tumCalisanlarYasOrtalamasi = Personel.calisanlar()
                .stream()
                .mapToDouble(Personel::yas)
                .average()
                .orElse(0.0);
        System.out.printf("""
                          ----------
                          TUM ÇALIŞANLAR YAS ORTALAMASI : %.2f
                          """,
                          tumCalisanlarYasOrtalamasi);

        List<Personel> onceKadinlarSonraErkeklerIsmeGoreSirali = tumCalisanBuyuktenKucuge
                .stream()
                .sorted(Comparator.comparing(Personel::cinsiyet)
                        .reversed()
                        .thenComparing(Personel::adi, Collator.getInstance(new Locale("tr", "TR"))))
                .toList();
        System.out.println("------------------\n"
                           +"ONCE KADINLAR SONRA ERKEKLER İSME GÖRE SIRALI LİSTE");
        onceKadinlarSonraErkeklerIsmeGoreSirali.forEach(System.out::println);


        double ismi_S_IleBaslayanlarinMaasToplami  = tumCalisanBuyuktenKucuge
                .stream()
                .filter(i -> i.adi().startsWith("S"))
                .mapToDouble(Personel::maas)
                .sum();
        System.out.println("-----------\n"+
                           "İSMİ 'S' İLE BAŞLAYANLARIN MAAŞ TOPLAMI: "
                           +ismi_S_IleBaslayanlarinMaasToplami);


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
