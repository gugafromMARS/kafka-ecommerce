package gsc.projects.orderservice.service.order;


import gsc.projects.orderservice.dto.order.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8082/api/user", value = "USER-SERVICE")
public interface APIUser {

    @GetMapping("/{userEmail}")
    UserDto get(@PathVariable("userEmail") String userEmail);
}
