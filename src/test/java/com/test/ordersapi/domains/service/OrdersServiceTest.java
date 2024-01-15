package com.test.ordersapi.domains.service;

import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.entity.Orders;
import com.test.ordersapi.domains.repository.OrdersRepository;
import com.test.ordersapi.domains.services.impl.OrdersServiceImpl;
import com.test.ordersapi.infrastructure.componente.MessagesComponent;
import com.test.ordersapi.infrastructure.configuration.MessagesConfiguration;
import com.test.ordersapi.infrastructure.exceptions.ConflictException;
import com.test.ordersapi.stubs.OrdersStub;
import com.test.ordersapi.stubs.SpecificationStub;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("Unit Test: Orders > Orders Service")
@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    private static final Long CONTROL_NUMBER = 2L;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private MessagesComponent messagesComponent;

    @Mock
    private MessagesConfiguration messages;

    private ResourceBundle rb;
    private Page<Orders> page;

    @BeforeEach
    void setup() {
        messagesComponent.setMessages(messages);
        page = new PageImpl<>(Collections.singletonList(OrdersStub.getEntity()), Pageable.ofSize(10), 1);
        rb = ResourceBundle.getBundle("messages");
    }

    @Nested
    class CreateOrdersTest {
        @DisplayName("createOrders() - Should Create Orders Without Errors")
        @ParameterizedTest
        @ValueSource(ints = {4, 5, 6, 9, 10, 11})
        void createOrders(Integer param) {
            OrdersBO ordersBO = OrdersStub.getBO();
            ordersBO.getOrdersListBO().get(0).setQuantity(new BigDecimal(param));
            Mockito.when(ordersRepository.findByControlNumber(CONTROL_NUMBER)).thenReturn(Optional.empty());
            Mockito.when(ordersRepository.save(Mockito.any(Orders.class))).thenReturn(OrdersStub.getEntity());

            ordersService.createOrders(ordersBO);

            Mockito.verify(ordersRepository).findByControlNumber(CONTROL_NUMBER);
            Mockito.verify(ordersRepository).save(Mockito.any(Orders.class));
        }

        @DisplayName("createOrders() - Should Return Conflict Exception When Control Number Already Exists")
        @Test
        void createOrdersConflictException() {
            Mockito.when(ordersRepository.findByControlNumber(CONTROL_NUMBER)).thenReturn(Optional.of(OrdersStub.getEntity()));

            Assertions.assertThrows(ConflictException.class, () -> ordersService.createOrders(OrdersStub.getBO()));

            Mockito.verify(ordersRepository).findByControlNumber(CONTROL_NUMBER);
            Mockito.verify(ordersRepository, Mockito.never()).save(Mockito.any(Orders.class));
        }
    }

    @Nested
    class FindAllOrdersTest {

        @DisplayName("findAllOrders() - Should Return Page Of Orders")
        @Test
        void findAllOrders() {
            Mockito.when(ordersRepository.findAll(any(Specification.class), (Pageable) any())).thenReturn(page);
            Mockito.when(ordersRepository.isEquals(any(), any())).thenReturn(new SpecificationStub<Orders>().equal());

            ordersService.findAllOrders(1L, LocalDate.now(), Pageable.ofSize(10));

            Mockito.verify(ordersRepository).findAll(any(Specification.class), (Pageable) any());
            Mockito.verify(ordersRepository, Mockito.times(2)).isEquals(any(), any());
        }
    }
}
