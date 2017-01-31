package com.kish.vaadin;

import com.kish.vaadin.bo.Person;
import com.kish.vaadin.db.PersonJPA;
import lombok.extern.log4j.Log4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Log4j
public class SpringbootVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVaadinApplication.class, args);
	}

	/**
	 * Setting this so that while we do the inmemory db it doesn't create any issue.
	 * @return
	 */
	@Bean
	public FlywayMigrationStrategy migrationStrategy(){
		FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
			@Override
			public void migrate(Flyway flyway) {
				flyway.clean();
				flyway.migrate();
			}
		};

		return strategy;
	}

	@Bean
	public CommandLineRunner runner(final PersonJPA repository) {
		//Using the lambda to create an anonymous class...

		//Feature of java 8 lambda expressions.
		return c->{
//				repository.save(constructPerson("Rakesh","Chandran","racky@gmail.com","Chennai,India"));
//				repository.save(constructPerson("Madhan","Babu","madhan@gmail.com","Chennai,India"));
//				repository.save(constructPerson("Vamsy","Raju","vamsyRaju@gmail.com","Austin,Texas,US"));

			List<Person> asd = repository.findAll();
			for (Person p:asd) {
				log.info(p);
				log.info(p.getFirstName());
			}
		};

		/*return new CommandLineRunner (){
			public void run(String[] args) throws Exception {

//				repository.save(constructPerson("Rakesh","Chandran","racky@gmail.com","Chennai,India"));
//				repository.save(constructPerson("Madhan","Babu","madhan@gmail.com","Chennai,India"));
//				repository.save(constructPerson("Vamsy","Raju","vamsyRaju@gmail.com","Austin,Texas,US"));

				List<Person> asd = repository.findAll();
				for (Person p:asd) {
					log.info(p);
				}

			}
		};*/
	}

	private Person constructPerson(String fName,String lName,String mailId,String address){
		Person p =new Person();
		p.setAddress(address);
		p.setFirstName(fName);
		p.setMailId(mailId);
		p.setLastName(lName);
		return p;
	}
}
