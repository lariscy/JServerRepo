package com.github.lariscy.jserverrepo.server.dao;

import com.github.lariscy.jserverrepo.server.model.Node;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Steven Lariscy
 */
@Repository
public class NodeDaoMysql implements NodeDao {
    
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;
    
    public List<Node> getNodes(){
        return mysqlJdbcTemplate.query("SELECT * FROM node", new BeanPropertyRowMapper<>(Node.class));
    }
    
}
