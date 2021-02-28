package com.cda.jee.crypto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cda.jee.crypto.dao.CryptoWalletDao;
import com.cda.jee.crypto.dao.DaoConnection;
import com.cda.jee.crypto.dao.exception.DaoException;
import com.cda.jee.crypto.model.CryptoWallet;

public class CryptoWalletImpl implements CryptoWalletDao {
	Connection conn = DaoConnection.getInstance().getConnection();
	PreparedStatement ps;
	ResultSet rs;

	public static void main(String[] args) {
		CryptoWalletImpl cWImp = new CryptoWalletImpl();
		CryptoWallet cW1 =  new CryptoWallet(0, 1, 1000.0, 0.5, LocalDateTime.of(2010, 5, 10, 15, 3));
		CryptoWallet cW2 =  new CryptoWallet(0, 2, 500.0, 0.10, LocalDateTime.of(2019, 5, 10, 15, 3));
		
		cWImp.save(cW1);
		System.out.println(cWImp.getAll());
		
		cWImp.update(cW2, 3);
		System.out.println(cWImp.getAll());
	}

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
            ps.setInt(5,id);
            
            ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(int id) throws DaoException {
        try {
            ps = conn.prepareStatement("delete from cryptoWallet where idWallet = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

}
