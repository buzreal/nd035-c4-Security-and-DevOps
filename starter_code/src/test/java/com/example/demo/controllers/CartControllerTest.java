package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartController cartController;

    @Test
    public void testAddToCart_ItemNotFound() {
        // Arrange
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("john");
        request.setItemId(999L); // Nonexistent item ID
        request.setQuantity(2);

        User user = new User();
        user.setUsername("john");

        when(userRepository.findByUsername("john")).thenReturn(user);
        when(itemRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Cart> response = cartController.addTocart(request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
    }


    @Test
    public void testAddToCart_UserNotFound() {
        // Arrange
        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername("nonexistent");
        request.setItemId(1L);
        request.setQuantity(2);

        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        // Act
        ResponseEntity<Cart> response = cartController.addTocart(request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
    }


//    @Test
//    public void testAddToCart_Success() {
//        // Arrange
//        ModifyCartRequest request = new ModifyCartRequest();
//        request.setUsername("john");
//        request.setItemId(1L);
//        request.setQuantity(2);
//
//        User user = new User();
//        user.setUsername("john");
//        Cart cart = new Cart();
//        user.setCart(cart);
//
//        Item item = new Item();
//        item.setId(1L);
//
//        when(userRepository.findByUsername("john")).thenReturn(user);
//        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
//
//        // Act
//        ResponseEntity<Cart> response = cartController.addTocart(request);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(cart, response.getBody());
//        assertEquals(2, cart.getItems().size());
//    }
}
