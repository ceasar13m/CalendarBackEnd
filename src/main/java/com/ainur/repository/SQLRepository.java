package com.ainur.repository;

import com.ainur.model.Event;
import com.ainur.model.EventDate;
import com.ainur.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;


@Repository
public class SQLRepository {
    Logger logger;
    DataSource dataSource;

    private static final String CREATE_DATABASE =
            "create database IF NOT EXISTS calendar;";

    private static final String USING_DATABASE =
            "use calendar;";

    private static final String CREATE_EVENTS_TABLE =
            "CREATE TABLE if not exists events " +
                    "(id bigint not null, " +
                    "eventDate datetime not null," +
                    "event text not null);";

    private static final String ADD_EVENT =
            "insert into events (id, eventDate, event) values (?, ?, ?);";


    private static final String GET_EVENTS =
            "select * from events " +
                    "where day(eventDate) = ? " +
                    "and month(eventDate) = ? " +
                    "and year (eventDate) = ?;";


    private static final String DELETE_EVENT =
            "delete from events " +
                    "where id = ?;";


    /**
     *
     * @param dataSource
     */
    @Autowired
    public SQLRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.logger = Logger.getLogger(SQLRepository.class.getName());
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_DATABASE);
            statement.executeUpdate(USING_DATABASE);
            statement.executeUpdate(CREATE_EVENTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     */
    public void addEvent(Event event) {
        logger.info("Add Event: \"" + event.getDescription() + "\" at: " + event.getDate());
        Date date = new Date(event.getDate().getTime());
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_EVENT);
            statement.setLong(1, event.getId());
            statement.setDate(2, date, java.util.Calendar.getInstance ());
            statement.setString(3, event.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param event
     */
    public void deleteEvent(Event event) {
        logger.info("Delete Event: \"" + event.getDescription() + "\" at: " + event.getDate());
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_EVENT);
            statement.setLong(1, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param eventDate
     * @return
     */
    public Events getEvents(EventDate eventDate) {
        logger.info("Get Events");
        LocalDate cvDate =
                Instant.ofEpochMilli(eventDate.getDate().getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_EVENTS);
            Events events = new Events();
            statement.setInt(1, cvDate.getDayOfMonth());
            statement.setInt(2, cvDate.getMonthValue());
            statement.setInt(3, cvDate.getYear());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong(1));
                event.setDate(resultSet.getDate(2));
                event.setDescription(resultSet.getString(3));
                events.addEvent(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


}