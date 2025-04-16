package com.manch.launchpad.services.impl;

import com.manch.launchpad.entities.VolumeEntity;
import com.manch.launchpad.models.request.VolumeModel;
import com.manch.launchpad.repositories.VolumeRepository;
import com.manch.launchpad.services.VolumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VolumeServiceImpl implements VolumeService {
    VolumeRepository volumeRepository;

    @Override
    public VolumeModel createVolume(VolumeModel volumeModel) {
        VolumeEntity volumeEntity = VolumeModel.toEntity(volumeModel);
        return VolumeModel.fromEntity(volumeRepository.save(volumeEntity));
    }

    @Override
    public VolumeModel getVolume(String name) {
        return VolumeModel.fromEntity(volumeRepository.findById(name));
    }

    @Override
    public List<VolumeModel> getAllVolumesByServiceId(String serviceId) {
        List<VolumeEntity> volumes = volumeRepository.findAllByServiceId(serviceId);
        return volumes.stream()
                .map(VolumeModel::fromEntity)
                .collect(Collectors.toList());
    }
}
