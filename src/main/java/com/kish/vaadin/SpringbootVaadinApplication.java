package com.kish.vaadin;

import com.kish.vaadin.bo.Person;
import com.kish.vaadin.db.PersonJPA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringbootVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVaadinApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(final PersonJPA repository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				repository.save(new Person("Rakesh","Chandran","racky@gmail.com","Chennai,India"));
				repository.save(new Person("Madhan","Babu","madhan@gmail.com","Chennai,India"));
				repository.save(new Person("Vamsy","Raju","vamsyRaju@gmail.com","Austin,Texas,US"));



				List<Person> asd = repository.findAll();
				for (Person p:asd) {
					System.out.println(p);
				}

			}
		};
	}
}
