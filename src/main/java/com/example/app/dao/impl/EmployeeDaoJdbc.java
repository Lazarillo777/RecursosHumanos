package com.example.app.dao.impl;

import com.example.app.dao.EmployeeDao;
import com.example.app.model.Employee;
import com.example.app.util.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJdbc implements EmployeeDao {

    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employee(document_type_id, document_number, first_name, last_name, gender_id, marital_status_id, birth_date, address, city, email, hire_date, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, employee.getDocumentTypeId());
            ps.setString(2, employee.getDocumentNumber());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getLastName());
            ps.setObject(5, employee.getGenderId());
            ps.setObject(6, employee.getMaritalStatusId());
            if (employee.getBirthDate() != null)
                ps.setDate(7, java.sql.Date.valueOf(employee.getBirthDate()));
            else
                ps.setNull(7, java.sql.Types.DATE);
            ps.setString(8, employee.getAddress());
            ps.setString(9, employee.getCity());
            ps.setString(10, employee.getEmail());
            if (employee.getHireDate() != null)
                ps.setDate(11, java.sql.Date.valueOf(employee.getHireDate()));
            else
                ps.setNull(11, java.sql.Types.DATE);
            ps.setString(12, employee.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getLong(1));
                }
            }
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee update(Employee employee) {
        String sql = "UPDATE employee SET document_type_id=?, document_number=?, first_name=?, last_name=?, gender_id=?, marital_status_id=?, birth_date=?, address=?, city=?, email=?, hire_date=?, status=? WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, employee.getDocumentTypeId());
            ps.setString(2, employee.getDocumentNumber());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getLastName());
            ps.setObject(5, employee.getGenderId());
            ps.setObject(6, employee.getMaritalStatusId());
            if (employee.getBirthDate() != null)
                ps.setDate(7, java.sql.Date.valueOf(employee.getBirthDate()));
            else
                ps.setNull(7, java.sql.Types.DATE);
            ps.setString(8, employee.getAddress());
            ps.setString(9, employee.getCity());
            ps.setString(10, employee.getEmail());
            if (employee.getHireDate() != null)
                ps.setDate(11, java.sql.Date.valueOf(employee.getHireDate()));
            else
                ps.setNull(11, java.sql.Types.DATE);
            ps.setString(12, employee.getStatus());
            ps.setLong(13, employee.getId());
            ps.executeUpdate();
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(long id) {
        String sql = "SELECT id, document_type_id, document_number, first_name, last_name, gender_id, marital_status_id, birth_date, address, city, email, hire_date, status, created_at, updated_at FROM employee WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return toEmployee(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT id, document_type_id, document_number, first_name, last_name, gender_id, marital_status_id, birth_date, address, city, email, hire_date, status, created_at, updated_at FROM employee ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                list.add(toEmployee(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Employee toEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getLong("id"));
        e.setDocumentTypeId(getIntOrNull(rs, "document_type_id"));
        e.setDocumentNumber(rs.getString("document_number"));
        e.setFirstName(rs.getString("first_name"));
        e.setLastName(rs.getString("last_name"));
        e.setGenderId(getIntOrNull(rs, "gender_id"));
        e.setMaritalStatusId(getIntOrNull(rs, "marital_status_id"));
        String bdStr = rs.getString("birth_date");
        LocalDate bd = parseToLocalDate(bdStr);
        if (bd != null)
            e.setBirthDate(bd);
        e.setAddress(rs.getString("address"));
        e.setCity(rs.getString("city"));
        e.setEmail(rs.getString("email"));
        String hdStr = rs.getString("hire_date");
        LocalDate hd = parseToLocalDate(hdStr);
        if (hd != null)
            e.setHireDate(hd);
        e.setStatus(rs.getString("status"));
        Timestamp cat = rs.getTimestamp("created_at");
        if (cat != null)
            e.setCreatedAt(cat.toLocalDateTime());
        Timestamp upt = rs.getTimestamp("updated_at");
        if (upt != null)
            e.setUpdatedAt(upt.toLocalDateTime());
        return e;
    }

    private Integer getIntOrNull(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }

    private LocalDate parseToLocalDate(String s) {
        if (s == null)
            return null;
        s = s.trim();
        if (s.isEmpty())
            return null;
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException ex) {
            // Try parse as LocalDateTime then convert
            try {
                return LocalDateTime.parse(s).toLocalDate();
            } catch (DateTimeParseException ex2) {
                // Try taking first 10 chars (yyyy-MM-dd)
                if (s.length() >= 10) {
                    String first = s.substring(0, 10);
                    try {
                        return LocalDate.parse(first);
                    } catch (DateTimeParseException ex3) {
                        return null;
                    }
                }
                return null;
            }
        }
    }
}
