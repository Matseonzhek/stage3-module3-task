package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {


    private final EntityManager entityManager;

    @Autowired
    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AuthorModel> readAll() {
        String jpql = "select authors from AuthorModel authors order by authors.id";
        TypedQuery<AuthorModel> query=entityManager.createQuery(jpql,AuthorModel.class);
        return query.getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        AuthorModel authorModel = entityManager.find(AuthorModel.class, id);
        return Optional.of(authorModel);
    }

    @Transactional
    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public AuthorModel update(AuthorModel entity) {
        long id = entity.getId();
        if (existById(id)) {
            AuthorModel authorModel = readById(id).get();
            authorModel.setName(entity.getName());
            entityManager.merge(authorModel);
            return authorModel;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            AuthorModel authorModel = readById(id).get();
            entityManager.remove(authorModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
