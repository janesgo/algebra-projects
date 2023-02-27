package hr.project.cikloteka.dao;

import hr.project.cikloteka.model.*;

import java.util.List;
import java.util.Optional;

public interface Repository {
    int addLogin(Login login) throws Exception;

    List<Login> getAllLogins() throws Exception;

    List<Payment> getAllPayments() throws Exception;

    Optional<Payment> getPayment(int id) throws Exception;

    int addBike(Bike bike) throws Exception;

    void updateBike(Bike bike) throws Exception;

    void deleteBike(Bike bike) throws Exception;

    Optional<Bike> getBike(int id) throws Exception;

    List<Bike> getAllBikes() throws Exception;

    List<Bike> getAllBikes(int first, int records) throws Exception;

    List<Bike> getAllBikes(int categoryId, int first, int records) throws Exception;

    int getBikesCount() throws Exception;

    int getBikesCount(int categoryId) throws Exception;

    Optional<Brand> getBrand(int id) throws Exception;

    List<Brand> getAllBrands() throws Exception;

    int addCategory(Category category) throws Exception;

    void updateCategory(Category category) throws Exception;

    void deleteCategory(Category category) throws Exception;

    Optional<Category> getCategory(int id) throws Exception;

    List<Category> getAllCategories() throws Exception;

    int getCategoriesCount() throws Exception;

    Optional<City> getCity(int id) throws Exception;

    List<City> getAllCities() throws Exception;

    Optional<Country> getCountry(int id) throws Exception;

    List<Country> getAllCountries() throws Exception;

    int addCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;

    Optional<Customer> getCustomer(int id) throws Exception;

    Optional<Customer> getCustomer(String email, String password) throws Exception;

    List<Customer> getAllCustomers() throws Exception;

    int addOrderDetails(OrderDetails orderDetails) throws Exception;

    void updateOrderDetails(OrderDetails orderDetails) throws Exception;

    void deleteOrderDetails(OrderDetails orderDetails) throws Exception;

    Optional<OrderDetails> getOrderDetails(int id) throws Exception;

    List<OrderDetails> getAllOrderDetails() throws Exception;

    List<OrderDetails> getAllOrderDetails(int customerId) throws Exception;

    int addOrderItem(OrderItem orderItem) throws Exception;

    void updateOrderItem(OrderItem orderItem) throws Exception;

    void deleteOrderItem(OrderItem orderItem) throws Exception;

    Optional<OrderItem> getOrderItem(int id) throws Exception;

    List<OrderItem> getAllOrderItems() throws Exception;

    default void release() throws Exception {
    }
}
