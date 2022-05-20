package com.tawdi7atnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class CategoryDto implements Serializable {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 4, message = "le min est 4 caractères")
    @Size(max = 20, message = "le min est 20 caractères")
    private String name;

    private byte[] picByte;

}
