package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week, int hours, int[] exercises, String course) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
        this.course = course;
    }

    public int getWeek() {
        return week;
    }
    
    public int getHours() {
        return hours;
    }
    
    public int[] getExercises() {
        return exercises;
    }
    
    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        String exerciseNumbers = "";
        for(int exercise : exercises) {
            exerciseNumbers += exercise + ", ";
        }
        return course + ", viikko " + week + " tehtyja tehtavia yhteensa " + exercises.length + " joihin kului aikaa " + hours + " tuntia. Tehdyt tehtavat: " + exerciseNumbers;
    }
    
}