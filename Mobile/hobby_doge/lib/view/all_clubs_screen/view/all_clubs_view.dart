import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/view/all_clubs_screen/model/Club.dart';
import 'package:hobby_doge/view/all_clubs_screen/viewmodel/all_clubs_view_model.dart';
import '../../../core/extensions/context_extension.dart';

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
        appBar: appBarWithBackLeading(context, "Clubs"),
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
        extendBodyBehindAppBar: true,
        body: FutureBuilder<List<Club>>(
            future: viewModel.allClubs,
            builder:
                (BuildContext context, AsyncSnapshot<List<Club>> snapshot) {
              if (!snapshot.hasData) {
                print("no data");
                return Center(
                  child: CircularProgressIndicator(),
                );
              } else {
                switch (snapshot.connectionState) {
                  case ConnectionState.waiting:
                    print(snapshot.connectionState);
                    return Center(
                      child: CircularProgressIndicator(),
                    );

                  default:
                    return GridView.builder(
                        itemCount: snapshot.data.length,
                        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                            childAspectRatio: (8 / 5), crossAxisCount: 2),
                        itemBuilder: (BuildContext context, int index) {
                          return GestureDetector(
                            onTap: () {
                              NavigationService.instance
                                  .navigateToPage(path: "/club_view",object: snapshot.data[index].id);
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
                                  child: Column(
                                    children: [
                                      Spacer(
                                        flex: 3,
                                      ),
                                      Expanded(
                                        flex: 1,
                                        child: Padding(
                                          padding: EdgeInsets.symmetric(
                                              horizontal: context.width * 0.05),
                                          child: Row(
                                            children: [
                                              AutoSizeText(
                                                snapshot.data[index].name,
                                                maxLines: 1,
                                                style: TextStyle(
                                                    fontWeight: FontWeight.w400,
                                                    color: Colors.white),
                                              )
                                            ],
                                          ),
                                        ),
                                      ),
                                      Spacer(
                                        flex: 1,
                                      )
                                    ],
                                  ),
                                )),
                          );
                        });
                }
              }
            }),
      ),
    );
  }
}
