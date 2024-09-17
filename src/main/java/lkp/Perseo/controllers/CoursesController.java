package lkp.Perseo.controllers;

import lkp.Perseo.models.Courses;
import lkp.Perseo.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")

public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    @GetMapping (path = "/getAll")
    public List<Courses> getAllCourses() {
        return coursesService.findAll();
    }

    @GetMapping(path ="/getCourse/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long id) {
        return coursesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping (path = "/createCourse")
    public Courses createCourse(@RequestBody Courses course) {
        return coursesService.save(course);
    }

    @PutMapping(path ="/updateCourse/{id}")
    public ResponseEntity<Courses> updateCourse(@PathVariable Long id, @RequestBody Courses courseDetails) {
        return coursesService.findById(id)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    course.setDescription(courseDetails.getDescription());
                    return ResponseEntity.ok(coursesService.save(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(path ="/delete/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        return coursesService.findById(id)
                .map(course -> {
                    coursesService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping (path = "/deleteAll")
    public void deleteAllDonations() {
        coursesService.deleteAllCourse(); }
}
