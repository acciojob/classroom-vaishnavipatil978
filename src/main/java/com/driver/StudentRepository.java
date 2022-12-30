package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class StudentRepository {

    HashMap <String,Student> studentDb;
    HashMap <String,Teacher> teacherDb;
    HashMap <String,List<String>> studentTeacherPair;

    public StudentRepository(){
        studentDb = new HashMap<>();
        teacherDb = new HashMap<>();
        studentTeacherPair = new HashMap<>();
    }

    void addStudent(Student student){ studentDb.put(student.getName(), student); }

    void addTeacher(Teacher teacher){ teacherDb.put(teacher.getName(), teacher); }

    void addStudentTeacherPair(String student,String teacher){
        if(studentDb.containsKey(student) && teacherDb.containsKey(teacher)){
            List<String> students = new ArrayList<>();

            if(studentTeacherPair.containsKey(teacher)) students=studentTeacherPair.get(teacher);

            students.add(student);
            studentTeacherPair.put(teacher,students);
        }
    }

    Student getStudent(String name){
        if(studentDb.containsKey(name)) return studentDb.get(name);

        return null;
    }

    Teacher getTeacher(String name){
        if(teacherDb.containsKey(name)) return teacherDb.get(name);

        return null;
    }

    List<String> getStudentsByTeacher(String teacher){
        if(studentTeacherPair.containsKey(teacher)){
            return studentTeacherPair.get(teacher);
        }
        return null;
    }

    List<String> getAllStudents(){
        return new ArrayList<>(studentDb.keySet());
    }

    void deleteTeacher(String teacher){
        try{
            teacherDb.remove(teacher);

            for(String student : studentTeacherPair.get(teacher)){
                  if(studentDb.containsKey(student)) studentDb.remove(student);
            }

            studentTeacherPair.remove(teacher);

        }
        catch(Exception e){

        }
    }

    void deleteAllTeachers(){
        try{
            teacherDb.clear();

            List<String> students = new ArrayList<>();

            for(String teacher : studentTeacherPair.keySet()){
                for(String student: studentTeacherPair.get(teacher)) students.add(student);
            }

            for(String student : students){
                if(studentDb.containsKey(student)) studentDb.remove(student);
            }

            studentTeacherPair.clear();

        }
        catch (Exception e){

        }
    }

}
