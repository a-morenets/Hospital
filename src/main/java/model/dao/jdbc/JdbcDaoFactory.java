package model.dao.jdbc;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PersonDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JdbcDaoFactory extends DaoFactory {
	    private static final String DB_URL = "url";
		private DataSource dataSource;

	    public JdbcDaoFactory() {
	        try{
	            InitialContext ic = new InitialContext();
	            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/football");
	            //connection = dataSource.getConnection();
	            
	            
	        }catch(Exception e){
	            throw new RuntimeException(e);
	        }
	    }

		@Override
		public DaoConnection getConnection() {
				try {
					return new JdbcDaoConnection( dataSource.getConnection() );
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}

		@Override
		public PersonDao createPersonDao(DaoConnection connection) {
			JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
			Connection sqlConnection = jdbcConnection.getConnection();
			return new JdbcPersonDao(sqlConnection);
		}


	}
