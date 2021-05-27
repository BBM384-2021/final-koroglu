import 'package:flutter/material.dart';
import 'package:hobby_doge/view/admin_panel_screen/view/admin_panel_view.dart';
import 'package:hobby_doge/view/admin_panel_screen/viewmodel/admin_panel_view_model.dart';
import 'package:hobby_doge/view/all_clubs_screen/view/all_clubs_view.dart';
import 'package:hobby_doge/view/club_screen/view/club_view.dart';
import 'package:hobby_doge/view/create_club_screen/view/create_club_view.dart';
import 'package:hobby_doge/view/feed_screen/view/feed_view.dart';
import 'package:hobby_doge/view/sub_club_screen/view/sub_club_view.dart';
import '../../../view/authantication/forgot_password_screen/view/forgot_password_email_view.dart';
import '../../../view/authantication/forgot_password_screen/view/forgot_password_enter_code_view.dart';
import 'package:hobby_doge/view/authantication/forgot_password_screen/view/forgot_password_set_password_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_first_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_fourth_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_second_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_third_view.dart';
import 'package:hobby_doge/view/home_screen/view/home_view.dart';
import 'package:hobby_doge/view/welcome_screen/view/welcome_view.dart';

import '../../../view/authantication/login_screen/view/login_view.dart';
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
      case NavigationConstants.LOGIN_VIEW:
        return MaterialPageRoute(builder: (context) => LoginView());
      case NavigationConstants.WELCOME_VIEW:
        return MaterialPageRoute(builder: (context) => WelcomeView());
      case NavigationConstants.SIGNUP_FIRST_VIEW:
        return MaterialPageRoute(builder: (context) => SignUpFirstView());
      case NavigationConstants.SIGNUP__SECOND_VIEW:
        return MaterialPageRoute(
            builder: (context) => SignUpSecondView(), settings: args);
      case NavigationConstants.SIGNUP__THIRD_VIEW:
        return MaterialPageRoute(
            builder: (context) => SignUpThirdView(), settings: args);
      case NavigationConstants.SIGNUP__FOURTH_VIEW:
        return MaterialPageRoute(
            builder: (context) => SignUpFourthView(), settings: args);
      case NavigationConstants.FORGOT_PASSWORD_EMAIL_VIEW:
        return MaterialPageRoute(
            builder: (context) => ForgotPasswordEmailView());
      case NavigationConstants.FORGOT_PASSWORD_ENTER_CODE_VIEW:
        return MaterialPageRoute(
            builder: (context) => ForgotPasswordEnterCodeView());
      case NavigationConstants.FORGOT_PASSWORD_SET_PASSWORD_VIEW:
        return MaterialPageRoute(
            builder: (context) => ForgotPasswordSetPasswordView());
      case NavigationConstants.ALL_CLUBS_VIEW:
        return MaterialPageRoute(builder: (context) => AllClubsView());
      case NavigationConstants.HOME_VIEW:
        return MaterialPageRoute(builder: (context) => HomeView());
      case NavigationConstants.ADMIN_PANEL_VIEW:
        return MaterialPageRoute(builder: (context) => AdminPanelView());
      case NavigationConstants.CREATE_CLUB_VIEW:
        return MaterialPageRoute(builder: (context) => CreateClubView());
      case NavigationConstants.FEED_VIEW:
        return MaterialPageRoute(builder: (context) => FeedView());
      case NavigationConstants.CLUB_VIEW:
        return MaterialPageRoute(
            builder: (context) => ClubView(
                  clubID: args.arguments,
                ));
      case NavigationConstants.SUB_CLUB_VIEW:
        return MaterialPageRoute(
            builder: (context) => SubClubView(
                  clubID: args.arguments,
                ));

      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  body: Center(child: Text("Not found page")),
                ));
    }
  }
}
