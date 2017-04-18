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
		// @Valid 에 넘어온 객체가 검증이 되고 결과가 BindingResult 에 자동으로 들어가게 된다.
		// View 로 Product 와 BindingResult 가 둘다 넘어가서 검증이 틀릴 경우 기존입력 값은 지워지지 않고 form 에 유지된다.
		// 사용자가 입력한 form data 가 Product 객체에 data binding 되서 들어온다.
		
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
		// 바로 productInventory 를 호출하면 db에서 가져오지 않으므로 다시 redirect 하여 db 에서 다 가져오도록 한다.
		return "redirect:/admin/customerInventory";
	}

	@RequestMapping("customerInventory/deleteCustomer/{id}")
	public String deleteProduct(@PathVariable int id) {

		
		// {id} 값이 @PathVariable로 맵핑 되게 된다.
		if (!customerService.deleteCustomerById(id)) {
			System.out.println("Deleting Customer Cannot be done");
		}
		return "redirect:/admin/customerInventory";
	}

	@RequestMapping(value = "customerInventory/editCustomer/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable int id, Model model) { 
		// {id} 값이 @PathVariable 로 맵핑 되게된다.

		Customer customer = customerService.getCustomerById(id);

		model.addAttribute("customer", customer);

		return "editCustomer";
	}

	@RequestMapping(value = "customerInventory/editCustomer", method = RequestMethod.POST)
	public String editCustomerPost(@Valid Customer customer, BindingResult result) { 
		// View 로 Product 와 BindingResult 가 둘다 넘어가서 검증이 틀릴 경우 기존입력 값은 지워지지 않고 form 에 유지된다.
		
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
