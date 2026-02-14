package com.laxman.job.server.cart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import com.laxman.job.server.cart.dto.AddToCartRequest;
import com.laxman.job.server.cart.model.CartItem;
import com.laxman.job.server.cart.repository.CartItemRepository;
import com.laxman.job.server.cart.service.impl.CartServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    void addToCart_shouldSaveItem() {

        // GIVEN
        AddToCartRequest request = new AddToCartRequest();
        request.setUsername("laxman");
        request.setProductid("P123");
        request.setProductName("Mouse");
        request.setPrice(75000);
        request.setQuntity(1);

        CartItem savedItem = new CartItem(
                1L,
                "laxman",
                "P123",
                "Mouse",
                75000,
                1
        );

        when(cartItemRepository.save(any(CartItem.class)))
                .thenReturn(savedItem);

        // WHEN
        CartItem result = cartService.addToCart(request);

        // THEN
        assertNotNull(result);
        assertEquals("Mouse", result.getProductName());
        assertEquals(1, result.getQuantity());

        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void getCartByUsername_shouldReturnItems() {

        CartItem item = new CartItem(
                1L,
                "laxman",
                "P123",
                "Mouse",
                75000,
                1
        );

        when(cartItemRepository.findByUsername("laxman"))
                .thenReturn(List.of(item));

        List<CartItem> items = cartService.getCartByUsername("laxman");

        assertEquals(1, items.size());
        assertEquals("Mouse", items.get(0).getProductName());
    }

    @Test
    void deleteCartItem_shouldCallRepository() {

        cartService.deleteCartItem(1L);

        verify(cartItemRepository, times(1)).deleteById(1L);
    }
}
