package edu.macalester.dailyfeast;

import java.util.List;

/**
 * Created by Andrew on 4/14/14.
 */
public class RunParser {

    public static void main(String[] args) throws Exception{

        List<PiperEvent> events = Parse.grabAndParse();

        for (PiperEvent event : events){
            System.out.println("TITLE: " + event.getTitle() + "\n");
            System.out.println("DESCRIPTION: " + event.getFoodDescription() + "\n");
            System.out.println("TIME: " + event.getTime() + "\n");
            System.out.println("PLACE: " + event.getPlace() + "\n");
            System.out.println("----------------------------------");
        }
        System.out.println(events.size());


    }

}
