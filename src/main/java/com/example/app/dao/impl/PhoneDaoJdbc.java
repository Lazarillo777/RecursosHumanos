package com.example.app.dao.impl;

import com.example.app.dao.PhoneDao;
import com.example.app.model.Phone;
import com.example.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoJdbc implements PhoneDao {

    @Override
    public Phone save(Phone phone) {
        String sql = "INSERT INTO phone(employee_id, phone_number, type) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, phone.getEmployeeId());
            ps.setString(2, phone.getPhoneNumber());
            ps.setString(3, phone.getType());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    phone.setId(rs.getLong(1));
            }
            return phone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Phone update(Phone phone) {
        String sql = "UPDATE phone SET employee_id=?, phone_number=?, type=? WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, phone.getEmployeeId());
            ps.setString(2, phone.getPhoneNumber());
            ps.setString(3, phone.getType());
            ps.setLong(4, phone.getId());
            ps.executeUpdate();
            return phone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM phone WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Phone findById(long id) {
        String sql = "SELECT * FROM phone WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return toPhone(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Phone> findAllByEmployeeId(long employeeId) {
        String sql = "SELECT * FROM phone WHERE employee_id=? ORDER BY id";
        List<Phone> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    list.add(toPhone(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Phone toPhone(ResultSet rs) throws SQLException {
        Phone p = new Phone();
        p.setId(rs.getLong("id"));
        p.setEmployeeId(rs.getLong("employee_id"));
        p.setPhoneNumber(rs.getString("phone_number"));
        p.setType(rs.getString("type"));
        return p;
    }
}
