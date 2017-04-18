package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Customer;
import kr.ac.hansung.cse.service.CustomerService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/customerInventory")
	public String getProducts(Model model) {
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		return "customerInventory";
	}

	@RequestMapping(value = "/customerInventory/addCustomer", method = RequestMethod.GET)
	public String addCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "addCustomer";
	}

	@RequestMapping(value = "/customerInventory/addCustomer", method = RequestMethod.POST)
	public String addCustomerPost(@Valid Customer customer, BindingResult result) {
		// @Valid �� �Ѿ�� ��ü�� ������ �ǰ� ����� BindingResult �� �ڵ����� ���� �ȴ�.
		// View �� Product �� BindingResult �� �Ѵ� �Ѿ�� ������ Ʋ�� ��� �����Է� ���� �������� �ʰ� form �� �����ȴ�.
		// ����ڰ� �Է��� form data �� Product ��ü�� data binding �Ǽ� ���´�.
		
		if(result.hasErrors()){
			System.out.println("=========Form data has some Errors========");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				System.out.println(error.getDefaultMessage());
			}
			
			return "addCustomer";
		}
				
		
		if (!customerService.addCustomer(customer)) {
			System.out.println("Adding Customer Cannot be done");
		}
		// �ٷ� productInventory �� ȣ���ϸ� db���� �������� �����Ƿ� �ٽ� redirect �Ͽ� db ���� �� ���������� �Ѵ�.
		return "redirect:/admin/customerInventory";
	}

	@RequestMapping("customerInventory/deleteCustomer/{id}")
	public String deleteProduct(@PathVariable int id) {

		
		// {id} ���� @PathVariable�� ���� �ǰ� �ȴ�.
		if (!customerService.deleteCustomerById(id)) {
			System.out.println("Deleting Customer Cannot be done");
		}
		return "redirect:/admin/customerInventory";
	}

	@RequestMapping(value = "customerInventory/editCustomer/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable int id, Model model) { 
		// {id} ���� @PathVariable �� ���� �ǰԵȴ�.

		Customer customer = customerService.getCustomerById(id);

		model.addAttribute("customer", customer);

		return "editCustomer";
	}

	@RequestMapping(value = "customerInventory/editCustomer", method = RequestMethod.POST)
	public String editCustomerPost(@Valid Customer customer, BindingResult result) { 
		// View �� Product �� BindingResult �� �Ѵ� �Ѿ�� ������ Ʋ�� ��� �����Է� ���� �������� �ʰ� form �� �����ȴ�.
		
		if(result.hasErrors()){
			System.out.println("=========Form data has some Errors========");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				System.out.println(error.getDefaultMessage());
			}
			
			return "editCustomer";
		}
		
		
		if (!customerService.editCustomer(customer)) {
			System.out.println("Editing Customer Cannot be done");
		}

		return "redirect:/admin/customerInventory";
	}

}
