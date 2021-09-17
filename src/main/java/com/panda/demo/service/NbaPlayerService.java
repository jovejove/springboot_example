package com.panda.demo.service;

import com.panda.demo.entity.NbaPlayer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: NbaPlayerService.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-08-18
 * @Version: 1.0
 */

@Service
public class NbaPlayerService {

    @Resource    // 自动注入，spring boot会帮我们实例化一个对象
    private JdbcTemplate jdbcTemplate;      // 一个通过JDBC连接数据库的工具类，可以通过这个工具类对数据库进行增删改查

    /**
     * 查询nba_player表所有的数据
     *
     * @return 返回nba_player表所有的数据
     */
    public List<NbaPlayer> queryAllData() {
        String sql = "select * from nba_player";
        List<NbaPlayer> list = jdbcTemplate.query(sql, new RowMapper<NbaPlayer>() {
            @Override
            public NbaPlayer mapRow(ResultSet resultSet, int i) throws SQLException {
                NbaPlayer nbaPlayer = new NbaPlayer();

                nbaPlayer.setCountryEn(resultSet.getString("countryEn"));
                nbaPlayer.setTeamName(resultSet.getString("teamName"));
                nbaPlayer.setTeamCity(resultSet.getString("teamCity"));
                nbaPlayer.setCountry(resultSet.getString("country"));
                nbaPlayer.setTeamConference(resultSet.getString("teamConference"));
                return nbaPlayer;
            }
        });

        return list;
    }

    /**
     * 根据ID查询nba_player表的数据
     *
     * @param id
     * @return
     */
    public NbaPlayer queryById(String id) {

        String sql = "select * from nba_player where id = ?";

        return jdbcTemplate.queryForObject(sql, new RowMapper<NbaPlayer>() {
            @Override
            public NbaPlayer mapRow(ResultSet resultSet, int i) throws SQLException {
                NbaPlayer nbaPlayer = new NbaPlayer();
                nbaPlayer.setCountryEn(resultSet.getString("countryEn"));
                nbaPlayer.setTeamName(resultSet.getString("teamName"));
                nbaPlayer.setTeamCity(resultSet.getString("teamCity"));
                nbaPlayer.setCountry(resultSet.getString("country"));
                nbaPlayer.setTeamConference(resultSet.getString("teamConference"));
                return nbaPlayer;
            }
        }, id);

    }
}
