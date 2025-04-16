package com.manch.launchpad.resources;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.models.MicroserviceModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/microservice")
public class MicroserviceResource {

    @RequestMapping("/{microserviceId}")
    public LaunchpadResponse<MicroserviceModel> getMicroservice(@PathVariable String microserviceId) {
        if (microserviceId == null || microserviceId.isEmpty()) {
            throw new LaunchpadException(ResponseInfoEnum.BAD_REQUEST, null);
        }
        MicroserviceModel microserviceModel = MicroserviceModel.builder()
                .id(1)
                .microserviceName("My Microservice")
                .userId("123")
                .build();
        return LaunchpadResponse.ok(microserviceModel) ;
    }
}
