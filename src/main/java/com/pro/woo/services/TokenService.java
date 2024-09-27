package com.pro.woo.services;

import com.pro.woo.components.JwtTokenUtil;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.Token;
import com.pro.woo.models.User;
import com.pro.woo.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService  implements ITokenService {
    private static final int  MAX_TOKENS=3;

    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.expiration-refresh-token}")
    private int expirationRefreshToken;

    private final TokenRepository tokenRepository;
    @Override
    public Token addToken(User user, String token, boolean isMobileDevice) {
        List<Token> tokens = tokenRepository.findByUser(user);
        if(tokens.size()>=MAX_TOKENS){
            boolean hasNonMobileToken=!tokens.stream().allMatch(Token::isMobile);
            Token tokenToDelete;
            if(hasNonMobileToken){
                tokenToDelete=tokens.stream()
                        .filter(userToken ->!userToken.isMobile())
                      .findFirst()
                      .orElse(tokens.get(0));
            }
            else{
                tokenToDelete=tokens.get(0);
            }
            tokenRepository.delete(tokenToDelete);
        }
        Long expirationInSeconds=expiration;
        LocalDateTime expirationDateTime=LocalDateTime.now().plusSeconds(expirationInSeconds);

        Token newToken=Token.builder()
                .user(user)
                .tokenType("Bearer")
                .token(token)
                .revoked(false)
                .expired(false)
                .isMobile(isMobileDevice)
                .expirationDate(expirationDateTime)
                .build();
//        newToken.setRefreshToken(UUID.randomUUID().toString());
//        newToken.setRefreshExpirationDate(
//        LocalDateTime.now().plusSeconds(expirationRefreshToken));
//        tokenRepository.save(newToken);
//        return newToken;
        newToken.setRefreshToken(UUID.randomUUID().toString());
        newToken.setRefreshExpirationDate(LocalDateTime.now().plusSeconds(expirationRefreshToken));
        tokenRepository.save(newToken);
        return newToken;
    }

    @Override
    public Token refreshToken(String refreshToken, User user) throws Exception {
        Token existingToken=tokenRepository.findByRefreshToken(refreshToken);
        if(existingToken==null){
            throw new DataNotFoundException("Refresh token does not exist");
        }
        if(existingToken.getRefreshExpirationDate().compareTo(LocalDateTime.now())<0){
           tokenRepository.delete(existingToken);
            throw new DataNotFoundException("Refresh token expired");
        }
        String token=jwtTokenUtil.generateToken(user);
        LocalDateTime expirationDateTime=LocalDateTime.now().plusSeconds(expiration);
        existingToken.setExpirationDate(expirationDateTime);
        existingToken.setToken(token);
       // existingToken.setRefreshToken(UUID.randomUUID().toString());
        existingToken.setRefreshExpirationDate(LocalDateTime.now().plusSeconds(expirationRefreshToken));
        tokenRepository.save(existingToken);
        return existingToken;
    }

}
