package by.merakses.hellospring.controller;

import java.util.List;
import java.util.NoSuchElementException;
import static java.lang.String.format;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    private static final String ID_PATH_VARIABLE_NAME = "id";

    private static final String CUSTOMER_LIST_ATTRIBUTE_NAME = "customers";
    private static final String CUSTOMER_ATTRIBUTE_NAME = "customer";
    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private static final String CUSTOMER_LIST_PAGE_NAME = "customer/customerList";
    private static final String NEW_CUSTOMER_PAGE_NAME = "customer/newCustomer";
    private static final String CUSTOMER_PAGE_NAME = "customer/customer";
    private static final String EDIT_CUSTOMER_PAGE_NAME = "customer/editCustomer";
    private static final String NOT_FOUND_PAGE_NAME = "pageNotFound";

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
    public String createCustomer(@ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) @Valid Customer customer,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            LOG.warn(format("Can't create customer cause: %s", bindingResult));
            return NEW_CUSTOMER_PAGE_NAME;
        }

        customerService.create(customer);
        return CUSTOMER_LIST_REDIRECT;
    }

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id, Model model) {
        try {
            Customer customer = customerService.get(id);
            model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
            return CUSTOMER_PAGE_NAME;
        }
        catch (NoSuchElementException e) {
            return handleNoSuchElementException("Can't show customer with id %d", id, e, model);
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditCustomerForm(@PathVariable(ID_PATH_VARIABLE_NAME) long id, Model model) {
        try {
            Customer customer = customerService.get(id);
            model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
            return EDIT_CUSTOMER_PAGE_NAME;
        }
        catch (NoSuchElementException e) {
            return handleNoSuchElementException("Can't show edit form of customer with id %d", id, e, model);
        }
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id,
        @ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) @Valid Customer customer,
        BindingResult bindingResult,
        Model model) {

        if (bindingResult.hasErrors()) {
            LOG.warn(format("Can't update customer cause: %s", bindingResult));
            return EDIT_CUSTOMER_PAGE_NAME;
        }

        try {
            customerService.update(id, customer);
            return format(CUSTOMER_REDIRECT, id);
        }
        catch (NoSuchElementException e) {
            return handleNoSuchElementException("Can't update customer with id %d", id, e, model);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable(ID_PATH_VARIABLE_NAME) long id, Model model) {
        try {
            customerService.delete(id);
            return CUSTOMER_LIST_REDIRECT;
        }
        catch (NoSuchElementException e) {
            return handleNoSuchElementException("Can't delete customer with id %d", id, e, model);
        }
    }

    private String handleNoSuchElementException(String logMessage, long id, NoSuchElementException e, Model model) {
        LOG.warn(format(logMessage, id), e);
        model.addAttribute(ERROR_ATTRIBUTE_NAME, format("Клиента с id %d не существует :(", id));
        return NOT_FOUND_PAGE_NAME;
    }

}
