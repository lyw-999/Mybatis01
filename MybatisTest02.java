import com.xiexin.dto.StudentDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest02 {

    private SqlSession sqlSession;
    @Before //  在@Test注解之前 执行的方法  提取重复的代码
    public void before() throws Exception {
        // 加载并读取 xml
        String path = "SqlMapConfig1.xml";
        //  导包 import org.apache.ibatis.io.Resources;
        InputStream is = Resources.getResourceAsStream(path);
        // sql 链接的 工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession); //sqlSession = org.apache.ibatis.session.defaults.DefaultSqlSession@71423665

    }
    // 题1: 查询"01"课程比"02"课程成绩高的学生的信息及课程分数  使用map 返回值 和map参数
    @Test
    public void test01(){
        Map map=new HashMap();
        map.put("c_id1","01");
        map.put("c_id2","02");
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo02",map);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }

    // 题2: 查询"01"课程比"02"课程成绩低的学生的信息及课程分数  使用map 返回值 和map参数
    @Test
    public void test02(){
        Map map=new HashMap();
        map.put("c_id1","01");
        map.put("c_id2","02");
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo01",map);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    // 题3:查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩  使用map,有一个参数
    @Test
    public void test03(){
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo03",60);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    // 题4: 查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩 使用map,有一个参数
    @Test
    public void test04(){
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo04",60);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    //题5：查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
    @Test
    public void test05(){
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo05");
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    //题6：查询"李"姓老师的数量   用like 模糊查询
    @Test
    public void test06(){
        Integer ss=sqlSession.selectOne("com.xiexin.dao.StudentDao.selectdemo06","李");
        System.out.println("ss = " + ss);
        sqlSession.close();
    }
    //题7：查询学过"张三"老师授课的同学的信息   四表联查
    @Test
    public void test07(){
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo07","张三");
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    //题8、查询没学过"张三"老师授课的同学的信息    四表联查
    @Test
    public void test08(){
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo08","张三");
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    //题9：查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息
    @Test
    public void test09(){
        Map map=new HashMap();
        map.put("c_id1","01");
        map.put("c_id2","02");
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo09",map);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
    //题10:查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息
    @Test
    public void test10(){
        Map map=new HashMap();
        map.put("c_id1","01");
        map.put("c_id2","02");
        List<Map> ss=sqlSession.selectList("com.xiexin.dao.StudentDao.selectdemo10",map);
        for (Map s : ss) {
            System.out.println("s = " + s);
        }
        sqlSession.close();
    }
}
