package com.zben.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.bo.AdminUserDetails;
import com.zben.mall.dto.UmsAdminParam;
import com.zben.mall.mapper.UmsAdminMapper;
import com.zben.mall.mapper.UmsAdminRoleRelationMapper;
import com.zben.mall.model.UmsAdmin;
import com.zben.mall.model.UmsAdminRoleRelation;
import com.zben.mall.model.UmsResource;
import com.zben.mall.model.UmsRole;
import com.zben.mall.security.util.JwtTokenUtil;
import com.zben.mall.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:UmsAdminService实现类
 * @author: zhouben
 * @date: 2020/5/21 0021 13:57
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    UmsAdminMapper adminMapper;
    @Autowired
    UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            //TODO: 添加登录记录
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin record = new UmsAdmin(){{setUsername(username);}};
        return adminMapper.selectOne(record);
    }


    /**
     * 获取指定用户的可访问权限
     * @param adminId
     * @return
     */
    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminRoleRelationMapper.getResourceList(adminId);
    }


    /**
     * 获取用户信息
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            //加载资源
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.orLike("username", "%" + keyword + "%");
            criteria.orLike("nickName", "%" + keyword + "%");
        }
        return adminMapper.selectByExample(example);
    }

    /**
     * 用户注册
     * @param param
     * @return
     */
    @Override
    public UmsAdmin register(UmsAdminParam param) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(param, umsAdmin);
        //查询是否有相同用户名的用户
        List<UmsAdmin> list = adminMapper.select(umsAdmin);
        if (!CollectionUtils.isEmpty(list)) {
            return null;
        }
        //将密码进行加密
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insertSelective(umsAdmin);
        return umsAdmin;
    }

    /**
     * 修改指定用户信息
     * @param id
     * @param admin
     * @return
     */
    @Override
    public boolean update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin umsAdmin = adminMapper.selectByPrimaryKey(id);
        if (umsAdmin == null) {
            log.warn("没有查询到用户信息");
            return Boolean.FALSE;
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminMapper.updateByPrimaryKeySelective(admin) > 0;
    }

    /**
     * 删除指定用户
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        int num = adminMapper.deleteByPrimaryKey(id);
        return num > 0;
    }

    /**
     * 获取指定用户的角色
     * @param adminId
     * @return
     */
    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationMapper.getRoleList(adminId);
    }

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional
    public boolean updateRole(Long adminId, List<Long> roleIds) {

        //先删除原有的关系
        UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
        roleRelation.setAdminId(adminId);
        adminRoleRelationMapper.deleteByAdminId(adminId);

        //建立新的关系
        roleIds.forEach(roleId -> {
            roleRelation.setRoleId(roleId);
            adminRoleRelationMapper.insertSelective(roleRelation);
        });
        return Boolean.TRUE;
    }

}
