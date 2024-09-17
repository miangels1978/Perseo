package lkp.Perseo.controllers;

import lkp.Perseo.models.Profile;
import lkp.Perseo.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(path = "/profileAll")
    public List<Profile> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping(path = "/profileId/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        return profileService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/newProfile")
    public Profile createProfile(@RequestBody Profile profile) {
        return profileService.save(profile);
    }

    @PutMapping(path ="/updateProfile/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profileDetails) {
        return profileService.findById(id)
                .map(profile -> {
                    profile.setCompany(profileDetails.getCompany());
                    profile.setYear(profileDetails.getYear());
                    profile.setDuration(profileDetails.getDuration());
                    profile.setExperience(profileDetails.getExperience());
                    return ResponseEntity.ok(profileService.save(profile));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ="/delete/{id}")
    public ResponseEntity<Object> deleteProfile(@PathVariable Long id) {
        return profileService.findById(id)
                .map(profile -> {
                    profileService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(path ="/deleteAll")
    public void deleteAllPets() {
        profileService.deleteAllProfile();
    }
}
