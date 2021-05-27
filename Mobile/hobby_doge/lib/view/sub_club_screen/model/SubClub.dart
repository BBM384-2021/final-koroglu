// To parse this JSON data, do
//
//     final subClub = subClubFromJson(jsonString);

import 'dart:convert';

import 'package:hobby_doge/view/sub_club_screen/model/Review.dart';

SubClub subClubFromJson(String str) => SubClub.fromJson(json.decode(str));

String subClubToJson(SubClub data) => json.encode(data.toJson());

class SubClub {
  SubClub({
    this.id,
    this.name,
    this.description,
    this.picture,
    this.rating,
    this.members,
    this.events,
    this.reviews,
    this.adminRequests,
  });

  int id;
  String name;
  String description;
  String picture;
  double rating;
  List<dynamic> members;
  List<dynamic> events;
  List<dynamic> reviews;
  List<dynamic> adminRequests;

  factory SubClub.fromJson(Map<String, dynamic> json) => SubClub(
        id: json["id"],
        name: json["name"],
        description: json["description"],
        picture: json["picture"],
        rating: json["rating"],
        members: List<dynamic>.from(json["members"].map((x) => x)),
        events: List<dynamic>.from(json["events"].map((x) => x)),
        reviews:
            List<dynamic>.from(json["reviews"].map((x) => Review.fromJson(x))),
        adminRequests: List<dynamic>.from(json["adminRequests"].map((x) => x)),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
        "description": description,
        "picture": picture,
        "rating": rating,
        "members": List<dynamic>.from(members.map((x) => x)),
        "events": List<dynamic>.from(events.map((x) => x)),
        "reviews": List<dynamic>.from(reviews.map((x) => x)),
        "adminRequests": List<dynamic>.from(adminRequests.map((x) => x)),
      };
}
