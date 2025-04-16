package com.manch.launchpad.services;

import com.manch.launchpad.models.MicroserviceModel;
import org.springframework.stereotype.Service;

@Service
public interface MicroserviceService {
    MicroserviceModel createMicroservice(MicroserviceModel microserviceModel);
}
