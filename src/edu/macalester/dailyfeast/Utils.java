package edu.macalester.dailyfeast;

import java.util.ArrayList;
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

    static String lookBehind = "(?<=(^| ))";
    static String lookAhead = "(?=($| |\\.|\\!|\\?|\\,|\\;|\\:))";

    static String foodRegex = lookBehind + "([Bb]reakfast|[Ll]unch|[Dd]inner|[Ss]nack[s]|[Bb]agels|[Rr]efreshments|[Pp]ie|[Cc](ake|offee|hocolate|andy)|[Ii]ce cream]|[Tt]ea|[Dd]rinks|([Cc]affeinated)?[Bb]everages|[Cc]hips|[Pp]retzels|[Ll]icorice|[Aa]pples|[Vv]egetables|[Vv]eggies|[Ff]ruit|[Pp]izza])" + lookAhead;
    static String placeRegex = lookBehind + "([Oo]lin[ -][Rr]ice|[Oo]lri|OLRI|[Cc]ampus[ -][Cc]enter|[Tt]ea [Gg]arden|[Cc]arnegie|[Oo]ld [Mm]ain|[Hh]all [Oo]f [Ff]ame [Rr]oom|[Ff]rench [Mm]eadow [Bb]akery|(Russian|Japan|French|German|Spanish|Chinese) [Hh]ouse|[Mm]arkim [Hh]all|[Ss]tudent [Gg]allery|Koch [Ll]ounge|[Ww]eyerhaeuser ([Mm]emorial)? [Cc]hapel|[Kk]agin|GSRC|[Kk]irk|[Dd]upre|[Tt]urck|[Dd]oty|GDD|G.D.D.|[Hh]armon [Rr]oom|[Hh]ealth [Aa]nd [Ww]ellness [Cc]enter|[Ss]ustainability [Oo]ffice|[M]innehaha [Ff]alls|[Nn]eill [Hh]all|[Bb]igelow|[Ww]eyerhaeuser ([Bb]oardroom)?|[Ss]cience [Mm]useum [Oo]f [Mm]innesota|[Dd]eWitt [Ww]allace [Ll]ibrary)" + lookAhead;
    static String timeRegex = lookBehind + "([0-9]{1,2}(:[0-9][0-9])?(( a.m.)|( p.m.))?|(noon))(( to )|( ?- ?))([0-9]{1,2}(:[0-9][0-9])?(( a.m.)|( p.m.))?|(noon))|([0-9]{1,2}:[0-9]{2,2}( a.m.| p.m.)?)|([0-9]{1,2}( a.m.| p.m.)|noon)" + lookAhead;
    static String todayRegex = lookBehind + "[Tt]oday" + lookAhead;

    static String[] extractData(String input){

        // 0:description 1:place 2:time
        String[] output = new String[3];

        String sentenceEndRegex = ("(?<=[\\.\\!\\?]) (?=[A-Z0-9])");
        String sentences[] = input.split(sentenceEndRegex);
        Pattern FOOD_PATTERN = Pattern.compile(foodRegex);
        Pattern PLACE_PATTERN = Pattern.compile(placeRegex);
        Pattern TIME_PATTERN = Pattern.compile(timeRegex);

        Matcher f = FOOD_PATTERN.matcher(input);
        Matcher p = PLACE_PATTERN.matcher(input);
        Matcher t = TIME_PATTERN.matcher(input);
        List<String> foodsFound = new ArrayList<String>();

        while (f.find()){ // http://stackoverflow.com/questions/6020384/create-array-of-regex-matches
              foodsFound.add(f.group());
              output[0] = f.group(0);
        }if (p.find()){
              output[1] = p.group(0);
        }if (t.find()){
              output[2] = t.group(0);
        }

        String foodListString = "";
        for( int i = 0; i < foodsFound.size(); i++ ){
            String food = foodsFound.get(i);
            foodListString += food;
            if( i < (foodsFound.size()-1)){
                foodListString += ", ";
            }

        }
        output[0] = foodListString;



        return output;

    }

    static boolean isToday (String title, String body){

        String allText = title + " " + body;

        Pattern TODAY_PATTERN = Pattern.compile(todayRegex);
        Matcher o = TODAY_PATTERN.matcher(allText);

        return o.find();
    }

}
