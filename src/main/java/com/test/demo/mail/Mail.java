package com.test.demo.mail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Mail {
    private @NotNull String from;
    private @NotNull String to;
    private String subject;
    private @NotNull String message;

}
