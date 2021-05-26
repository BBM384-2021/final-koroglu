// To parse this JSON data, do
//
//     final OneClub = OneClubFromJson(jsonString);

import 'dart:convert';

import 'package:hobby_doge/view/club_screen/model/Review.dart';
import 'package:hobby_doge/view/club_screen/model/SubClub.dart';

OneClub clubFromJson(String str) => OneClub.fromJson(json.decode(str));

String clubToJson(OneClub data) => json.encode(data.toJson());

class OneClub {
  OneClub({
    this.id,
    this.name,
    this.description,
    this.picture,
    this.rating,
    this.members,
    this.subClubs,
    this.reviews,
  });

  int id;
  String name;
  String description;
  String picture;
  double rating;
  List<dynamic> members;
  List<dynamic> subClubs;
  List<dynamic> reviews;

  factory OneClub.fromJson(Map<String, dynamic> json) => OneClub(
        id: json["id"],
        name: json["name"],
        description: json["description"],
        picture: json["picture"],
        rating: json["rating"],
        members: List<dynamic>.from(json["members"].map((x) => x)),
        subClubs: List<dynamic>.from(
            json["subClubs"].map((x) => SubClub.fromJson(x))),
        reviews:
            List<dynamic>.from(json["reviews"].map((x) => Review.fromJson(x))),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
        "description": description,
        "picture": picture,
        "rating": rating,
        "members": List<dynamic>.from(members.map((x) => x)),
        "subClubs": List<dynamic>.from(subClubs.map((x) => x)),
        "reviews": List<dynamic>.from(reviews.map((x) => x)),
      };
}
