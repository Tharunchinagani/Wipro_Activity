package com.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
