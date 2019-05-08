package se.sina.webshop.model.conversion;

import org.springframework.stereotype.Component;
import se.sina.webshop.model.entity.*;
import se.sina.webshop.model.web.*;

import java.util.ArrayList;
import java.util.List;

@Component
public final class Converter {

    public EmployeeWeb convertFrom(Employee employee){
        return new EmployeeWeb(employee.getEmployeeNumber(), employee.getUsername(), employee.getPassword(), employee.getFirstname(), employee.getLastname());
    }

    public Employee convertFrom(EmployeeWeb employeeWeb){
        return new Employee(employeeWeb.getEmployeeNumber(), employeeWeb.getUsername(), employeeWeb.getPassword(), employeeWeb.getFirstname(), employeeWeb.getLastname());
    }

    public List<EmployeeWeb> convertEmployeeList(List<Employee> employees){
        List<EmployeeWeb> employeeWebs = new ArrayList<>();
        employees.forEach(employee -> employeeWebs.add(convertFrom(employee)));
        return employeeWebs;
    }

    public Category convertFrom(CategoryWeb categoryWeb){
        return new Category(categoryWeb.getCategoryNumber(), categoryWeb.getName());
    }

    public CategoryWeb convertFrom(Category category){
        return new CategoryWeb(category.getCategoryNumber(), category.getName());
    }

    public Brand convertFrom(BrandWeb brandWeb){
        return new Brand(brandWeb.getBrandNumber(), brandWeb.getName(), brandWeb.getCategory() == null ? null : convertFrom(brandWeb.getCategory()));
    }

    public BrandWeb convertFrom(Brand brand){
        return new BrandWeb(brand.getBrandNumber(), brand.getName(), convertFrom(brand.getCategory()));
    }

    public Model convertFrom(ModelWeb modelWeb){
        return new Model(modelWeb.getModelNumber(), modelWeb.getName(), modelWeb.getBrand() == null ? null : convertFrom(modelWeb.getBrand()), modelWeb.getModelStatus(), modelWeb.getPrice());
    }

    public ModelWeb convertFrom(Model model){
        return new ModelWeb(model.getModelNumber(), model.getName(), convertFrom(model.getBrand()), model.getModelStatus(), model.getPrice());
    }

    public Item convertFrom(ItemWeb itemWeb){
        return new Item(itemWeb.getItemNumber(), itemWeb.getItemStatus(), itemWeb.getModel() == null ? null : convertFrom(itemWeb.getModel()));
    }

    public ItemWeb convertFrom(Item item){
        return new ItemWeb(item.getItemNumber(), item.getItemStatus(), convertFrom(item.getModel()));
    }

    public Customer convertFrom(CustomerWeb customerWeb){
        return new Customer(customerWeb.getCustomerNumber(), customerWeb.getFirstname(), customerWeb.getLastname(), customerWeb.getSocialSecurityNumber(), customerWeb.getAddress(), customerWeb.getEmail());
    }

    public CustomerWeb convertFrom(Customer customer){
        return new CustomerWeb(customer.getCustomerNumber(), customer.getFirstname(), customer.getLastname(), customer.getSocialSecurityNumber(), customer.getEmail());
    }

    public Order convertFrom(OrderWeb orderWeb){
        return new Order(orderWeb.getOrderNumber(), orderWeb.getOrderDate(), convertFrom(orderWeb.getCustomer()));
    }

    public OrderWeb convertFrom(Order order){
        return new OrderWeb(order.getOrderNumber(), order.getOrderDate(), convertFrom(order.getCustomer()));
    }

    public OrderItem convertFrom(OrderItemWeb orderItemWeb){
        return new OrderItem(orderItemWeb.getOrderItemNumber(), convertFrom(orderItemWeb.getItem()), convertFrom(orderItemWeb.getOrder()));
    }

    public OrderItemWeb convertFrom(OrderItem orderItem){
        return new OrderItemWeb(orderItem.getOrderItemNumber(), convertFrom(orderItem.getItem()), convertFrom(orderItem.getOrder()));
    }

}
