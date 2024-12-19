package org.example.kutuphanesistemi.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.kutuphanesistemi.controller.Login;
import org.example.kutuphanesistemi.controller.loginstudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBCrud {


    public boolean loginPerson(String eposta, String sifre) {
        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "select * from tbl_personel where personel_eposta=? and personel_sifre=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean loginStudent(String eposta, String sifre) {
        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "select * from tbl_ogrenci where ogrenci_eposta=? and ogrenci_sifre=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else
                return false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }


    }

    public boolean signUpStudent(String ad, String soyad, String eposta, String sifre, String ogrNo) {

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "INSERT into tbl_ogrenci values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setString(3, eposta);
            ps.setString(4, sifre);
            ps.setString(5, ogrNo);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ObservableList<String> listBooksPerson() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi,case  kitap_raftami  \n" +
                "when  0 then 'rafta degil'\n" +
                "else 'rafta'\n" +
                "end as 'kitap' FROM tbl_kitap inner join tbl_tur on tbl_kitap.kitap_tur_id=tbl_tur.tur_id";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_ad = resultSet.getString("tur_adi");
                String kitap_rafta_mi = resultSet.getString("kitap");        // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_ad + "  " + kitap_rafta_mi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public ObservableList<String> listBooksPersonNotInnerJoin() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,kitap_tur_id,kitap_raftami FROM tbl_kitap";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_id = resultSet.getString("kitap_tur_id");
                boolean kitap_rafta_mi = resultSet.getBoolean("kitap_raftami");        // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_id + "  " + kitap_rafta_mi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public ObservableList<String> listTypeBook() throws SQLException {
        String selectQuery = "SELECT * from tbl_tur";
        ObservableList<String> type = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                int kitap_tur_id = resultSet.getInt("tur_id");
                String tur_Adi = resultSet.getString("tur_adi");
                // Veriyi listeye ekle
                type.add(kitap_tur_id + "   " + tur_Adi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public boolean createBooks(String ad, String yazar, String basimTarihi, String yayinEvi, String tur) {

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "INSERT into tbl_kitap(kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,kitap_tur_id) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            try {
                int turSayi = Integer.parseInt(tur);
                ps.setString(1, ad);
                ps.setString(2, yazar);
                ps.setString(3, yayinEvi);
                ps.setString(4, basimTarihi);
                ps.setInt(5, turSayi);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                } else
                    return false;
            } catch (RuntimeException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Lütfen türü sayı olarak giriniz", ButtonType.OK);
                alert.show();
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return false;
    }

    public ObservableList<String> removeBookListPerson(String kitap_ad) throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi,kitap_raftami FROM tbl_kitap inner join tbl_tur on tbl_kitap.kitap_tur_id=tbl_tur.tur_id where kitap_adi like ?";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, "%" + kitap_ad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_ad = resultSet.getString("tur_adi");
                boolean kitap_rafta_mi = resultSet.getBoolean("kitap_raftami");        // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_ad + "  " + kitap_rafta_mi);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean deleteBooks(String id) throws SQLException {

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "delete from tbl_kitap where kitap_id = (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            try {
                int kitapId = Integer.parseInt(id);
                ps.setInt(1, kitapId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                } else
                    return false;
            } catch (RuntimeException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Lütfen türü sayı olarak giriniz", ButtonType.OK);
                alert.show();
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return false;


    }

    public ObservableList<String> updateBookListPerson(String kitap_ad) throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,kitap_tur_id,kitap_raftami FROM tbl_kitap where kitap_adi like ?";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, "%" + kitap_ad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                int tur_id = resultSet.getInt("kitap_tur_id");
                boolean kitap_rafta_mi = resultSet.getBoolean("kitap_raftami");        // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_id + "  " + kitap_rafta_mi);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean updateBookPerson(String ad, String yazar, String basimTarihi, String yayinEvi, int tur, int kitap_id) throws SQLException {
        String selectQuery = "update tbl_kitap set kitap_adi=? , kitap_yazari=? , kitap_yayinevi=?,kitap_basim_tarihi=?,kitap_tur_id=? where kitap_id=?";
        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(selectQuery);
        try {

            ps.setString(1, ad);
            ps.setString(2, yazar);
            ps.setString(3, yayinEvi);
            ps.setString(4, basimTarihi);
            ps.setInt(5, tur);
            ps.setInt(6, kitap_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else
                return false;
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Lütfen türü sayı olarak giriniz", ButtonType.OK);
            alert.show();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return false;
    }

    public ObservableList<String> listBooksNotShelfPerson() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi FROM tbl_kitap inner join tbl_tur on tbl_kitap.kitap_tur_id=tbl_tur.tur_id where kitap_raftami=0";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_ad = resultSet.getString("tur_adi");
                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_ad + "  ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public ObservableList<String> listBooksShelfPerson() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi FROM tbl_kitap inner join tbl_tur on tbl_kitap.kitap_tur_id=tbl_tur.tur_id where kitap_raftami=1";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_ad = resultSet.getString("tur_adi");
                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_ad + "  ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean odunc_ver(int kitap_id, int ogr_no) throws SQLException {

        LocalDateTime now = LocalDateTime.now();
        Connection connection = DBConnector.getInstance().getConnection();

        String query1 = "update tbl_kitap set kitap_raftami=0 where kitap_id=?";
        String query2 = "insert into tbl_odunc (ogrenci_id,kitap_id,odunc_tarihi) values (?,?,?) ";
        PreparedStatement ps = connection.prepareStatement(query1);
        ps.setInt(1, kitap_id);
        PreparedStatement ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, ogr_no);
        ps2.setInt(2, kitap_id);
        ps2.setString(3, now.toString());


        if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    public boolean iade_al(int kitap_id) throws SQLException {

        LocalDateTime now = LocalDateTime.now();
        Connection connection = DBConnector.getInstance().getConnection();
        String query1 = "update tbl_kitap set kitap_raftami=1 where kitap_id=?";
        String query2 = "update tbl_odunc set iade_tarihi=? where kitap_id=? ";
        PreparedStatement ps = connection.prepareStatement(query1);
        ps.setInt(1, kitap_id);
        PreparedStatement ps2 = connection.prepareStatement(query2);
        ps2.setString(1, now.toString());
        ps2.setInt(2, kitap_id);


        if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0) {
            return true;
        }
        return false;
    }


    public boolean rafta_mi(int kitap_id) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String query = "select kitap_raftami from tbl_kitap where kitap_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, kitap_id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getBoolean("kitap_raftami");
        } else
            return false;


    }
    public String get_kitapAdi(int kitap_id) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String query = "select kitap_adi from tbl_kitap where kitap_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, kitap_id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("kitap_adi");
        } else
            return "";


    }

    public int get_ogrenci_id(String ogr_No) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Select * from tbl_ogrenci where ogrenci_no=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ogr_No);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("ogrenci_id");
        } else {
            return 0;
        }
    }
    public String get_ogrenci_no(int id) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Select * from tbl_ogrenci where ogrenci_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("ogrenci_no");
        } else {
            return " ";
        }
    }

    public boolean isStudent(String ogrenci_no) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String sql = "Select * from tbl_ogrenci where ogrenci_no=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ogrenci_no);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }

    public ObservableList<String> listBooksStudent() throws SQLException {
        String selectQuery = "SELECT kitap_adi ,odunc_tarihi, DATEADD(DAY,15,odunc_tarihi) as 'iade' FROM tbl_odunc inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where ogrenci_id=? and iade_tarihi is null";
        ObservableList<String> books = FXCollections.observableArrayList();
        Connection connection = DBConnector.getInstance().getConnection();


        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, loginstudent.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_odunc_tarih = resultSet.getString("odunc_tarihi");
                String kitap_teslim_tarih = resultSet.getString("iade");


                // Veriyi listeye ekle
                books.add("Kitap Adı: " + kitap_adi + "     " + "Kitap Alınan Tarih: " + kitap_odunc_tarih + "      " + "Kitap İade Edilmesi Gereken Tarih:  " + kitap_teslim_tarih);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int get_ogr_id(String email, String pass) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String query = "Select ogrenci_id from tbl_ogrenci where ogrenci_eposta=? and ogrenci_sifre=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, pass);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("ogrenci_id");
        } else {
            return 0;
        }
    }

    public ObservableList<String> listWasReadBooksStudent() throws SQLException {
        String selectQuery = "SELECT kitap_adi ,odunc_tarihi, iade_tarihi FROM tbl_odunc inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where ogrenci_id=? and iade_tarihi is not null";
        ObservableList<String> books = FXCollections.observableArrayList();
        Connection connection = DBConnector.getInstance().getConnection();


        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, loginstudent.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_odunc_tarih = resultSet.getString("odunc_tarihi");
                String kitap_teslim_tarih = resultSet.getString("iade_tarihi");


                // Veriyi listeye ekle
                books.add("Kitap Adı: " + kitap_adi + "     " + "Kitap Alınan Tarih: " + kitap_odunc_tarih + "      " + "Kitap İade Edilen Tarih:  " + kitap_teslim_tarih);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public ObservableList<String> get_whereIsBook() throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String query = "select tbl_kitap.kitap_adi,tbl_kitap.kitap_yazari,tbl_ogrenci.ogrenci_adi,tbl_ogrenci.ogrenci_soyadi,tbl_ogrenci.ogrenci_no,odunc_tarihi from tbl_ogrenci inner join tbl_odunc on tbl_ogrenci.ogrenci_id=tbl_odunc.ogrenci_id inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where tbl_odunc.iade_tarihi is null";

        ObservableList<String> books = FXCollections.observableArrayList();
        PreparedStatement pd = connection.prepareStatement(query);
        ResultSet rs = pd.executeQuery();
        while (rs.next()) {

            String kitap_adi = rs.getString("kitap_adi");
            String yazari = rs.getString("kitap_yazari");
            String ogrenci_adi = rs.getString("ogrenci_adi");
            String ogrenci_soyadi = rs.getString("ogrenci_soyadi");
            String ogrenci_no = rs.getString("ogrenci_no");
            String odunc_tarihi = rs.getString("odunc_tarihi");
            books.add("Kitap adı: " + kitap_adi + "  Kitap yazarı: " + yazari + "  Ögrenci adı: " + ogrenci_adi + " " + ogrenci_soyadi + "  Öğrenci no: " + ogrenci_no + "  Ödünç tarihi: " + odunc_tarihi);
        }
        return books;
    }

    public ObservableList<String> listBooksStudent(String kitap_ad) throws SQLException {
        String selectQuery = "SELECT kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi,case  kitap_raftami  \n" +
                "when  0 then 'rafta degil'\n" +
                "else 'rafta'\n" +
                "end as 'kitap' FROM tbl_kitap inner join tbl_tur on tbl_kitap.kitap_tur_id=tbl_tur.tur_id where kitap_adi like ?";
        ObservableList<String> books = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, "%" + kitap_ad + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_ad = resultSet.getString("tur_adi");
                String kitap_rafta_mi = resultSet.getString("kitap");        // Veriyi listeye ekle
                books.add(kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_ad + "  " + kitap_rafta_mi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;

    }

    public ObservableList<String> kitap_list2() throws SQLException {
        String selectQuery = "SELECT " +
                "    kitap_adi," +
                "    kitap_yazari," +
                "    kitap_yayinevi," +
                "    kitap_basim_tarihi," +
                "    tbl_tur.tur_adi," +
                "    CASE kitap_raftami" +
                "        WHEN 1  THEN 'Rafta'" +
                "        ELSE 'Rafta değil'" +
                "end as 'kitap_durum' " +
                "FROM " +
                "    tbl_kitap " +
                "INNER JOIN " +
                "    tbl_tur " +
                "ON " +
                "    tbl_kitap.kitap_tur_id = tbl_tur.tur_id;";

        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");
                String kitap_rafta_mi = resultSet.getString("kitap_durum");

                // Veriyi listeye ekle
                books.add("Kitap Adi: " + kitap_adi + "  Kitap yazarı: " + kitap_yazari + "  Kitap yayınevi: " + kitap_yayinevi + "  Basım Tarihi:" + kitap_basim_tarihi + "  Litap Türü:" + tur_adi + "  Rafta Mı: " + kitap_rafta_mi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;

    }

    public int getOgrenciId_BooksId(int kitap_id) throws SQLException {
       String sql="Select ogrenci_id from tbl_odunc where kitap_id=? and iade_tarihi is not null";
       Connection connection= DBConnector.getInstance().getConnection();
       PreparedStatement ps= connection.prepareStatement(sql);
       ps.setInt(1,kitap_id);
       ResultSet rs= ps.executeQuery();
       if(rs.next()){
           return rs.getInt("ogrenci_id");
       }
return 0;

    }

    public boolean setDuyuru(String mesaj,String ogrNo) throws SQLException {

       String sql="insert into tbl_duyuru (ogrenci_id,duyuru_icerik) values (?,?)";
       Connection connection= DBConnector.getInstance().getConnection();
       PreparedStatement preparedStatement= connection.prepareStatement(sql);

       preparedStatement.setInt(1,get_ogrenci_id(ogrNo));
       preparedStatement.setString(2,mesaj);

        if(preparedStatement.executeUpdate()>0){
            return true;
        }
        return false;
    }

    public ObservableList<String> listAnnounce() throws SQLException {
        String selectQuery = "Select duyuru_icerik from tbl_duyuru where ogrenci_id=?";
        ObservableList<String> announces = FXCollections.observableArrayList();
        ;
        Connection connection = DBConnector.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1,loginstudent.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String duyuru_adi = resultSet.getString("duyuru_icerik");

                announces.add(duyuru_adi );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return announces;

    }

    public String get_ogrenci_name() throws SQLException {
        Connection connection= DBConnector.getInstance().getConnection();
        String sql="Select ogrenci_adi,ogrenci_soyadi from tbl_ogrenci where ogrenci_id=?";
        PreparedStatement ps= connection.prepareStatement(sql);

        ps.setInt(1,loginstudent.ogr_id);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
          return  rs.getString("ogrenci_adi") + " " +rs.getString("ogrenci_soyadi");

        }
        return "";


    }



}



