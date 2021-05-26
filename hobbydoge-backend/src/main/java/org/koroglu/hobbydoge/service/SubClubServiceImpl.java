package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewSubClubRequest;
import org.koroglu.hobbydoge.controller.request.SubClubRequest;
import org.koroglu.hobbydoge.dto.mapper.SubClubMapper;
import org.koroglu.hobbydoge.dto.model.ReviewDTO;
import org.koroglu.hobbydoge.dto.model.SubClubDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSubClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSubClubNameAlreadyExistException;
import org.koroglu.hobbydoge.exception.RestUserNotFoundException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.SubClub;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.SubClubRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class SubClubServiceImpl implements SubClubService {

  private final ClubRepository clubRepository;
  private final SubClubRepository subClubRepository;
  private final UserRepository userRepository;
  private final ReviewService reviewService;

  @Override
  public SubClubDTO get(Long id) {
    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);

    List<ReviewDTO> reviews = reviewService.getSubClubReviews(id);

    return SubClubMapper.toSubClubDTO(subClub).setReviews(reviews);
  }

  @Override
  public SubClubDTO create(NewSubClubRequest newSubClubRequest) {

    Club club = clubRepository.findById(newSubClubRequest.getClubId()).orElseThrow(RestClubDoesNotExistException::new);

    for (SubClub subClub : club.getSubClubs()) {
      if (subClub.getName().equals(newSubClubRequest.getName())) {
        throw new RestSubClubNameAlreadyExistException();
      }
    }

    SubClub subClub = new SubClub(newSubClubRequest.getName(), newSubClubRequest.getDescription(), newSubClubRequest.getPicture(), club);

    subClubRepository.save(subClub);

    club.getSubClubs().add(subClub);

    clubRepository.save(club);

    return SubClubMapper.toSubClubDTO(subClub);
  }

  @Override
  public SubClubDTO update(Long id, SubClubRequest subClubRequest) {

    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);


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

    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);

    HashMap<String, String> response = new HashMap<>();

    response.put("message", String.format("Sub Club %s deleted successfully.", subClub.getName()));

    subClubRepository.delete(subClub);

    return response;
  }

  @Override
  public String join(Long id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);

    Club club = clubRepository.findById(subClub.getClub().getId()).orElseThrow(RestClubDoesNotExistException::new);

    //TODO: Add new exception for user not in club.
    if (!club.getMembers().contains(user)) throw new RestUserNotFoundException();

    //TODO: Add user in subclub check.

    subClub.getMembers().add(user);

    subClubRepository.save(subClub);

    user.getEnrolledSubClubs().add(subClub);

    userRepository.save(user);

    return String.format("User with ID: %d joined to the %s sub club successfully.", user.getId(), subClub.getName());
  }

  @Override
  public String leave(Long id) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);

    subClub.getMembers().remove(user);

    subClubRepository.save(subClub);

    user.getEnrolledSubClubs().remove(subClub);

    userRepository.save(user);

    return String.format("User with ID: %d left the %s sub club successfully.", user.getId(), subClub.getName());

  }

  @Override
  public String adminRequest(Long id) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    SubClub subClub = subClubRepository.findById(id).orElseThrow(RestSubClubDoesNotExistException::new);

    //TODO: Add an exception for this.
    if (!subClub.getMembers().contains(user)) throw new RestUserNotFoundException();

    subClub.getAdminRequests().add(user);

    subClubRepository.save(subClub);

    return "Request added.";

  }


}
