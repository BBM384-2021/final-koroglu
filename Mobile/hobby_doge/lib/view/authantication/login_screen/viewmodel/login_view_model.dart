import 'dart:convert';
import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobx/mobx.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../../../core/base/model/base_view_model.dart';
import '../../../../core/components/warning_snack_bar.dart';
import '../../../../core/constants/app_constants.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/navigation/navigation_service.dart';
import '../model/LoginResponse.dart';
part 'login_view_model.g.dart';

class LoginViewModel = _LoginViewModelBase with _$LoginViewModel;

abstract class _LoginViewModelBase with Store, BaseViewModel {
  TextEditingController emailController;
  TextEditingController passwordController;
  GlobalKey<FormState> formState;

  @override
  Future<void> init() async {
    emailController = TextEditingController();
    passwordController = TextEditingController();
    formState = GlobalKey();
  }

  @override
  void setContext(BuildContext context) => this.context = context;

  @observable
  bool isHidden = true;

  @action
  void isHiddenChange() {
    isHidden = !isHidden;
  }

  Future<void> login() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();

    var url = Uri.parse(ApplicationConstants.BASE_URL + "/api/v1/auth/login/");
    var response = await http.post(url,
        body: json.encode({
          'email': emailController.text.trim(),
          'password': passwordController.text
        }),
        headers: {
          HttpHeaders.authorizationHeader: "",
          "content-type": "application/json",
          "accept": "application/json",
        });

    if (response.statusCode == HttpStatus.ok) {
      print("asjdasd");

      LoginResponse responseBody =
          LoginResponse.fromJson(json.decode(response.body));
      prefs.setString("token", responseBody.token);
      print(responseBody.username);
      prefs.setString("username", responseBody.username);
      NavigationService.instance
          .navigatorToPageRemoveOld(path: "/all_clubs_view");
    } else {
      print("asjdasd");

      ScaffoldMessenger.of(context).showSnackBar(
          warningSnackBar(LocaleKeys.loginScreen_invalidLogin.locale));
    }
  }
}
