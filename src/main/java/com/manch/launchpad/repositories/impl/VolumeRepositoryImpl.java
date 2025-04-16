package com.manch.launchpad.repositories.impl;

import com.manch.launchpad.entities.VolumeEntity;
import com.manch.launchpad.repositories.VolumeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class VolumeRepositoryImpl implements VolumeRepository {
    EntityManager entityManager;

    @Override
    @Transactional
    public VolumeEntity save(VolumeEntity volume) {
        this.entityManager.persist(volume);
        return volume;
    }

    @Override
    public VolumeEntity findById(String id) {
        return this.entityManager.createQuery(
                "SELECT v FROM VolumeEntity v WHERE v.id = :id", VolumeEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<VolumeEntity> findAllByServiceId(String serviceId) {
        return this.entityManager.createQuery(
                "SELECT v FROM VolumeEntity v WHERE v.serviceId = :serviceId", VolumeEntity.class)
                .setParameter("serviceId", serviceId)
                .getResultList();
    }
}
