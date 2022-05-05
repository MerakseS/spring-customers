package by.merakses.hellospring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public static final String CUSTOMER_LIST_PAGE_NAME = "customerList";
    public static final String NEW_CUSTOMER_PAGE_NAME = "newCustomer";
    public static final String EDIT_CUSTOMER_PAGE_NAME = "editCustomer";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomerList(Model model) {
        List<Customer> customerList = customerService.getAll();
        model.addAttribute("customers", customerList);
        return CUSTOMER_LIST_PAGE_NAME;
    }

    @GetMapping("/new")
    public String showNewCustomerForm(@ModelAttribute("customer") Customer customer) {
        return NEW_CUSTOMER_PAGE_NAME;
    }

    @PostMapping
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.create(customer);
        return "redirect:/customer";
    }
}
