package com.shoping.controllers;

import com.shoping.domain.dto.requests.CreateShopItemRequest;
import com.shoping.domain.dto.requests.UpdateShopItemRequest;
import com.shoping.domain.models.entities.ShopItem;
import com.shoping.exceptions.ShopingException;
import com.shoping.repo.entity.ShopItemRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopItemController {
    private final ShopItemRepository shopItemRepository;

    @GetMapping("/api/v1/all/")
    public List<ShopItem> getAllShopItems() {
        return shopItemRepository.findAll();
    }

    @GetMapping("/api/v1/{id:[0-9]+}/")
    public ShopItem getShopItemById(@PathVariable long id) {
        return shopItemRepository.findById(id).orElseThrow(
                () -> new ShopingException("Не найден id")
        );
    }

    @PostMapping("/api/v1/")
    public ShopItem createShopItem(@RequestBody @Valid CreateShopItemRequest request) {
        ShopItem shopItem = ShopItem.builder()
                .name(request.getName())
                .build();
        shopItemRepository.save(shopItem);
        return shopItem;
    }

    @PostMapping("/api/v1/{id:[0-9]+}/")
    public ShopItem updateShopItem(@PathVariable long id, @RequestBody @Valid UpdateShopItemRequest request) {
        ShopItem shopItem = shopItemRepository.findById(id).orElseThrow(
                () -> new ShopingException("Не найден id")
        );
        shopItem.setName(request.getName());
        shopItemRepository.save(shopItem);
        return shopItem;
    }
}
