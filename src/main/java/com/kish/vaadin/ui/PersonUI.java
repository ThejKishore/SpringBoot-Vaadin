package com.kish.vaadin.ui;

import com.kish.vaadin.bo.Person;
import com.kish.vaadin.db.PersonJPA;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

/**
 * Created by ThejKishore on 1/28/2017.
 */
@SpringUI
@Theme("valo")
@Log4j
public class PersonUI extends UI {

    private final PersonJPA personJPA;
    private TextField fNameTxtFld;
    private TextField lNameTxtFld;
    private TextField emailTxtFld;
    private TextField addressTxtFld;
    private FormLayout form;
    private TextField personIdTxt;

    public PersonUI(PersonJPA personJPA){
        this.personJPA = personJPA;
    }

    TextField filterField = new TextField();
    Grid table = new Grid();

    @Override
    public void init(VaadinRequest vaadinRequest) {

        Button search = new Button();
        search.setIcon(FontAwesome.BINOCULARS);
        search.addClickListener(e->{
            String enterText = ((TextField)filterField).getValue();
            if(enterText == null ||"".equals(enterText)){
                refershTable();
            } else{
                table.setContainerDataSource(new BeanItemContainer(Person.class,personJPA.searchByName(enterText)));
            }
        });

        Button addPerson = new Button();
        addPerson.setIcon(FontAwesome.USER_PLUS);
        addPerson.addClickListener(e->{
            form.setVisible(true);
            clearPersonForm();
        });

        table.setColumns("personId", "firstName", "lastName","mailId","address");
        table.setSelectionMode(Grid.SelectionMode.SINGLE);
        refershTable();


        table.addSelectionListener(e->{
            Object selected = ((Grid.SingleSelectionModel)table.getSelectionModel()).getSelectedRow();
            if(selected instanceof  Person){
                Person p =(Person) selected;
                form.setVisible(true);
                personIdTxt.setReadOnly(false);
                personIdTxt.setValue(p.getPersonId()+"");
                fNameTxtFld.setValue(p.getFirstName());
                lNameTxtFld.setValue(p.getLastName());
                emailTxtFld.setValue(p.getMailId());
                addressTxtFld.setValue(p.getAddress());
                personIdTxt.setReadOnly(true);
            }
        });

        form = new FormLayout();

        personIdTxt = new TextField("Person ID");

        personIdTxt.setReadOnly(true);
        form.addComponent(personIdTxt);

        fNameTxtFld = new TextField("First Name");

        form.addComponent(fNameTxtFld);

        lNameTxtFld = new TextField("Last Name");
        form.addComponent(lNameTxtFld);



        emailTxtFld = new TextField("Mail Id");
        emailTxtFld.setIcon(FontAwesome.ENVELOPE);
        form.addComponent(emailTxtFld);

        addressTxtFld = new TextField("Address");
        addressTxtFld.setIcon(FontAwesome.ROAD);
        form.addComponent(addressTxtFld);

        Button saveBt = new Button("Save");
        saveBt.setIcon(FontAwesome.SAVE);
        saveBt.addClickListener(e->{
            Person p = new Person();
            p.setFirstName(fNameTxtFld.getValue());
            p.setLastName(lNameTxtFld.getValue());
            p.setMailId(emailTxtFld.getValue());
            p.setAddress(addressTxtFld.getValue());
            String personId = personIdTxt .getValue();
            boolean recordUpdated = Boolean.FALSE;
            if(personId == null || "".equals(personId)){
                personJPA.saveAndFlush(p);
                recordUpdated = Boolean.TRUE;
            } else {
                long l= Long.parseLong(personId);
                p.setPersonId(l);
                personJPA.saveAndFlush(p);
                recordUpdated = Boolean.TRUE;
            }
            if(recordUpdated) {
                refershTable();
                clearPersonForm();
            }
        });

        Button deleteBt = new Button("Delete");
        deleteBt.setIcon(FontAwesome.TRASH);
        deleteBt.addClickListener(e->{
            String personId = personIdTxt .getValue();
            boolean recordUpdated = Boolean.FALSE;
            if(personId == null || "".equals(personId)){
                // Alert
                System.out.println("No Records to delete");
            } else {
                long l= Long.parseLong(personId);
                personJPA.delete(l);
                recordUpdated = Boolean.TRUE;
            }
            if(recordUpdated) {
                refershTable();
                clearPersonForm();
            }
        });

        form.addComponent(saveBt);
        form.addComponent(deleteBt);

        form.setVisible(false);


        table.setSizeFull();
        VerticalLayout tableHolder =new VerticalLayout(table);
        tableHolder.setWidth("99%");

        CustomLayout customLayout = null;
        try {
            customLayout = new CustomLayout(this.getClass().getResourceAsStream("/META-INF/layout.html"));
            customLayout.addComponent(filterField,"searchTxt");
            customLayout.addComponent(search,"searchBtn");
            customLayout.addComponent(addPerson,"addPersonBtn");
            customLayout.addComponent(tableHolder,"table");
            customLayout.addComponent(form,"personEditForm");
            setContent(customLayout);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void refershTable(){
        table.setContainerDataSource(new BeanItemContainer(Person.class,personJPA.findAll()));
    }

    private void clearPersonForm(){
        form.setVisible(true);
        personIdTxt.setReadOnly(false);
        personIdTxt.setValue("");
        fNameTxtFld.setValue("");
        lNameTxtFld.setValue("");
        emailTxtFld.setValue("");
        addressTxtFld.setValue("");
        personIdTxt.setReadOnly(true);
    }
}
