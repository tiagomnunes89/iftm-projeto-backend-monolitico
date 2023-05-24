package com.iftm.exercicio03.repositories;


import com.iftm.exercicio03.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Consulta JPQL personalizada
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<User> findUsersByName(@Param("name") String name);

    @Query("SELECT u FROM User u JOIN u.groups g WHERE g.name = :groupName")
    List<User> findUsersByGroupName(@Param("groupName") String groupName);

    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    List<User> findUsersByPhone(@Param("phone") String phone);

    @Query("SELECT u FROM User u JOIN u.groups g WHERE g.code = :groupCode")
    List<User> findUsersByGroupCode(@Param("groupCode") String groupCode);
}