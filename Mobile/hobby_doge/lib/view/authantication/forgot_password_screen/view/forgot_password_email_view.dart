import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:hobby_doge/core/constants/navigation_constants.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';

import '../../../../core/base/view/base_view.dart';
import '../../../../core/components/common_text_field.dart';
import '../../../../core/components/large_outlined_button.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/theme/color_scheme.dart';
import '../viewmodel/forgot_password_view_model.dart';

class ForgotPasswordEmailView extends StatelessWidget {
  const ForgotPasswordEmailView({Key key}) : super(key: key);

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
            Spacer(flex: 8),
            buildTitles(context),
            Spacer(flex: 1),
            buildForm(context, viewmodel),
            Spacer(flex: 1),
            LargeOutlinedButton(
                text:
                    LocaleKeys.forgotPassword_sendCodeToEmailView_sendResetCode,
                onPressed: () {
                  NavigationService.instance.navigateToPage(
                      path:
                          NavigationConstants.FORGOT_PASSWORD_ENTER_CODE_VIEW);
                }),
            Spacer(
              flex: 8,
            )
          ],
        ),
      ),
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 4,
        child: Padding(
          padding: EdgeInsets.symmetric(horizontal: context.width * 0.05),
          child: Column(
            children: [
              buildMainTitle(context),
              Spacer(flex: 1),
              buildSubTitle(context),
            ],
          ),
        ));
  }

  Expanded buildSubTitle(BuildContext context) {
    return Expanded(
      flex: 2,
      child: Text(
        LocaleKeys
            .forgotPassword_sendCodeToEmailView_resetPasswordSubTitle.locale,
        style: TextStyle(
            fontSize: context.height * 0.024,
            color: AppColorScheme.instance.grey,
            fontWeight: FontWeight.w600),
        textAlign: TextAlign.center,
      ),
    );
  }

  Expanded buildMainTitle(BuildContext context) {
    return Expanded(
      flex: 1,
      child: Text(
        LocaleKeys.forgotPassword_sendCodeToEmailView_resetPassword.locale,
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

Expanded buildForm(BuildContext context, ForgotPasswordViewModel viewmodel) {
  return Expanded(
    flex: 3,
    child: Form(key: viewmodel.formState, child: buildEmailField(viewmodel)),
  );
}

Observer buildEmailField(ForgotPasswordViewModel viewmodel) {
  return Observer(
      builder: (_) => CommonTextField(
            text: LocaleKeys.signUpScreen_email.locale,
            textController: viewmodel.emailController,
            validator: (String value) => value.isValidEmail
                ? null
                : LocaleKeys.signUpScreen_invalidEmail.locale,
          ));
}
