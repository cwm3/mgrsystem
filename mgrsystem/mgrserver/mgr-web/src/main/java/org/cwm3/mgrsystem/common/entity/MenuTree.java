package org.cwm3.mgrsystem.common.entity;

import lombok.Data;
import org.cwm3.mgrsystem.model.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mrcwm
 */
@Data
public class MenuTree<T> implements Serializable {

    private static final long serialVersionUID = 7681873362531265829L;

    private String id;
    private String icon;
    private String href;
    private String title;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<MenuTree<T>> childs = new ArrayList<>();
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Menu data;

}
