# KutuphaneYonetimSistemi

Kütüphane Yönetim Sistemi
Bir okulun kütüphanesi için bir kütüphane sistemi oluşturuldu. Proje Java ile yazıldı. Arayüz javaFX ile yapılmıştır.
Personel ve öğrenci olmak üzere iki kullanıcı türü vardır.
Öğrenciler kütüphanedeki kitapları listeleyebilir, duyuruları görebilir ayrıca şu an elinde olan kitapları görebilir ve önceden aldığı kitapları listeleyebilir.
Personeller ise kitapları listeleyebilir, kitap ekleyebilir ,kitap silebilir, kitap güncelleyebilir, kitap türü ekleyebilir. öğrenciye kitap ödünç verebilir veya kitabın iadesini alabilir.. Ayrıca ödünç verilmiş kitapların nerede olduğunu görebilir.
Giriş sayfasında öğrenci üyelik oluşturup öğrenci sayfasına giriş yapabilir ancak personel üyelik oluşturamaz. Sadece sisteme giriş yapabilir.


Sistemde Singleton, Abstract Factory, State, Observer tasarım desenleri kullanılmıştır.
Singleton -> Veritabanı bağlantısında kullanılmıştır.
Abstract Factory -> Yeni bit tür ekleme işleminde kullanılmıştır.
State -> Kitabın ödünç ve iade durumununu yönetmekte kullanılmıştır.
Observer -> Kitabın ödünç veya iade edilme durumunda kitabı alan öğrenciye bildirim göndermede kullanılmıştır.

Projede bir tane abstract sınıf kullanılmıştır. Kullanıcıların login işlemlerinde doğrulama işlemi için kullanılmıştır.


Singleton sınıfı -> DBConnector
Abstract Factory -> ItypeAdd,typeAddModel, ItypeAddFactory, typeAddFactory, typeFactory
State -> IbookLendState, Lend, LendReturn,
Observer -> Subject, Observer, Student, duyuru
Abstract class -> Login, student_login, person_login


Furkan Görür https://github.com/FurkanGorur
Melih Gönan https://github.com/gonanMelih 
