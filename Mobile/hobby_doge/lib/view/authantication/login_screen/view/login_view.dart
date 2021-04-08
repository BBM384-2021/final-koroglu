import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';

import '../../../../core/base/view/base_view.dart';
import '../../../../core/components/common_text_field.dart';
import '../../../../core/components/large_outlined_button.dart';
import '../../../../core/components/password_text_field.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/navigation/navigation_service.dart';
import '../../../../core/init/theme/color_scheme.dart';
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
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
        extendBodyBehindAppBar: true,
        appBar: buildAppBar(context),
        body: Center(
          child: Column(
            children: [
              Spacer(flex: 4),
              buildTitles(context),
              Spacer(flex: 1),
              buildTextFields(context, viewmodel),
              buildForgotPasswordButton(context),
              buildLoginRegisterButtons(context, viewmodel),
              Spacer(flex: 3)
            ],
          ),
        ),
      ),
    );
  }

  Expanded buildLoginRegisterButtons(
      BuildContext context, LoginViewModel viewmodel) {
    return Expanded(
      flex: 2,
      child: Column(
        children: [
          LargeOutlinedButton(
              text: LocaleKeys.loginScreen_login.locale,
              onPressed: () {
                viewmodel.formState.currentState.validate();
              }),
          Spacer(flex: 1),
          Expanded(
            flex: 5,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(LocaleKeys.loginScreen_dontYouHaveAccount.locale,
                    style: TextStyle(
                        color: AppColorScheme.instance.grey,
                        fontWeight: FontWeight.w400,
                        fontSize: context.height * 0.02)),
                TextButton(
                  child: Text(
                    LocaleKeys.loginScreen_signup.locale,
                    style: TextStyle(
                        color: AppColorScheme.instance.greenLight3,
                        fontWeight: FontWeight.w400,
                        fontSize: context.height * 0.02),
                  ),
                  onPressed: () {
                    NavigationService.instance.navigateToPage(path: "/signup");
                  },
                )
              ],
            ),
          )
        ],
      ),
    );
  }

  Expanded buildForgotPasswordButton(BuildContext context) {
    return Expanded(
        child: Container(
      alignment: Alignment.centerRight,
      width: context.width * 0.8,
      child: TextButton(
          onPressed: () {},
          child: Text(
            LocaleKeys.loginScreen_forgotPassword.locale,
            style: TextStyle(color: AppColorScheme.instance.greenLight2),
          )),
    ));
  }

  Expanded buildTextFields(BuildContext context, LoginViewModel viewmodel) {
    return Expanded(
      flex: 3,
      child: Form(
          key: viewmodel.formState,
          child: Column(
            children: [
              buildEmailField(viewmodel),
              Spacer(flex: 1),
              buildPasswordField(viewmodel),
            ],
          )),
    );
  }

  Observer buildEmailField(LoginViewModel viewmodel) {
    return Observer(
        builder: (_) => CommonTextField(
              text: LocaleKeys.loginScreen_email.locale,
              textController: viewmodel.emailController,
              validator: (String value) => value.isValidEmail
                  ? null
                  : LocaleKeys.loginScreen_invalidEmail.locale,
            ));
  }

  Observer buildPasswordField(LoginViewModel viewmodel) {
    return Observer(
      builder: (_) => CustomPasswordTextField(
        text: LocaleKeys.loginScreen_password,
        isHidden: viewmodel.isHidden,
        isHiddenChange: viewmodel.isHiddenChange,
        textController: viewmodel.passwordController,
      ),
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Column(
          children: [
            Expanded(
              flex: 3,
              child: Text(
                LocaleKeys.welcomeScreen_welcomeToHobbyDoge.locale,
                style: TextStyle(
                    fontSize: context.height * 0.03,
                    color: AppColorScheme.instance.darkerGrey,
                    fontWeight: FontWeight.w600),
              ),
            ),
            Spacer(flex: 1),
            Expanded(
              flex: 3,
              child: Text(
                LocaleKeys.welcomeScreen_timeForHobbyDoge.locale,
                style: TextStyle(
                    fontSize: context.height * 0.024,
                    color: AppColorScheme.instance.grey,
                    fontWeight: FontWeight.w600),
              ),
            )
          ],
        ));
  }

  AppBar buildAppBar(BuildContext context) {
    return AppBar(
        elevation: 0,
        automaticallyImplyLeading: true,
        backgroundColor: Colors.transparent,
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: Icon(
            Icons.arrow_back_ios_rounded,
            size: context.height * 0.03,
            color: AppColorScheme.instance.greenLight2,
          ),
        ));
  }
}
