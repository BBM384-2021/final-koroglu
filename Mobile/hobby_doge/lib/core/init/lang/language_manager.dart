import 'package:flutter/material.dart';

class LanguageManager {
  static LanguageManager _instance;
  static LanguageManager get instance {
    if (_instance == null) _instance = LanguageManager.init();
    return _instance;
  }

  LanguageManager.init();

  final enLocale = Locale("en");
  final trLocale = Locale("tr");
  List<Locale> get supportedLocales => [trLocale, enLocale];
}
