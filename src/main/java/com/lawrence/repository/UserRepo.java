package com.lawrence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lawrence.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long > {


        UserEntity findByEmail(String email);

}
