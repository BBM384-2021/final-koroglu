package org.koroglu.hobbydoge.service;

public interface EmailService {

  String sendResetMail(String to);

  void send(String to, String subject, String headline, String body, String token);
}
