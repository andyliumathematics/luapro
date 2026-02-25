-- 插入初始告警记录数据
INSERT INTO alarm_records (title, description, is_active) VALUES 
('系统启动提醒', '系统已成功启动，所有服务正常运行', 1),
('定时任务执行', '每日定时任务执行完成，处理了100条数据', 1),
('数据库连接测试', '数据库连接状态正常，响应时间在可接受范围内', 1),
('内存使用率警告', '当前内存使用率达到85%，请注意监控', 0),
('磁盘空间不足', 'C盘剩余空间不足10GB，请及时清理', 0);

-- 插入初始用户数据
INSERT INTO users (username, email, password, is_enabled) VALUES 
('admin', 'admin@example.com', '$2a$10$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 1),
('user1', 'user1@example.com', '$2a$10$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 1),
('monitor', 'monitor@example.com', '$2a$10$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 1);

-- 插入一些历史告警数据
INSERT INTO alarm_records (title, description, is_active, created_time) VALUES 
('网络连接中断', '主网络连接在凌晨2点出现短暂中断，已自动恢复', 0, datetime('now', '-1 day')),
('CPU使用率过高', 'CPU使用率连续30分钟超过90%，已触发告警', 0, datetime('now', '-2 hours')),
('服务重启通知', 'Web服务因配置更新而重启，服务已恢复正常', 1, datetime('now', '-30 minutes'));