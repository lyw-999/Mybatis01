package com.xiexin.dao;

import java.util.List;
import java.util.Map;

public interface StudenDao {
    //1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
    List<Map> selectdemo01(Map map);
    // 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数
    List<Map> selectdemo02(Map map);
    //3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
    List<Map> selectdemo03(int s_score);
    //4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
    //        -- (包括有成绩的和无成绩的)
    List<Map> selectdemo04(int s_score);
    //5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
    List<Map> selectdemo05();
    // 6、查询"李"姓老师的数量   用like 模糊查询
    Integer selectdemo06(String t_name);
    //7、查询学过"张三"老师授课的同学的信息   四表联查
    List<Map> selectdemo07(String t_name);
    //8、查询没学过"张三"老师授课的同学的信息
    List<Map> selectdemo08(String t_name);
    //9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息
    List<Map> selectdemo09(Map map);
    //10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息
    List<Map> selectdemo10(Map map);
}
