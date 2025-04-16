package com.manch.launchpad.resources;

import com.manch.launchpad.commons.exceptions.LaunchpadException;
import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.commons.responses.ResponseInfoEnum;
import com.manch.launchpad.models.request.MicroserviceModel;
import com.manch.launchpad.services.MicroserviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/microservice")
@AllArgsConstructor
public class MicroserviceResource {
    MicroserviceService microserviceService;

    @RequestMapping(method = RequestMethod.POST)
    public LaunchpadResponse<MicroserviceModel> createMicroservice(@RequestBody MicroserviceModel microserviceModel) {
        MicroserviceModel microservice = microserviceService.createMicroservice(microserviceModel);
        return LaunchpadResponse.ok(microservice);
    }

    @RequestMapping(value = "/{microserviceId}", method = RequestMethod.GET)
    public LaunchpadResponse<MicroserviceModel> getMicroservice(@PathVariable int microserviceId) {
        if (microserviceId == 0) {
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
