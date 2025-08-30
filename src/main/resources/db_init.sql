-- Create database
CREATE DATABASE IF NOT EXISTS scaffold_db DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

USE scaffold_db;

-- User table
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'User ID',
    username VARCHAR(64) NOT NULL UNIQUE COMMENT 'Username',
    password VARCHAR(128) NOT NULL COMMENT 'Password',
    nickname VARCHAR(64) COMMENT 'Nickname',
    email VARCHAR(128) COMMENT 'Email',
    phone VARCHAR(32) COMMENT 'Phone number',
    avatar VARCHAR(255) COMMENT 'Avatar URL',
    status TINYINT DEFAULT 1 COMMENT 'Status: 0-disabled, 1-enabled',
    deleted TINYINT DEFAULT 0 COMMENT 'Deleted flag: 0-not deleted, 1-deleted',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    last_login_time DATETIME COMMENT 'Last login time'
) COMMENT = 'System user table';

-- Role table
CREATE TABLE IF NOT EXISTS sys_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Role ID',
    role_name VARCHAR(64) NOT NULL UNIQUE COMMENT 'Role name',
    role_code VARCHAR(64) NOT NULL UNIQUE COMMENT 'Role code',
    description VARCHAR(255) COMMENT 'Role description',
    status TINYINT DEFAULT 1 COMMENT 'Status: 0-disabled, 1-enabled',
    deleted TINYINT DEFAULT 0 COMMENT 'Deleted flag: 0-not deleted, 1-deleted',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) COMMENT = 'System role table';

-- Permission table
CREATE TABLE IF NOT EXISTS sys_permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Permission ID',
    permission_name VARCHAR(64) NOT NULL COMMENT 'Permission name',
    permission_code VARCHAR(64) NOT NULL UNIQUE COMMENT 'Permission code',
    resource_type VARCHAR(32) NOT NULL COMMENT 'Resource type: menu, button, api',
    url VARCHAR(255) COMMENT 'Request URL',
    method VARCHAR(16) COMMENT 'Request method',
    parent_id BIGINT DEFAULT 0 COMMENT 'Parent permission ID',
    sort_order INT DEFAULT 0 COMMENT 'Sort order',
    description VARCHAR(255) COMMENT 'Permission description',
    status TINYINT DEFAULT 1 COMMENT 'Status: 0-disabled, 1-enabled',
    deleted TINYINT DEFAULT 0 COMMENT 'Deleted flag: 0-not deleted, 1-deleted',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
) COMMENT = 'System permission table';

-- User-Role relationship table
CREATE TABLE IF NOT EXISTS sys_user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    user_id BIGINT NOT NULL COMMENT 'User ID',
    role_id BIGINT NOT NULL COMMENT 'Role ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    UNIQUE KEY uk_user_role (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES sys_role(id) ON DELETE CASCADE
) COMMENT = 'User-Role relationship table';

-- Role-Permission relationship table
CREATE TABLE IF NOT EXISTS sys_role_permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    role_id BIGINT NOT NULL COMMENT 'Role ID',
    permission_id BIGINT NOT NULL COMMENT 'Permission ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    UNIQUE KEY uk_role_permission (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES sys_role(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES sys_permission(id) ON DELETE CASCADE
) COMMENT = 'Role-Permission relationship table';

-- Insert initial data
-- Insert default admin user (password: admin123)
INSERT INTO sys_user (username, password, nickname, email, status) VALUES 
('admin', '$2a$10$YJDXeP7VQ9Z8K3xGgf9h5.Yh8Qf9w8uT5Nf9eZ8t1Yq8b5w9K6Nf9', 'Administrator', 'admin@example.com', 1);

-- Insert default roles
INSERT INTO sys_role (role_name, role_code, description) VALUES 
('Administrator', 'ADMIN', 'System administrator'),
('User', 'USER', 'Regular user');

-- Insert default permissions
INSERT INTO sys_permission (permission_name, permission_code, resource_type, url, method, description) VALUES
('User Management', 'user:manage', 'menu', '/user/**', 'GET,POST,PUT,DELETE', 'User management module'),
('User List', 'user:list', 'api', '/user/list', 'GET', 'View user list'),
('User Create', 'user:create', 'api', '/user/create', 'POST', 'Create new user'),
('User Update', 'user:update', 'api', '/user/update', 'PUT', 'Update user information'),
('User Delete', 'user:delete', 'api', '/user/delete', 'DELETE', 'Delete user'),
('Role Management', 'role:manage', 'menu', '/role/**', 'GET,POST,PUT,DELETE', 'Role management module'),
('Role List', 'role:list', 'api', '/role/list', 'GET', 'View role list'),
('Role Create', 'role:create', 'api', '/role/create', 'POST', 'Create new role'),
('Role Update', 'role:update', 'api', '/role/update', 'PUT', 'Update role information'),
('Role Delete', 'role:delete', 'api', '/role/delete', 'DELETE', 'Delete role');

-- Assign roles to admin user
INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);

-- Assign permissions to admin role
INSERT INTO sys_role_permission (role_id, permission_id) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10);

-- Assign basic permissions to user role
INSERT INTO sys_role_permission (role_id, permission_id) VALUES (2, 2), (2, 7);