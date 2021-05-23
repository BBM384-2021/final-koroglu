package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewSubClubRequest;
import org.koroglu.hobbydoge.controller.request.SubClubRequest;
import org.koroglu.hobbydoge.dto.mapper.SubClubMapper;
import org.koroglu.hobbydoge.dto.model.SubClubDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSubClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSubClubNameAlreadyExistException;
import org.koroglu.hobbydoge.model.SubClub;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.SubClubRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubClubServiceImpl implements SubClubService {
    private final SubClubRepository subClubRepository;
    private final UserRepository userRepository;
    @Override
    public SubClubDTO get(Long id){
        SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);
        return SubClubMapper.toSubClubDTO(subClub);

    }
    @Override
    public List<SubClubDTO> getSubClub(int offset, int limit) {
        return subClubRepository.getAllSubClubs(offset,limit).stream().map(SubClubMapper::toSubClubDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubClubDTO> search(String q) {
        return subClubRepository.findByNameContainingIgnoreCase(q).stream().map(SubClubMapper::toSubClubDTO).collect(Collectors.toList());
    }

    @Override
    public SubClubDTO create(NewSubClubRequest newSubClubRequest) {
        Optional<SubClub> optionalSubClub = subClubRepository.findByName(newSubClubRequest.getName());
        if(optionalSubClub.isPresent()){
            throw new RestSubClubNameAlreadyExistException();
        }
        SubClub subClub = new SubClub(
                newSubClubRequest.getName(), newSubClubRequest.getDescription(), newSubClubRequest.getDescription()
        );
        return SubClubMapper.toSubClubDTO(subClubRepository.save(subClub));
    }

    @Override
    public SubClubDTO update(Long id, SubClubRequest subClubRequest) {
        Optional<SubClub> optionalSubClub = subClubRepository.findById(id);
        if (optionalSubClub.isEmpty()) {
            throw new RestClubDoesNotExistException();
        }
        SubClub subClub = optionalSubClub.get();

        if (subClubRequest.getName() != null) {
            subClub.setName(subClubRequest.getName());
        }

        if (subClubRequest.getDescription() != null) {
            subClub.setDescription(subClubRequest.getDescription());
        }

        if (subClubRequest.getPicture() != null) {
            subClub.setPicture(subClubRequest.getPicture());
        }

        return SubClubMapper.toSubClubDTO(subClubRepository.save(subClub));

    }

    @Override
    public HashMap<String, String> delete(Long id) {
        Optional<SubClub> optionalSubClub = subClubRepository.findById(id);

        if (optionalSubClub.isEmpty()) {
            throw new RestClubDoesNotExistException();
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("message", String.format("Sub Club %s deleted succesfully.", optionalSubClub.get().getName()));
        subClubRepository.delete(optionalSubClub.get());
        return response;
    }

    @Override
    public String join(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        SubClub subClub = subClubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

        subClub.getMembers().add(user);

        subClubRepository.save(subClub);

        return String.format("User with ID: %d joined to the %s sub club successfully.", user.getId(), subClub.getName());
    }

    @Override
    public String leave(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SubClub subClub = subClubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);
        subClub.getMembers().remove(user);

        subClubRepository.save(subClub);

        return String.format("User with ID: %d left the %s sub club successfully.", user.getId(), subClub.getName());

    }


}
