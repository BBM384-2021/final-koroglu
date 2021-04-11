import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'signup_view_model.g.dart';

class SignUpViewModel = _SignUpViewModelBase with _$SignUpViewModel;

abstract class _SignUpViewModelBase with Store, BaseViewModel {
  TextEditingController emailController;
  TextEditingController passwordController;
  TextEditingController confirmPasswordController;
  TextEditingController nameController;
  TextEditingController surnameController;
  TextEditingController usernameController;

  GlobalKey<FormState> formState;

  @override
  void init() {
    emailController = TextEditingController();
    passwordController = TextEditingController();
    confirmPasswordController = TextEditingController();
    nameController = TextEditingController();
    surnameController = TextEditingController();
    usernameController = TextEditingController();
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

  bool checkPasswordMatched() {
    if (passwordController.text == confirmPasswordController.text) {
      return true;
    } else {
      return false;
    }
  }
}
