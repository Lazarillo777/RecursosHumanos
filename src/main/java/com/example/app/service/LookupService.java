package com.example.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LookupService {
    public static class Item {
        private int id;
        private String code;
        private String name;

        public Item(int id, String code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return name;
        }
    }

    public List<Item> findAll(String table) {
        String sql = String.format("SELECT id, code, name FROM %s ORDER BY id", table);
        List<Item> list = new ArrayList<>();
        try (Connection conn = com.example.app.util.Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                list.add(new Item(rs.getInt("id"), rs.getString("code"), rs.getString("name")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
