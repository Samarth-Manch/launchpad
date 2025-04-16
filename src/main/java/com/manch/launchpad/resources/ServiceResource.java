package com.manch.launchpad.resources;

import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/service")
@AllArgsConstructor
public class ServiceResource {
    ServicesService servicesService;

    @RequestMapping(method = RequestMethod.POST)
    public LaunchpadResponse<ServiceModel> createService(@RequestBody ServiceModel serviceModel) {
        ServiceModel service = servicesService.createService(serviceModel);
        return LaunchpadResponse.ok(service);
    }

    @RequestMapping(path = "/{serviceId}", method = RequestMethod.GET)
    public LaunchpadResponse<ServiceModel> getService(@PathVariable String serviceId) {
        return LaunchpadResponse.ok(servicesService.getService(serviceId));
    }
}
