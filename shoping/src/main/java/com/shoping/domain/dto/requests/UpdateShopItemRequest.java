package com.shoping.domain.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateShopItemRequest {
    @NotBlank(message = "Название не может быть пустым")
    private String name;
}
