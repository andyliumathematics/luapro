package com.rover.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db")
public class DatabaseController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/records")
    public List<Map<String, Object>> getAllRecords() {
        return jdbcTemplate.queryForList("SELECT * FROM alarm_records ORDER BY created_time DESC");
    }
    
    @GetMapping("/users")
    public List<Map<String, Object>> getAllUsers() {
        return jdbcTemplate.queryForList("SELECT * FROM users ORDER BY created_time DESC");
    }
    
    @GetMapping("/active-alarms")
    public List<Map<String, Object>> getActiveAlarms() {
        return jdbcTemplate.queryForList("SELECT * FROM alarm_records WHERE is_active = 1 ORDER BY created_time DESC");
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getDatabaseStats() {
        Map<String, Object> stats = new java.util.HashMap<>();
        stats.put("totalRecords", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM alarm_records", Integer.class));
        stats.put("activeRecords", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM alarm_records WHERE is_active = 1", Integer.class));
        stats.put("totalUsers", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class));
        return stats;
    }
}