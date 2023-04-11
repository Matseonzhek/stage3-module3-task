package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseEntity;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
}
