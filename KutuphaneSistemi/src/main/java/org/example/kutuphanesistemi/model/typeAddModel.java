package org.example.kutuphanesistemi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class typeAddModel implements ItypeAdd{
    @Override
    public boolean typeAdd(String tur) throws SQLException {
        String query="insert into tbl_tur (tur_adi) values (?)";
        Connection connection=DBConnector.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, tur);

        return ps.executeUpdate()>0;
    }
}
