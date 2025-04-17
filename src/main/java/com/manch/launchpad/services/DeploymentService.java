package com.manch.launchpad.services;

import com.manch.launchpad.models.request.PortModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.models.request.VolumeModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface DeploymentService {
    ServiceModel createService(ServiceModel service, List<VolumeModel> volume, List<PortModel> port);
    void runService(String serviceId);
    List<String> listServiceIds() throws URISyntaxException, IOException, InterruptedException;
    String getServiceIdFromName(String name);
    void stopService(String serviceId);
    void removeService(String serviceId);
    Map<String, String> getServiceStatus();
}