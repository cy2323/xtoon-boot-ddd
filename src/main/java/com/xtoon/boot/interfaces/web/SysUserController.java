package com.xtoon.boot.interfaces.web;

import com.xtoon.boot.interfaces.common.Page;
import com.xtoon.boot.infrastructure.util.log.SysLog;
import com.xtoon.boot.infrastructure.util.validator.ValidatorUtils;
import com.xtoon.boot.infrastructure.util.validator.group.AddGroup;
import com.xtoon.boot.infrastructure.util.validator.group.UpdateGroup;
import com.xtoon.boot.interfaces.common.AbstractController;
import com.xtoon.boot.interfaces.common.CommonConstant;
import com.xtoon.boot.interfaces.common.Result;
import com.xtoon.boot.interfaces.facade.SysUserServiceFacade;
import com.xtoon.boot.interfaces.facade.dto.UserDTO;
import com.xtoon.boot.interfaces.web.command.PasswordCommand;
import com.xtoon.boot.interfaces.web.command.UserCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author haoxin
 * @date 2021-02-20
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserServiceFacade sysUserServiceFacade;

    /**
     * 用户分页查询
     */
    @ApiOperation("用户分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = sysUserServiceFacade.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 获取登录的用户信息
     */
    @ApiOperation("获取登录的用户信息")
    @GetMapping("/info")
    public Result info(){
        return Result.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation("修改密码")
    @SysLog("修改密码")
    @PostMapping("/password")
    public Result changePassword(@RequestBody PasswordCommand passwordCommand){
        ValidatorUtils.validateEntity(passwordCommand);
        sysUserServiceFacade.changePassword(getUser().getId(),passwordCommand.getPassword(),passwordCommand.getNewPassword());
        return Result.ok();
    }

    /**
     * 用户信息
     */
    @ApiOperation("用户信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("id") String id){
        return Result.ok().put("user", sysUserServiceFacade.find(id));
    }

    /**
     * 保存用户
     */
    @ApiOperation("保存用户")
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, AddGroup.class);
        sysUserServiceFacade.save(new UserDTO(userCommand.getId(),userCommand.getUserName(),userCommand.getEmail(),userCommand.getMobile(),
                null,userCommand.getRoleIdList()));
        return Result.ok();
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, UpdateGroup.class);
        sysUserServiceFacade.update(new UserDTO(userCommand.getId(),userCommand.getUserName(),userCommand.getEmail(),userCommand.getMobile(),
                null,userCommand.getRoleIdList()));
        return Result.ok();
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody String[] userIds){
        sysUserServiceFacade.deleteBatch(Arrays.asList(userIds));
        return Result.ok();
    }

    /**
     * 禁用用户
     */
    @ApiOperation("禁用用户")
    @SysLog("禁用用户")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("sys:user:disable")
    public Result disable(@PathVariable("id") String id){
        sysUserServiceFacade.disable(id);
        return Result.ok();
    }
}
