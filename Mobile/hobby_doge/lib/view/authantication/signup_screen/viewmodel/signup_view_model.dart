import 'dart:convert';
import 'dart:io';
import 'package:hobby_doge/core/components/warning_snack_bar.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'package:mobx/mobx.dart';
import '../../../../core/extensions/string_extension.dart';
part 'signup_view_model.g.dart';

class SignUpViewModel = _SignUpViewModelBase with _$SignUpViewModel;

abstract class _SignUpViewModelBase with Store, BaseViewModel {
  TextEditingController emailController;
  TextEditingController passwordController;
  TextEditingController confirmPasswordController;
  TextEditingController nameController;
  TextEditingController surnameController;
  TextEditingController usernameController;

  GlobalKey<FormState> firstViewFormState;
  GlobalKey<FormState> secondViewFormState;
  GlobalKey<FormState> thirdViewFormState;
  GlobalKey<FormState> fourthViewFormState;

  @override
  void init() {
    emailController = TextEditingController();
    passwordController = TextEditingController();
    confirmPasswordController = TextEditingController();
    nameController = TextEditingController();
    surnameController = TextEditingController();
    usernameController = TextEditingController();
    firstViewFormState = GlobalKey();
    secondViewFormState = GlobalKey();
    thirdViewFormState = GlobalKey();
    fourthViewFormState = GlobalKey();
  }

  @override
  void setContext(BuildContext context) => this.context = context;

  @observable
  bool isHidden = true;

  @action
  void isHiddenChange() {
    isHidden = !isHidden;
  }

  bool checkPasswordMatched() {
    if (passwordController.text == confirmPasswordController.text) {
      return true;
    } else {
      return false;
    }
  }

  Future<void> signUp() async {
    print(
      emailController.text,
    );
    print(passwordController.text);
    print(nameController.text.trim());
    print(surnameController.text.trim());
    print(surnameController.text);
    print(usernameController.text.trim());
    var url =
        Uri.parse(ApplicationConstants.BASE_URL + "/api/v1/auth/register/");
    var response = await http.post(url,
        body: json.encode({
          'email': emailController.text.trim(),
          'password': passwordController.text,
          "name": nameController.text.trim(),
          "surname": surnameController.text.trim(),
          "username": usernameController.text.trim(),
          "dateOfBirth": "01/01/1881",
          "profilePicture": null
        }),
        headers: {
          HttpHeaders.authorizationHeader: "",
          "content-type": "application/json",
          "accept": "application/json",
        });
    print(response.statusCode);
    if (response.statusCode == HttpStatus.ok) {
      NavigationService.instance.navigatorToPageRemoveOld(path: "/home");
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
          warningSnackBar(LocaleKeys.loginScreen_invalidLogin.locale));
    }
  }
}
