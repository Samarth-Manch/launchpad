package com.manch.launchpad.services;

import com.manch.launchpad.models.request.VolumeModel;

import java.util.List;

public interface VolumeService {
    VolumeModel createVolume(VolumeModel volumeModel);
    VolumeModel getVolume(String name);
    List<VolumeModel> getAllVolumesByServiceId(String serviceId);
}
