package com.mindflow.restful.jersey.service;


import com.mindflow.restful.jersey.dao.DaoMgr;
import com.mindflow.restful.jersey.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class useSql {

//    @Autowired
//    private DaoMgr daoMgr;
    static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
        "classpath:META-INF/spring/applicationContext.xml");
    // DataSource datasource = ctx.getBean("dataSource",DataSource.class);
    static JdbcTemplate template = ctx.getBean("jdbcTemplate",JdbcTemplate.class);

    public static void main(String[] args) {
        /*
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/spring/applicationContext.xml");
        // DataSource datasource = ctx.getBean("dataSource",DataSource.class);
        JdbcTemplate template = ctx.getBean("jdbcTemplate",JdbcTemplate.class);
        */
        //DaoMgr daoMgr = ctx.getBean("DaoMgr",DaoMgr.class);
        //JdbcTemplate template = new JdbcTemplate(datasource);
        String sql = "select * from user";
        //List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        System.out.println("Consultando tabla User");
        List<User> userList = findAll();
        for (User user : userList) {
            System.out.println(user.getName());
            System.out.println(user.getAge());
        }
        // ahora insertando en tabla User
        System.out.println("Insertando en tabla User");
        User user = new User();
        user.setName("Isabel2");
        user.setAge(25);
        add(user);

        ctx.close();
    }

    private static List<User> findAll() {
        String sql = "select * from user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
    private static void add(User user) {
        String sql = "insert into user (name, age) values (?, ?)";
        template.update(sql, user.getName(), user.getAge());
    }


}
