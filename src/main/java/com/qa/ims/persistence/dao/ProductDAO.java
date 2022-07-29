package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.model.Product;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Dao<Product> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Product modelFromResultSet(ResultSet resultSet) throws SQLException {
        long productId = resultSet.getLong("product_id");
        String productDescription = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        String productName = resultSet.getString("product_name");
        return new Product(productId, price, productName, productDescription);
    }

    @Override
    public List<Product> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product");) {
            List<Product> product = new ArrayList<>();
            while (resultSet.next()) {
                product.add(modelFromResultSet(resultSet));
            }
            return product;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Product readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY product_id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO product(price, product_name, description) VALUES (?, ?, ?)");) {
            statement.setDouble(1, product.getPrice());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product read(Long productId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_id = ?");) {
            statement.setLong(1, productId);
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
    public Product update(Product product) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE product SET price = ?, product_name= ?, description= ? WHERE product_id = ?");) {
            statement.setDouble(1, product.getPrice());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.setLong(4, product.getProductId());
            statement.executeUpdate();
            return read(product.getProductId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    @Override
    public int delete(long productId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE product_id= ?");) {
            statement.setLong(1, productId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}

