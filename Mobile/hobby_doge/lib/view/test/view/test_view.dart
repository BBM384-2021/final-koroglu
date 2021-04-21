import 'package:flutter/material.dart';

import '../../../core/base/state/base_state.dart';
import '../../../core/base/view/base_view.dart';
import '../viewmodel/test_viewmodel.dart';

class TestEkrani extends StatefulWidget {
  TestEkrani({Key key}) : super(key: key);

  @override
  _TestEkraniState createState() => _TestEkraniState();
}

class _TestEkraniState extends BaseState<TestEkrani> {
  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: TestViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (context, value) => Scaffold(
        body: Container(
          child: Center(
            child: Text(
              "Denemere yazıları deneme bir iki ",
              style: TextStyle(fontSize: 50),
            ),
          ),
        ),
      ),
    );
  }
}
