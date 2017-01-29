
package com.kish.vaadin.db;

import com.kish.vaadin.bo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ThejKishore on 1/28/2017.
 */

@Repository
public interface PersonJPA extends JpaRepository<Person,Long>{

    @Query("Select p from Person p where p.firstName LIKE %:name% OR p.lastName LIKE %:name% OR p.mailId LIKE %:name% OR p.address LIKE %:name%")
    public List<Person> searchByName( @Param("name") String name);
}
