package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagModel,Long> {

}
