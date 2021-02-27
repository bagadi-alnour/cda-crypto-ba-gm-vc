package com.cda.jee.crypto.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cda.jee.crypto.dao.exception.DaoConfigurationException;

public class DaoConnection {
	private static volatile DaoConnection instance;
	private Connection connection = null;

	private static final String PROPERTIES_FILE = "/com/cda/jee/crypto/dao/db.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_PASSWORD = "password";

	private String url;
	private String username;
	private String password;

	private DaoConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoConnection getInstance() throws DaoConfigurationException {
		DaoConnection result = instance;
		if (result != null) {
			return result;
		}

		synchronized (DaoConnection.class) {
			if (instance == null) {
				instance = createInstance();
			}
			return instance;
		}
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance
	 */
	private static DaoConnection createInstance() {
		Properties properties = new Properties();
		String url;
		String driver;
		String userName;
		String password;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (propertiesFile == null) {
			throw new DaoConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable.");
		}

		try {
			properties.load(propertiesFile);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			userName = properties.getProperty(PROPERTY_USERNAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (IOException e) {
			throw new DaoConfigurationException("Impossible de charger le fichier properties " + PROPERTIES_FILE, e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DaoConfigurationException("Le driver est introuvable dans le classpath.", e);
		}

		DaoConnection instance = new DaoConnection(url, userName, password);
		return instance;
	}

	/* Méthode chargée de fournir une connexion à la base de données */
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}


	public void stopConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
