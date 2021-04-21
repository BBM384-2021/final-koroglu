import 'package:flutter/material.dart';

SnackBar warningSnackBar(String text) {
  return SnackBar(
    content: Row(
      children: [
        Icon(
          Icons.priority_high,
          color: Colors.white,
        ),
        Expanded(child: Text(text)),
      ],
    ),
    backgroundColor: Colors.red,
  );
}
