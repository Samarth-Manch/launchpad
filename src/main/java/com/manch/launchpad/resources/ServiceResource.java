package com.manch.launchpad.resources;

import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.models.request.PortModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.models.request.VolumeModel;
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

    @RequestMapping(path = "/volumes", method = RequestMethod.POST)
    public LaunchpadResponse<VolumeModel> createVolume(@RequestBody VolumeModel volumeModel) {
        return null;
    }

    @RequestMapping(path = "/volumes/{serviceId}", method = RequestMethod.GET)
    public LaunchpadResponse<VolumeModel> getVolumes(@PathVariable String serviceId) {
        return null;
    }

    @RequestMapping(path = "/volumes/{volumeId}", method = RequestMethod.GET)
    public LaunchpadResponse<VolumeModel> getVolume(@PathVariable String volumeId) {
        return null;
    }

    @RequestMapping(path = "/ports", method = RequestMethod.POST)
    public LaunchpadResponse<PortModel> createPort(@RequestBody PortModel portModel) {
        return null;
    }

    @RequestMapping(path = "/ports/{portId}}", method = RequestMethod.GET)
    public LaunchpadResponse<PortModel> getPort(@PathVariable String portId) {
        return null;
    }

    @RequestMapping(path = "/volumes/{serviceId}", method = RequestMethod.GET)
    public LaunchpadResponse<VolumeModel> getPorts(@PathVariable String serviceId) {
        return null;
    }
}
