package com.tawdi7atnet.dto.client;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ClientResponseDto {
    Long id;
    String firstName;
    String lastName;
}
