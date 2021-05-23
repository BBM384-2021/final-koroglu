import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/view/feed_screen/viewmodel/feed_view_model.dart';
import '../../../core/extensions/context_extension.dart';
import '../../../core/extensions/string_extension.dart';

class FeedView extends StatelessWidget {
  const FeedView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
        viewModel: FeedViewModel(),
        onModelReady: (FeedViewModel model) {
          model.setContext(context);
          model.init();
        },
        onPageBuilder: (BuildContext context, FeedViewModel viewmodel) =>
            Scaffold(
              appBar:
                  appBarWithBackLeading(context, LocaleKeys.feed_feed.locale),
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              body: ListView.builder(
                itemCount: 5,
                itemBuilder: (BuildContext context, int index) {
                  return Card();
                },
              ),
            ));
  }
}
