package com.lvs.demo;

import com.lvs.demo.entity.Student;
import com.lvs.demo.rest.StudentErrorResponse;
import com.lvs.demo.rest.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define endpoint for "/students" - return a list of students
    private List<Student> theStudent;
    @PostConstruct
    public void loadData(){
           theStudent=new ArrayList<>();
            theStudent.add(new Student("Porrima","Patel"));
            theStudent.add(new Student("Mario","Rossi"));
            theStudent.add(new Student("Mary","Smith"));

    }



    @GetMapping("/students")
    public List<Student> getStudents(){
//        List<Student> theStudents=new ArrayList<>();
//        theStudents.add(new Student("Porrima","Patel"));
//        theStudents.add(new Student("Mario","Rossi"));
//        theStudents.add(new Student("Mary","Smith"));

        return theStudent;
    }

    // define endpoint or "/students/{studentId}" -> return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // just index into the list

        // check the studeentId again list.size

        if(studentId>=theStudent.size() || studentId<0){
            throw new StudentNotFoundException("Student ID not found - "+studentId);
        }
        return theStudent.get(studentId);
    }

    // Add an Exception Handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        // create a StudentErrorResponse
        StudentErrorResponse error =new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        //return ResponseEntity

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    // add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error =new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        //return ResponseEntity

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);


    }

}
