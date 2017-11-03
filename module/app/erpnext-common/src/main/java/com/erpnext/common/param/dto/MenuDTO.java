package com.erpnext.common.param.dto;

import java.util.List;

import com.erpnext.common.param.domain.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MenuDTO {
	
	@JsonProperty("id")
	private String menuId;

    @JsonProperty("name")
    private String menuName;

    private String actionUrl;

    private Long sequence;

    private String iconCls;
    
    private String perm;
    
    private Menu parentMenu;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<MenuDTO> children;
    
    public MenuDTO(){}
	
	public MenuDTO(Menu menu){
		this.menuId = menu.getMenuId();
		this.menuName = menu.getMenuName();
		this.actionUrl = menu.getActionUrl();
		this.sequence = menu.getSequence();
		this.iconCls = menu.getIconCls();
		this.perm = menu.getPerm();
	}
	
	public MenuDTO(Menu menu,Menu pMenu){
		this(menu);
		this.parentMenu = pMenu;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<MenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}
    
    

}
