import 'package:flutter/material.dart';

class AppColorScheme {
  static AppColorScheme _instance;
  static AppColorScheme get instance {
    if (_instance == null) _instance = AppColorScheme._init();

    return _instance;
  }

  AppColorScheme._init();
  final Color greenLight0 = Color(0xffB8E6CA);
  final Color greenLight1 = Color(0xff8FD6AB);
  final Color greenLight2 = Color(0xff6AC88F);
  final Color greenLight3 = Color(0xff45BA73);
  final Color greenLight4 = Color(0xff26342B);

  final Color grey = Color(0xff495A5F);
  final Color darkerGrey = Color(0xff26342B);
  final Color lightGrey = Color(0xff667E85);
  final Color boxBorderLightGrey = Color(0xffD6DBD8);
  final Color veryLightGrey = Color(0xffFAFAFA);
}
