package Dao;

import models.DepartmentNews;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao implements  NewsDao{

    private final Sql2o sql2o;
    public static final String GENERAL_NEWS = "general";
    public static final String DEPARTMENT_NEWS = "department";

    public Sql2oNewsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<News> getAllNews(){
        List<News> news = new ArrayList<>();
        news.addAll(getGeneralNews());
        news.addAll(getDepartmentNews());
        return news;
    }

    @Override
    public List<News> getGeneralNews(){
        String sql = "SELECT * FROM news WHERE type=:type";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", GENERAL_NEWS)
                    .executeAndFetch(News.class);
        }
    }
    @Override
    public List<DepartmentNews> getDepartmentNews(){
        String sql = "SELECT * FROM news WHERE type =: type";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .executeAndFetch(DepartmentNews.class);
        }
    }
    @Override
    public void addGeneralNews(News news){
        String sql = "INSERT INTO news (userId, type,content,postdate) VALUES (:userId, :type, :content,now())";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userId",news.getUserid())
                    .addParameter("type",news.getType())
                    .addParameter("content",news.getContent())
                    .executeUpdate().getKey();
            news.setId(id);
        }
    }
    @Override
    public void addDepartmentNews(DepartmentNews dptNews){
        String sql = "INSERT INTO news (userId, type, content, postdate,departmentId) VALUES (:userId, :type, :content, now(), :departmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .addParameter("userId",dptNews.getId())
                    .addParameter("type",dptNews.getType())
                    .addParameter("content", dptNews.getContent())
                    .addParameter("departmentId",dptNews.getDepartmentId())
                    .executeUpdate().getKey();
            dptNews.setId(id);
        }
    }
    @Override
    public News findGeneralNewsById(int id){
        String sql = "SELECT * FROM news WHERE type=:type AND id=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type",GENERAL_NEWS)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }
    //@Override
    public DepartmentNews findDepartmentNewsById(int id){
        String sql = "SELECT * FROM news WHERE type=:type AND id=:id ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("type", DEPARTMENT_NEWS)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }
    @Override
    public void updateGeneralNews(News news, int userId, String content){
        String sql = "UPDATE news SET (userId,content) = (:userId, :content)";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("content",content)
                    .addParameter("id",news.getId())
                    .executeUpdate();
            news.setUserid(userId);
            news.setContent(content);
        }
    }
    //@Override
    public void updateDepartmentNews(DepartmentNews dptNews, int userId, String content, int departmentId){
        String sql = "UPDATE news SET (userId, content, departmentId) = (:userId, :content, :departmentId) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("content", content)
                    .addParameter("departmentId", departmentId)
                    .addParameter("id", dptNews.getId())
                    .executeUpdate();
            dptNews.setUserid(userId);
            dptNews.setContent(content);
            dptNews.setDepartmentId(departmentId);
        }
    }
    @Override
    public void clearAllNews(){
        String sql = "DELETE FROM news";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }
    @Override
    public void clearGeneralNews(){
        String sql = "DELETE FROM WHERE type=:type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",GENERAL_NEWS)
                    .executeUpdate();
        }
    }
    //@Override
    public void clearAllDepartmentNews(){
        String sql = "DELETE FROM news WHERE type=:type";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .executeUpdate();
        }
    }
}
