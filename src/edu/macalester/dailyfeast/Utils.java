package edu.macalester.dailyfeast;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 4/8/14.
 */
public class Utils {

    public Utils (){
    }

    static String[] extractData(String input){

        // 0:description 1:place 2:time
        String[] output = new String[3];

        String lookBehind = "(?<=(^| ))";
        String lookAhead = "(?=($| |\\.|\\!|\\?|\\,|\\;|\\:))";

        String foodRegex = lookBehind + "([Bb]reakfast|[Ll]unch|[Dd]inner|[Ss]nack[s]|[Bb]agels|[Rr]efreshments|[Pp]ie|[Cc](ake|offee|hocolate|andy)|[Ii]ce cream]|[Tt]ea|[Dd]rinks|[Pp]izza])" + lookAhead;
        String placeRegex = lookBehind + "([Oo]lin[ -][Rr]ice|[Cc]ampus[ -][Cc]enter|[Cc]arnegie|[Oo]ld [Mm]ain|[Hh]all [Oo]f [Ff]ame [Rr]oom|[Ff]rench [Mm]eadow [Bb]akery|[Mm]arkim [Hh]all|[Kk]agin|GSRC|[Dd]eWitt [Ww]allace [Ll]ibrary)" + lookAhead;
        String timeRegex = lookBehind + "([0-9]{1,2}(:[0-9][0-9])?(( a.m.)|( p.m.))?|(noon))(( to )|( ?- ?))([0-9]{1,2}(:[0-9][0-9])?(( a.m.)|( p.m.))?|(noon))|([0-9]{1,2}:[0-9]{2,2}( a.m.| p.m.)?)|([0-9]{1,2}( a.m.| p.m.))" + lookAhead;

        String sentenceEndRegex = ("(?<=[\\.\\!\\?]) (?=[A-Z0-9])");
        String sentences[] = input.split(sentenceEndRegex);
        Pattern FOOD_PATTERN = Pattern.compile(foodRegex);
        Pattern PLACE_PATTERN = Pattern.compile(placeRegex);
        Pattern TIME_PATTERN = Pattern.compile(timeRegex);

        for(String sentence : sentences){
            Matcher f = FOOD_PATTERN.matcher(sentence);
            Matcher p = PLACE_PATTERN.matcher(sentence);
            Matcher t = TIME_PATTERN.matcher(sentence);
            if (f.find()){
                output[0] = f.group(0);
            }if (p.find()){
                output[1] = p.group(0);
            }if (t.find()){
                output[2] = t.group(0);
            }
        }

        return output;

    }

}
