// To parse this JSON data, do
//
//     final member = memberFromJson(jsonString);

import 'dart:convert';

Member memberFromJson(String str) => Member.fromJson(json.decode(str));

String memberToJson(Member data) => json.encode(data.toJson());

class Member {
    Member({
        this.name,
        this.surname,
        this.username,
        this.profilePicture,
    });

    String name;
    String surname;
    String username;
    String profilePicture;

    factory Member.fromJson(Map<String, dynamic> json) => Member(
        name: json["name"],
        surname: json["surname"],
        username: json["username"],
        profilePicture: json["profilePicture"],
    );

    Map<String, dynamic> toJson() => {
        "name": name,
        "surname": surname,
        "username": username,
        "profilePicture": profilePicture,
    };
}
