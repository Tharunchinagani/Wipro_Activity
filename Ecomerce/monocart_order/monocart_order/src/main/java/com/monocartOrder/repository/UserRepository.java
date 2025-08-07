package com.monocartOrder.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartOrder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}

