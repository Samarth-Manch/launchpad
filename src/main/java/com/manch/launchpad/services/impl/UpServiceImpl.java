package com.manch.launchpad.services.impl;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.models.request.*;
import com.manch.launchpad.services.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Service
@AllArgsConstructor
public class UpServiceImpl implements UpService {
    DeploymentService deploymentService;
    ServicesService servicesService;
    MicroserviceService microserviceService;
    VolumeService volumeService;
    PortService portService;

    private static void DFS(ServiceModel node, HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph,
                            HashMap<ServiceModel, Boolean> visited, Stack<ServiceModel> stack) {
        visited.put(node, true);
        List<ServiceModel> neighbors = serviceDependencyGraph.get(node);
        if (neighbors != null) {
            for (ServiceModel it : neighbors) {
                if (!visited.get(it)) DFS(it, serviceDependencyGraph, visited, stack);
            }
        }
        stack.push(node);
    }

    private static List<ServiceModel> topologicalSort(HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph) {
        List<ServiceModel> servicesInTopologicalOrder = new ArrayList<>();
        Stack<ServiceModel> stack = new Stack<>();
        HashMap<ServiceModel, Boolean> visited = new HashMap<>();
        for (ServiceModel node : serviceDependencyGraph.keySet()) {
            visited.put(node, false);
        }

        for (ServiceModel node : serviceDependencyGraph.keySet()) {
            if (!visited.get(node)) {
                DFS(node, serviceDependencyGraph, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            servicesInTopologicalOrder.add(stack.pop());
        }

        return servicesInTopologicalOrder;
    }

    private static boolean cycleDetection(HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph,
                                          HashMap<ServiceModel, Integer> visited,
                                          ServiceModel node) {
        visited.put(node, 2);
        List<ServiceModel> neighbors = serviceDependencyGraph.get(node);
        if (neighbors != null) {
            for (ServiceModel neighbor : neighbors) {
                if (visited.get(neighbor) != null && visited.get(neighbor) == 2) {
                    return true;
                }

                if (visited.get(neighbor) == null || visited.get(neighbor) == 1) {
                    if (cycleDetection(serviceDependencyGraph, visited, neighbor)) {
                        return true;
                    }
                }
            }
        }
        visited.put(node, 3);
        return false;
    }

    private static boolean isThereCircularDependency(HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph) {
        HashMap<ServiceModel, Integer> visited = new HashMap<>();
        for (ServiceModel node : serviceDependencyGraph.keySet()) {
            visited.put(node, 1);
        }

        for (ServiceModel node : serviceDependencyGraph.keySet()) {
            if (visited.get(node) == 1) {
                if (cycleDetection(serviceDependencyGraph, visited, node)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Map<ServiceModel, List<ServiceModel>> createDependencyGraph(Long id,
                                                                               List<ServiceDependencyModel> dependencyList,
                                                                               MicroserviceService microserviceService,
                                                                               ServicesService servicesService) {
        Map<ServiceModel, List<ServiceModel>> serviceDependencyGraph = new HashMap<>();
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

    @Override
    public void createServices(Long microserviceId) {
        List<ServiceModel> services = this.microserviceService.getServicesOfMicroservice(microserviceId);
        Map<ServiceModel, List<VolumeModel>> volumeMap = services.stream()
                .collect(Collectors.toMap(serviceModel -> serviceModel,
                        serviceModel -> volumeService.getAllVolumesByServiceId(serviceModel.getId())));

        Map<ServiceModel, List<PortModel>> portMap = services.stream()
                .collect(Collectors.toMap(serviceModel -> serviceModel,
                        serviceModel -> portService.getPortsByServiceId(serviceModel.getId())));

        for (ServiceModel service : services) {
            List<VolumeModel> volumes = volumeMap.getOrDefault(service, Collections.emptyList());
            List<PortModel> ports = portMap.getOrDefault(service
                    , Collections.emptyList());
            deploymentService.createService(service, volumes, ports);
        }
    }

    @Override
    public void runServices(HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph) {
        if (isThereCircularDependency(serviceDependencyGraph)) {
            throw new LaunchpadException(ResponseInfoEnum.BAD_REQUEST, "Circular dependency found. Fix circular dependency before running the services");
        }

        List<ServiceModel> servicesInTopologicalOrder = topologicalSort(serviceDependencyGraph);
        for (ServiceModel service : servicesInTopologicalOrder) {
            deploymentService.runService(service.getServiceId());
        }
    }
}
