import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/components/common_text_field.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/core/components/very_large_text_field.dart';
import 'package:hobby_doge/core/components/warning_snack_bar.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/create_club_screen/viewmodel/create_club_view_model.dart';
import 'package:image_cropper/image_cropper.dart';
import '../../../core/extensions/context_extension.dart';
import 'package:firebase_picture_uploader/firebase_picture_uploader.dart';
import '../../../core/extensions/string_extension.dart';

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
              appBar: appBarWithBackLeading(
                  context, LocaleKeys.createClub_createClub.locale),
              backgroundColor: Colors.white,
              resizeToAvoidBottomInset: false,
              body: Column(
                children: [
                  Spacer(
                    flex: 1,
                  ),
                  Expanded(
                    flex: 1,
                    child: Padding(
                      padding:
                          EdgeInsets.symmetric(horizontal: context.width * 0.1),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          PictureUploadWidget(
                            initialImages: viewmodel.profilePictures,
                            onPicturesChange: viewmodel.profilePictureCallback,
                            buttonStyle: PictureUploadButtonStyle(
                                width: context.height * 0.1,
                                height: context.height * 0.1,
                                fontSize: context.height * 0.015,
                                iconSize: context.height * 0.04),
                            buttonText:
                                LocaleKeys.createClub_uploadImage.locale,
                            localization: PictureUploadLocalization(),
                            settings: PictureUploadSettings(
                                // customDeleteFunction: ProfileController.deleteProfilePicture,
                                // customUploadFunction: RecipeController.uploadRecipePicture,
                                imageSource: ImageSourceExtended.askUser,
                                minImageCount: 0,
                                maxImageCount: 1,
                                imageManipulationSettings:
                                    const ImageManipulationSettings(
                                  aspectRatio:
                                      CropAspectRatio(ratioX: 37, ratioY: 23),
                                  enableCropping: true,
                                  compressQuality: 75,
                                )),
                            enabled: true,
                          ),
                        ],
                      ),
                    ),
                  ),
                  Spacer(flex: 1),
                  Expanded(
                    flex: 2,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Padding(
                          padding: EdgeInsets.symmetric(
                              horizontal: context.width * 0.1,
                              vertical: context.height * 0.01),
                          child: Text(
                            LocaleKeys.createClub_clubName.locale,
                            style: TextStyle(
                                fontSize: context.height * 0.025,
                                fontWeight: FontWeight.w600,
                                color: AppColorScheme.instance.darkerGrey),
                          ),
                        ),
                        Expanded(
                            child: CommonTextField(
                          text: LocaleKeys.createClub_clubName.locale,
                          textController: viewmodel.clubName,
                        )),
                      ],
                    ),
                  ),
                  Expanded(
                      flex: 4,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Padding(
                            padding: EdgeInsets.symmetric(
                                horizontal: context.width * 0.1,
                                vertical: context.height * 0.01),
                            child: Text(
                              LocaleKeys.createClub_description.locale,
                              style: TextStyle(
                                  fontSize: context.height * 0.025,
                                  fontWeight: FontWeight.w600,
                                  color: AppColorScheme.instance.darkerGrey),
                            ),
                          ),
                          Expanded(
                              child: VeryLargeTextField(
                            text: LocaleKeys.createClub_description.locale,
                            textController: viewmodel.description,
                          ))
                        ],
                      )),
                  Spacer(flex: 1),
                  LargeOutlinedButton(
                      text: LocaleKeys.createClub_createClub.locale,
                      onPressed: () {
                        print(viewmodel.profilePictures.toList());
                        if (viewmodel.clubName.text.length < 3) {
                          ScaffoldMessenger.of(context).showSnackBar(
                              warningSnackBar(
                                  LocaleKeys.createClub_tooShortName.locale));
                        } else if (viewmodel.description.text.length < 15) {
                          ScaffoldMessenger.of(context).showSnackBar(
                              warningSnackBar(LocaleKeys
                                  .createClub_tooShortDescription.locale));
                        } else if (viewmodel.profilePictures.isEmpty) {
                          ScaffoldMessenger.of(context).showSnackBar(
                              warningSnackBar(
                                  LocaleKeys.createClub_uploadImage.locale));
                        } else {
                          viewmodel.createClub();
                        }
                      }),
                  Spacer(flex: 1)
                ],
              )),
    );
  }
}
