package com.ainur.repository;

import com.ainur.model.Event;
import com.ainur.model.EventDate;
import com.ainur.model.TheDayEvents;
import com.ainur.model.TheMonthEvents;
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

    private static final String ADD_EVENT =
            "insert into events (eventDate, event) values (?, ?);";


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

    public void addEvent(TheDayEvents events) {
        System.out.println("AddEvent");
        try (Connection connection = dataSource.getConnection()) {

            for (Event event: events.getDescriptions()) {
                PreparedStatement statement = connection.prepareStatement(ADD_EVENT);
                statement.setDate(1, new java.sql.Date(events.getDate().getTime()));
                statement.setString(2, event.getDescription());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public TheMonthEvents getTheMonthEvents(EventDate eventDate) {

        System.out.println("get Events");
        return null;
    }


}
