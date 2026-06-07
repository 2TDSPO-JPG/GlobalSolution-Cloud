package com.fiap.astrocolony.lodistics.spring.dto.function;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String emailOrPin;
    private String senha;

}
