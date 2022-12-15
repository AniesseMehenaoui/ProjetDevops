package com.kenkogroup.kenko.user.repository;
import com.kenkogroup.kenko.user.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryy extends CrudRepository<UserInfo, Integer> {

    UserInfo findOneByEmail(String email);

}