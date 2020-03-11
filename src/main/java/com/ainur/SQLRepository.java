package com.ainur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;


@Repository
public class SQLRepository {

    DataSource dataSource;

    private static final String CREATE_DATABASE =
            "create database IF NOT EXISTS calendar;";

    private static final String USING_DATABASE =
            "use calendar;";

    private static final String CREATE_EVENTS_TABLE =
            "CREATE TABLE if not exists events " +
                    "(id int AUTO_INCREMENT not null PRIMARY KEY, " +
                    "eventDate datetime not null," +
                    "event text not null);";


    @Autowired
    public SQLRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_DATABASE);
            statement.executeUpdate(USING_DATABASE);
            statement.executeUpdate(CREATE_EVENTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Hello() {
        System.out.println("Hello");
    }


}
