package com.xiexin.dao;

import com.xiexin.bean.Human;
import com.xiexin.bean.HumanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HumanDAO {
    long countByExample(HumanExample example); //用example 类查总条数 动态的sql去查询总条数
    // 当example类 为null的时候，sql. 语句等于如下
    // select . count(*). from. human
    // 当example类 不为null的时候
    // select count(*) from human where. gender = 2参数如何传递呢

    int deleteByExample(HumanExample example); //  按照条件删除

    int deleteByPrimaryKey(Integer id); // 按照  主键 删除

    int insert(Human record);  // 当 human 对象 所以属性都在可以用他  就是一个普通的添加

    int insertSelective(Human record); //  尽量用他  !!!  优先使用这个方法 报错用上面的

    List<Human> selectByExample(HumanExample example); // 动态查询

    Human selectByPrimaryKey(Integer id); //  按主键 id查一个对象

    int updateByExampleSelective(@Param("record") Human record, @Param("example") HumanExample example); //动态的批量修改 用这个

    int updateByExample(@Param("record") Human record, @Param("example") HumanExample example); //动态的批量修改 不用

    int updateByPrimaryKeySelective(Human record); //按照主键id 修改 1个数据

    int updateByPrimaryKey(Human record);   // 千万不敢用 短的  要用就用长的 Selective !!!
}