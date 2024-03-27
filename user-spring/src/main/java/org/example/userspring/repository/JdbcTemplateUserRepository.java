package org.example.userspring.repository;

import org.example.userspring.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
public class JdbcTemplateUserRepository implements UserRepository {


    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public User add(User user) {
        // 객체 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // users라는 테이블 선언하고 index가 key라고 알려줌
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("index");
        // 실제로 데이터를 넣기 위해 map 생성
        Map<String, Object> parameters = new HashMap<>();
        // 파라미터 이용해서 id , password를 만들어서 값을 넣음
        parameters.put("id", user.getId());
        parameters.put("password", user.getPassword());
        // key 생성
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setIndex(key.longValue());

        return user;

    }

    @Override
    public Optional<User> findById(String id) {
        List<User> result = jdbcTemplate.query("select * from users where id = ?", userRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users",userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
            User user = new User();
            user.setIndex(rs.getLong("index"));
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
}
