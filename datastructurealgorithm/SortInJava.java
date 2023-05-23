import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortInJava {

    public static class Student implements Comparable
    {
        public int age;
        public String name;
        public Student(String name, int age){
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return "{name: " + this.name + ", age:" + this.age + "}";
        }

         @Override
         public int compareTo(Object other) {
             return this.name.compareTo(((Student)other).name);
         }
    }


    public static void main(String[] args) {

        // =================== 1. Using Arrays.sort =======================
        // #1.1: Sort an integer array
        int[] integerArray = {3,2,1};
        Arrays.sort(integerArray);
        for (int i = 0; i < integerArray.length; i++) {
            System.out.print(integerArray[i] + " ");
        }
        System.out.println();

        // #1.2: Sort a string object array
        String[] stringArray = {"C2", "C1", "C012"};
        Arrays.sort(stringArray);
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print(stringArray[i] + " ");
        }
        System.out.println();

        // #1.3: Sort an Object array
        System.out.println("Compare by name: ");
        Student[] students = {new Student("Jim", 8), new Student("Jack", 10)};
        Arrays.sort(students);
        for (Student student : students) {
            System.out.println(student);
        }

        // #1.4 New sorting by age by using Comparator
        System.out.println("Compare by age:");
        Arrays.sort(students, new Comparator<Student>(){
			@Override
			public int compare(SortInJava.Student before, SortInJava.Student after) {
                // compare by age
                if(before.age == after.age)
                    return 0;
                if(before.age > after.age)
                    return 1;
				return -1;
			}
        });
        for (Student student : students) {
            System.out.println(student);
        }



        // #1.5: Sort an integer array
        System.out.println("Sort by index: ");
        int[] descArray = {7, 6, 5, 4, 3, 2, 1};
        Arrays.sort(descArray, 3, 6);
        
        for (int i = 0; i < descArray.length; i++) {
            System.out.print(descArray[i] + " ");
        }
        System.out.println();
    
        // =================== 2. Using Collections.sort =======================

        // 2.1 Using with ArrayList
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Will", 8));
        studentList.add(new Student("Bob", 10));
        System.out.println("2.1 Compare by default comparable (by Name): ");
        Collections.sort(studentList);
        for (Student student : studentList) {
            System.out.println(student);
        }

        // 2.2 Using Comparator
        Collections.sort(studentList, new Comparator<Student>(){
            @Override
            public int compare(SortInJava.Student before, SortInJava.Student after) {
                if(before.age == after.age) 
                    return 0;
                if(before.age > after.age)
                    return 1; // before consider is a bigger one
                return -1;
            }
        });
        System.out.println("2.2 Compare by using Comparator (by Age): ");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
