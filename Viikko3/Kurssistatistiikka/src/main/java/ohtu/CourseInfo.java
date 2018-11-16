/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author Olli K. Kärki
 */
public class CourseInfo {
    
    private int students;
    private int hour_total;
    private double exercise_total;
    private int[] hours;
    
    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getHour_total() {
        return hour_total;
    }

    public void setHour_total(int hour_total) {
        this.hour_total = hour_total;
    }

    public double getExercise_total() {
        return exercise_total;
    }

    public void setExercise_total(double exercise_total) {
        this.exercise_total = exercise_total;
    }
    
}
