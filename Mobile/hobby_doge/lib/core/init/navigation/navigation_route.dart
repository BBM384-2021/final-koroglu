import 'package:flutter/material.dart';

import '../../../view/splash_screen/view/splash_view.dart';
import '../../../view/test/view/test_view.dart';
import '../../constants/navigation_constants.dart';

class NavigationRoute {
  static NavigationRoute _instance = NavigationRoute._init();
  static NavigationRoute get instance => _instance;

  NavigationRoute._init();

  Route<dynamic> generateRoute(RouteSettings args) {
    switch (args.name) {
      case NavigationConstants.TEST_VIEW:
        return MaterialPageRoute(builder: (context) => TestEkrani());
      case NavigationConstants.SPLASH_VIEW:
        return MaterialPageRoute(builder: (context) => SplashView());
      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  body: Center(child: Text("Not found page")),
                ));
    }
  }

}
