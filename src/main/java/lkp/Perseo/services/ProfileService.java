package lkp.Perseo.services;

import lkp.Perseo.models.Profile;
import lkp.Perseo.repositories.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private IProfileRepository iProfileRepository;

    public List<Profile> findAll() {
        return iProfileRepository.findAll();
    }

    public Optional<Profile> findById(Long id) {
        return iProfileRepository.findById(id);
    }

    public Profile save(Profile profile) {
        return iProfileRepository.save(profile);
    }

    public void deleteById(Long id) {
        iProfileRepository.deleteById(id);
    }

    public void deleteAllProfile() {
        iProfileRepository.deleteAll();
    }
}
