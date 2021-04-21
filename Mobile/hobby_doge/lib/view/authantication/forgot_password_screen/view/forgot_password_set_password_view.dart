import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import '../../../../core/base/view/base_view.dart';
import '../../../../core/components/large_outlined_button.dart';
import '../../../../core/components/password_text_field.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/theme/color_scheme.dart';
import '../viewmodel/forgot_password_view_model.dart';

class ForgotPasswordSetPasswordView extends StatelessWidget {
  const ForgotPasswordSetPasswordView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: ForgotPasswordViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder:
          (BuildContext context, ForgotPasswordViewModel viewmodel) =>
              buildScaffold(context, viewmodel),
    );
  }

  Scaffold buildScaffold(
      BuildContext context, ForgotPasswordViewModel viewmodel) {
    return Scaffold(
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
            Spacer(flex: 1),
            buildSendCodeButton(context, viewmodel),
            Spacer(flex: 3)
          ],
        ),
      ),
    );
  }

  Expanded buildSendCodeButton(
      BuildContext context, ForgotPasswordViewModel viewmodel) {
    return Expanded(
      flex: 3,
      child: Column(
        children: [
          LargeOutlinedButton(
              text: LocaleKeys
                  .forgotPassword_sendCodeToEmailView_sendResetCode.locale,
              onPressed: () {
                if (viewmodel.formState.currentState.validate()) {}
              }),
        ],
      ),
    );
  }

  Expanded buildTextFields(
      BuildContext context, ForgotPasswordViewModel viewmodel) {
    return Expanded(
      flex: 4,
      child: Form(
          key: viewmodel.formState,
          child: Column(
            children: [
              Expanded(flex: 4, child: buildPasswordField(viewmodel)),
              Spacer(
                flex: 1,
              ),
              Expanded(flex: 4, child: buildConfirmPasswordField(viewmodel)),
            ],
          )),
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Column(
          children: [
            buildMainTitle(context),
            Spacer(flex: 1),
            buildSubTitle(context),
          ],
        ));
  }

  Expanded buildSubTitle(BuildContext context) {
    return Expanded(
      flex: 3,
      child: Text(
        LocaleKeys
            .forgotPassword_resetPasswordView_subTitleAboutPassword.locale,
        style: TextStyle(
            fontSize: context.height * 0.024,
            color: AppColorScheme.instance.grey,
            fontWeight: FontWeight.w600),
      ),
    );
  }

  Expanded buildMainTitle(BuildContext context) {
    return Expanded(
      flex: 3,
      child: Text(
        LocaleKeys.forgotPassword_resetPasswordView_resetPassword.locale,
        style: TextStyle(
            fontSize: context.height * 0.03,
            color: AppColorScheme.instance.darkerGrey,
            fontWeight: FontWeight.w600),
      ),
    );
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

Observer buildPasswordField(ForgotPasswordViewModel viewmodel) {
  return Observer(
    builder: (_) => CustomPasswordTextField(
      text: LocaleKeys.forgotPassword_resetPasswordView_newPassword.locale,
      isHidden: viewmodel.isHidden,
      isHiddenChange: viewmodel.isHiddenChange,
      textController: viewmodel.passwordController,
      validator: (String value) {
        if (value.isEmpty) {
          return LocaleKeys.signUpScreen_fieldRequired.locale;
        } else if (value.length < 6) {
          return LocaleKeys.signUpScreen_tooShortPassword.locale;
        } else {
          return null;
        }
      },
    ),
  );
}

Observer buildConfirmPasswordField(ForgotPasswordViewModel viewmodel) {
  return Observer(
    builder: (_) => CustomPasswordTextField(
      text:
          LocaleKeys.forgotPassword_resetPasswordView_confirmNewPassword.locale,
      isHidden: viewmodel.isHidden,
      isHiddenChange: viewmodel.isHiddenChange,
      textController: viewmodel.confirmPasswordController,
      validator: (String value) {
        if (value.isEmpty) {
          return LocaleKeys.signUpScreen_fieldRequired.locale;
        } else if (value.length < 6) {
          return LocaleKeys.signUpScreen_tooShortPassword.locale;
        } else if (!viewmodel.checkPasswordMatched()) {
          return LocaleKeys.signUpScreen_passwordsNotMatched.locale;
        } else {
          return null;
        }
      },
    ),
  );
}
