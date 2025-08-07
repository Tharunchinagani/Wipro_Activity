package com.user.service;

import com.user.model.Contact;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface ContactService {
    Contact saveContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(Long id);
    Contact updateContact(Long id, Contact contact);
    void deleteContact(Long id);
}

