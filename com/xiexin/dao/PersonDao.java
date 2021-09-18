package com.xiexin.dao;

import com.xiexin.bean.Orders;
import com.xiexin.bean.Person;
import com.xiexin.dto.PersonDTO;

import java.util.List;
import java.util.Map;

public interface PersonDao {

    //全查
    List<Person> selectAll();
    // 根据 性别查询
    List<Person> selectPersonBySex(int sex);
    //List<Person> selectPersonBySex(int sex,String name);//mybatis 不支持
    // 查询总条数
    int selectCount();
    // 查询总条数+多个参数 第一种方式 2个都是person类中的属性 所以直接可以吧person当做参数
    // 实体类做参数
    int selectCountByParam(Person person);
    // 查 性别和生日  当查询出的sql语句不确定是唯一的一条的时候 一定要用list
    // 当多表联查的时候 请求的参数一定要为 map 或者是 自己写个实体类.   应用场景 多表联查的多参数查询
    List<Person> selectByParam2(Map map);

    //分值最高
    List<Person> max();
    //男生和女生的平均分
    List<PersonDTO> avg();
    // 男生和女生的平均值大于200 的
    List<PersonDTO> selectAvgScoreParam(int score);
    // 男生和女生的平均值大于200 的  使用 map做返回值
    List<Map> selectAvgScoreParam02(int score);
    //分值最高
    List<Person> selectByParam3(Map map);
   // 查询姓 孙的 第一种方式  不建议
    List<Person> selectPersonByLike (String name);
    // 查询姓 孙的 第二种方式
    List<Person> selectPersonByLike02( String name);
    // 查询姓 孙的 第三种方式
    List<Person> selectPersonByLike03( String name);


    // 增加的方法
    int insertPerson(Person person);
    // 删除的方法
    int deletePersonById(Integer id); //之后要讲解 动态sql
    //那么 dao层接口中 只有基础类型 int String 不好的  不方便执行动态sql 对以后扩展不方便
    // 以后自己写代码 参数一定是 一个实体类 或者是个map 或者DTO

    // 动态sql
    List<Person> dongTaiSelect(Person person); // 动态sql如果参数不是实体类不是集合 是个空参就没有任何意义
    // 长成 返回值是 List<实体类>  参数也是同样的的 实体类 他是个典型的动态sql语句

    // 动态的 修改
    int dongTaiUpdate(Person person);

    //批量删除
    void piLiangDel(Map map);

    // 1对多 方法写在  1方
    List<Person> selectOrdersByPersonId(Integer id);
    // 一对多对多  学校--班级--学生    省--小--县区. .，适用于下拉框
    List<Person> selsectDetailByPersonId(Integer id);
    // 三表联查 适用于 数据表格 参数就是map！！ 双 map 返回值和参数类型都是 map  俗称万能查.
    List<Map> selsectDetailByParam(Map map);

    // 多对多的查询  查询id 孙尚香都有哪些角色
    List<Person> selectRoleByName(String name);


    // 1. 对 account 表做   逆向生成 ，  2. 写出  account 表的  1） 按主键id查询的测试类
    //  Account selectByPrimaryKey(Integer ordersId);
    //2） 测试出  按 账户名查询的 测试类
   // List<Account> selectByParam(String name);
    //3） 测试出   用户名和密码 登录的测试类
   // List<Account> selectByParam1(Map map);
}

