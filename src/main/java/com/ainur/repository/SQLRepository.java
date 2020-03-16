package com.ainur.repository;

import com.ainur.model.Event;
import com.ainur.model.EventDate;
import com.ainur.model.TheDayEvents;
import com.ainur.model.TheMonthEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                    "(id int AUTO_INCREMENT not null PRIMARY KEY, " +
                    "eventDate datetime not null," +
                    "event text not null);";

    private static final String ADD_EVENT =
            "insert into events (eventDate, event) values (?, ?);";


    private static final String GET_THE_DAY_EVENTS =
            "select * from events " +
                    "where day (eventDate) = ? " +
                    "and month(eventDate) = ? " +
                    "and year (eventDate) = ?;";



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

    public void addEvent(TheDayEvents events) {
        logger.info("Add Events");
        try (Connection connection = dataSource.getConnection()) {

            for (Event event : events.getDescriptions()) {
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
        logger.info("Get Month Events");
        LocalDate cvDate =
                Instant.ofEpochMilli(eventDate.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        try (Connection connection = dataSource.getConnection()) {


            PreparedStatement statement = connection.prepareStatement(GET_THE_DAY_EVENTS);
            TheMonthEvents monthEvents = new TheMonthEvents();
            for (int i = 0; i < cvDate.lengthOfMonth(); i++) {
                java.util.Date date = new java.util.Date(cvDate.getYear(), cvDate.getMonthValue() - 1, i+1);
                statement.setInt(1, cvDate.getDayOfMonth());
                statement.setInt(2, cvDate.getMonthValue());
                statement.setInt(3, cvDate.getYear());
                ResultSet resultSet = statement.executeQuery();
                TheDayEvents theDayEvents = new TheDayEvents();
                theDayEvents.setDate(date);

                while (resultSet.next()) {
                    Event event = new Event();
                    event.setDate(date);
                    event.setDescription(resultSet.getString(3));
                    theDayEvents.addDayEvent(event);
                }
                monthEvents.addDayEvent(theDayEvents);
                cvDate = cvDate.plusDays(1);
            }


            return monthEvents;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


}
