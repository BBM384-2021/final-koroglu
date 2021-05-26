package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.dto.mapper.ClubMapper;
import org.koroglu.hobbydoge.dto.mapper.ListClubsMapper;
import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.dto.model.ListClubsDTO;
import org.koroglu.hobbydoge.dto.model.ReviewDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestClubNameAlreadyExistException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

  private final ClubRepository clubRepository;
  private final ReviewService reviewService;
  private final UserRepository userRepository;

  @Override
  public ClubDTO get(Long id) {

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    List<ReviewDTO> reviews = reviewService.getClubReviews(id);

    return ClubMapper.toClubDTO(club).setReviews(reviews);

  }

  @Override
  public List<ListClubsDTO> getClubs(int offset, int limit) {

    return clubRepository.getAllClubs(offset, limit).stream()
            .map(ListClubsMapper::toListClubsDTO).collect(Collectors.toList());

  }

  @Override
  public List<ClubDTO> search(String q) {

    return clubRepository.findByNameContainingIgnoreCase(q).stream()
            .map(ClubMapper::toClubDTO).collect(Collectors.toList());

  }

  @Override
  public ClubDTO create(NewClubRequest newClubRequest) {

    Optional<Club> optionalClub = clubRepository.findByName(newClubRequest.getName());
    if (optionalClub.isPresent()) {
      throw new RestClubNameAlreadyExistException();
    }

    Club club = new Club(
            newClubRequest.getName(),
            newClubRequest.getDescription(),
            newClubRequest.getPicture()
    );

    return ClubMapper.toClubDTO(clubRepository.save(club)).setReviews(new ArrayList<>());
  }

  public ClubDTO update(Long id, ClubRequest clubRequest) {

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    if (clubRequest.getName() != null) {
      club.setName(clubRequest.getName());
    }

    if (clubRequest.getDescription() != null) {
      club.setDescription(clubRequest.getDescription());
    }

    if (clubRequest.getPicture() != null) {
      club.setPicture(clubRequest.getPicture());
    }

    return ClubMapper.toClubDTO(clubRepository.save(club));
  }

  @Override
  public HashMap<String, String> delete(Long id) {


    Optional<Club> optionalClub = clubRepository.findById(id);

    if (!optionalClub.isPresent()) {
      throw new RestClubDoesNotExistException();
    }

    HashMap<String, String> response = new HashMap<String, String>();
    response.put("message", String.format("Club %s deleted succesfully.", optionalClub.get().getName()));

    clubRepository.delete(optionalClub.get());

    return response;
  }

  @Override
  public String join(Long id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //TODO: Add interest point check here.

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    user.getEnrolledClubs().add(club);

    club.getMembers().add(user);

    userRepository.save(user);

    clubRepository.save(club);

    return String.format("User with ID: %d joined to the %s club successfully.", user.getId(), club.getName());

  }

  @Override
  public String leave(Long id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    club.getMembers().remove(user);

    user.getEnrolledClubs().remove(club);

    userRepository.save(user);

    clubRepository.save(club);

    return String.format("User with ID: %d left the %s club successfully.", user.getId(), club.getName());

  }


}
