package com.example.app.dao.impl;

import com.example.app.dao.FamilyMemberDao;
import com.example.app.model.FamilyMember;
import com.example.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyMemberDaoJdbc implements FamilyMemberDao {

    @Override
    public FamilyMember save(FamilyMember member) {
        String sql = "INSERT INTO family_member(employee_id, family_role_id, first_name, last_name, gender_id, birth_date, document_type_id, document_number, phone, email) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, member.getEmployeeId());
            ps.setObject(2, member.getFamilyRoleId());
            ps.setString(3, member.getFirstName());
            ps.setString(4, member.getLastName());
            ps.setObject(5, member.getGenderId());
            if (member.getBirthDate() != null)
                ps.setDate(6, java.sql.Date.valueOf(member.getBirthDate()));
            else
                ps.setNull(6, java.sql.Types.DATE);
            ps.setObject(7, member.getDocumentTypeId());
            ps.setString(8, member.getDocumentNumber());
            ps.setString(9, member.getPhone());
            ps.setString(10, member.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    member.setId(rs.getLong(1));
            }
            return member;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FamilyMember update(FamilyMember member) {
        String sql = "UPDATE family_member SET employee_id=?, family_role_id=?, first_name=?, last_name=?, gender_id=?, birth_date=?, document_type_id=?, document_number=?, phone=?, email=? WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, member.getEmployeeId());
            ps.setObject(2, member.getFamilyRoleId());
            ps.setString(3, member.getFirstName());
            ps.setString(4, member.getLastName());
            ps.setObject(5, member.getGenderId());
            ps.setObject(6, member.getBirthDate());
            ps.setObject(7, member.getDocumentTypeId());
            ps.setString(8, member.getDocumentNumber());
            ps.setString(9, member.getPhone());
            ps.setString(10, member.getEmail());
            ps.setLong(11, member.getId());
            ps.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM family_member WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FamilyMember findById(long id) {
        String sql = "SELECT * FROM family_member WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return toFamilyMember(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FamilyMember> findAllByEmployeeId(long employeeId) {
        String sql = "SELECT * FROM family_member WHERE employee_id=? ORDER BY id";
        List<FamilyMember> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    list.add(toFamilyMember(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private FamilyMember toFamilyMember(ResultSet rs) throws SQLException {
        FamilyMember m = new FamilyMember();
        m.setId(rs.getLong("id"));
        m.setEmployeeId(rs.getLong("employee_id"));
        m.setFamilyRoleId(getIntOrNull(rs, "family_role_id"));
        m.setFirstName(rs.getString("first_name"));
        m.setLastName(rs.getString("last_name"));
        m.setGenderId(getIntOrNull(rs, "gender_id"));
        Date bd = rs.getDate("birth_date");
        if (bd != null)
            m.setBirthDate(bd.toLocalDate());
        m.setDocumentTypeId(getIntOrNull(rs, "document_type_id"));
        m.setDocumentNumber(rs.getString("document_number"));
        m.setPhone(rs.getString("phone"));
        m.setEmail(rs.getString("email"));
        return m;
    }

    private Integer getIntOrNull(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }
}
