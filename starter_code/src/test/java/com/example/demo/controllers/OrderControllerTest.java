package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testSubmitOrder() {
        // Mock data
        String username = "john_doe";
        String password = "password";

        // Mocked user without using a constructor
        User mockUser = mock(User.class);
//        when(mockUser.getUsername()).thenReturn(username);
//        when(mockUser.getPassword()).thenReturn(password); Stubbing.

        Cart mockCart = mock(Cart.class); // Mock the Cart object
        when(mockUser.getCart()).thenReturn(mockCart); // Provide the mocked Cart when user.getCart() is called

        // Mock userRepository behavior
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Mock orderRepository behavior
        when(orderRepository.save(any(UserOrder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Perform the test
        ResponseEntity<UserOrder> responseEntity = orderController.submit(username);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UserOrder resultOrder = responseEntity.getBody();
        assertNotNull(resultOrder);

        // Verify that the userRepository.findByUsername method is called
        verify(userRepository, times(1)).findByUsername(username);

        // Verify that the orderRepository.save method is called
        verify(orderRepository, times(1)).save(any(UserOrder.class));
    }

}

