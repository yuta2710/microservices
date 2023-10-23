package com.yutaservices.notification;

import com.yuta.clients.fraud.FraudCheckResponse;
import com.yuta.clients.fraud.FraudClient;
import com.yuta.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final FraudClient fraudClient;

    public void saveNotification(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sentAt(LocalDateTime.now())
                .message(notificationRequest.message())
                .build();

        notificationRepository.save(notification);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(notification.getToCustomerId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("There is fraudster in this notification");
        }
    }
}
