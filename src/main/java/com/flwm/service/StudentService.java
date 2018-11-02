package com.flwm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoupj on 10/26/18.
 */
@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map> getList(){
        String sql = "SELECT * FROM share_data_basic limit 10";
        return (List<Map>) jdbcTemplate.query(sql, new RowMapper<Map>(){

            @Override
            public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map map=new HashMap();
                map.put("id",rs.getInt(("id")));
                map.put("code",rs.getString("code"));
                return map;
            }

        });
    }


}