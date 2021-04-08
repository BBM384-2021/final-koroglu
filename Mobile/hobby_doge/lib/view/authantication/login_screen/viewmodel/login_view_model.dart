import 'package:flutter/cupertino.dart';
import 'package:mobx/mobx.dart';

import '../../../../core/base/model/base_view_model.dart';

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


}
