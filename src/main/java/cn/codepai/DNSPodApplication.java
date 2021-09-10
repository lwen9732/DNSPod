package cn.codepai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DNSPodApplication {

	public static void main(String[] args) {
		SpringApplication.run(DNSPodApplication.class, args);
		Task.startTask();
	}

}
