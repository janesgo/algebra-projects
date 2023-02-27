package hr.project.cikloteka.dao.sql;

import hr.project.cikloteka.dao.Repository;
import hr.project.cikloteka.model.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HibernateRepository implements Repository {
    @Override
    public int addLogin(Login data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Login login = new Login(data);
            em.persist(login);
            em.getTransaction().commit();
            return login.getId();
        }
    }

    @Override
    public List<Login> getAllLogins() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Login.class)).getResultList();
        }
    }

    @Override
    public List<Payment> getAllPayments() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Payment.class)).getResultList();
        }
    }

    @Override
    public Optional<Payment> getPayment(int id) throws Exception {
        Payment payment;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            payment = em.find(Payment.class, id);
        }
        return Optional.ofNullable(payment);
    }

    @Override
    public int addBike(Bike data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Bike bike = new Bike(data);
            em.persist(bike);
            em.getTransaction().commit();
            return bike.getId();
        }
    }

    @Override
    public void updateBike(Bike bike) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(Bike.class, bike.getId()).copyData(bike);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteBike(Bike bike) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(bike) ? bike : em.merge(bike));
            em.getTransaction().commit();
        }
    }

    @Override
    public Optional<Bike> getBike(int id) throws Exception {
        Bike bike;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            bike = em.find(Bike.class, id);
        }
        return Optional.ofNullable(bike);
    }

    @Override
    public List<Bike> getAllBikes() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Bike.class)).getResultList();
        }
    }

    @Override
    public List<Bike> getAllBikes(int first, int records) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            final Query query = em.createNamedQuery(HibernateFactory.getSelectAll(Bike.class));
            query.setFirstResult(first);
            query.setMaxResults(records);
            return query.getResultList();
        }
    }

    @Override
    public List<Bike> getAllBikes(int categoryId, int first, int records) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            final Query query = em.createNamedQuery("Bike.findAllByCategory", Bike.class);
            query.setParameter("categoryId", categoryId);
            query.setFirstResult(first);
            query.setMaxResults(records);
            return query.getResultList();
        }
    }

    @Override
    public int getBikesCount() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return ((Long) em.createNamedQuery("Bike.count").getSingleResult()).intValue();
        }
    }

    @Override
    public int getBikesCount(int categoryId) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            final Query query = em.createNamedQuery("Bike.countByCategory");
            query.setParameter("categoryId", categoryId);
            return ((Long) query.getSingleResult()).intValue();
        }
    }

    @Override
    public Optional<Brand> getBrand(int id) throws Exception {
        Brand brand;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            Map hints = new HashMap();
            hints.put("javax.persistence.fetchgraph", em.getEntityGraph("graph.Brand.bikes"));
            brand = em.find(Brand.class, id, hints);
        }
        return Optional.ofNullable(brand);
    }

    @Override
    public List<Brand> getAllBrands() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Brand.class)).getResultList();
        }
    }

    @Override
    public int addCategory(Category data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Category category = new Category(data);
            em.persist(category);
            em.getTransaction().commit();
            return category.getId();
        }
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(Category.class, category.getId()).copyData(category);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(category) ? category : em.merge(category));
            em.getTransaction().commit();
        }
    }

    @Override
    public Optional<Category> getCategory(int id) throws Exception {
        Category category;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            category = em.find(Category.class, id);
        }
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> getAllCategories() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Category.class)).getResultList();
        }
    }

    @Override
    public int getCategoriesCount() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return ((Long) em.createNamedQuery("Category.count").getSingleResult()).intValue();
        }
    }

    @Override
    public Optional<City> getCity(int id) throws Exception {
        City city;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            city = em.find(City.class, id);
        }
        return Optional.ofNullable(city);
    }

    @Override
    public List<City> getAllCities() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(City.class)).getResultList();
        }
    }

    @Override
    public Optional<Country> getCountry(int id) throws Exception {
        Country country;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            country = em.find(Country.class, id);
        }
        return Optional.ofNullable(country);
    }

    @Override
    public List<Country> getAllCountries() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Country.class)).getResultList();
        }
    }

    @Override
    public int addCustomer(Customer data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Customer customer = new Customer(data);
            em.persist(customer);
            em.getTransaction().commit();
            return customer.getId();
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(Customer.class, customer.getId()).copyData(customer);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(customer) ? customer : em.merge(customer));
            em.getTransaction().commit();
        }
    }

    @Override
    public Optional<Customer> getCustomer(int id) throws Exception {
        Customer customer;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            customer = em.find(Customer.class, id);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> getCustomer(String email, String password) throws Exception {
        Customer customer;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            final Query query = em.createNamedQuery("Customer.findByEmailAndPassword");
            query.setParameter("email", email);
            query.setParameter("password", password);
            customer = (Customer) query.getSingleResult();
            return Optional.ofNullable(customer);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(Customer.class)).getResultList();
        }
    }

    @Override
    public int addOrderItem(OrderItem data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            OrderItem orderItem = new OrderItem(data);
            em.persist(orderItem);
            em.getTransaction().commit();
            return orderItem.getId();
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(OrderItem.class, orderItem.getId()).copyData(orderItem);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(orderItem) ? orderItem : em.merge(orderItem));
            em.getTransaction().commit();
        }
    }

    @Override
    public Optional<OrderItem> getOrderItem(int id) throws Exception {
        OrderItem orderItem;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            orderItem = em.find(OrderItem.class, id);
        }
        return Optional.ofNullable(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(OrderItem.class)).getResultList();
        }
    }

    @Override
    public int addOrderDetails(OrderDetails data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            OrderDetails orderDetails = new OrderDetails(data);
            em.persist(orderDetails);
            em.getTransaction().commit();
            return orderDetails.getId();
        }
    }

    @Override
    public void updateOrderDetails(OrderDetails orderDetails) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.find(OrderDetails.class, orderDetails.getId()).copyData(orderDetails);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteOrderDetails(OrderDetails orderDetails) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(orderDetails) ? orderDetails : em.merge(orderDetails));
            em.getTransaction().commit();
        }
    }

    @Override
    public Optional<OrderDetails> getOrderDetails(int id) throws Exception {
        OrderDetails orderDetails;
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            orderDetails = em.find(OrderDetails.class, id);
        }
        return Optional.ofNullable(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.getSelectAll(OrderDetails.class)).getResultList();
        }
    }

    @Override
    public List<OrderDetails> getAllOrderDetails(int customerId) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            final Query query = em.createNamedQuery("OrderDetails.findAllByCustomer", OrderDetails.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();
        }
    }

    @Override
    public void release() throws Exception {
        Repository.super.release();
    }
}
