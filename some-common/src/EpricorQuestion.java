import java.util.*;
import java.util.stream.Collectors;

class Student  {
    String name;
    int id;

    public Student(int id, String name){
        this.name = name;
        this.id = id;
    }
}

class Score  {
    int score;
    int id;

    public Score (int id, int score){
        this.score = score;
        this.id = id;
    }
}

public class EpricorQuestion {

    public static void main(String[] args) {

        Student one = new Student(1, "S1");
        Student two = new Student(2, "S2");
        Student three = new Student(3, "S3");

        Score scoreOne = new Score(1, 78);
        Score scoreTwo = new Score(2, 65);

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(one);
        studentList.add(two);
        studentList.add(three);

        List<Score> scoreList = new ArrayList<Score>();
        scoreList.add(scoreOne);
        scoreList.add(scoreTwo);
        Map<String, Integer> merit = new HashMap<String, Integer> ();
        List<Integer> idPresentInScores =
                scoreList.stream().map(sc->sc.id)
                        .collect(Collectors.toList());
//        List<Student> notPresentInScore =
//                studentList.stream()
//                        .filter(s-> !idPresentInScores.contains(s.id))
//                        .collect(Collectors.toList());
//
//        studentList.stream().forEach(s-> {
//            scoreList.stream().forEach(sc-> {
//                if(s.id == sc.id){
//                    merit.put(s.name, sc.score);
//                }
//            });
//        });
//
//        notPresentInScore.forEach(s->{
//            merit.put(s.name, 0);
//        });
//
//        notPresentInScore.forEach(s-> System.out.println(s.name));
//        merit.forEach((k, v) ->{
//            System.out.println(k  + " : " + v);
//        });


        // One More way :: Better
        studentList.stream().forEach(s-> {
            if(idPresentInScores.contains(s.id))
                merit.put(s.name, scoreList.stream().filter(sc-> sc.id == s.id).findFirst().get().score);
            else merit.put(s.name, 0);
        });
        merit.forEach((k, v) ->{
            System.out.println(k  + " : " + v);
        });
    }
}
