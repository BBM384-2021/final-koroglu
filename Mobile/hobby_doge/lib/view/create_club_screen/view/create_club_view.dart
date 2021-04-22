import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/components/common_text_field.dart';
import 'package:hobby_doge/core/components/large_common_text_field.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/view/create_club_screen/viewmodel/create_club_view_model.dart';
import '../../../core/extensions/context_extension.dart';

class CreateClubView extends StatelessWidget {
  const CreateClubView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: CreateClubViewModel(),
      onModelReady: (CreateClubViewModel model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, CreateClubViewModel viewmodel) =>
          Scaffold(
              appBar: appBarWithBackLeading(context, "Create Club"),
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              body: Padding(
                padding: EdgeInsets.only(top: context.height * 0.1),
                child: Column(
                  children: [
                    Spacer(flex: 1),
                    Expanded(
                      flex: 2,
                      child: Column(
                        children: [
                          Text(
                            "Club Name",
                          ),
                          CommonTextField(text: "Club Name"),
                        ],
                      ),
                    ),
                    Expanded(
                        flex: 4,
                        child: Column(
                          children: [
                            Text("Description"),
                            LargeCommonTextField(text: "Description")
                          ],
                        )),
                    Spacer(flex: 1),
                    LargeOutlinedButton(text: "Create Club", onPressed: () {}),
                    Spacer(flex: 1)
                  ],
                ),
              )),
    );
  }
}
