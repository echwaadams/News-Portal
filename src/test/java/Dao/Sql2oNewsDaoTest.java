package Dao;

import models.DepartmentNews;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static  Sql2oNewsDao newsDao;
    private static Connection con;
    @BeforeClass
    public static void setUp() throws Exception {
        String connectionStr="jdbc:postgresql://localhost:5432/newsportal_test";
        Sql2o sql2o = new Sql2o(connectionStr,"pkminor","password");

        newsDao = new Sql2oNewsDao(sql2o);
        con = sql2o.open();
        newsDao.clearAllNews(); //start with empty table
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @AfterClass
    public static void shutDown() throws Exception { con.close(); }


    @Test
    public void getAllNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        assertEquals(4,newsDao.getAllNews().size());
        assertTrue(newsDao.getAllNews().containsAll(Arrays.asList(n1,n2,dn1,dn2)));
    }

    @Test
    public void getGeneralNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        int n1_id = n1.getId();
        int n2_id = n2.getId();

        assertEquals(2,newsDao.getGeneralNews().size());
        assertTrue(newsDao.getGeneralNews().containsAll(Arrays.asList(n1,n2)));
    }

    @Test
    public void getDepartmentNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        assertEquals(2,newsDao.getDepartmentNews().size());
        assertTrue(newsDao.getDepartmentNews().containsAll(Arrays.asList(dn1,dn2)));
    }

    @Test
    public void addGeneralNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        int oln1_id = n1.getId();
        int oln2_id = n2.getId();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        assertNotEquals(oln1_id, n1.getId());
        assertNotEquals(oln2_id, n2.getId());
        assertTrue(n2.getId() > n1.getId());
        assertTrue( 1==n2.getId() - n1.getId());
        assertEquals(2,newsDao.getGeneralNews().size());
        assertTrue(newsDao.getGeneralNews().containsAll(Arrays.asList(n1,n2)));
    }

    @Test
    public void addDepartmentNews() {
        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        int oldn1_id = dn1.getId();
        int oldn2_id = dn2.getId();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        assertNotEquals(oldn1_id, dn1.getId());
        assertNotEquals(oldn2_id, dn2.getId());
        assertTrue(dn2.getId() > dn1.getId());
        assertTrue( 1==dn2.getId() - dn1.getId());
        assertEquals(2,newsDao.getDepartmentNews().size());
        assertTrue(newsDao.getDepartmentNews().containsAll(Arrays.asList(dn1,dn2)));
    }

    @Test
    public void findGeneralNewsById() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        News foundNews = newsDao.findGeneralNewsById(n1.getId());
        assertEquals(foundNews, n1);
    }

    @Test
    public void findDepartmentNewsById() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        DepartmentNews foundDepartmentNews = newsDao.findDepartmentNewsById(dn1.getId());
        assertEquals(foundDepartmentNews, dn1);
    }

    @Test
    public void updateGeneralNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        int ol_uid = n1.getUserId();
        String ol_content=n1.getContent();

        int ol_uid2 = n2.getUserId();
        String ol_content2=n2.getContent();

        newsDao.updateGeneralNews(n1,2,"Climate change");

        assertNotEquals(ol_uid,n1.getUserId());
        assertNotEquals(ol_content,n1.getContent());
        assertEquals(ol_uid2,n2.getUserId());
        assertEquals(ol_content2,n2.getContent());
    }

    @Test
    public void updateDepartmentNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        int ol_uid = n1.getUserId();
        String ol_content=n1.getContent();

        int ol_uid2 = n2.getUserId();
        String ol_content2=n2.getContent();

        newsDao.updateGeneralNews(n1,2,"Climate change");

        assertNotEquals(ol_uid,n1.getUserId());
        assertNotEquals(ol_content,n1.getContent());
        assertEquals(ol_uid2,n2.getUserId());
        assertEquals(ol_content2,n2.getContent());
    }

    @Test
    public void clearAllNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        newsDao.clearAllNews();
        assertEquals(0,newsDao.getAllNews().size());
    }

    @Test
    public void clearGeneralNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        newsDao.clearGeneralNews();
        assertEquals(0,newsDao.getGeneralNews().size());
        assertEquals(2,newsDao.getDepartmentNews().size());
    }

    @Test
    public void clearAllDepartmentNews() {
        News n1 = setupGeneralNews();
        News n2 = setupGeneralNews();

        newsDao.addGeneralNews(n1);
        newsDao.addGeneralNews(n2);

        DepartmentNews dn1 = setupDepartmentNews();
        DepartmentNews dn2 = setupDepartmentNews();

        newsDao.addDepartmentNews(dn1);
        newsDao.addDepartmentNews(dn2);

        newsDao.clearDepartmentNews();
        assertEquals(2,newsDao.getGeneralNews().size());
        assertEquals(0,newsDao.getDepartmentNews().size());
    }
    private static News setupGeneralNews(){
        return new News(-1,1,Sql2oNewsDao.GENERAL_NEWS,"Space Travel",new Timestamp(new Date().getTime()));
    }

    private static DepartmentNews setupDepartmentNews(){
        return new DepartmentNews(-1,1,Sql2oNewsDao.DEPARTMENT_NEWS,"Space Travel",new Timestamp(new Date().getTime()),1);
    }
}