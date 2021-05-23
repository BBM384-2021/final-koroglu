import 'dart:convert';
import 'dart:io';

import 'package:firebase_picture_uploader/firebase_picture_uploader.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:hobby_doge/core/components/warning_snack_bar.dart';
import 'package:hobby_doge/core/constants/app_constants.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import '../../../core/extensions/string_extension.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:mobx/mobx.dart';
import 'package:http/http.dart' as http;

part 'create_club_view_model.g.dart';

class CreateClubViewModel = _CreateClubViewModelBase with _$CreateClubViewModel;

abstract class _CreateClubViewModelBase with Store, BaseViewModel {
  List<UploadJob> profilePictures = [];
  TextEditingController clubName;
  TextEditingController description;
  GlobalKey<FormState> formState;

  Future<void> createClub() async {
    print(profilePictures[0]);
    try {
      downloadUrl = await FirebaseStorage.instance
          .ref()
          .child(profilePictures[0].storageReference.fullPath)
          .getDownloadURL();
    } catch (e) {
      downloadUrl = "null";
    }

    var url = Uri.parse(ApplicationConstants.BASE_URL + "/api/v1/clubs");
    var response = await http.post(url,
        body: json.encode({
          'name': clubName.text.trim(),
          'description': description.text.trim(),
          'picture': downloadUrl,
        }),
        headers: {
          HttpHeaders.authorizationHeader: "",
          "content-type": "application/json",
          "accept": "application/json",
        });
    print(response.statusCode);
    if (response.statusCode == HttpStatus.ok) {
      NavigationService.instance.navigatorToPageRemoveOld(path: "/home");
    } else if (response.statusCode == HttpStatus.notAcceptable) {
      ScaffoldMessenger.of(context).showSnackBar(
          warningSnackBar(LocaleKeys.createClub_clubIsExist.locale));
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
          warningSnackBar(LocaleKeys.createClub_commonError.locale));
    }
  }

  String downloadUrl;
  @action
  Future<void> profilePictureCallback(
      {List<UploadJob> uploadJobs, bool pictureUploadProcessing}) async {
    profilePictures = uploadJobs;
  }

  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {
    clubName = TextEditingController();
    description = TextEditingController();
    formState = GlobalKey();
  }
}
