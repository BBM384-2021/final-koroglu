class Club {
  final int id;
  final String name;
  final String description;

  Club(this.name, this.id, this.description);

  Club.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        id = json['id'] as int,
        description = json['description'];

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'description': description,
      };
}
