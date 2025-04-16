package com.manch.launchpad.services;


import com.manch.launchpad.models.request.PortModel;

import java.util.List;

public interface PortService {
    PortModel createPort(PortModel portModel);
    PortModel getPort(String id);
    List<PortModel> getPortsByServiceId(String serviceId);
}
