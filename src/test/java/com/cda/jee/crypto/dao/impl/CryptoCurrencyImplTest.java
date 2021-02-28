package com.cda.jee.crypto.dao.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;
import java.sql.Connection;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cda.jee.crypto.dao.CryptoCurrencyDao;
import com.cda.jee.crypto.dao.DaoConnection;

class CryptoCurrencyImplTest {
	
	static CryptoCurrencyDao currencyImp;
	
	@BeforeAll
	static void init() throws Exception {
		currencyImp = new CryptoCurrencyImpl();
		Connection conn = DaoConnection.getInstance().getConnection();
		RunScript.execute(conn, new FileReader(CryptoCurrencyImplTest.class.getResource("/ddl_script.sql").getFile()));
		RunScript.execute(conn, new FileReader(CryptoCurrencyImplTest.class.getResource("/data_script.sql").getFile()));
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
