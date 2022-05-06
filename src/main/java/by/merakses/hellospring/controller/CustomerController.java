package by.merakses.hellospring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.merakses.hellospring.entity.Customer;
import by.merakses.hellospring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private static final String ID_PATH_VARIABLE_NAME = "id";
    private static final String CUSTOMER_LIST_ATTRIBUTE_NAME = "customers";
    private static final String CUSTOMER_ATTRIBUTE_NAME = "customer";

    private static final String CUSTOMER_LIST_PAGE_NAME = "customerList";
    private static final String NEW_CUSTOMER_PAGE_NAME = "newCustomer";
    private static final String CUSTOMER_PAGE_NAME = "customer";
    private static final String EDIT_CUSTOMER_PAGE_NAME = "editCustomer";
    private static final String CUSTOMER_LIST_REDIRECT = "redirect:/customer";
    private static final String CUSTOMER_REDIRECT = "redirect:/customer/%d";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomerList(Model model) {
        List<Customer> customerList = customerService.getAll();
        model.addAttribute(CUSTOMER_LIST_ATTRIBUTE_NAME, customerList);
        return CUSTOMER_LIST_PAGE_NAME;
    }

    @GetMapping("/new")
    public String showNewCustomerForm(@ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) Customer customer) {
        return NEW_CUSTOMER_PAGE_NAME;
    }

    @PostMapping
    public String createCustomer(@ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) Customer customer) {
        customerService.create(customer);
        return CUSTOMER_LIST_REDIRECT;
    }

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id, Model model) {
        Customer customer = customerService.get(id);
        model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
        return CUSTOMER_PAGE_NAME;
    }

    @GetMapping("/{id}/edit")
    public String showEditCustomerForm(@PathVariable(ID_PATH_VARIABLE_NAME) long id, Model model) {
        Customer customer = customerService.get(id);
        model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
        return EDIT_CUSTOMER_PAGE_NAME;
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id,
        @ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) Customer customer) {
        customerService.update(id, customer);
        return String.format(CUSTOMER_REDIRECT, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id) {
        customerService.delete(id);
        return CUSTOMER_LIST_REDIRECT;
    }
}
