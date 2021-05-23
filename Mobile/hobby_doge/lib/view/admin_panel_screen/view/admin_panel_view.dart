import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/view/admin_panel_screen/viewmodel/admin_panel_view_model.dart';
import '../../../core/extensions/context_extension.dart';

class AdminPanelView extends StatelessWidget {
  const AdminPanelView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: AdminPanelViewModel(),
      onModelReady: (AdminPanelViewModel model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, AdminPanelViewModel viewModel) =>
          Scaffold(
              appBar: appBarWithBackLeading(context, "Clubs"),
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              extendBodyBehindAppBar: true,
              body: GridView(
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    childAspectRatio: (8 / 5), crossAxisCount: 2),
                children: [
                  buildButton(context, "Clubs", "/all_clubs_view"),
                  buildButton(context, "Create Club", "/login")
                ],
              )),
    );
  }

  GestureDetector buildButton(BuildContext context, String text, String path) {
    return GestureDetector(
      onTap: () {
        NavigationService.instance.navigateToPage(path: path);
      },
      child: Container(
          height: context.height * 0.1,
          padding: EdgeInsets.only(
              top: context.height * 0.01,
              left: context.width * 0.03,
              right: context.width * 0.03,
              bottom: context.height * 0.01),
          child: Card(
              color: Color(0xff45BA73),
              elevation: 5,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(
                      ApplicationConstants.BORDER_RADIUS)),
              child: Center(
                child: AutoSizeText(
                  text,
                  maxLines: 1,
                  style: TextStyle(
                      fontWeight: FontWeight.w400, color: Colors.white),
                ),
              ))),
    );
  }
}
