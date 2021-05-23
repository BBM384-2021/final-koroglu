class LoginResponse {
  String name;
  String surname;
  String username;
  String email;
  String dateOfBirth;
  Null profilePicture;
  bool isBanned;
  bool isAnswered;
  bool isAdmin;
  bool isConfirmed;
  String token;

  LoginResponse(
      {this.name,
      this.surname,
      this.username,
      this.email,
      this.dateOfBirth,
      this.profilePicture,
      this.isBanned,
      this.isAnswered,
      this.isAdmin,
      this.isConfirmed,
      this.token});

  LoginResponse.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    surname = json['surname'];
    username = json['username'];
    email = json['email'];
    dateOfBirth = json['dateOfBirth'];
    profilePicture = json['profilePicture'];
    isBanned = json['isBanned'];
    isAnswered = json['isAnswered'];
    isAdmin = json['isAdmin'];
    isConfirmed = json['isConfirmed'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['surname'] = this.surname;
    data['username'] = this.username;
    data['email'] = this.email;
    data['dateOfBirth'] = this.dateOfBirth;
    data['profilePicture'] = this.profilePicture;
    data['isBanned'] = this.isBanned;
    data['isAnswered'] = this.isAnswered;
    data['isAdmin'] = this.isAdmin;
    data['isConfirmed'] = this.isConfirmed;
    data['token'] = this.token;
    return data;
  }
}
