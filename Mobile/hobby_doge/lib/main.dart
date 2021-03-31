import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'core/init/lang/language_manager.dart';
import 'core/extensions/localization_extension.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  runApp(
    EasyLocalization(
      child: MyApp(),
      supportedLocales: LanguageManager.instance.supportedLocales,
      path: ApplicationConstants.LANG_ASSET_PATH,
    ),
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        localizationsDelegates: context.localizationDelegates,
        supportedLocales: context.supportedLocales,
        locale: context.locale,
        title: 'Material App',
        home: Center());
  }
}
