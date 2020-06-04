package com.zben.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.mapper.UmsMenuMapper;
import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsMenuNode;
import com.zben.mall.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @DESC:MenuService实现
 * @author: zhouben
 * @date: 2020/5/27 0027 14:04
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    UmsMenuMapper menuMapper;

    /**
     * 分页查询后台菜单
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsMenu.class);
        example.createCriteria().andEqualTo("parentId", parentId);
        example.orderBy("sort").desc();
        return menuMapper.selectByExample(example);
    }

    /**
     * 根据ID查询菜单详情
     * @param id
     * @return
     */
    @Override
    public UmsMenu getItem(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加后台菜单
     * @param umsMenu
     * @return
     */
    @Override
    public boolean create(UmsMenu umsMenu) {
        //更新level
        updateLevel(umsMenu);
        return menuMapper.insertSelective(umsMenu) == 1;
    }

    /**
     * 根据ID删除后台菜单
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     * @return
     */
    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        UmsMenu menu = new UmsMenu(){{setId(id);setHidden(hidden);}};
        return menuMapper.updateByPrimaryKeySelective(menu) == 1;
    }

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    @Override
    public boolean update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        if (id == umsMenu.getParentId()) {
            umsMenu.setId(null);
            return menuMapper.insertSelective(umsMenu) == 1;
        }
        return menuMapper.updateByPrimaryKeySelective(umsMenu) == 1;
    }

    /**
     * 树形结构返回所有菜单列表
     * @return
     */
    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectAll();
        List<UmsMenuNode> result = menuList.stream()
                .filter(umsMenu -> umsMenu.getParentId().equals(0L))
                .map(umsMenu -> convertMenuNode(umsMenu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 将umsMenu转化为UmsMenuNode并设置children属性
     * @param umsMenu
     * @param menuList
     * @return
     */
    private UmsMenuNode convertMenuNode(UmsMenu umsMenu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(umsMenu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(umsMenu.getId()))
                .map(subMenu -> convertMenuNode(subMenu, menuList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    /**
     * 修改菜单层级
     * @param umsMenu
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if (parentMenu != null){
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }
}
