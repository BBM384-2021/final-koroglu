import 'dart:convert';
import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobx/mobx.dart';
import '../../../../core/base/model/base_view_model.dart';
import '../../../../core/components/warning_snack_bar.dart';
import '../../../../core/constants/app_constants.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/navigation/navigation_service.dart';
part 'login_view_model.g.dart';

class LoginViewModel = _LoginViewModelBase with _$LoginViewModel;

abstract class _LoginViewModelBase with Store, BaseViewModel {
  TextEditingController emailController;
  TextEditingController passwordController;
  GlobalKey<FormState> formState;

  @override
  void init() {
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
    print(
      emailController.text.trim(),
    );
    var url = Uri.parse(ApplicationConstants.BASE_URL + "/api/v1/user/login/");
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
    ScaffoldMessenger.of(context).showSnackBar(
        warningSnackBar(LocaleKeys.loginScreen_invalidLogin.locale));
    if (response.statusCode == HttpStatus.ok) {
      NavigationService.instance.navigatorToPageRemoveOld(path: "/home");
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
          warningSnackBar(LocaleKeys.loginScreen_invalidLogin.locale));
    }
  }
}
