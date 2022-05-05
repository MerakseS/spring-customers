package by.merakses.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public static final String CUSTOMER_LIST_PAGE_NAME = "customerList";
    public static final String CUSTOMER_ADD_PAGE_NAME = "customerAdd";
    public static final String CUSTOMER_EDIT_PAGE_NAME = "customerEdit";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomerList(Model model) {
        List<Customer> customerList = customerService.getAll();
        model.addAttribute("customers", customerList);
        return CUSTOMER_LIST_PAGE_NAME;
    }
}
