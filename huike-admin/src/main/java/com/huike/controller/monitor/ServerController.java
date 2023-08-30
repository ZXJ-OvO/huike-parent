package com.huike.controller.monitor;

import com.huike.common.annotation.PreAuthorize;
import com.huike.domain.common.AjaxResult;
import com.huike.web.domain.server.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @ApiOperation("服务器监控")
    @PreAuthorize("monitor:server:list")
    @GetMapping
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
