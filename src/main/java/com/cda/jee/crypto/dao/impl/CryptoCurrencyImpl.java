package com.cda.jee.crypto.dao.impl;

<<<<<<< HEAD
public class CryptoCurrencyImpl {
	
=======
import java.util.List;
import java.util.Optional;

import com.cda.jee.crypto.dao.CryptoCurrencyDao;
import com.cda.jee.crypto.dao.exception.DaoException;
import com.cda.jee.crypto.model.CryptoCurrency;

public class CryptoCurrencyImpl implements CryptoCurrencyDao {

	@Override
	public Optional<CryptoCurrency> get(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CryptoCurrency> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CryptoCurrency t) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CryptoCurrency t, String[] params) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CryptoCurrency t) throws DaoException {
		// TODO Auto-generated method stub

	}
>>>>>>> b75140c... Add implementations of DAO and personalized exceptions to IDao
}
