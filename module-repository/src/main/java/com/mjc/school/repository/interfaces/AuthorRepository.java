package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    @Query(value = "select * from authors order by id",
            nativeQuery = true)
    List<AuthorModel> findAll();

    @Query(value = "select * from authors  where id = ?1 ",
            nativeQuery = true)
    Optional<AuthorModel> findById(Long id);

}
