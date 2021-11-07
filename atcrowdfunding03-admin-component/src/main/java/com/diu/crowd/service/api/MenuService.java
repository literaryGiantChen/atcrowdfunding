package com.diu.crowd.service.api;

import com.diu.crowd.entity.Menu;

import java.util.List;

/**
 * @author DIU
 * @date 2021/11/6 16:24
 */
public interface MenuService {

    /**
     * 获取全部 菜单信息
     *
     * @return
     */
    public List<Menu> getAll();

    /**
     * 增加菜单信息
     *
     * @param menu 菜单信息
     * @return 受影响的行数
     */
    public int saveMenu(Menu menu);

    /**
     * 删除菜单为Id信息
     *
     * @param id 菜单id
     * @return 受影响的行数
     */
    public int removeMenu(Integer id);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     */
    public void updateMenu(Menu menu);
}
