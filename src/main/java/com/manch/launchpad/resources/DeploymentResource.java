package com.manch.launchpad.resources;

import com.manch.launchpad.commons.responses.LaunchpadResponse;
import com.manch.launchpad.services.UpService;
import com.manch.launchpad.utility.commons.CommonUtilities;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/deployment")
@AllArgsConstructor
public class DeploymentResource {
    UpService upService;
    CommonUtilities commonUtilities;

    @RequestMapping(value = "/{microserviceId}/create", method = RequestMethod.POST)
    public LaunchpadResponse<?> createServices(@PathVariable Long microserviceId) {
        upService.createServices(microserviceId);
        return LaunchpadResponse.created(null);
    }

    @RequestMapping(value = "/{microserviceId}/run", method = RequestMethod.GET)
    public LaunchpadResponse<?> runServices(@PathVariable Long microserviceId) {
        upService.runServices(commonUtilities.createDependencyGraph(microserviceId));
        return LaunchpadResponse.ok(null);
    }
}
