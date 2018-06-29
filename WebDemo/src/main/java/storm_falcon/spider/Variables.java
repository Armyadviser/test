package storm_falcon.spider;

import java.util.List;

/**
 * 描述一个DOM节点的权重信息
 */
public class Variables {

    /**
     * 占用面积百分比
     */
    double area;

    /**
     * 层次深度、该节点是第几代
     */
    int level;

    /**
     * 重复出现次数
     */
    int appearTime;

    /**
     * a标签连接文字长度
     */
    List<Integer> linkTextLengths;

    /**
     * 链接文字平均长度
     */
    double avgLinkTextLength;

    /**
     * 是否含有子节点
     */
    boolean hasChildren;
}
