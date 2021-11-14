package Dao;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {

    List<News> getAllNews();
    List<News> getGeneralNews();
    List<DepartmentNews> getDepartmentNews();

    void addGeneralNews(News news);

    default void addDepartmentNews(DepartmentNews dptNews) {

    }

    News findGeneralNewsById(int id);
    DepartmentNews findDepartmentNewsByid(int id);

    void updateGeneralNews(News news, int userId, String content);
    void updateGeneralNews(DepartmentNews dptNews, int userId, String content, int departmentId);

    void clearAllNews();
    void clearGeneralNews();
    void clearDepartmentNews();
}
