package com.erpnext.common.param.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.util.IDUtils;
import com.erpnext.framework.web.util.WebConst;
import com.erpnext.common.param.domain.Menu;
import com.erpnext.common.param.domain.MenuXref;
import com.erpnext.common.param.mapper.MenuMapper;
import com.erpnext.common.param.mapper.MenuXrefMapper;

/**
 * @author Heshm
 */
@Service
@Transactional(readOnly=true) 
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuXrefMapper menuXrefMapper;
	
	@Override
	//@Cacheable(cacheNames="sysCache",key="'menuAll_' + #menuId")
	public Menu readAllMenuById(String menuId) {
		Menu menu = menuMapper.selectByMenuId(menuId);
		if (null != menu && !menu.getIsLeaf()){
			List<Menu> menuList = new ArrayList<Menu>();
			for(Menu childMenu : menu.getChildren()){
				menuList.add(readAllMenuById(childMenu.getMenuId()));
			}
			menu.setChildren(menuList);
		}
		return menu;
	}

	@Override
	public Menu readOneMenuById(String menuId) {
		return menuMapper.selectByMenuId(menuId);
	}

	@Override
	//@Cacheable(cacheNames="sysCache",key="'menuParent_' + #menuId")
	public Menu readParentMenu(String menuId) {
		String parentMenuId = menuXrefMapper.selectOne(menuId).getMenuId();
		return menuMapper.selectByPrimaryKey(parentMenuId);
	}
	
	@Override
	//@Cacheable(cacheNames="sysCache")
	public List<String> readParentMenu(String[] menuIdArray) {
		// TODO Auto-generated method stub
		List<MenuXref> menuXrefList = menuXrefMapper.selectListByCmenu(menuIdArray);
		int len = menuXrefList.size();
		List<String> pIdList = new ArrayList<String>(len);
		for(int i = 0; i < len; i++){
			pIdList.add(menuXrefList.get(i).getMenuId());
		}
		return pIdList;
	}


	@Override
	public Menu readByPrimaryKey(String menuId) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW) 
	@Override
	//@CacheEvict(cacheNames="sysCache",allEntries=true)
	public void savaMenu(Menu menu) {
		if(StringUtils.isEmpty(menu.getMenuId())){
			menu.setMenuId(IDUtils.uuid());
			menuMapper.insert(menu);
			MenuXref menuXref = new MenuXref();
			menuXref.setMenuId(menu.getParent().getMenuId());
			menuXref.setChildMenuId(menu.getMenuId());
			menuXrefMapper.insert(menuXref);
		}else{
			menuMapper.updateByPrimaryKey(menu);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW) 
	//@CacheEvict(cacheNames="sysCache",allEntries=true)
	public void deleteMenu(String menuId) {
		Menu menu = readAllMenuById(menuId);
		Set<String> menuSet = listMenuId(menu);
		for (String id : menuSet){
			menuXrefMapper.deleteByMenuId(id, null);
			menuXrefMapper.deleteByMenuId(null, id);
			menuMapper.deleteByPrimaryKey(id);
		}
				
	}
	
	
	private Set<String> listMenuId(Menu menu){
		Set<String> menuSet = new HashSet<String>();
		menuSet.add(menu.getMenuId());
		for(Menu childMenu : menu.getChildren()){
			menuSet.addAll(listMenuId(childMenu));
		}
		return menuSet;
	}

	@Override
	public List<Menu> readMenuList(String menuId) {
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menu = menuMapper.selectByPrimaryKey(menuId);
		if(!WebConst.ROOT.equals(menuId)){
			MenuXref menuXref = menuXrefMapper.selectOne(menuId);
			Menu pMenu = new Menu();
			pMenu.setMenuId(menuXref.getMenuId());
			menu.setParent(pMenu);
		}
		menuList.add(menu);
		List<MenuXref> listMenuXref = menuXrefMapper.selectList(menuId, null);
		if(listMenuXref != null){
			for(MenuXref cMenu : listMenuXref){
				menuList.addAll(readMenuList(cMenu.getChildMenuId()));
			}
		}
		return menuList;
	}

	@Override
	public List<Menu> readAuthApp(User user) {
		List<MenuXref> menuXrefList = menuXrefMapper.selectList(WebConst.ROOT, null);
		Collection<GrantedAuthority> auths = user.getAuthorities();
		List<Menu> authApp = new ArrayList<Menu>(16);
		for(MenuXref menuXref : menuXrefList){
			Menu menu = readByPrimaryKey(menuXref.getChildMenuId());
			String perm = menu.getPerm();
			if(!StringUtils.isEmpty(perm)){
				for(GrantedAuthority auth : auths){
					if(perm.equals(auth.getAuthority())){
						authApp.add(menu);
						break;
					}
	            }
			}else{
				authApp.add(menu);
			}
            
		}
		Collections.sort(authApp, new Menu.SeqOrder());
		return authApp;
	}

}
