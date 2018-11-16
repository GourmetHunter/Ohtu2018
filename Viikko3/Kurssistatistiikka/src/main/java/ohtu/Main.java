package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url_student_info = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String url_course_info = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText_si = Request.Get(url_student_info).execute().returnContent().asString();
        String bodyText_ci = Request.Get(url_course_info).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText_si, Submission[].class);
        Course[] courses = mapper.fromJson(bodyText_ci, Course[].class);

        System.out.println("Opiskelijanumero: " + studentNr + "\n");

        for (Course course : courses) {

            int tehtavat = 0;
            int tunnit = 0;

            System.out.println(course);

            for (Submission submission : subs) {
                if (course.getName().equals(submission.getCourse())) {

                    submission.setMaxExercises(course.getExercises()[submission.getWeek()]);

                    System.out.print("\n" + submission);
                    tehtavat += submission.getExercises().length;
                    tunnit += submission.getHours();
                }
            }
            if (tehtavat + tunnit == 0) {
                System.out.println("Ei osallistumista\n");
            } else {
                System.out.println("\n\nYhteensa: " + tehtavat + "/" + course.getTotalExercises() + " tehtavaa ja " + tunnit + " tuntia\n");

                String url = "https://studies.cs.helsinki.fi/courses/" + course.getName() + "/stats";
                String bodyText = Request.Get(url).execute().returnContent().asString();

                JsonParser parser = new JsonParser();
                JsonObject parsittuData = parser.parse(bodyText).getAsJsonObject();

                int students = 0;
                double hours = 0;
                int eT = 0;

                for (int i = 1; i < course.getExercises().length; i++) {

                    JsonObject instance = parsittuData.getAsJsonObject("" + i);
                    if (instance != null) {
                        students += Integer.parseInt(instance.getAsJsonPrimitive("students").toString());
                        hours += Double.parseDouble(instance.getAsJsonPrimitive("hour_total").toString());
                        eT += Integer.parseInt(instance.getAsJsonPrimitive("exercise_total").toString());
                    }
                }
                //CourseInfo[] courseInfos = mapper.fromJson(array, CourseInfo[].class);

//                for(CourseInfo ci : courseInfos) {
//                    students += ci.getStudents();
//                    hours += ci.getHour_total();
//                    eT += ci.getExercise_total();
//                }
                System.out.println("Kurssilla yhteensa " + students + " palautusta, palautettuja tehtavia " + eT + " ja aikaa kaytetty " + hours + "\n");

            }
        }

    }
}
