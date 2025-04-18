package com.manch.launchpad.utility.tasks.scheduled;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.services.DeploymentService;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class DockerStatusChecker {
    DeploymentService deploymentService;
    ServicesService servicesService;

    @Scheduled(fixedRate = 5000)
    public void checkStatus() {
        Map<String, String> statusMap = deploymentService.getServiceStatus();
        statusMap.forEach((serviceId, status) -> {
            try {
                ServiceModel service = servicesService.getServiceByServiceId(serviceId);
                if(!service.getStatus().equals(status)) return;

                service.setStatus(status);
                servicesService.updateService(service);
            } catch (LaunchpadException ignored){
            }
        });
    }
}
