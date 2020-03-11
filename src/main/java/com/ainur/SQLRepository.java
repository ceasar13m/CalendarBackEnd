package com.ainur;

import com.ainur.model.Date;
import com.ainur.model.MonthEventsList;
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


    public SQLRepository() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_DATABASE);
            statement.executeUpdate(USING_DATABASE);
            statement.executeUpdate(CREATE_EVENTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
