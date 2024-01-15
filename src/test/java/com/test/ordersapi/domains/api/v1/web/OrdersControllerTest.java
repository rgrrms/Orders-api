package com.test.ordersapi.domains.api.v1.web;

import com.test.ordersapi.domains.business.OrdersBO;
import com.test.ordersapi.domains.business.SingleOrderBO;
import com.test.ordersapi.domains.services.impl.OrdersServiceImpl;
import com.test.ordersapi.stubs.OrdersStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Unit test: Orders > Orders Controller")
@ExtendWith(MockitoExtension.class)
public class OrdersControllerTest {

    private static final String PATH = "/orders";

    @InjectMocks
    private OrdersController ordersController;

    @Mock
    private OrdersServiceImpl ordersService;

    private MockMvc mockMvc;
    private Page<SingleOrderBO> pageComplete;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ordersController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        pageComplete = new PageImpl<>(Collections.singletonList(OrdersStub.getSingleOrderBO()), Pageable.ofSize(10), 1);
    }

    @DisplayName("searchOrders() - Should Return 200 OK And A Orders List")
    @Test
    void searchOrders() throws Exception {
        Mockito.when(ordersService.findAllOrders(any(), any(), any(Pageable.class))).thenReturn(pageComplete);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("saveOrders() - Should Return 201 Created")
    @Test
    void saveOrders() throws Exception {
        Mockito.when(ordersService.createOrders(any(OrdersBO.class))).thenReturn(OrdersStub.getBO());

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(OrdersStub.getRequest()))
                .andDo(print()).andExpect(status().isCreated());
    }
}
