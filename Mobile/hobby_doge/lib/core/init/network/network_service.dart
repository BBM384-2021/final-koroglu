import 'dart:io';

import 'package:flutter/material.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'package:hobby_doge/view/all_clubs_screen/model/Club.dart';
import 'package:hobby_doge/view/sub_club_screen/model/SubClub.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../../view/club_screen/model/OneClub.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class NetworkService {
  static NetworkService _instance;
  static NetworkService get instance {
    return _instance ??= NetworkService._init();
  }

  NetworkService._init();

  Future<List<Club>> getAllClubs() async {
    var url = Uri.parse(ApplicationConstants.BASE_URL + "/api/v1/clubs/");
    print("asda");

    var response = await http.get(url, headers: {
      HttpHeaders.authorizationHeader: "",
      "content-type": "application/json",
      "accept": "application/json",
    });
    print(response.statusCode);
    if (response.statusCode == HttpStatus.ok) {
      Iterable iterableBody = json.decode(response.body);
      List<Club> allClubs = [];
      for (var item in iterableBody) {
        allClubs.add(Club.fromJson(item));
      }
      print(allClubs.isEmpty);
      return allClubs;
    } else {
      return null;
    }
  }

  Future<OneClub> getClubByID(int id) async {
    print("id " + id.toString());
    var url = Uri.parse(
        ApplicationConstants.BASE_URL + "/api/v1/clubs/" + id.toString());
    print("girer");
    var response = await http.get(url, headers: {
      HttpHeaders.authorizationHeader: "",
      "content-type": "application/json",
      "accept": "application/json",
    });
    print(json.decode(response.body));
    if (response.statusCode == HttpStatus.ok) {
      OneClub club = OneClub.fromJson(json.decode(response.body));

      print(club.description + " deneme");
      return club;
    } else {
      return null;
    }
  }

  Future<SubClub> getSubClubByID(int id) async {
    print("id " + id.toString());
    var url = Uri.parse(
        ApplicationConstants.BASE_URL + "/api/v1/subclubs/" + id.toString());
    print("girer");
    var response = await http.get(url, headers: {
      HttpHeaders.authorizationHeader: "",
      "content-type": "application/json",
      "accept": "application/json",
    });
    print(json.decode(response.body));
    if (response.statusCode == HttpStatus.ok) {
      SubClub subClub = SubClub.fromJson(json.decode(response.body));

      print(subClub.description + " deneme");
      return subClub;
    } else {
      return null;
    }
  }

  void joinSubClub(int id) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();

    print("id " + id.toString());
    var url = Uri.parse(ApplicationConstants.BASE_URL +
        "/api/v1/subclubs/" +
        id.toString() +
        "/join");
    print(prefs.getString('token'));
    var response = await http.post(url,
        body: jsonEncode(<String, int>{
          'subClubId ': id,
        }),
        headers: {
          HttpHeaders.authorizationHeader: "Bearer " + prefs.getString('token'),
          "content-type": "application/json",
          "accept": "application/json",
        });
    print((response.body));
  }
}
