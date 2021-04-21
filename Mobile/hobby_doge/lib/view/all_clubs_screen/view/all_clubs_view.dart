import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/view/all_clubs_screen/viewmodel/all_clubs_view_model.dart';

class AllClubsView extends StatelessWidget {
  const AllClubsView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: AllClubsViewModel(),
      onModelReady: (AllClubsViewModel model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, AllClubsViewModel viewModel) =>
          Scaffold(
        appBar: AppBar(),
      ),
    );
  }
}
