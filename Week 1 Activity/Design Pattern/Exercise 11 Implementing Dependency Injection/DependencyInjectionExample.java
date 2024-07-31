public interface CustomerRepository {
    Customer findCustomerById(int id);
}
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        // Simulate finding a customer by ID
        return new Customer(id, "John Doe", "john.doe@example.com");
    }
}
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }
}
// No additional implementation needed, as we're using constructor injection
public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);

        Customer customer = customerService.findCustomerById(1);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Email: " + customer.getEmail());
    }
}