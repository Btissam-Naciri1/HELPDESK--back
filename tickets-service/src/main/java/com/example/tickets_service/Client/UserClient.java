package com.example.tickets_service.Client;

import com.example.tickets_service.dto.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "User-Auth-service", path = "/auth")
public interface UserClient {
    @GetMapping("/users")
    List<AppUserDTO> getUsers(@RequestParam(required = false) String search);
}
