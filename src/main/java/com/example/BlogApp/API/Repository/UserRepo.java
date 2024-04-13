package com.example.BlogApp.API.Repository;

import com.example.BlogApp.API.Entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UsersEntity, String> {
}
