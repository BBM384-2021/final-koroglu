import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';

import '../extensions/string_extension.dart';
import '../init/lang/locale_keys.g.dart';

Widget connectivitySnackBar(BuildContext context) {
  return SnackBar(
    content: Text(LocaleKeys.pleaseCheckInternet.locale),
    duration: Duration(days: 1),
    action: SnackBarAction(
      label: LocaleKeys.tryAgain.locale,
      onPressed: () async {
        var connectivityResult = await Connectivity().checkConnectivity();
        if (connectivityResult == ConnectivityResult.none) {
          ScaffoldMessenger.of(context)
              .showSnackBar(connectivitySnackBar(context));
        } else {
          ScaffoldMessenger.of(context).removeCurrentSnackBar();
        }
      },
    ),
  );
}
