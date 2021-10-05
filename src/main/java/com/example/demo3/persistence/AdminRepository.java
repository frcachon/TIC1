package com.example.demo3.persistence;

import com.example.demo3.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {

    Admin findByMailAndPassword(String mail, String password);

}
