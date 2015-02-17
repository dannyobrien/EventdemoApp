package com.event.app.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventTableGateway {
    private static final String TABLE_NAME = "events";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_STAFF_NUMBER = "staffNumber";
    private static final String COLUMN_SKILLS = "skills";
    private static final String COLUMN_SALARY = "salary";

    private Connection mConnection;

    public EventTableGateway(Connection connection) {
        mConnection = connection;
    }
    public List<Event> getEvents() throws SQLException {
        String query;       // the SQL query to execute
        Statement stmt;     // the java.sql.Statement object used to execute the
                            // SQL query
        ResultSet rs;       // the java.sql.ResultSet representing the result of
                            // SQL query 
        List<Event> events;   // the java.util.List containing the Event objects
                            // created for each row in the result of the query
        int id;             // the id of a event
        String name, email, mobile, skills;
        int staffNumber;
        double salary;
        Event p;       // a Event object created from a row in the result of
                            // the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Event object, which is inserted into an initially
        // empty ArrayList
        events = new ArrayList<Event>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            email = rs.getString(COLUMN_EMAIL);
            mobile = rs.getString(COLUMN_MOBILE);
            staffNumber = rs.getInt(COLUMN_STAFF_NUMBER);
            skills = rs.getString(COLUMN_SKILLS);
            salary = rs.getDouble(COLUMN_SALARY);

            p = new Event(name, email, mobile, staffNumber, skills, salary);
            events.add(p);
        }

        return events;
    }
    
}
