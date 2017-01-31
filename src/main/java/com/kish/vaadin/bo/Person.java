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
/*
        public long getPersonId() {
                return personId;
        }

        public void setPersonId(long personId) {
                this.personId = personId;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getMailId() {
                return mailId;
        }

        public void setMailId(String mailId) {
                this.mailId = mailId;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }*/
        public Person(){}

        public Person(String firstName, String lastName, String mailId, String address) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.mailId = mailId;
                this.address = address;
        }

        @Override
        public String toString() {
                return "Person{" +
                        "personId=" + personId +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", mailId='" + mailId + '\'' +
                        ", address='" + address + '\'' +
                        '}';
        }
}
