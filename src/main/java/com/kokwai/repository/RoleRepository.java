package com.kokwai.repository;

import com.kokwai.model.Role;

import java.util.Collection;

public interface RoleRepository<T extends Role> {

    T create( T user );
    Collection<T> list(int page, int pageSize );
    T get( Long id );
    T update ( T user );
    Boolean delete( Long userId, String roleName );

    void addRoleToUser(Long userId, String roleName);

}
