package org.isdb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.isdb.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert productInsert;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.productInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("productSB")
                .usingGeneratedKeyColumns("id");
    }

    public int save(org.isdb.model.Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("amount", product.getAmount());
        parameters.put("brand", product.getBrand());
        parameters.put("purchaseDate", product.getPurchaseDate());

        Number key = productInsert.executeAndReturnKey(parameters);
        return key.intValue();
    }

    public Optional<Product> findById(int id) {
        try {
            String sql = "SELECT * FROM productSB WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM productSB";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public int update(Product product) {
        String sql = "UPDATE productSB SET name = ?, amount = ?, brand = ?, purchaseDate = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getName(), product.getAmount(), product.getBrand(), product.getPurchaseDate(), product.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM productSB WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM productSB WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public List<Product> findByName(String name) {
        String sql = "SELECT * FROM productSB WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), "%" + name + "%");
    }

    // Move saveAndReturnPro outside ProductRowMapper
    public Product saveAndReturnPro(Product product) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO productSB (name, amount, brand, purchaseDate) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getAmount());
            preparedStatement.setString(3, product.getBrand());

            // Use java.sql.Date to set LocalDate correctly
            preparedStatement.setDate(4, java.sql.Date.valueOf(product.getPurchaseDate()));

            // Execute the insert
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            // Get the generated ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);

                    // Set the ID in the product object
                    return new Product(id, product.getName(), product.getAmount(), product.getBrand(), product.getPurchaseDate());
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            // Log the error for debugging
            e.printStackTrace(); // This is for quick debugging; replace with proper logging
            throw new RuntimeException("Error saving product: " + e.getMessage(), e);
        }
    }

    // RowMapper class
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("amount"),
                    rs.getString("brand"),
                    rs.getObject("purchaseDate", LocalDate.class)
            );
        }
    }
}
