package com.ljcoogan.knights.handler;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class exists so that, when authentication is successful, the app will redirect to the
 * frontend
 */

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Value("${frontend.url}")
  private String frontendUrl;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
      Authentication auth) throws ServletException, IOException {
    this.setDefaultTargetUrl(frontendUrl);
    this.setAlwaysUseDefaultTargetUrl(true);
    super.onAuthenticationSuccess(req, res, auth);
  }
}
