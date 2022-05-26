package com.movie.api.controller;

import com.movie.api.constant.Roles;
import com.movie.api.model.entity.Role;
import com.movie.api.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对工作人员的权限管理
 * 所有接口都需要管理员权限验证
 */
@RestController
@Api(tags = "权限接口")
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/system")
    @ApiOperation("查看系统设置有哪些权限")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String[] listSystemRoles() {
        return Roles.roles;
    }

    @PostMapping("")
    @ApiOperation("添加权限")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Role create(@RequestBody Role role) throws Exception {
        return roleService.create(role);
    }

    @GetMapping("")
    @ApiOperation("查询员工的权限")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Role> listByWorkerId(String wid) {
        return roleService.listRolesByWorkerId(wid);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除权限")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@PathVariable String id) throws Exception {
        roleService.deleteById(id);
    }


}
