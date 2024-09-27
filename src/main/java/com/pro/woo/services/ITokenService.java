package com.pro.woo.services;

import com.pro.woo.models.Token;
import com.pro.woo.models.User;

public interface ITokenService {
    Token addToken(User user,String token, boolean isMobileDevice);
    Token refreshToken(String refreshToken,User user) throws Exception;
}
