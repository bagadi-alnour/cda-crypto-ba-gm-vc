package com.cda.jee.crypto.dao.impl;

import com.cda.jee.crypto.dao.CryptoWalletDao;
import com.cda.jee.crypto.dao.DaoConnection;
import com.cda.jee.crypto.dao.exception.DaoException;
import com.cda.jee.crypto.model.CryptoWallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CryptoWalletImpl implements CryptoWalletDao {
    public static void main(String[] args) {
        CryptoCurrencyImpl cc = new CryptoCurrencyImpl();
        ArrayList<Integer> delta = cc.delta();
        System.out.println( "blal" +delta.toString());

    }

    public ArrayList<Integer> delta() {
        ArrayList<Integer> delta = new ArrayList<Integer>();
        try {
            ps = conn.prepareStatement("select (currentPrice - cw.purchasePrice) from cryptoCurrency cc join cryptoWallet cw  using(idCrypto)");
            rs = ps.executeQuery();
            if(rs.next()) {
                delta.add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return delta;
    }

    Connection conn = DaoConnection.getInstance().getConnection();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Optional<CryptoWallet> get(int id) throws DaoException {
        CryptoWallet cryptoWallet = null;
        try {
            ps = conn.prepareStatement("select * from cryptoWallet where idWallet = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                cryptoWallet = new CryptoWallet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getTimestamp(5).toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(cryptoWallet);
    }

    @Override
    public List<CryptoWallet> getAll() throws DaoException {
        List<CryptoWallet> cryptoWallets = new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from cryptoWallet");
            rs = ps.executeQuery();
            while (rs.next()) {
                CryptoWallet cryptoWallet = new CryptoWallet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getTimestamp(5).toLocalDateTime());
                cryptoWallets.add(cryptoWallet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return cryptoWallets;
    }


    @Override
    public void save(CryptoWallet cW) throws DaoException {
        try {
            ps = conn.prepareStatement(
                    "insert into cryptoWallet (idCrypto, purchasePrice, quantity, purchaseDate) values(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, cW.getIdCrypto());
            ps.setDouble(2, cW.getPurchasePrice());
            ps.setDouble(3, cW.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(cW.getPurchaseDate()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CryptoWallet cW, int id) throws DaoException {
        try {
            ps = conn.prepareStatement("update cryptoWallet set  idCrypto = ?, purchasePrice = ?, quantity = ?, purchaseDate = ? where idWallet = ?");
            ps.setInt(1, cW.getIdCrypto());
            ps.setDouble(2, cW.getPurchasePrice());
            ps.setDouble(3, cW.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(cW.getPurchaseDate()));
            ps.setInt(5, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public boolean delete(int id) throws DaoException {
    	boolean res = false;
        try {
            ps = conn.prepareStatement("delete from cryptoWallet where idWallet = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            res = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
			throw new DaoException(e);
        }
		return  res;
    }


}
