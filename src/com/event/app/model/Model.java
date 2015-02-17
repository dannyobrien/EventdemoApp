package com.event.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Event> events;

    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            EventTableGateway gateway = new EventTableGateway(conn);
            
            this.events = gateway.getEvents();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Event> getEvents() {
        return new ArrayList<Event>(this.events);
    }

    public void addEvent(Event p) {
        this.events.add(p);
    }
    
    public boolean removeEvent(Event p) {
        return this.events.remove(p);
    }

    public Event findEventByStaffNumber(int staffNumber) {
        Event p = null;
        int i = 0;
        boolean found = false;
        while (i < this.events.size() && !found) {
            p = this.events.get(i);
            if (p.getStaffNumber() == staffNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
}
