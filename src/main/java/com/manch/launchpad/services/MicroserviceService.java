package com.manch.launchpad.services;

import com.manch.launchpad.enums.DeploymentServiceEnum;
import com.manch.launchpad.models.request.MicroserviceModel;
import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.ServiceModel;

import java.util.List;

public interface MicroserviceService {
    MicroserviceModel createMicroservice(MicroserviceModel microserviceModel);
    MicroserviceModel getMicroserviceModel(Long microserviceId);
    List<ServiceModel> getServicesOfMicroservice(Long microserviceId);
    MicroserviceModel updateMicroservice(MicroserviceModel microserviceModel);
    MicroserviceModel updateMicroserviceDeployment(Long microserviceId, DeploymentServiceEnum deployment);
    List<ServiceDependencyModel> getServiceDependencies(Long microserviceId);
}
