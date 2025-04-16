package com.manch.launchpad.repositories;

import com.manch.launchpad.entities.VolumeEntity;
import com.manch.launchpad.models.request.VolumeModel;

import java.util.List;

public interface VolumeRepository {
    VolumeEntity save(VolumeEntity volume);
    VolumeEntity findById(String id);
    List<VolumeEntity> findAllByServiceId(String serviceId);
}
