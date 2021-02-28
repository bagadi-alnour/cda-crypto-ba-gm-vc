package com.cda.jee.crypto.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		CryptoWalletImpl cw = new CryptoWalletImpl();
		System.out.println(cw.getAll());
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
				CryptoWallet cryptoWallet = new CryptoWallet(rs.getInt(1), 
						rs.getInt(2), 
						rs.getDouble(3),
						rs.getDouble(4), 
						rs.getTimestamp(5).toLocalDateTime()
				);
				cryptoWallets.add(cryptoWallet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return cryptoWallets;
	}

	@Override
	public void save(CryptoWallet t) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CryptoWallet cryptoWallet, int id) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DaoException {
		// TODO Auto-generated method stub

	}

}
