package kr.ac.hansung.cse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.cse.model.Customer;
import kr.ac.hansung.cse.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/table2")
	public String getCustomers(Model model){
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers",customers);
		return "home2";
	}
	

	@RequestMapping("/todayamount")
	public String getTodayAmount(Model model){
		int todayamount = customerService.getTodayAmount();
		model.addAttribute("todayamount",todayamount);
		return "home2";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/table3", method = RequestMethod.GET)
	@ResponseBody
	public void json(HttpServletResponse res) throws IOException {
		System.out.println("Request!!!!!!!!");
		JSONObject resultObj = new JSONObject();
		List<Customer> customers = customerService.getCustomers();
		resultObj.put("customers", customers);	
		
		res.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = res.getWriter();
		out.print(resultObj.toString());
	}
	
    
   @RequestMapping(value="/table")
   public ResponseEntity<List> listAllPhones() {
       System.out.println("*************************************ListAllPhones");
		List<Customer> customers = customerService.getCustomers();
       if(customers.isEmpty()){
           return new ResponseEntity<List>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
       }
       return new ResponseEntity<List>(customers, HttpStatus.OK);
   }
	
	
}
