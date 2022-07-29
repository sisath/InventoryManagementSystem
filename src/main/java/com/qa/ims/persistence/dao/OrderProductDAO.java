package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Order;
import com.qa.ims.persistence.model.OrderProduct;
import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO implements Dao<OrderProduct> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public OrderProduct modelFromResultSet(ResultSet resultSet) throws SQLException {
        long orderProductId = resultSet.getLong("order_product_id");
        long productId = resultSet.getLong("fk_product_id");
        long orderId = resultSet.getLong("fk_order_id");
        long quantity = resultSet.getLong("quantity");

        Product product = new ProductDAO().read(productId);
        Order order = new OrderDAO().read(orderId);

        return new OrderProduct(orderProductId, product, order, quantity);
    }

    @Override
    public List<OrderProduct> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT op.order_product_id, op.fk_order_id, " +
                     "fk_product_id, op.quantity, p.price, p.product_name, p.description, " +
                     "c.customer_id, c.first_name, c.surname, c.email, c.postcode " +
                     "FROM order_product op " +
                     "LEFT JOIN orders o " +
                     "ON op.fk_order_id = o.order_id " +
                     "LEFT JOIN product p " +
                     "ON op.fk_product_id = p.product_id " +
                     "LEFT JOIN customer c " +
                     "ON o.fk_customer_id = c.customer_id");){
            List<OrderProduct> orderProduct = new ArrayList<>();
            while (resultSet.next()) {
                orderProduct.add(modelFromResultSet(resultSet));
            }
            return orderProduct;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public OrderProduct readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order_product " +
                     "ORDER BY order_product_id " +
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
    public OrderProduct create(OrderProduct orderProduct) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO order_product(fk_product_id, fk_order_id, quantity) " +
                             "VALUES (?, ?, ?)");) {
            statement.setLong(1, orderProduct.getProduct().getProductId());
            statement.setLong(2, orderProduct.getOrder().getOrderId());
            statement.setLong(3, orderProduct.getQuantity());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public OrderProduct read(Long orderProductId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_product " +
                     "WHERE order_product_id= ?");) {
            statement.setLong(1, orderProductId);
            try (ResultSet resultSet = statement.executeQuery()) {
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
    public OrderProduct update(OrderProduct orderProduct) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE order_product SET fk_product_id= ?, fk_order_id=?, quantity=? " +
                                "WHERE order_product_id = ?");) {
			statement.setLong(1, orderProduct.getProduct().getProductId());
			statement.setLong(2, orderProduct.getOrder().getOrderId());
			statement.setLong(3, orderProduct.getQuantity());
			statement.executeUpdate();
			return read(orderProduct.getOrderProductId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
        return null;
    }

    public OrderProduct addProduct(long orderId, long productId, long quantity) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO order_product(fk_product_id, fk_order_id, quantity) " +
                             "VALUES (?, ?, ?)");) {
            statement.setLong(1, productId);
            statement.setLong(2, orderId);
            statement.setLong(3, quantity);
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long orderProductId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM order_product " +
                     "WHERE order_product_id = ?");) {
            statement.setLong(1, orderProductId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int deleteByOrderIdAndProductId(long orderId, long productId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM order_product " +
                     "WHERE fk_order_id = ? AND fk_product_id = ?");) {
            statement.setLong(1, orderId);
            statement.setLong(2, productId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}
