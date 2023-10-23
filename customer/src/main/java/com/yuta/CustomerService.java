package com.yuta;

import com.yuta.clients.fraud.FraudCheckResponse;
import com.yuta.clients.fraud.FraudClient;
import com.yuta.clients.notification.NotificationClient;
import com.yuta.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient; // inject by enable open feign basedPackages in module main application
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        // todo: check if email is valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
        notificationClient.saveNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        "Hi, welcome to suck my dick"
                )
        );
    }
}
