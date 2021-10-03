package com.example.clip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clip.entities.User;
/**
 * @author Ivan
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
