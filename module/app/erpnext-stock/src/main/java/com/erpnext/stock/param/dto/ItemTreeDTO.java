package com.erpnext.stock.param.dto;

import java.util.List;

/**
 * Created by Heshm on 2017/6/14.
 */
public class ItemTreeDTO {

    private String id;

    private String name;

    private boolean isParent;

    private List<ItemTreeDTO> children;

    public ItemTreeDTO(){

    }

    public ItemTreeDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemTreeDTO(String id, String name, boolean isParent) {
        this.id = id;
        this.name = name;
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent){
        this.isParent = isParent;
    }

    public List<ItemTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ItemTreeDTO> children) {
        this.children = children;
    }
}
