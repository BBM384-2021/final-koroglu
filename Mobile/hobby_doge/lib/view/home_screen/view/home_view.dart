import 'package:flutter/material.dart';

import '../../../core/base/view/base_view.dart';
import '../viewmodel/home_view_model.dart';

class HomeView extends StatelessWidget {
  const HomeView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: HomeViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, HomeViewModel viewModel) =>
          Scaffold(
        appBar: AppBar(),
      ),
    );
  }
}
