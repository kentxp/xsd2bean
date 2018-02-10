package com.wanghaorui.file;

import java.util.List;

/**
 * 文件夹节点数据
 */
public class DirectoryNode {
    private String text;
    private List<DirectoryNode> childNode;

    public DirectoryNode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DirectoryNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<DirectoryNode> childNode) {
        this.childNode = childNode;
    }
}
