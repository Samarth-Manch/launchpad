package com.manch.launchpad.services;


import com.manch.launchpad.models.request.PortModel;

public interface PortService {
    PortModel createPort(PortModel portModel);
    PortModel getPort(String id);
    PortModel getPortsByServiceId(String serviceId);
}
