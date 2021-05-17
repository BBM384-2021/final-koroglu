package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.exception.RestUserNotFoundException;
import org.koroglu.hobbydoge.model.PasswordResetToken;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.PasswordResetTokenRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  private final JavaMailSender mailSender;
  private final UserRepository userRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;


  @Override
  public String sendResetMail(String to) {

    User user = userRepository.findByEmail(to).orElseThrow(() -> new RestUserNotFoundException());

    PasswordResetToken passwordResetToken = new PasswordResetToken(
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30),
            user);

    passwordResetTokenRepository.save(passwordResetToken);

    send(
            to,
            "Reset your HobbyDoge password",
            "Reset your password",
            "To reset your password use this code in app. This code will expire in 30 minutes.",
            passwordResetToken.getToken());

    return "Reset password token sent.";

  }

  @Override
  @Async
  public void send(String to, String subject, String headline, String body, String token) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(buildEmail(headline, body, token), true);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setFrom("info@hobbydoge.com");
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Failed to send email.", e);
      throw new IllegalStateException("Failed to send email.");
    }

  }

  public String buildEmail(String headline, String body, String token) {
    return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
            "<html\n" +
            "  data-editor-version=\"2\"\n" +
            "  class=\"sg-campaigns\"\n" +
            "  xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
            ">\n" +
            "  <head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "    <meta\n" +
            "      name=\"viewport\"\n" +
            "      content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1\"\n" +
            "    />\n" +
            "    <!--[if !mso]>\n" +
            "      <!-->\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\" />\n" +
            "    <!--<![endif]-->\n" +
            "    <!--[if (gte mso 9)|(IE)]>\n" +
            "      <xml>\n" +
            "        <o:OfficeDocumentSettings>\n" +
            "          <o:AllowPNG />\n" +
            "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
            "        </o:OfficeDocumentSettings>\n" +
            "      </xml>\n" +
            "    <![endif]-->\n" +
            "    <!--[if (gte mso 9)|(IE)]>\n" +
            "      <style type=\"text/css\">\n" +
            "        body {\n" +
            "          width: 600px;\n" +
            "          margin: 0 auto;\n" +
            "        }\n" +
            "        table {\n" +
            "          border-collapse: collapse;\n" +
            "        }\n" +
            "        table,\n" +
            "        td {\n" +
            "          mso-table-lspace: 0pt;\n" +
            "          mso-table-rspace: 0pt;\n" +
            "        }\n" +
            "        img {\n" +
            "          -ms-interpolation-mode: bicubic;\n" +
            "        }\n" +
            "      </style>\n" +
            "    <![endif]-->\n" +
            "    <style type=\"text/css\">\n" +
            "      body,\n" +
            "      p,\n" +
            "      div {\n" +
            "        font-family: arial, helvetica, sans-serif;\n" +
            "        font-size: 16px;\n" +
            "      }\n" +
            "      body {\n" +
            "        color: #ffffff;\n" +
            "      }\n" +
            "      body a {\n" +
            "        color: #6AC88F;\n" +
            "        text-decoration: none;\n" +
            "      }\n" +
            "      p {\n" +
            "        margin: 0;\n" +
            "        padding: 0;\n" +
            "      }\n" +
            "      table.wrapper {\n" +
            "        width: 100% !important;\n" +
            "        table-layout: fixed;\n" +
            "        -webkit-font-smoothing: antialiased;\n" +
            "        -webkit-text-size-adjust: 100%;\n" +
            "        -moz-text-size-adjust: 100%;\n" +
            "        -ms-text-size-adjust: 100%;\n" +
            "      }\n" +
            "      img.max-width {\n" +
            "        max-width: 100% !important;\n" +
            "      }\n" +
            "      .column.of-2 {\n" +
            "        width: 50%;\n" +
            "      }\n" +
            "      .column.of-3 {\n" +
            "        width: 33.333%;\n" +
            "      }\n" +
            "      .column.of-4 {\n" +
            "        width: 25%;\n" +
            "      }\n" +
            "      @media screen and (max-width: 480px) {\n" +
            "        .preheader .rightColumnContent,\n" +
            "        .footer .rightColumnContent {\n" +
            "          text-align: left !important;\n" +
            "        }\n" +
            "        .preheader .rightColumnContent div,\n" +
            "        .preheader .rightColumnContent span,\n" +
            "        .footer .rightColumnContent div,\n" +
            "        .footer .rightColumnContent span {\n" +
            "          text-align: left !important;\n" +
            "        }\n" +
            "        .preheader .rightColumnContent,\n" +
            "        .preheader .leftColumnContent {\n" +
            "          font-size: 80% !important;\n" +
            "          padding: 5px 0;\n" +
            "        }\n" +
            "        table.wrapper-mobile {\n" +
            "          width: 100% !important;\n" +
            "          table-layout: fixed;\n" +
            "        }\n" +
            "        img.max-width {\n" +
            "          height: auto !important;\n" +
            "          max-width: 100% !important;\n" +
            "        }\n" +
            "        a.bulletproof-button {\n" +
            "          display: block !important;\n" +
            "          width: auto !important;\n" +
            "          font-size: 80%;\n" +
            "          padding-left: 0 !important;\n" +
            "          padding-right: 0 !important;\n" +
            "        }\n" +
            "        .columns {\n" +
            "          width: 100% !important;\n" +
            "        }\n" +
            "        .column {\n" +
            "          display: block !important;\n" +
            "          width: 100% !important;\n" +
            "          padding-left: 0 !important;\n" +
            "          padding-right: 0 !important;\n" +
            "          margin-left: 0 !important;\n" +
            "          margin-right: 0 !important;\n" +
            "        }\n" +
            "        .social-icon-column {\n" +
            "          display: inline-block !important;\n" +
            "        }\n" +
            "      }\n" +
            "    </style>\n" +
            "    <!--user entered Head Start-->\n" +
            "    <!--End Head user entered-->\n" +
            "  </head>\n" +
            "\n" +
            "  <body>\n" +
            "    <center\n" +
            "      class=\"wrapper\"\n" +
            "      data-link-color=\"#6AC88F\"\n" +
            "      data-body-style=\"font-size:16px; font-family:arial,helvetica,sans-serif; color:#ffffff; background-color:#F8F8FC;\"\n" +
            "    >\n" +
            "      <div class=\"webkit\">\n" +
            "        <table\n" +
            "          cellpadding=\"0\"\n" +
            "          cellspacing=\"0\"\n" +
            "          border=\"0\"\n" +
            "          width=\"100%\"\n" +
            "          class=\"wrapper\"\n" +
            "          bgcolor=\"#F8F8FC\"\n" +
            "        >\n" +
            "          <tr>\n" +
            "            <td valign=\"top\" bgcolor=\"#F8F8FC\" width=\"100%\">\n" +
            "              <table\n" +
            "                width=\"100%\"\n" +
            "                role=\"content-container\"\n" +
            "                class=\"outer\"\n" +
            "                align=\"center\"\n" +
            "                cellpadding=\"0\"\n" +
            "                cellspacing=\"0\"\n" +
            "                border=\"0\"\n" +
            "              >\n" +
            "                <tr>\n" +
            "                  <td width=\"100%\">\n" +
            "                    <table\n" +
            "                      width=\"100%\"\n" +
            "                      cellpadding=\"0\"\n" +
            "                      cellspacing=\"0\"\n" +
            "                      border=\"0\"\n" +
            "                    >\n" +
            "                      <tr>\n" +
            "                        <td>\n" +
            "                          <!--[if mso]>\n" +
            "                            <center>\n" +
            "                              <table>\n" +
            "                                <tr>\n" +
            "                                  <td width=\"600\">\n" +
            "                                  <![endif]-->\n" +
            "                          <table\n" +
            "                            width=\"100%\"\n" +
            "                            cellpadding=\"0\"\n" +
            "                            cellspacing=\"0\"\n" +
            "                            border=\"0\"\n" +
            "                            style=\"width: 100%; max-width: 600px\"\n" +
            "                            align=\"center\"\n" +
            "                          >\n" +
            "                            <tr>\n" +
            "                              <td\n" +
            "                                role=\"modules-container\"\n" +
            "                                style=\"\n" +
            "                                  padding: 0px 0px 0px 0px;\n" +
            "                                  color: #ffffff;\n" +
            "                                  text-align: left;\n" +
            "                                \"\n" +
            "                                bgcolor=\"#FFFFFF\"\n" +
            "                                width=\"100%\"\n" +
            "                                align=\"left\"\n" +
            "                              >\n" +
            "                                <table\n" +
            "                                  class=\"module preheader preheader-hide\"\n" +
            "                                  role=\"module\"\n" +
            "                                  data-type=\"preheader\"\n" +
            "                                  border=\"0\"\n" +
            "                                  cellpadding=\"0\"\n" +
            "                                  cellspacing=\"0\"\n" +
            "                                  width=\"100%\"\n" +
            "                                  style=\"\n" +
            "                                    display: none !important;\n" +
            "                                    mso-hide: all;\n" +
            "                                    visibility: hidden;\n" +
            "                                    opacity: 0;\n" +
            "                                    color: transparent;\n" +
            "                                    height: 0;\n" +
            "                                    width: 0;\n" +
            "                                  \"\n" +
            "                                >\n" +
            "                                  <tr>\n" +
            "                                    <td role=\"module-content\">\n" +
            "                                      <p>\n" +
            "                                        Secure your Panther account by verifying\n" +
            "                                        your email address.\n" +
            "                                      </p>\n" +
            "                                    </td>\n" +
            "                                  </tr>\n" +
            "                                </table>\n" +
            "                                <table\n" +
            "                                  border=\"0\"\n" +
            "                                  cellpadding=\"0\"\n" +
            "                                  cellspacing=\"0\"\n" +
            "                                  align=\"center\"\n" +
            "                                  width=\"100%\"\n" +
            "                                  role=\"module\"\n" +
            "                                  data-type=\"columns\"\n" +
            "                                  style=\"padding: 0px 0px 0px 0px\"\n" +
            "                                  bgcolor=\"#6AC88F\"\n" +
            "                                  data-distribution=\"1,1\"\n" +
            "                                >\n" +
            "                                  <tbody>\n" +
            "                                    <tr role=\"module-content\">\n" +
            "                                      <td height=\"100%\" valign=\"top\">\n" +
            "                                        <table\n" +
            "                                          width=\"290\"\n" +
            "                                          style=\"\n" +
            "                                            width: 290px;\n" +
            "                                            border-spacing: 0;\n" +
            "                                            border-collapse: collapse;\n" +
            "                                            margin: 0px 10px 0px 0px;\n" +
            "                                          \"\n" +
            "                                          cellpadding=\"0\"\n" +
            "                                          cellspacing=\"0\"\n" +
            "                                          align=\"left\"\n" +
            "                                          border=\"0\"\n" +
            "                                          bgcolor=\"\"\n" +
            "                                          class=\"column column-0\"\n" +
            "                                        >\n" +
            "                                          <tbody>\n" +
            "                                            <tr>\n" +
            "                                              <td\n" +
            "                                                style=\"\n" +
            "                                                  padding: 0px;\n" +
            "                                                  margin: 0px;\n" +
            "                                                  border-spacing: 0;\n" +
            "                                                \"\n" +
            "                                              >\n" +
            "                                                <table\n" +
            "                                                  class=\"wrapper\"\n" +
            "                                                  role=\"module\"\n" +
            "                                                  data-type=\"image\"\n" +
            "                                                  border=\"0\"\n" +
            "                                                  cellpadding=\"0\"\n" +
            "                                                  cellspacing=\"0\"\n" +
            "                                                  width=\"100%\"\n" +
            "                                                  style=\"table-layout: fixed\"\n" +
            "                                                  data-muid=\"214e5601-35a6-4fe7-911a-62959a775603\"\n" +
            "                                                >\n" +
            "                                                  <tbody>\n" +
            "                                                    <tr>\n" +
            "                                                      <td\n" +
            "                                                        style=\"\n" +
            "                                                          font-size: 6px;\n" +
            "                                                          line-height: 10px;\n" +
            "                                                          padding: 32px 0px 16px\n" +
            "                                                            40px;\n" +
            "                                                        \"\n" +
            "                                                        valign=\"top\"\n" +
            "                                                        align=\"left\"\n" +
            "                                                      >\n" +
            "                                                        <img\n" +
            "                                                          class=\"max-width\"\n" +
            "                                                          border=\"0\"\n" +
            "                                                          style=\"\n" +
            "                                                            display: block;\n" +
            "                                                            color: #000000;\n" +
            "                                                            text-decoration: none;\n" +
            "                                                            font-family: Helvetica,\n" +
            "                                                              arial, sans-serif;\n" +
            "                                                            font-size: 16px;\n" +
            "                                                            max-width: 50% !important;\n" +
            "                                                            width: 50%;\n" +
            "                                                            height: auto !important;\n" +
            "                                                          \"\n" +
            "                                                          width=\"145\"\n" +
            "                                                          alt=\"\"\n" +
            "                                                          data-proportionally-constrained=\"true\"\n" +
            "                                                          data-responsive=\"true\"\n" +
            "                                                          src=\"https://liquipedia.net/commons/images/4/4a/TeamDoge_logo.png\"\n" +
            "                                                        />\n" +
            "                                                      </td>\n" +
            "                                                    </tr>\n" +
            "                                                  </tbody>\n" +
            "                                                </table>\n" +
            "                                              </td>\n" +
            "                                            </tr>\n" +
            "                                          </tbody>\n" +
            "                                        </table>\n" +
            "                                        <table\n" +
            "                                          width=\"290\"\n" +
            "                                          style=\"\n" +
            "                                            width: 290px;\n" +
            "                                            border-spacing: 0;\n" +
            "                                            border-collapse: collapse;\n" +
            "                                            margin: 0px 0px 0px 10px;\n" +
            "                                          \"\n" +
            "                                          cellpadding=\"0\"\n" +
            "                                          cellspacing=\"0\"\n" +
            "                                          align=\"left\"\n" +
            "                                          border=\"0\"\n" +
            "                                          bgcolor=\"\"\n" +
            "                                          class=\"column column-1\"\n" +
            "                                        >\n" +
            "                                          <tbody>\n" +
            "                                            <tr>\n" +
            "                                              <td\n" +
            "                                                style=\"\n" +
            "                                                  padding: 0px;\n" +
            "                                                  margin: 0px;\n" +
            "                                                  border-spacing: 0;\n" +
            "                                                \"\n" +
            "                                              ></td>\n" +
            "                                            </tr>\n" +
            "                                          </tbody>\n" +
            "                                        </table>\n" +
            "                                      </td>\n" +
            "                                    </tr>\n" +
            "                                  </tbody>\n" +
            "                                </table>\n" +
            "                                <table\n" +
            "                                  border=\"0\"\n" +
            "                                  cellpadding=\"0\"\n" +
            "                                  cellspacing=\"0\"\n" +
            "                                  align=\"center\"\n" +
            "                                  width=\"100%\"\n" +
            "                                  role=\"module\"\n" +
            "                                  data-type=\"columns\"\n" +
            "                                  style=\"padding: 0px 0px 40px 0px\"\n" +
            "                                  bgcolor=\"#6AC88F\"\n" +
            "                                  data-distribution=\"1\"\n" +
            "                                >\n" +
            "                                  <tbody>\n" +
            "                                    <tr role=\"module-content\">\n" +
            "                                      <td height=\"100%\" valign=\"top\">\n" +
            "                                        <table\n" +
            "                                          width=\"580\"\n" +
            "                                          style=\"\n" +
            "                                            width: 580px;\n" +
            "                                            border-spacing: 0;\n" +
            "                                            border-collapse: collapse;\n" +
            "                                            margin: 0px 10px 0px 10px;\n" +
            "                                          \"\n" +
            "                                          cellpadding=\"0\"\n" +
            "                                          cellspacing=\"0\"\n" +
            "                                          align=\"left\"\n" +
            "                                          border=\"0\"\n" +
            "                                          bgcolor=\"\"\n" +
            "                                          class=\"column column-0\"\n" +
            "                                        >\n" +
            "                                          <tbody>\n" +
            "                                            <tr>\n" +
            "                                              <td\n" +
            "                                                style=\"\n" +
            "                                                  padding: 0px;\n" +
            "                                                  margin: 0px;\n" +
            "                                                  border-spacing: 0;\n" +
            "                                                \"\n" +
            "                                              >\n" +
            "                                                <table\n" +
            "                                                  class=\"module\"\n" +
            "                                                  role=\"module\"\n" +
            "                                                  data-type=\"text\"\n" +
            "                                                  border=\"0\"\n" +
            "                                                  cellpadding=\"0\"\n" +
            "                                                  cellspacing=\"0\"\n" +
            "                                                  width=\"100%\"\n" +
            "                                                  style=\"table-layout: fixed\"\n" +
            "                                                  data-muid=\"d6628df0-8f24-439e-9923-264e845eac3c\"\n" +
            "                                                  data-mc-module-version=\"2019-10-22\"\n" +
            "                                                >\n" +
            "                                                  <tbody>\n" +
            "                                                    <tr>\n" +
            "                                                      <td\n" +
            "                                                        style=\"\n" +
            "                                                          padding: 16px 80px 0px\n" +
            "                                                            32px;\n" +
            "                                                          line-height: 40px;\n" +
            "                                                          text-align: inherit;\n" +
            "                                                        \"\n" +
            "                                                        height=\"100%\"\n" +
            "                                                        valign=\"top\"\n" +
            "                                                        bgcolor=\"\"\n" +
            "                                                        role=\"module-content\"\n" +
            "                                                      >\n" +
            "                                                        <div>\n" +
            "                                                          <div\n" +
            "                                                            style=\"\n" +
            "                                                              font-family: inherit;\n" +
            "                                                              text-align: inherit;\n" +
            "                                                            \"\n" +
            "                                                          >\n" +
            "                                                            <span\n" +
            "                                                              style=\"\n" +
            "                                                                font-size: 48px;\n" +
            "                                                                color: #ffffff;\n" +
            "                                                              \"\n" +
            "                                                              >" + headline + "</span\n" +
            "                                                            >\n" +
            "                                                          </div>\n" +
            "                                                          <div></div>\n" +
            "                                                        </div>\n" +
            "                                                      </td>\n" +
            "                                                    </tr>\n" +
            "                                                  </tbody>\n" +
            "                                                </table>\n" +
            "                                              </td>\n" +
            "                                            </tr>\n" +
            "                                          </tbody>\n" +
            "                                        </table>\n" +
            "                                      </td>\n" +
            "                                    </tr>\n" +
            "                                  </tbody>\n" +
            "                                </table>\n" +
            "                                <table\n" +
            "                                  class=\"module\"\n" +
            "                                  role=\"module\"\n" +
            "                                  data-type=\"text\"\n" +
            "                                  border=\"0\"\n" +
            "                                  cellpadding=\"0\"\n" +
            "                                  cellspacing=\"0\"\n" +
            "                                  width=\"100%\"\n" +
            "                                  style=\"table-layout: fixed\"\n" +
            "                                  data-muid=\"65921ceb-990e-4437-838c-c7b8ba7a420d\"\n" +
            "                                  data-mc-module-version=\"2019-10-22\"\n" +
            "                                >\n" +
            "                                  <tbody>\n" +
            "                                    <tr>\n" +
            "                                      <td\n" +
            "                                        style=\"\n" +
            "                                          padding: 24px 32px 32px 32px;\n" +
            "                                          line-height: 22px;\n" +
            "                                          text-align: inherit;\n" +
            "                                          background-color: #ffffff;\n" +
            "                                        \"\n" +
            "                                        height=\"100%\"\n" +
            "                                        valign=\"top\"\n" +
            "                                        bgcolor=\"#ffffff\"\n" +
            "                                        role=\"module-content\"\n" +
            "                                      >\n" +
            "                                        <div>\n" +
            "                                          <div\n" +
            "                                            style=\"\n" +
            "                                              font-family: inherit;\n" +
            "                                              text-align: inherit;\n" +
            "                                            \"\n" +
            "                                          >\n" +
            "                                            <br />\n" +
            "                                          </div>\n" +
            "                                          <div\n" +
            "                                            style=\"\n" +
            "                                              font-family: inherit;\n" +
            "                                              text-align: inherit;\n" +
            "                                            \"\n" +
            "                                          >\n" +
            "                                            <span\n" +
            "                                              style=\"\n" +
            "                                                font-style: normal;\n" +
            "                                                font-variant-ligatures: normal;\n" +
            "                                                font-variant-caps: normal;\n" +
            "                                                font-weight: 400;\n" +
            "                                                letter-spacing: normal;\n" +
            "                                                orphans: 2;\n" +
            "                                                text-align: justify;\n" +
            "                                                text-indent: 0px;\n" +
            "                                                text-transform: none;\n" +
            "                                                white-space: normal;\n" +
            "                                                widows: 2;\n" +
            "                                                word-spacing: 0px;\n" +
            "                                                -webkit-text-stroke-width: 0px;\n" +
            "                                                background-color: rgb(\n" +
            "                                                  255,\n" +
            "                                                  255,\n" +
            "                                                  255\n" +
            "                                                );\n" +
            "                                                text-decoration-style: initial;\n" +
            "                                                text-decoration-color: initial;\n" +
            "                                                float: none;\n" +
            "                                                display: inline;\n" +
            "                                                color: #322b5f;\n" +
            "                                                font-family: arial, helvetica,\n" +
            "                                                  sans-serif;\n" +
            "                                                font-size: 18px;\n" +
            "                                              \"\n" +
            "                                              >" + body + "</span\n" +
            "                                            >\n" +
            "                                          </div>\n" +
            "                                          <div></div>\n" +
            "                                        </div>\n" +
            "                                      </td>\n" +
            "                                    </tr>\n" +
            "                                  </tbody>\n" +
            "                                </table>\n" +
            "                                <table\n" +
            "                                  border=\"0\"\n" +
            "                                  cellpadding=\"0\"\n" +
            "                                  cellspacing=\"0\"\n" +
            "                                  class=\"module\"\n" +
            "                                  data-role=\"module-button\"\n" +
            "                                  data-type=\"button\"\n" +
            "                                  role=\"module\"\n" +
            "                                  style=\"table-layout: fixed\"\n" +
            "                                  width=\"100%\"\n" +
            "                                  data-muid=\"d99c9a07-0c86-4635-be8a-d4ed4b9271a3\"\n" +
            "                                >\n" +
            "                                  <tbody>\n" +
            "                                    <tr>\n" +
            "                                      <td\n" +
            "                                        align=\"left\"\n" +
            "                                        bgcolor=\"\"\n" +
            "                                        class=\"outer-td\"\n" +
            "                                        style=\"padding: 0px 0px 40px 32px\"\n" +
            "                                      >\n" +
            "                                        <table\n" +
            "                                          border=\"0\"\n" +
            "                                          cellpadding=\"0\"\n" +
            "                                          cellspacing=\"0\"\n" +
            "                                          class=\"wrapper-mobile\"\n" +
            "                                          style=\"text-align: center\"\n" +
            "                                        >\n" +
            "                                          <tbody>\n" +
            "                                            <tr>\n" +
            "                                              <td\n" +
            "                                                align=\"center\"\n" +
            "                                                bgcolor=\"#6AC88F\"\n" +
            "                                                class=\"inner-td\"\n" +
            "                                                style=\"\n" +
            "                                                  border-radius: 6px;\n" +
            "                                                  font-size: 16px;\n" +
            "                                                  text-align: left;\n" +
            "                                                  background-color: inherit;\n" +
            "                                                \"\n" +
            "                                              >\n" +
            "                                                <a\n" +
            "                                                  href=\"#\"\n" +
            "                                                  style=\"\n" +
            "                                                    color: #6AC88F;\n" +
            "                                                    display: inline-block;\n" +
            "                                                    font-size: 32px;\n" +
            "                                                    font-weight: 700;\n" +
            "                                                    letter-spacing: 0px;\n" +
            "                                                    line-height: 40px;\n" +
            "                                                    padding: 8px 16px 8px 16px;\n" +
            "                                                    text-align: center;\n" +
            "                                                    text-decoration: none;\n" +
            "                                                    font-family: arial,\n" +
            "                                                      helvetica, sans-serif;\n" +
            "                                                  \"\n" +
            "                                                  target=\"_blank\"\n" +
            "                                                  >" + token + "</a\n" +
            "                                                >\n" +
            "                                              </td>\n" +
            "                                            </tr>\n" +
            "                                          </tbody>\n" +
            "                                        </table>\n" +
            "                                      </td>\n" +
            "                                    </tr>\n" +
            "                                  </tbody>\n" +
            "                                </table>\n" +
            "                              </td>\n" +
            "                            </tr>\n" +
            "                          </table>\n" +
            "                          <!--[if mso]>\n" +
            "                                  </td>\n" +
            "                                </tr>\n" +
            "                              </table>\n" +
            "                            </center>\n" +
            "                          <![endif]-->\n" +
            "                        </td>\n" +
            "                      </tr>\n" +
            "                    </table>\n" +
            "                  </td>\n" +
            "                </tr>\n" +
            "              </table>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    </center>\n" +
            "  </body>\n" +
            "</html>\n";
  }
}
