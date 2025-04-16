package com.manch.launchpad.services;

import com.manch.launchpad.models.request.MicroserviceModel;

public interface MicroserviceService {
    MicroserviceModel createMicroservice(MicroserviceModel microserviceModel);
    MicroserviceModel getMicroserviceModel(Long microserviceId);
}
