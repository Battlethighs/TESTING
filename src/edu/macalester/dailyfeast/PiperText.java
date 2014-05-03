package edu.macalester.dailyfeast;

/**
 * Created by Andrew on 4/7/14.
 */
public class PiperText {

    private String title = null;
    private String body = null;


    public void setTitle (String input){
        this.title = input;
    }
    public void setBody (String input){
        this.body = input;
    }
    public String getTitle (){
        return title;
    }
    public String getBody (){
        return body;
    }

    public PiperText () {

    }
}
