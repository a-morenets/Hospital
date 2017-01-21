package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.PersonDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

public class JdbcDaoFactory extends DaoFactory {

	private Connection connection;
	private static final String DB_URL = "url";

	public JdbcDaoFactory() {
		try {
			InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
			Properties dbProps = new Properties();
			dbProps.load(inputStream);
			String url = dbProps.getProperty(DB_URL);
			connection = DriverManager.getConnection(url, dbProps);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PersonDao createPersonDao() {
		return new JdbcPersonDao(connection);
	}

}
