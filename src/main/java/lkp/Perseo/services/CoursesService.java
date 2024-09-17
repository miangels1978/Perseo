package lkp.Perseo.services;

import lkp.Perseo.models.Courses;
import lkp.Perseo.repositories.ICoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    @Autowired
    private ICoursesRepository iCoursesRepository;

    public List<Courses> findAll() {
        return iCoursesRepository.findAll();
    }

    public Optional<Courses> findById(Long id) {
        return iCoursesRepository.findById(id);
    }

    public Courses save(Courses course) {
        return iCoursesRepository.save(course);
    }

    public void deleteById(Long id) {
        iCoursesRepository.deleteById(id);
    }
}
