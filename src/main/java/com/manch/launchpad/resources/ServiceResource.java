package com.manch.launchpad.resources;

import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.PortModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.models.request.VolumeModel;
import com.manch.launchpad.services.PortService;
import com.manch.launchpad.services.ServicesService;
import com.manch.launchpad.services.VolumeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/service")
@AllArgsConstructor
public class ServiceResource {
    ServicesService servicesService;
    VolumeService volumeService;
    PortService portService;

    @RequestMapping(method = RequestMethod.POST)
    public LaunchpadResponse<ServiceModel> createService(@RequestBody ServiceModel serviceModel) {
        return LaunchpadResponse.created(servicesService.createService(serviceModel));
    }

    @RequestMapping(path = "/{serviceId}", method = RequestMethod.GET)
    public LaunchpadResponse<ServiceModel> getService(@PathVariable String serviceId) {
        return LaunchpadResponse.ok(servicesService.getService(serviceId));
    }

    @RequestMapping(path = "/dependency", method = RequestMethod.POST)
    public LaunchpadResponse<ServiceDependencyModel> createServiceDependency(@RequestBody ServiceDependencyModel serviceDependencyModel) {
        return LaunchpadResponse.created(servicesService.createDependency(serviceDependencyModel));
    }

    @RequestMapping(path = "/volumes", method = RequestMethod.POST)
    public LaunchpadResponse<VolumeModel> createVolume(@RequestBody VolumeModel volumeModel) {
        return LaunchpadResponse.created(volumeService.createVolume(volumeModel));
    }

    @RequestMapping(path = "/{serviceId}/volumes", method = RequestMethod.GET)
    public LaunchpadResponse<List<VolumeModel>> getVolumes(@PathVariable String serviceId) {
        return LaunchpadResponse.ok(volumeService.getAllVolumesByServiceId(serviceId));
    }

    @RequestMapping(path = "/{volumeId}/volume", method = RequestMethod.GET)
    public LaunchpadResponse<VolumeModel> getVolume(@PathVariable String volumeId) {
        return LaunchpadResponse.ok(volumeService.getVolume(volumeId));
    }

    @RequestMapping(path = "/ports", method = RequestMethod.POST)
    public LaunchpadResponse<PortModel> createPort(@RequestBody PortModel portModel) {
        return LaunchpadResponse.created(portService.createPort(portModel));
    }

    @RequestMapping(path = "/{portId}/port", method = RequestMethod.GET)
    public LaunchpadResponse<PortModel> getPort(@PathVariable String portId) {
        return LaunchpadResponse.ok(portService.getPort(portId));
    }

    @RequestMapping(path = "/{serviceId}/ports", method = RequestMethod.GET)
    public LaunchpadResponse<List<PortModel>> getPorts(@PathVariable String serviceId) {
        return LaunchpadResponse.ok(portService.getPortsByServiceId(serviceId));
    }
}
