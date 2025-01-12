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
