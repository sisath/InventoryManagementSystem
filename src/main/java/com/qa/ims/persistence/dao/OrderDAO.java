package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Customer;
import com.qa.ims.persistence.model.Order;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Dao<Order> {
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("order_id");
        long customerId = resultSet.getLong("fk_customer_id");
        String firstName = resultSet.getString("first_name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String postCode = resultSet.getString("postcode");
        Customer customer = new Customer(customerId, firstName, surname, email, postCode);

        long cost = resultSet.getLong("cost");
        return new Order(orderId, customer, cost);
    }

    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT o.order_id,op.fk_product_id, fk_customer_id, " +
                     "sum(op.quantity*p.price) " +
                     "AS cost, c.first_name, c.surname, c.email, c.postcode " +
                     "FROM orders o " +
                     "LEFT JOIN customer c " +
                     "ON c.customer_id = o.fk_customer_id " +
                     "LEFT JOIN order_product op " +
                     "ON op.fk_order_id = o.order_id " +
                     "LEFT JOIN product p " +
                     "ON op.fk_product_id=p.product_id " +
                     "GROUP BY o.order_id");) {
            List<Order> order = new ArrayList<>();
            while (resultSet.next()) {
                order.add(modelFromResultSet(resultSet));
            }
            return order;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT o.order_id, fk_customer_id, " +
                     "sum(quantity*price) " +
                     "AS cost, c.first_name, c.surname, c.email, c.postcode " +
                     "FROM orders o " +
                     "JOIN customer c " +
                     "ON c.customer_id = o.fk_customer_id " +
                     "JOIN order_product op ON op.fk_order_id = o.order_id " +
                     "JOIN product p " +
                     "ON op.fk_product_id=p.product_id " +
                     "GROUP BY o.order_id " +
                     "ORDER BY o.order_id " +
                     "DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(fk_customer_id) VALUES (?)");) {
            statement.setLong(1, order.getCustomer().getCustomerId());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order read(Long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT o.order_id, fk_customer_id, " +
                     "sum(quantity*price) " +
                     "AS cost, c.first_name, c.surname, c.email, c.postcode " +
                     "FROM orders o " +
                     "JOIN customer c " +
                     "ON c.customer_id = o.fk_customer_id " +
                     "JOIN order_product op ON op.fk_order_id = o.order_id " +
                     "JOIN product p " +
                     "ON op.fk_product_id=p.product_id " +
                     "WHERE order_id = ? " +
                     "GROUP BY o.order_id " +
                     "ORDER BY o.order_id");) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE orders SET fk_customer_id = ? WHERE order_id = ?");) {
            statement.setLong(1, order.getCustomer().getCustomerId());
            statement.setLong(2, order.getOrderId());
            statement.executeUpdate();
            return read(order.getOrderId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id= ?");) {
            statement.setLong(1, orderId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}