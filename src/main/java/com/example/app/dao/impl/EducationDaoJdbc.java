package com.example.app.dao.impl;

import com.example.app.dao.EducationDao;
import com.example.app.model.Education;
import com.example.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoJdbc implements EducationDao {

    @Override
    public Education save(Education education) {
        String sql = "INSERT INTO education(employee_id, university_id, level_id, title, field_of_study, year, notes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, education.getEmployeeId());
            ps.setObject(2, education.getUniversityId());
            ps.setObject(3, education.getLevelId());
            ps.setString(4, education.getTitle());
            ps.setString(5, education.getFieldOfStudy());
            if (education.getYear() != null)
                ps.setInt(6, education.getYear());
            else
                ps.setNull(6, java.sql.Types.INTEGER);
            ps.setString(7, education.getNotes());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    education.setId(rs.getLong(1));
            }
            return education;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Education update(Education education) {
        String sql = "UPDATE education SET employee_id=?, university_id=?, level_id=?, title=?, field_of_study=?, year=?, notes=? WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, education.getEmployeeId());
            ps.setObject(2, education.getUniversityId());
            ps.setObject(3, education.getLevelId());
            ps.setString(4, education.getTitle());
            ps.setString(5, education.getFieldOfStudy());
            ps.setObject(6, education.getYear());
            ps.setString(7, education.getNotes());
            ps.setLong(8, education.getId());
            ps.executeUpdate();
            return education;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM education WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Education findById(long id) {
        String sql = "SELECT * FROM education WHERE id=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return toEducation(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Education> findAllByEmployeeId(long employeeId) {
        String sql = "SELECT * FROM education WHERE employee_id=? ORDER BY id";
        List<Education> list = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    list.add(toEducation(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Education toEducation(ResultSet rs) throws SQLException {
        Education ed = new Education();
        ed.setId(rs.getLong("id"));
        ed.setEmployeeId(rs.getLong("employee_id"));
        ed.setUniversityId(getLongOrNull(rs, "university_id"));
        ed.setLevelId(getIntOrNull(rs, "level_id"));
        ed.setTitle(rs.getString("title"));
        ed.setFieldOfStudy(rs.getString("field_of_study"));
        int yr = rs.getInt("year");
        ed.setYear(rs.wasNull() ? null : yr);
        ed.setNotes(rs.getString("notes"));
        return ed;
    }

    private Integer getIntOrNull(ResultSet rs, String col) throws SQLException {
        int v = rs.getInt(col);
        return rs.wasNull() ? null : v;
    }

    private Long getLongOrNull(ResultSet rs, String col) throws SQLException {
        long v = rs.getLong(col);
        return rs.wasNull() ? null : v;
    }
}
