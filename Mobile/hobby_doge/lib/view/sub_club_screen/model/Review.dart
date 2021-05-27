class Review {
  int id;
  String comment;
  double rating;
  String username;

  Review({this.id, this.comment, this.rating, this.username});

  Review.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    comment = json['comment'];
    rating = json['rating'];
    username = json['username'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['comment'] = this.comment;
    data['rating'] = this.rating;
    data['username'] = this.username;
    return data;
  }
}
