package edu.macalester.dailyfeast;

public class PiperEvent {

    private boolean hasFood;
    private String title = "";
    private String body = "";
    private String time = "";
    private String place = "";
    private String foodDescription = "";

    public PiperEvent(){

    }

    public boolean getHasFood() { return hasFood; }
    public String getTitle(){
        return title;
    }
    public String getTime(){
        return time;
    }
    public String getPlace(){
        return place;
    }
    public String getFoodDescription(){
        return foodDescription;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }
}