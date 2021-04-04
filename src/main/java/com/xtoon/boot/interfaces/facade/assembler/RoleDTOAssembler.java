package com.xtoon.boot.interfaces.facade.assembler;

import com.xtoon.boot.domain.model.Role;
import com.xtoon.boot.domain.model.types.PermissionId;
import com.xtoon.boot.domain.model.types.RoleCode;
import com.xtoon.boot.domain.model.types.RoleId;
import com.xtoon.boot.domain.model.types.RoleName;
import com.xtoon.boot.infrastructure.persistence.mybatis.entity.SysRoleDO;
import com.xtoon.boot.interfaces.facade.dto.RoleDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Assembler
 *
 * @author haoxin
 * @date 2021-02-18
 **/
public class RoleDTOAssembler {

    public static RoleDTO fromRole(final Role role) {
        final RoleDTO dto = new RoleDTO();
        dto.setId(role.getRoleId()==null?null:role.getRoleId().getId());
        dto.setRoleCode(role.getRoleCode()==null?null:role.getRoleCode().getCode());
        dto.setRoleName(role.getRoleName()==null?null:role.getRoleName().getName());
        dto.setRemarks(role.getRemarks());
        if(role.getPermissionIds() != null) {
            List<String> permissionIdList = new ArrayList<>();
            role.getPermissionIds().forEach(permissionId -> {
                permissionIdList.add(permissionId.getId());
            });
            dto.setPermissionIdList(permissionIdList);
        }
        dto.setStatus(role.getStatus()==null?null:role.getStatus().getValue());
        return dto;
    }

    public static Role toRole(final RoleDTO roleDTO) {
        RoleId roleId = null;
        if(roleDTO.getId() != null) {
            roleId = new RoleId(roleDTO.getId());
        }
        RoleCode roleCode = null;
        if(roleDTO.getRoleCode() != null) {
            roleCode = new RoleCode(roleDTO.getRoleCode());
        }
        RoleName roleName = null;
        if(roleDTO.getRoleName() != null) {
            roleName = new RoleName(roleDTO.getRoleName());
        }
        List<PermissionId> permissionIdList = null;
        if(roleDTO.getPermissionIdList() != null) {
            permissionIdList = new ArrayList<>();
            for(String permissionId:roleDTO.getPermissionIdList()) {
                permissionIdList.add(new PermissionId(permissionId));
            }
        }
        Role Role = new Role(roleId,roleCode,roleName,roleDTO.getRemarks(),null,permissionIdList);
        return Role;
    }

    public static RoleDTO getRoleDTO(final SysRoleDO sysRoleDO) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(sysRoleDO, roleDTO);
        return roleDTO;
    }

    public static List<RoleDTO> getRoleDTOList(final List<SysRoleDO> roles) {
        if(roles == null) {
            return null;
        }
        final List<RoleDTO> List = new ArrayList<>();
        for(SysRoleDO sysRoleDO : roles) {
            List.add(getRoleDTO(sysRoleDO));
        }
        return List;
    }
}
