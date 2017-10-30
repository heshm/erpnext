package com.erpnext.common.param.domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty("id")
	private String menuId;

    @JsonProperty("name")
    private String menuName;

    private String actionUrl;

    private Long sequence;

    private String iconCls;

    private Boolean isLeaf;

    @JsonIgnore
    private String perm;
    
    @JsonIgnore
    private String parentId;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<Menu> children;

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

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	public static final class SeqOrder implements Comparator<Menu>{
		@Override
		public int compare(Menu o1, Menu o2) {
			return o1.sequence.compareTo(o2.sequence);
		}
	}


}