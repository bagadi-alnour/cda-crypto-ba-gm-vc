package com.cda.jee.crypto.dao;

import com.cda.jee.crypto.model.CryptoCurrency;

import java.util.ArrayList;

public interface CryptoCurrencyDao extends IDao<CryptoCurrency> {

    ArrayList<Integer> delta();
	void updateDelta(int idCrypto, double newDelta);
}
