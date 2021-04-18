package ru.itis.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 18-04-2021 - 21:33
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokensRefreshDto {

    private String accessToken;

    private String refreshToken;

    private String deviceFingerprint;

}
