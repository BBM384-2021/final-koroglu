import 'package:flutter/material.dart';
import 'package:hobby_doge/core/constants/navigation_constants.dart';
import 'package:hobby_doge/view/test/view/test_view.dart';

class NavigationRoute {
  static NavigationRoute _instance = NavigationRoute._init();
  static NavigationRoute get instance => _instance;

  NavigationRoute._init();

  Route<dynamic> generateRoute(RouteSettings args) {
    switch (args.name) {
      case NavigationConstants.TEST_VIEW:
        return MaterialPageRoute(builder: (context) => TestEkrani());
        break;
      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  body: Center(child: Text("Not found page")),
                ));
    }
  }
}
