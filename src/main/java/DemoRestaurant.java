

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.lawrence")
@EnableJpaRepositories(basePackages = "com.lawrence")
@EntityScan(basePackages = "com.lawrence")
public class DemoRestaurant {

	public static void main(String[] args) { 
		SpringApplication.run(DemoRestaurant.class, args);
	}

}
