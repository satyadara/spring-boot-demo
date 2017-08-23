package com.satyadara.springdemo.repository;

import com.satyadara.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Menandakan interface ini merupakan repository//
                                                            //v Long merupakan tipe data ID pada model.
public interface UserRepository extends JpaRepository<User, Long> {     //Menggunakan JPARepository sebagai induk.
                                                     //^ User merupakan model yang akan kita gunakan untuk repo.
}