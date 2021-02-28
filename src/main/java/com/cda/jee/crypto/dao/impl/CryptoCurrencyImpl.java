package com.cda.jee.crypto.dao.impl;

import com.cda.jee.crypto.dao.CryptoCurrencyDao;
import com.cda.jee.crypto.dao.DaoConnection;
import com.cda.jee.crypto.dao.exception.DaoException;
import com.cda.jee.crypto.model.CryptoCurrency;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CryptoCurrencyImpl implements CryptoCurrencyDao {
    Connection conn = DaoConnection.getInstance().getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public static void main(String[] args) {

        CryptoCurrencyImpl cc = new CryptoCurrencyImpl();
    }

    @Override
    public Optional<CryptoCurrency> get(int id) throws DaoException {
        CryptoCurrency cryptoCurrency = null;
        try {
            ps = conn.prepareStatement("select * from cryptoCurrency where idCrypto = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                cryptoCurrency = new CryptoCurrency(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime()
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(cryptoCurrency);

    }

    @Override
    public List<CryptoCurrency> getAll() throws DaoException {
        List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from cryptoCurrency");
            rs = ps.executeQuery();
            while (rs.next()) {
                CryptoCurrency cc = new CryptoCurrency(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getTimestamp(6).toLocalDateTime()
                );
                cryptoCurrencies.add(cc);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cryptoCurrencies;
    }

    @Override
    public void save(CryptoCurrency cc) throws DaoException {

		try {
			ps = conn.prepareStatement("insert into cryptoCurrency (name, symbol, currentPrice, imageUrl, lastUpdated) values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,cc.getName());
			ps.setString(2,cc.getSymbol());
			ps.setInt(3, (int) cc.getCurrentPrice());
			ps.setString(4,cc.getImageUrl());
			ps.setTimestamp(5, Timestamp.valueOf(cc.getLastUpdated()));
			
			ps.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}


	}

    @Override
    public void update(CryptoCurrency cryptoCurrency, int id) throws DaoException {
        try {
            ps = conn.prepareStatement("update cryptoCurrency set  name = ?, symbol = ?, currentPrice = ?, imageUrl = ?, lastUpdated = ? where idCrypto = ?");
            ps.setString(1,cryptoCurrency.getName());
            ps.setString(2,cryptoCurrency.getSymbol());
            ps.setDouble(3,cryptoCurrency.getCurrentPrice());
            ps.setString(4,cryptoCurrency.getImageUrl());
            ps.setTimestamp(5,Timestamp.valueOf(cryptoCurrency.getLastUpdated()));
            ps.setInt(6,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            ps = conn.prepareStatement("delete from cryptoCurrency where idCrypto = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
