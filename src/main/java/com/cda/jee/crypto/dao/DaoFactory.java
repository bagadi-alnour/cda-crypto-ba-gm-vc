package com.cda.jee.crypto.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cda.jee.crypto.dao.exception.DaoConfigurationException;

public class DaoFactory {

	private static final String PROPERTIES_FILE = "/com/sdzee/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USERNAME = "nomutilisateur";
	private static final String PROPERTY_PASSWORD = "motdepasse";

	private String url;
	private String username;
	private String password;

	DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DaoFactory getInstance() throws DaoConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String userName;
		String password;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(PROPERTIES_FILE);

		if (fichierProperties == null) {
			throw new DaoConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable.");
		}

		try {
			properties.load(fichierProperties);
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

		DaoFactory instance = new DaoFactory(url, userName, password);
		return instance;
	}

	/* Méthode chargée de fournir une connexion à la base de données */
	/* package */ Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
