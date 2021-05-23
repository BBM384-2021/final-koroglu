import 'package:flutter/material.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import '../../core/extensions/context_extension.dart';

Widget appBarWithBackLeading(BuildContext context, String title) {
  return AppBar(
      centerTitle: true,
      title: Text(
        title,
        style: TextStyle(color: AppColorScheme.instance.darkerGrey),
      ),
      elevation: 0,
      automaticallyImplyLeading: true,
      backgroundColor: Colors.transparent,
      leading: IconButton(
        onPressed: () {
          Navigator.pop(context);
        },
        icon: Icon(
          Icons.arrow_back_ios_rounded,
          size: context.height * 0.04,
          color: AppColorScheme.instance.greenLight2,
        ),
      ));
}
