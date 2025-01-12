## HikariCP Database Connection Pool in Spring Boot

**HikariCP** (Hikari Connection Pool) is the default connection pool used in Spring Boot for managing database connections. It is known for its performance, simplicity, and efficiency in handling database connections. By default, Spring Boot uses HikariCP when you include a database dependency such as `spring-boot-starter-data-jpa` or `spring-boot-starter-jdbc`.

HikariCP is fast, lightweight, and highly configurable, making it a preferred choice for connection pooling in modern Java applications.

### How HikariCP Works in Spring Boot

When your Spring Boot application starts, the `DataSource` bean is created to manage database connections. If you have a database-related starter (e.g., `spring-boot-starter-data-jpa` or `spring-boot-starter-jdbc`), Spring Boot auto-configures a `HikariDataSource` (an implementation of the `DataSource` interface) to manage the database connections.

### Basic HikariCP Configuration in `application.properties` or `application.yml`

You can configure HikariCP's behavior by setting various properties in your `application.properties` or `application.yml` file. These properties allow you to define connection pool settings like maximum pool size, connection timeout, and idle timeout.

#### Example Configuration in `application.properties`:

```properties
# Database connection settings
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=root
spring.datasource.password=root

# HikariCP specific settings
spring.datasource.hikari.pool-name=HikariPool
spring.datasource.hikari.maximum-pool-size=10       # Maximum number of connections in the pool
spring.datasource.hikari.minimum-idle=5             # Minimum number of idle connections
spring.datasource.hikari.idle-timeout=300000       # Maximum time (in ms) a connection can remain idle
spring.datasource.hikari.max-lifetime=1800000      # Maximum lifetime (in ms) of a connection
spring.datasource.hikari.connection-timeout=30000  # Maximum time (in ms) to wait for a connection from the pool
spring.datasource.hikari.validation-timeout=3000   # Time to wait (in ms) for connection validation
spring.datasource.hikari.leak-detection-threshold=15000  # Detect connections that have been open for too long (in ms)
```

# Key HikariCP Configuration Properties

- **`spring.datasource.hikari.pool-name`**: Specifies the name of the connection pool (useful for logging).
- **`spring.datasource.hikari.maximum-pool-size`**: Defines the maximum number of connections that can be in the pool at any given time. Default is 10.
- **`spring.datasource.hikari.minimum-idle`**: Defines the minimum number of idle connections in the pool. Default is maximum-pool-size / 2.
- **`spring.datasource.hikari.idle-timeout`**: The maximum time that a connection can be idle before being removed from the pool. Default is 600,000 ms (10 minutes).
- **`spring.datasource.hikari.max-lifetime`**: The maximum lifetime of a connection in the pool. Connections older than this value will be closed and removed from the pool. Default is 30 minutes.
- **`spring.datasource.hikari.connection-timeout`**: The maximum time to wait for a connection to be established before throwing an exception. Default is 30,000 ms (30 seconds).
- **`spring.datasource.hikari.validation-timeout`**: The time to wait for a connection to be validated. Default is 5 seconds.
- **`spring.datasource.hikari.leak-detection-threshold`**: The time threshold for detecting and logging connection leaks (when a connection is not closed). Default is 0 (disabled).

## Default Configuration in Spring Boot

If you don’t explicitly configure HikariCP, Spring Boot automatically provides the default connection pool configuration. For most applications, the default settings are sufficient, but you can always tweak them to meet the requirements of your project.

## Example Code for HikariCP Auto-Configuration

In a typical Spring Boot application, you can rely on auto-configuration, so you usually don't need to create the `DataSource` bean manually. Spring Boot does it for you:

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HikariCpExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikariCpExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return (args) -> {
            // Example of querying the database with JdbcTemplate
            System.out.println("Database connected successfully!");
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM my_table", Integer.class);
        };
    }
}
```

# Monitoring and Metrics

HikariCP provides built-in support for connection pool metrics that can be accessed via JMX or by using a metrics library like **Micrometer** in Spring Boot. This allows you to track the pool's usage, health, and performance in real-time.

If you're using **Spring Boot Actuator**, you can monitor the connection pool's status and performance metrics such as:

- Current number of active connections
- Number of idle connections
- Connection pool utilization

These metrics are helpful for identifying bottlenecks or connection leaks, allowing you to proactively manage database connections and improve application performance.

## Performance Benefits of HikariCP

- **Low Latency**: HikariCP is optimized for low-latency connection acquisition. It has a fast and efficient way of managing database connections, leading to minimal overhead.
- **Fast Connection Pooling**: HikariCP offers the fastest connection pooling performance compared to other Java-based connection pools like **Apache DBCP2** or **C3P0**.
- **Lightweight**: HikariCP’s design focuses on providing minimal overhead, which keeps its footprint small.

## Conclusion

HikariCP is the default connection pool in Spring Boot due to its performance, simplicity, and reliability. By configuring settings such as the pool size, idle timeout, connection validation, and leak detection, you can fine-tune the pool's behavior for your specific application needs. For most use cases, the default settings will work well, but tweaking them may help optimize your app’s database interactions.
















