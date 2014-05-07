package edu.macalester.dailyfeast; /**
 * Created by Andrew on 4/3/14.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.LinkedList;
import java.util.List;


public class Parse {

    private static String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";

    //TODO: Catch exceptions such as connection timeout, other errors, etc...
    public static List<PiperEvent> grabAndParse() throws Exception {

        String html = Jsoup.connect(url).get().toString();
        Document doc = Jsoup.parse(html);

        List<PiperText> piperTexts = new LinkedList<PiperText>();
        List<Element> fullElements = doc.select("div.story");

        for (Element element : fullElements){

            PiperText piperText = new PiperText();

            String title = element.select("h4").text();
            String body = element.children().not("h4").text();

            //TODO: deal with piperTexts w null bodies or titles!
            piperText.setTitle(title);
            piperText.setBody(body);

            piperTexts.add(piperText);
        }

        List<PiperEvent> piperEvents = new LinkedList<PiperEvent>();


        for (PiperText piperText:piperTexts ){
            String title = piperText.getTitle();
            String body = piperText.getBody();
            String[] data = Utils.extractData(body);
            PiperEvent piperEvent = new PiperEvent();
            boolean isToday = Utils.isToday(title,body);

            piperEvent.setTitle(title);
            piperEvent.setFoodDescription(data[0]);
            piperEvent.setPlace(data[1]);
            piperEvent.setTime(data[2]);
            piperEvent.setBody(body);

            if (piperEvent.getFoodDescription() != null && piperEvent.getFoodDescription().equals("") == false && isToday){
                piperEvents.add(piperEvent);
            }

        }

        return piperEvents;

    }
}