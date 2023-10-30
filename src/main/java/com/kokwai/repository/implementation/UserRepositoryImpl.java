package com.kokwai.repository.implementation;

import com.kokwai.exception.ApiException;
import com.kokwai.model.Role;
import com.kokwai.model.User;
import com.kokwai.repository.RoleRepository;
import com.kokwai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static com.kokwai.constant.UserRepositoryConstant.*;
import static com.kokwai.constant.UserRepositoryException.*;
import static com.kokwai.enumeration.RoleType.*;
import static com.kokwai.enumeration.VerificationType.*;
import static com.kokwai.query.RoleQuery.INSERT_VERIFICATION_URL_QUERY;
import static com.kokwai.query.UserQuery.*;
import static java.util.Objects.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create( User user ) {
        if( getEmailCount(user.getEmail().trim().toLowerCase()) > 0 ) {
            throw new ApiException(EMAIL_EXIST_CONSTANT);
        }
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            jdbc.update(INSERT_VERIFICATION_URL_QUERY, Map.of("userId", user.getId(), "url", verificationUrl));

            //emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);

            user.setEnabled(false);
            user.setNonLocked(true);

            return user;
        } catch( EmptyResultDataAccessException exception ) {
            throw new ApiException(ROLE_EMPTY_EXCEPTION);
        } catch( Exception exception ) {
            throw new ApiException(COMMON_EXCEPTION);
        }
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(EMAIL_COUNT_QUERY, Map.of("email", email), Integer.class);
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Boolean delete(Long userId, String roleName) {
        return null;
    }


    /******* PRIVATE FUNCTION ***********/
    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" +key).toUriString();
    }

}
