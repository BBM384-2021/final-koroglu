import 'package:flutter/material.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import '../../core/extensions/context_extension.dart';

Widget appBarWithBackLeading(BuildContext context, String title) {
  return AppBar(
      centerTitle: true,
      title: Text(
        title,
        style: TextStyle(color: Colors.black),
      ),
      actions: [
        Padding(
          padding: EdgeInsets.only(right: 10.0),
          child: Icon(
            Icons.search,
            color: Colors.green,
            size: context.width * 0.1,
          ),
        )
      ],
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
