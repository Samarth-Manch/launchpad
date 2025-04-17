package com.manch.launchpad.services;

import com.manch.launchpad.enums.DeploymentServiceEnum;
import com.manch.launchpad.models.request.MicroserviceModel;

public interface MicroserviceService {
    MicroserviceModel createMicroservice(MicroserviceModel microserviceModel);
    MicroserviceModel getMicroserviceModel(Long microserviceId);
    MicroserviceModel updateMicroservice(MicroserviceModel microserviceModel);
    MicroserviceModel updateMicroserviceDeployment(Long microserviceId, DeploymentServiceEnum deployment);
}
