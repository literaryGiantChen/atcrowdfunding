package com.diu.crowd.service.impl;

import com.diu.crowd.entity.Menu;
import com.diu.crowd.entity.MenuExample;
import com.diu.crowd.mapper.MenuMapper;
import com.diu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DIU
 * @date 2021/11/6 16:25
 */
@Service
public class MenuServiceImpl implements MenuService {

    public MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public int saveMenu(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int removeMenu(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateMenu(Menu menu) {
        // 由于 pid 没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
        menuMapper.updateByPrimaryKeySelective(menu);
    }

}
