import com.xiexin.bean.Human;
import com.xiexin.bean.HumanExample;
import com.xiexin.bean.Orders;
import com.xiexin.bean.Person;
import com.xiexin.dto.PersonDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.criteria.Order;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MybatisTest {


    private SqlSession sqlSession;
    @Before //  在@Test注解之前 执行的方法  提取重复的代码
        public void before() throws Exception {
        // 加载并读取 xml
        String path = "SqlMapConfig.xml";
        //  导包 import org.apache.ibatis.io.Resources;
        InputStream is = Resources.getResourceAsStream(path);
        // sql 链接的 工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession); //sqlSession = org.apache.ibatis.session.defaults.DefaultSqlSession@71423665

    }
    // 全查
    @Test
    public void test01(){
        List<Object> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectAll");
        for (Object person  : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //  单个查询
     @Test
    public void test02(){
         List<Object> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectPersonBySex",2);
         for (Object person  : personList) {
             System.out.println("person = " + person);
         }
         sqlSession.close();
     }

    //  查询总条数 这个主要学习的是 返回的数据类型，和上面的数据类型不一样
    @Test
    public void test03(){
        int o = sqlSession.selectOne("com.xiexin.dao.PersonDao.selectCount");
        System.out.println("i= " + o);

        sqlSession.close();
    }


    //  带参数查询 第一种方式: 实体类传参--多见于单表
    @Test
    public void test04(){
        Person person = new Person();
        person.setScore(100);
        person.setGender(2);
        int o = sqlSession.selectOne("com.xiexin.dao.PersonDao.selectCountByParam",person);
        System.out.println("i= " + o);
        sqlSession.close();
    }
    // 多参数查询  第二种方式  map 用于多表
    @Test
    public void test05() throws ParseException {
        String date = "2020-10-14";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = simpleDateFormat.parse(date);
        Map map = new HashMap();
        map.put("gender", 2);  // key 一定要跟#{gender} 值保持一致
        map.put("birthday", birthday); // key 一定要跟#{birthday} 值保持一致

        List<Object> objects = sqlSession.selectList("com.xiexin.dao.PersonDao.selectByParam2", map);
        for (Object object : objects) {
            System.out.println("object = " + object);
        }
    }

        //   查询 最高分
        @Test
        public void test06()  {
            List<Object> objects = sqlSession.selectList("com.xiexin.dao.PersonDao.max");
            for (Object object : objects) {
                System.out.println("object = " + object);
            }
            sqlSession.close();
        }
        //   查询所以女生平均分
        @Test
        public void test07()  {
            List<PersonDTO> personDTOS = sqlSession.selectList("com.xiexin.dao.PersonDao.avg");
            for (PersonDTO personDTO : personDTOS) {
                System.out.println("personDTO = " + personDTO);
            }
            sqlSession.close();
            }
    // 查询 分数大于100 且性别为1的
    @Test
    public void test08() {
        Map map = new HashMap();
        map.put("score", 100);
        map.put("gender", 1);
        List<Object> objects = sqlSession.selectList("com.xiexin.dao.PersonDao.selectByParam3", map);
        for (Object object : objects) {
            System.out.println("object = " + object);
        }
    }
    //   查询所以女生平均分 并且大于200的
    @Test
    public void test09()  {
        List<PersonDTO> personDTOS = sqlSession.selectList("com.xiexin.dao.PersonDao.selectAvgScoreParam",200);
        for (PersonDTO personDTO : personDTOS) {
            System.out.println("personDTO = " + personDTO);
        }
        sqlSession.close();
    }
    //  分组查询+ map接收
    @Test
    public void test10()  {
        List<Map> maps = sqlSession.selectList("com.xiexin.dao.PersonDao.selectAvgScoreParam02",20);//大于20就出来2个
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }
    //  模糊查询 姓孙的 不要用 拼接的方式去写$
    @Test
    public void test11()  {
        Map map = new HashMap();
        map.put("name","孙");
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectPersonByLike",map);
      // List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectPersonByLike","孙");//大于20就出来2个
        //There is no getter for property named 'name'  因为 $是拼接的，没有getter这个概念
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }
    //  模糊查询 姓孙的 可以用这个
    @Test
    public void test12(){
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectPersonByLike02","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
            sqlSession.close();
        }
    }

    //  模糊查询 姓孙的  常用  面试题: $ 和 # 的区别
    @Test
    public void test13(){
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectPersonByLike03","孙");
        for (Person person : personList) {
            System.out.println("person = " + person);
            sqlSession.close();
        }
    }

    // 以上是单表的 所有查询！！！ 这些例子很重要 公司用得到
    @Test

    public void test14(){
        Person person = new Person();
        person.setName("签哥");
        person.setGender(1);
        person.setBirthday(new Date());
        person.setAddress("加拿大");
        person.setScore(666);

        int insert = sqlSession.insert("com.xiexin.dao.PersonDao.insertPerson",person);
        System.out.println("insert = " + insert);
        sqlSession.commit();
        sqlSession.close();
    }

    //  删除一条数据
    @Test
    public void test15(){
        int delete = sqlSession.delete("com.xiexin.dao.PersonDao.deletePersonById", 17);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }

    // 动态sql 重点 难点
    // 动态sql其实就是 让达到 1条xml中的语句可以实现 N多种查询
    // 那么 要实现多种查询 就要硬性的条件! 你的参数越多  参数越多 就要放弃单个属性 intString ---->改用实体类  参数改用map

    //第一类，特征    1)返回----》正常表的结果集，对应的是person实体类
    //              2)都是select * from. person开头的
    // 1.1 select *. from personif.如果 where.后面投参数那么就是全查
    // 1.2 select * from person wheregender. = 2 if 如果where后面参数是gender. 那么就是 单查gender
    // 1.3 select * from person where gender=#{gender} and birthday>=#{birthday}
    // 1.4. select *. from person. where name like "%”#{name} "%"
    //  1-4可以合N为1.，. 只需要把where 后面的参数做个if判断


    //第二类:特征1) 返回值---->| 一个数，单行单列非person实体类， 是一个数据类型
    //   特征2 )都是.. select. count(*) from. person. 开头的
    //  2.1 select count(*) from person
    //  2.2 select count(*) from person where gender-2 and score>100

    //综上所述,以上的sql可以进行动态判断,形成一个sql!!!这就叫动态sql...

    //动态查询   16之前都是 基础的
    @Test
    public void test16(){
        Person person = new Person();
        // null 就是 全查
        // person.setId(16);  select * from person where id = ?
        person.setGender(2);
        person.setScore(200);
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.dongTaiSelect", person);
        for (Person person1 : personList) {
            System.out.println("person1 = " + person1);
        }
        sqlSession.close();
    }

    //动态修改   其实就是有选择的 修改多个字段 : 比如可以修改 姓名 生日 等等
    @Test
    public void test17(){

        Person person = new Person();
        person.setId(20);
        person.setScore(777);

        int update = sqlSession.update("com.xiexin.dao.PersonDao.dongTaiUpdate", person);
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();
    }

    // 批量删除 del xxx in (2,5,7,9)
    // 构造一个 ids
    @Test
    public void test18(){
        List<Integer> idList = new ArrayList<>();
        idList.add(16);
        idList.add(18);
        Map map = new HashMap();
        map.put("ids",idList);
        sqlSession.delete("com.xiexin.dao.PersonDao.piLiangDel",map );
        sqlSession.commit();
        sqlSession.close();
    }

    // 工具的查询
    @Test
    public  void test19(){
        // select . count(*). from. human
        // select count(*) from human where. gender = 2
        HumanExample example = new HumanExample();//创建一个 子类
        HumanExample.Criteria criteria = example.createCriteria(); //用例子类实现查询的规则或者标准

            // criteria.andAddressEqualTo("北京");
            // criteria.andScoreEqualTo(555.00);
            // criteria.andGenderEqualTo(2);   // select count(*) from human WHERE ( gender = ? )
            // criteria.andAddressEqualTo("西京") ; // select count(*) from human where gender = 2 and address like "%西京%"
            // 案例：  查询 地址 是 西京的人 有几个？
            // criteria.andGenderEqualTo(2);
            //  criteria.andAddressLike("%"+"西京"+"%"); // select count(*) from human WHERE ( gender = ? and address like ? )
            // 练习：  查询 家住在北京的 或者  分数是 555 的 人 有几个 ？
            // select * from human where address="北京"  or  score=555
            //example.or().andAddressEqualTo("北京");  // or 不要 criteria类
            // example.or().andScoreEqualTo(555.0); // select count(*) from human WHERE ( address = ? ) or( score = ? )
            // select * from human where id=1  or id=4 or id =5
            // 等于  select * from human where id in (1,4,5)   // select count(*) from human WHERE ( id in ( ? , ? , ? ) )
            List<Integer> ids=new ArrayList<>();
            ids.add(1);
            ids.add(4);
            ids.add(5);
            criteria.andIdIn(ids);
            // 当 example 的 criteria 不用 赋值的时候， 则是   Preparing: select count(*) from human
            long o = sqlSession.selectOne("com.xiexin.dao.HumanDAO.countByExample", example);
            System.out.println("o = " + o);
            sqlSession.close();
        }
        // 工具的增加
        @Test
        public void test20(){
            Human human = new Human();
            human.setName("刘晓云");
            human.setAddress("郑州");
            human.setGender(2);
            int insert = sqlSession.insert("com.xiexin.dao.HumanDAO.insertSelective", human);
            System.out.println("insert = " + insert);
            sqlSession.commit();
            sqlSession.close();
        }
    // 工具的删除
    @Test
    public void test21(){
        int delete = sqlSession.delete("com.xiexin.dao.HumanDAO.deleteByPrimaryKey", 5);
        System.out.println("delete = " + delete); // delete from human where id = ?
        sqlSession.commit();
        sqlSession.close();
    }
    // 条件删除
    //.条件删除:
    // 1.删除所有的女生!  2.删除分数小于20的3. 删除名字中带有云的人.. 4. 删除女生并且分数小于20的
    // 5.删除女生并且分数小于20的并且名字中含有带云的人
    @Test
    public void test22(){
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
       // criteria.andGenderEqualTo(2);
       // criteria.andScoreLessThan(20.0);
        criteria.andNameLike("%云%");
        int delete = sqlSession.delete("com.xiexin.dao.HumanDAO.deleteByExample", example);
        System.out.println("delete = " + delete);
        sqlSession.commit();
        sqlSession.close();
    }
    // 工具的动态修改  把孙悟空 修改为 猪八戒
    @Test
    public void test23(){
        Human human = new Human();
        human.setId(4);
        human.setName("猪八戒"); // update human SET `name` = ? where id = ?
        int update = sqlSession.update("com.xiexin.dao.HumanDAO.updateByPrimaryKeySelective", human);
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();
    }
    // 批量的动态修改 当分数超过100 的都改为100  测试不了
    @Test
    public void test24(){
        Human human = new Human();
           //     human.setScore(100.0);
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
        // criteria.andScoreGreaterThan(100.0);
        int update = sqlSession.update("com.xiexin.dao.HumanDAO.updateByExampleSelective", human);
        sqlSession.commit();
        sqlSession.close();
    }
    // 按住键 id 查
    @Test
    public void test25(){
        Human o = sqlSession.selectOne("com.xiexin.dao.HumanDAO.selectByPrimaryKey", 4);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    // 动态查询
    // 1.查询分数大于100的人
    // 2.查询分数大于100的人  并且生日 大于 2020-11-04的人
    @Test
    public void test26() throws ParseException {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2020-11-04");
        HumanExample example = new HumanExample();
        HumanExample.Criteria criteria = example.createCriteria();
      //  criteria.andScoreGreaterThan(100.0)  查询大于100的
      //  criteria.andBirthdayEqualTo(date);  这个是 等于 2020-11-04的
        List<Human> humans = sqlSession.selectList("com.xiexin.dao.HumanDAO.selectByExample", example);
        for (Human human : humans) {
            System.out.println("human = " + human);
        }
        sqlSession.close();
    }
    //  一对多
    @Test
    public void test27(){
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectOrdersByPersonId", 1);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //  一对多 对多 动态
    @Test
    public void test28(){
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selsectDetailByPersonId", 3);
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //  N表联查
    @Test
    public void test29(){
        Map map = new HashMap();
        map.put("id",3);
        List<Map> maps = sqlSession.selectList("com.xiexin.dao.PersonDao.selsectDetailByParam",map);
        for (Map map1 : maps) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }
    // 多对 1  反向 注意: 多方里写一方的 实体类
    @Test
    public void test30(){
        Orders o = sqlSession.selectOne("com.xiexin.dao.OrdersDAO.selectPersonByOrdersId",3);
        System.out.println("o = " + o);
        sqlSession.close();
    }
    // 多对多 ---- 可以看作是  带中间表 的 一对多 它是由 2个1对多组成的
    @Test
    public void test31(){
        List<Person> personList = sqlSession.selectList("com.xiexin.dao.PersonDao.selectRoleByName", "孙尚香");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    @Test
    public void test33(){
        Object o = sqlSession.selectOne("com.xiexin.dao.PersonDao.selectByPrimaryKey", 1);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    @Test
    public void test34(){
        Object o = sqlSession.selectOne("com.xiexin.dao.PersonDao.selectByParam", "hanzhu");
        System.out.println("o = " + o);
        sqlSession.close();
    }

    @Test
    public void test35(){
        Map map = new HashMap();
        map.put("name","hanzhu");
        map.put("pwd",123);
        List<Object> objects = sqlSession.selectList("com.xiexin.dao.PersonDao.selectByParam1", map);
        for (Object object : objects) {
            System.out.println("object = " + object);
        }

        sqlSession.close();
    }
}
