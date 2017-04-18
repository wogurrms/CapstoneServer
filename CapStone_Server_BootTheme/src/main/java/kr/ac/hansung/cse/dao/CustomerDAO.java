package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Customer;

@Repository
public class CustomerDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<Customer> getCustomers() {
		String sqlStatement = "select * from customer";
		return jdbcTemplateObject.query(sqlStatement, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Customer customer = new Customer();
			
				customer.setId(rs.getInt("id"));
				customer.setDate(rs.getString("date"));
				customer.setCount(rs.getInt("count"));

				return customer;
			}

		});
	}

	public boolean addCustomer(Customer customer) {

		String date = customer.getDate();
		int count = customer.getCount();
		
		String sqlStatement = "insert into customer (date, count) values (?,?)";

		// update 함수를 수행하면 성공한 record 의 갯수가 넘어오므로 == 1 인지 비교한다.
		return (jdbcTemplateObject.update(sqlStatement,
				new Object[] { date, count }) == 1);
	}

	public boolean deleteCustomer(int id) {

		String sqlStatement = "delete from customer where id =?";
		return (jdbcTemplateObject.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Customer getCustomerById(int id) {
		String sqlStatement = "select * from customer where id=?";
		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { id }, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Customer customer = new Customer();

				customer.setId(rs.getInt("id"));
				customer.setDate(rs.getString("date"));
				customer.setCount(rs.getInt("count"));

				return customer;
			}

		});
	}


	public int getTodayAmount() {


		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( cal.YEAR );
		int month = cal.get ( cal.MONTH ) + 1 ;
		int date = cal.get ( cal.DATE ) ;
		String today = year+"-"+month+"-"+date;

		String sqlStatement = "select count(*) from customer where time like '?%'";
		
		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { today }, Integer.class);
	}
	

	public boolean editCustomer(Customer customer) {
		
		int id = customer.getId();
		String date = customer.getDate();
		int count = customer.getCount();

		String sqlStatement = "update customer set date=?, count=? where id =?";

		// update 함수를 수행하면 성공한 record 의 갯수가 넘어오므로 == 1 인지 비교한다.
		return (jdbcTemplateObject.update(sqlStatement,
				new Object[] { date, count, id }) == 1);
	}

}
