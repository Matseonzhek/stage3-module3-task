//package com.mjc.school.repository.implementation;
//
//import com.mjc.school.repository.BaseRepository;
//import com.mjc.school.repository.model.AuthorModel;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//
////@Repository
//public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
//
//
//    private final DataSourceCreation dataSource;
//
////    @Autowired
//    public AuthorRepository(DataSourceCreation dataSource) {
//        this.dataSource = DataSourceCreation.getDataSource();
//    }
//
//    @Override
//    public List<AuthorModel> readAll() {
//        return dataSource.getAuthorModelList();
//    }
//
//    @Override
//    public Optional<AuthorModel> readById(Long id) {
//        return Optional.of(dataSource.getAuthorModelList()
//                .stream()
//                .filter(authorModel -> authorModel.getId().equals(id))
//                .findFirst()
//                .get());
//    }
//
//    @Override
//    public AuthorModel create(AuthorModel entity) {
//        List<AuthorModel> authorModelList = dataSource.getAuthorModelList();
//        authorModelList.sort(Comparator.comparing(AuthorModel::getId));
//        if (!authorModelList.isEmpty()) {
//            entity.setId(authorModelList.get(authorModelList.size() - 1).getId() + 1);
//        } else {
//            entity.setId(1L);
//        }
//        authorModelList.add(entity);
//        return entity;
//    }
//
//    @Override
//    public AuthorModel update(AuthorModel entity) {
//        long id = entity.getId();
//        Optional<AuthorModel> updatedAuthorModel = readById(id);
//        if (updatedAuthorModel.isPresent()) {
//            updatedAuthorModel.get().setName(entity.getName());
//            updatedAuthorModel.get().setUpdatedDate(entity.getUpdatedDate());
//            return updatedAuthorModel.get();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        return dataSource.getAuthorModelList().remove(readById(id).get());
//    }
//
//    @Override
//    public boolean existById(Long id) {
//        return dataSource
//                .getAuthorModelList()
//                .stream()
//                .anyMatch(authorModel -> authorModel.getId().equals(id));
//    }
//}
