package rs.raf.domaci3;

import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class Domaci3Application {

	public static void main(String[] args) {
		SpringApplication.run(Domaci3Application.class, args);
	}

}
