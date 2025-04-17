package com.manch.launchpad.utility.commons;

import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.services.MicroserviceService;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CommonUtilities {
    MicroserviceService microserviceService;
    ServicesService servicesService;

    public HashMap<ServiceModel, List<ServiceModel>> createDependencyGraph(Long id) {
        List<ServiceDependencyModel> dependencyList = microserviceService.getServiceDependencies(id);
        HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph = new HashMap<>();
        Map<String, ServiceModel> serviceMap = microserviceService.getServicesOfMicroservice(id)
                .stream()
                .collect(Collectors.toMap(ServiceModel::getId, serviceModel -> serviceModel));

        for (ServiceModel serviceModel : serviceMap.values()) {
            serviceDependencyGraph.put(serviceModel, new ArrayList<>());
        }

        for (ServiceDependencyModel dependency : dependencyList) {
            List<ServiceModel> services = serviceDependencyGraph.get(
                    servicesService.getService(dependency.getDependentServiceId()));
            services.add(servicesService.getService(dependency.getRequiredServiceId()));
            serviceDependencyGraph.put(serviceMap.get(dependency.getDependentServiceId()), services);
        }

        return serviceDependencyGraph;
    }
}
