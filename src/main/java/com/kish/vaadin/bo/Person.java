package com.kish.vaadin.bo;

/**
 * Created by ThejKishore on 1/28/2017.
 */


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class  Person implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long personId;

        private String firstName;

        private String lastName;

        private String mailId;

        private String address;

        public Person(){}

}
