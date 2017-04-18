package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.CustomerDAO;
import kr.ac.hansung.cse.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDao;
	
	public List<Customer> getCustomers(){
		return customerDao.getCustomers();
	}
	
	public boolean addCustomer(Customer customer){
		return customerDao.addCustomer(customer);
	}
	
	public boolean deleteCustomerById(int id){
		return customerDao.deleteCustomer(id);
	}

	public Customer getCustomerById(int id) {
		return customerDao.getCustomerById(id);
	}

	public boolean editCustomer(Customer customer) {
		return customerDao.editCustomer(customer);
	}
	
	public int getTodayAmount(){
		return customerDao.getTodayAmount();
	}

}
