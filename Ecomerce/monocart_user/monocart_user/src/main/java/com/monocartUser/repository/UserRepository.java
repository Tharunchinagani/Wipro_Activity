package com.monocartUser.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocartUser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}

