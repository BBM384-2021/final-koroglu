import 'package:flutter/material.dart';
import '../../../../core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'forgot_password_view_model.g.dart';

class ForgotPasswordViewModel = _ForgotPasswordViewModelBase
    with _$ForgotPasswordViewModel;

abstract class _ForgotPasswordViewModelBase with Store, BaseViewModel {
  TextEditingController emailController;
  TextEditingController passwordController;
  TextEditingController confirmPasswordController;

  GlobalKey<FormState> formState;

  @override
  init() {
    emailController = TextEditingController();
    passwordController = TextEditingController();
    confirmPasswordController = TextEditingController();
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
