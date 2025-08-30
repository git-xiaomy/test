# Spring Boot Scaffold

一个完善的、开箱即用的 Spring Boot 脚手架项目，包含用户认证、权限管理、数据库集成等核心功能。

## 🚀 特性

- ✅ **Spring Boot 3.2** - 最新版本，性能优化
- ✅ **Spring Security** - JWT 认证，细粒度权限控制  
- ✅ **MyBatis-Plus** - 强大的 ORM 框架，代码生成
- ✅ **MySQL 数据库** - 主流关系型数据库
- ✅ **JWT 认证** - 无状态认证机制
- ✅ **角色权限管理** - 基于 RBAC 的权限系统
- ✅ **VO/DTO 封装** - 规范的数据传输对象
- ✅ **全局异常处理** - 统一错误响应格式
- ✅ **参数校验** - JSR303 参数验证
- ✅ **代码规范** - 清晰的项目结构和编码规范

## 📋 技术栈

| 技术 | 版本 | 说明 |
|-----|------|------|
| Spring Boot | 3.2.0 | 基础框架 |
| Spring Security | 6.x | 安全框架 |
| MyBatis-Plus | 3.5.4.1 | ORM 框架 |
| MySQL | 8.0+ | 数据库 |
| JWT | 0.12.3 | 认证token |
| Lombok | latest | 代码简化 |
| Validation | latest | 参数校验 |
| Hutool | 5.8.23 | 工具类库 |

## 🏗️ 项目结构

```
src/main/java/com/example/scaffold/
├── config/          # 配置类
│   ├── MybatisPlusConfig.java
│   └── SecurityConfig.java
├── controller/      # 控制层
│   ├── AuthController.java
│   ├── HealthController.java
│   └── SysUserController.java
├── dto/            # 数据传输对象
│   ├── LoginRequestDTO.java
│   └── UserRequestDTO.java
├── entity/         # 实体类
│   ├── BaseEntity.java
│   ├── SysPermission.java
│   ├── SysRole.java
│   └── SysUser.java
├── exception/      # 异常处理
│   ├── BusinessException.java
│   └── GlobalExceptionHandler.java
├── mapper/         # 数据访问层
│   ├── SysPermissionMapper.java
│   ├── SysRoleMapper.java
│   └── SysUserMapper.java
├── security/       # 安全相关
│   ├── CustomUserDetails.java
│   └── JwtAuthenticationFilter.java
├── service/        # 业务逻辑层
│   ├── SysUserService.java
│   └── impl/
│       ├── CustomUserDetailsServiceImpl.java
│       └── SysUserServiceImpl.java
├── utils/          # 工具类
│   ├── JwtUtils.java
│   └── SecurityUtils.java
├── vo/             # 视图对象
│   ├── ApiResponse.java
│   ├── PageVO.java
│   ├── RoleVO.java
│   └── UserVO.java
└── ScaffoldApplication.java
```

## 🚀 快速开始

### 1. 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 2. 克隆项目

```bash
git clone https://github.com/git-xiaomy/test.git
cd test
```

### 3. 数据库配置

1. 创建 MySQL 数据库：
```sql
CREATE DATABASE scaffold_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

2. 执行初始化脚本：
```bash
mysql -u root -p scaffold_db < src/main/resources/db_init.sql
```

3. 修改数据库连接配置（`application.yml`）：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/scaffold_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

### 4. 编译运行

```bash
# 编译
mvn clean compile

# 运行
mvn spring-boot:run
```

或者：

```bash
# 打包
mvn clean package

# 运行 jar 包
java -jar target/springboot-scaffold-1.0.0.jar
```

### 5. 访问应用

- 应用地址：http://localhost:8080/api
- 健康检查：http://localhost:8080/api/health
- API 信息：http://localhost:8080/api/info

## 📖 API 接口

### 认证接口

| 方法 | 路径 | 说明 |
|-----|------|------|
| POST | /auth/login | 用户登录 |
| POST | /auth/logout | 用户登出 |
| GET | /auth/me | 获取当前用户信息 |

### 用户管理接口（需要管理员权限）

| 方法 | 路径 | 说明 |
|-----|------|------|
| GET | /admin/user/page | 分页获取用户列表 |
| GET | /admin/user/{id} | 获取用户详情 |
| POST | /admin/user | 创建用户 |
| PUT | /admin/user/{id} | 更新用户 |
| DELETE | /admin/user/{id} | 删除用户 |
| PATCH | /admin/user/{id}/status | 更改用户状态 |
| PATCH | /admin/user/{id}/password | 重置用户密码 |

### 示例请求

#### 用户登录
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

#### 获取用户列表（需要认证）
```bash
curl -X GET "http://localhost:8080/api/admin/user/page?page=1&size=10" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔑 默认用户

系统初始化时会创建一个管理员用户：

- **用户名**: admin
- **密码**: admin123
- **角色**: Administrator
- **权限**: 所有用户管理权限

## 🔧 配置说明

### JWT 配置
```yaml
jwt:
  secret: mySecretKey123456789012345678901234567890  # JWT 签名密钥
  expiration: 86400000  # token 过期时间（24小时）
  header: Authorization # 请求头名称
  prefix: Bearer        # token 前缀
```

### MyBatis-Plus 配置
```yaml
mybatis-plus:
  mapper-locations: classpath*:/mapper/**/*.xml
  type-aliases-package: com.example.scaffold.entity
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: auto
      logic-delete-field: deleted
      logic-delete-value: 1
      logic-not-delete-value: 0
```

## 🛠️ 开发指南

### 添加新的实体

1. 继承 `BaseEntity` 类
2. 使用 `@TableName` 注解指定表名
3. 使用 `@TableField` 注解映射字段

### 添加新的控制器

1. 使用 `@RestController` 注解
2. 继承或使用 `ApiResponse<T>` 统一响应格式
3. 使用 `@PreAuthorize` 进行权限控制

### 权限控制

系统支持基于角色和权限的访问控制：

- `@PreAuthorize("hasRole('ADMIN')")` - 角色控制
- `@PreAuthorize("hasAuthority('user:create')")` - 权限控制

## 📝 注意事项

1. **密码安全**: 生产环境请修改默认密码和 JWT 密钥
2. **数据库配置**: 根据实际环境修改数据库连接配置
3. **日志配置**: 生产环境建议修改日志级别
4. **跨域配置**: 如需支持前端跨域，请添加 CORS 配置

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来改进这个项目！

## 📄 许可证

本项目采用 MIT 许可证 - 详情请查看 [LICENSE](LICENSE) 文件。

---

🎉 **开箱即用的 Spring Boot 脚手架，让你专注于业务逻辑开发！**