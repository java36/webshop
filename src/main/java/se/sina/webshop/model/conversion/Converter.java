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
        return new CustomerWeb(customer.getCustomerNumber(), customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getEmail());
    }

    public Order convertFrom(OrderWeb orderWeb){
        return new Order(orderWeb.getOrderNumber(), orderWeb.getOrderDate(), orderWeb.getCustomer() == null ? null : convertFrom(orderWeb.getCustomer()), orderWeb.getTotal());
    }

    public OrderWeb convertFrom(Order order){
        return new OrderWeb(order.getOrderNumber(), order.getOrderDate(), convertFrom(order.getCustomer()), order.getTotal());
    }

    public OrderItem convertFrom(OrderItemWeb orderItemWeb){
        return new OrderItem(orderItemWeb.getOrderItemNumber(), convertFrom(orderItemWeb.getItem()), convertFrom(orderItemWeb.getOrder()), orderItemWeb.isShipped(), orderItemWeb.getShippingDate());
    }

    public OrderItemWeb convertFrom(OrderItem orderItem){
        return new OrderItemWeb(orderItem.getOrderItemNumber(), convertFrom(orderItem.getItem()), convertFrom(orderItem.getOrder()), orderItem.isShipped(), orderItem.getShippingDate());
    }

    public List<EmployeeWeb> convertEmployeeList(List<Employee> employees){
        List<EmployeeWeb> employeeWebs = new ArrayList<>();
        employees.forEach(employee -> employeeWebs.add(convertFrom(employee)));
        return employeeWebs;
    }
    public List<CategoryWeb> convertCategoryList(List<Category> categories){
        List<CategoryWeb> categoryWebs = new ArrayList<>();
        categories.forEach(category -> categoryWebs.add(convertFrom(category)));
        return categoryWebs;
    }
    public List<BrandWeb> convertBrandList(List<Brand> brands){
        List<BrandWeb> brandWebs = new ArrayList<>();
        brands.forEach(brand -> brandWebs.add(convertFrom(brand)));
        return brandWebs;
    }
    public List<ModelWeb> convertModelList(List<Model> models){
        List<ModelWeb> modelWebs = new ArrayList<>();
        models.forEach(model -> modelWebs.add(convertFrom(model)));
        return modelWebs;
    }
    public List<ItemWeb> convertItemList(List<Item> items){
        List<ItemWeb> itemWebs = new ArrayList<>();
        items.forEach(item -> itemWebs.add(convertFrom(item)));
        return itemWebs;
    }
    public List<CustomerWeb> convertCustomerList(List<Customer> customers){
        List<CustomerWeb> customerWebs = new ArrayList<>();
        customers.forEach(customer -> customerWebs.add(convertFrom(customer)));
        return customerWebs;
    }
    public List<OrderWeb> convertOrderList(List<Order> orders){
        List<OrderWeb> orderWebs = new ArrayList<>();
        orders.forEach(order -> orderWebs.add(convertFrom(order)));
        return orderWebs;
    }
    public List<OrderItemWeb> convertOrderItemList(List<OrderItem> orderItems){
        List<OrderItemWeb> orderItemWebs = new ArrayList<>();
        orderItems.forEach(orderItem -> orderItemWebs.add(convertFrom(orderItem)));
        return orderItemWebs;
    }

}
