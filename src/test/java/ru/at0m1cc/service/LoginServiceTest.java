package ru.at0m1cc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.at0m1cc.db.Password;
import ru.at0m1cc.repository.PasswordRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

//Бля я ебу эта хуйня не работает так что временно без тестов похуй + похуй
public class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        PasswordRepository passwordRepository = new PasswordRepository() {
            @Override
            public List<Password> findByPassword(String password) {
                return List.of();
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Password> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Password> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<Password> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Password getOne(Long aLong) {
                return null;
            }

            @Override
            public Password getById(Long aLong) {
                return null;
            }

            @Override
            public Password getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Password> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Password> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Password> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<Password> findAll() {
                return List.of();
            }

            @Override
            public List<Password> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public <S extends Password> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Password> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Password entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Password> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<Password> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Password> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Password> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Password> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Password> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Password> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Password, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        loginService = new LoginService(passwordRepository);
    }
    @Test
    public void testLogin() {
        Assertions.assertTrue(loginService.login("password"));
    }
    @Test
    public void testLogin2() {
        Assertions.assertFalse(loginService.login("password1"));
    }
}
