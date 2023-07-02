package com.rova.accountservice.repositories;

import com.rova.accountservice.dals.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends CrudRepository<Users, Long> {
    Optional<Users> findUserById(long id);

    @Query(value = "SELECT * FROM users WHERE phone_no=?1", nativeQuery = true)
    Optional<Users> findByPhoneNo(String phoneNo);

    @Query(value = "SELECT id FROM users WHERE phone_no=?1", nativeQuery = true)
    Optional<Long> findUserIdByPhoneNo(String phoneNo);

    @Query(value = "SELECT username FROM users WHERE phone_no=?1", nativeQuery = true)
    Optional<String> findUsernameByPhoneNo(String phoneNo);
}
