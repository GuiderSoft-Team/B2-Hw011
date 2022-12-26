import java.util.List;

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
