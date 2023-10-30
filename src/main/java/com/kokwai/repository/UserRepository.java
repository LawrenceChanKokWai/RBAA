package com.kokwai.repository;

import com.kokwai.model.User;

import java.util.Collection;

public interface UserRepository<T extends User> {

    T create( T user );
    Collection<T> list( int page, int pageSize );
    T get( Long id );
    T update ( T user );
    Boolean delete( Long userId, String roleName );

}
