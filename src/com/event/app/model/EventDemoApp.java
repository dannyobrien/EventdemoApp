package com.event.app.model;

import java.util.List;
import java.util.Scanner;

public class EventDemoApp {
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        Model model = Model.getInstance();
        
        Event p;
        
        int opt;
        do {
            System.out.println("1. Create new Event");
            System.out.println("2. Delete existing Event");
            System.out.println("3. View all Events");
            System.out.println("4. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            System.out.println("You chose option " + opt);
            switch (opt) {
                case 1: {
                    System.out.println("Creating event");
                    p = readEvent(keyboard);
                    model.addEvent(p);
                    
                    break;
                }
                case 2: {
                    System.out.println("Deleting event");
                    deleteEvent(keyboard, model);
                    break;
                }
                case 3: {
                    System.out.println("Viewing events");
                    viewEvents(model);
                    break;
                }
            }
        }
        while (opt != 4);
        System.out.println("Goodbye");
    }
    
    private static Event readEvent(Scanner keyb) {
        String name, email, mobile, date, endDate;
        int attendees;
        String line;

        name = getString(keyb, "Enter name: ");
        email = getString(keyb, "Enter location: ");
        mobile = getString(keyb, "Enter mobile: ");
        date = getString(keyb, "Enter date: ");
        endDate = getString(keyb, "Enter end date: ");
        line = getString(keyb, "Enter attendees: ");
        attendees = Integer.parseInt(line);

        Event p = 
                new Event(name, email, mobile, 
                        attendees, date, endDate);
        
        return p;
    }
    
    private static void deleteEvent(Scanner kb, Model m) {
        System.out.print("Enter the staff number of the event to delete:");
        int attendees = Integer.parseInt(kb.nextLine());
        Event p;

        p = m.findEventByStaffNumber(attendees);
        if (p != null) {
            if (m.removeEvent(p)) {
                System.out.println("Event deleted");
            }
            else {
                System.out.println("Event not deleted");
            }
        }
        else {
            System.out.println("Event not found");
        }
    }
    
    private static void viewEvents(Model model) {
        List<Event> events = model.getEvents();
        for (Event pr : events) {
            System.out.println("Name: " + pr.getName());
        }
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }
}