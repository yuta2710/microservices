package com.yutaservices.notification;

import com.yuta.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public void saveNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.saveNotification(notificationRequest);
    }
}
