class SubClub {
  int id;
  String name;
  String picture;
  Null rating;

  SubClub({this.id, this.name, this.picture, this.rating});

  SubClub.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    picture = json['picture'];
    rating = json['rating'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['name'] = this.name;
    data['picture'] = this.picture;
    data['rating'] = this.rating;
    return data;
  }
}