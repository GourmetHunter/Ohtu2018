package ohtu;

public class Submission {

    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
    
    private int me;
    
    public void setMaxExercises(int me) {
        this.me = me;
    }

    @Override
    public String toString() {
        String exerciseNumbers = "";
        for (int exercise : exercises) {
            exerciseNumbers += exercise + ", ";
        }
        return "viikko " + week + ":\n tehtyja tehtavia yhteensa " + exercises.length + "/" + me +", joihin kului aikaa " + hours + " tuntia. Tehdyt tehtavat: " + exerciseNumbers;
    }

}
