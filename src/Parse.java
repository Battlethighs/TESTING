/**
 * Created by Andrew on 4/3/14.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;


public class Parse {

    private static String url = "http://webapps.macalester.edu/dailypiper/dailypiper-portal.cfm?expanded=true";

    public static void main(String[] args) throws Exception {

        String html = Jsoup.connect(url).get().toString();
        Document doc = Jsoup.parse(html);

        List<PiperText> piperTexts = new LinkedList<PiperText>();
        List<Element> fullElements = doc.select("div.story");

        for (Element element : fullElements){

            PiperText piperText = new PiperText();

            String title = element.select("h4").text();
            Elements something = element.select("p");
            String body = element.select(":not(h4)").text();

            piperText.setTitle(title);
            piperText.setBody(body);

            piperTexts.add(piperText);
        }

        for (PiperText piperText:piperTexts){
            System.out.println("--------------------\n");
            System.out.println("TITLE: " + piperText.getTitle());
            System.out.println("BODY: " + piperText.getBody());

        }


    }
}
