import 'package:flutter/material.dart';
import '../../../../core/base/view/base_view.dart';
import '../viewmodel/login_view_model.dart';

class LoginView extends StatelessWidget {
  const LoginView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: LoginViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, LoginViewModel viewmodel) =>
          Scaffold(
        body: Container(
          child: Text("Login page"),
        ),
      ),
    );
  }
}
