package com.manch.launchpad.services.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.*;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.manch.launchpad.enums.DockerContainerStatusEnum;
import com.manch.launchpad.models.request.PortModel;
import com.manch.launchpad.models.request.ServiceModel;
import com.manch.launchpad.models.request.VolumeModel;
import com.manch.launchpad.services.DeploymentService;
import com.manch.launchpad.services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DockerDeploymentServiceImpl implements DeploymentService {
    private static DeploymentService instance;
    private DockerClient dockerClient;
    ServicesService servicesService;

    private DockerClientConfig setupDockerClientConfig(DockerClientConfig defaultConfig) {
        return new DefaultDockerClientConfig.Builder()
                .withDockerHost("unix:///var/run/docker.sock")
                .withApiVersion(defaultConfig.getApiVersion())
                .withRegistryUsername(defaultConfig.getRegistryUsername())
                .withRegistryPassword(defaultConfig.getRegistryPassword())
                .withRegistryEmail(defaultConfig.getRegistryEmail())
                .withRegistryUrl(defaultConfig.getRegistryUrl())
                .withCustomSslConfig(defaultConfig.getSSLConfig())
                .build();
    }

    private DockerDeploymentServiceImpl() throws URISyntaxException {
        DockerClientConfig config = this.setupDockerClientConfig(DefaultDockerClientConfig.createDefaultConfigBuilder().build());
        URI uri = new URI("unix:///var/run/docker.sock");
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(uri)
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        this.dockerClient = DockerClientImpl.getInstance(config, httpClient);
    }

    @Override
    public ServiceModel createService(ServiceModel service, List<VolumeModel> volume, List<PortModel> port) {
        System.out.println("YO");
        CreateContainerResponse containerResponse = this.dockerClient.createContainerCmd(service.getServiceImage())
                .withHostConfig(HostConfig.newHostConfig()
                        .withBinds(volume.stream()
                                .map(volumeModel -> new Bind(volumeModel.getVolumeSource(), new Volume(volumeModel.getVolumeDestination()), AccessMode.rw))
                                .collect(Collectors.toList()))
                        .withPortBindings(port.stream()
                                .map(portModel -> new PortBinding(Ports.Binding.bindPort(portModel.getPrivatePort()), ExposedPort.tcp(portModel.getPublicPort())))
                                .collect(Collectors.toList())))
                .withName(service.getName())
                .withEnv(service.getEnv())
                .exec();
        System.out.println("DSKIP");
        service.setServiceId(containerResponse.getId());
        return servicesService.updateService(service);
    }

    @Override
    public void runService(String serviceId) {             // Container ID here which is given by docker
        this.dockerClient.startContainerCmd(serviceId).exec();
    }

    @Override
    public List<String> listServiceIds() throws URISyntaxException, IOException, InterruptedException {
        List<Container> containers = this.dockerClient.listContainersCmd()
                .withStatusFilter(new ArrayList<>(
                        Arrays.asList(DockerContainerStatusEnum.created.toString(),
                                DockerContainerStatusEnum.exited.toString(),
                                DockerContainerStatusEnum.running.toString())
                ))
                .exec();

        return containers.stream()
                .map(Container::getId)
                .collect(Collectors.toList());
    }

    @Override
    public String getServiceIdFromName(String name) {
        return "";
    }

    @Override
    public void stopService(String serviceId) {
        this.dockerClient.stopContainerCmd(serviceId).exec();
    }

    @Override
    public void removeService(String serviceId) {
        this.dockerClient.removeContainerCmd(serviceId).exec();
        this.servicesService.removeServiceByServiceId(serviceId);
    }
}
