package dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;

import DATABASE.DatabaseHelper;
import DTO.Customer;
import DTO.PhoneNumber;
 

import org.apache.log4j.Logger;

import BEAN.DatabaseProperties;

public class Dao32Bit extends DatabaseHelper {
	Logger logger = Logger.getLogger(Dao32Bit.class);

	public void init(Properties appProperties) {

		DatabaseProperties databaseProperties = new DatabaseProperties();

		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
	 
		super.init(databaseProperties);

	}

 
	public boolean addCustomer(Customer customer, PhoneNumber phoneNumber) throws Exception {

		Connection conn = getConnection();
		try {
			phoneNumber = insertCustomer(customer, phoneNumber, conn);
			insertPhone(phoneNumber, conn);

			conn.commit();
			logger.error("Veritabanýna Veri Eklenildi");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

			logger.debug("Veritabanýna Veri Eklenirken SIkýntý Oldu Rollback Tamamlandi");
			return false;
		} finally {

			closeConnection(conn);

		}

		return true;
	}

	public PhoneNumber insertCustomer(Customer customer, PhoneNumber phoneNumber, Connection con) throws Exception {
		long customernumber = 0;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		try {

			preparedStatement = con.prepareStatement(
					"INSERT INTO musteribilgileri( musteriisim,musterisoyisim  )VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, customer.getName());

			preparedStatement.setString(2, customer.getLastname());
			preparedStatement.executeUpdate();
			logger.info("Veritabanýna Musteri Ekleme Giriþi Yapýldi");
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				customernumber = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(" insertCustomer incomplete");
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();

		}

		phoneNumber.setCustomerNo(customernumber);
		System.out.println("" + customernumber);
		System.out.println("1. islev Basarili Bir Sekilde Tamamlandi");
		return phoneNumber;

	}

	public void insertPhone(PhoneNumber phoneNumber, Connection con) throws Exception {

		PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement("INSERT INTO telefonnumaralari(musterino,musteritel)VALUES (?,?) ");
			for (int i = 0; i < phoneNumber.getCustomerPhoneNumber().size(); i++) {

				stmt.setInt(2, phoneNumber.getCustomerPhoneNumber().get(i));
				stmt.setLong(1, phoneNumber.getCustomerNo());
				stmt.executeUpdate();
				logger.info("Veritabanýna Telefon Ekleme Giriþi Yapýldi");
			}

			System.out.println("2. islev Basarili Bir Sekilde Tamamlandi");

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("insertPhone incomplete");
			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public List<Customer> getCustomer() throws Exception {
	 
		Connection conn = getConnection();
		Session sessi = null;
		List<Customer> products = new ArrayList<Customer>();

		try {

			PreparedStatement statement = conn.prepareStatement("SELECT * from musteribilgileri");
			ResultSet resultSet = statement.executeQuery();

			{
				while (resultSet.next()) {

					Customer customer = new Customer();
					int id = Integer.valueOf(resultSet.getString("musterino"));
					customer.setName(resultSet.getString("musteriisim"));
				 
					System.out.println(id);

					products.add(customer);
				}
			}
		 
			//deneme.asd(sessi, "Liste Basarýyla Göndelirdi");
			logger.error("Liste Basarýyla Göndelirdi");
		} catch (Exception e) {
			logger.debug("Liste Gönderiminde Sýkýntý Var");
		//	deneme.asd(sessi, "Liste Gönderiminde Sýkýntý Var");
		}
		return products;
	}

	public List<PhoneNumber> phone() throws Exception {

		Connection conn = getConnection();

		List<PhoneNumber> products = new ArrayList<PhoneNumber>();

		try {
			ArrayList<Integer> CustomerPhoneNumber = new ArrayList<Integer>();
			PreparedStatement statement = conn.prepareStatement("SELECT * from telefonnumaralari");
			ResultSet resultSet = statement.executeQuery();

			{
				while (resultSet.next()) {

					PhoneNumber customer = new PhoneNumber();
					int id = Integer.valueOf(resultSet.getString("musteritel"));
					CustomerPhoneNumber.add(id);
					customer.setCustomerPhoneNumber(CustomerPhoneNumber);
					System.out.println(id);

					products.add(customer);
				}
			}

			logger.error("Liste Basarýyla Göndelirdi");
		} catch (Exception e) {
			logger.debug("Liste Gönderiminde Sýkýntý Var");
		}
		return products;
	}

}
