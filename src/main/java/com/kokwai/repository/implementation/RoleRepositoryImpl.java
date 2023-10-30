package com.kokwai.repository.implementation;

import com.kokwai.exception.ApiException;
import com.kokwai.model.Role;
import com.kokwai.repository.RoleRepository;
import com.kokwai.rowMapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static com.kokwai.constant.UserRepositoryException.COMMON_EXCEPTION;
import static com.kokwai.constant.UserRepositoryException.ROLE_EMPTY_EXCEPTION;
import static com.kokwai.query.RoleQuery.*;
import static java.util.Objects.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role user) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role user) {
        return null;
    }

    @Override
    public Boolean delete(Long userId, String roleName) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY, Map.of("userId", userId, "roleId", requireNonNull(role).getId()));
        } catch( EmptyResultDataAccessException exception ) {
            throw new ApiException(ROLE_EMPTY_EXCEPTION);
        } catch( Exception exception ) {
            throw new ApiException(COMMON_EXCEPTION);
        }
    }
}
