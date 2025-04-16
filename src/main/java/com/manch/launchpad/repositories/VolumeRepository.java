package com.manch.launchpad.repositories;

public interface VolumeRepository {
    void save(Volume volume);
    VolumeModel findByName(String name);
}
