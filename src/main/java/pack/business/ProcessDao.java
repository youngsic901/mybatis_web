package pack;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class ProcessDao { // 비즈니스 로직
    private static ProcessDao processDao = new ProcessDao();
    public static ProcessDao getInstance() {
        return processDao;
    }

    private SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();

    public List<DataDto> selectDataAll() throws SQLException { // 전체 자료 읽기
        // SqlSession : DataMapper.xml에 정의된 id에 접근 가능
        SqlSession sqlSession = sqlSessionFactory.openSession(); // sqlSession 객체 생성 후 열기
        List<DataDto> list = sqlSession.selectList("selectDataAll");
        sqlSession.close();
        return list;
    }

    public DataDto selectDataPart(String code) throws SQLException { // 부분 자료 읽기
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DataDto dto = sqlSession.selectOne("selectDataByCode", code);
        sqlSession.close();
        return dto;
    }

    public void insData(DataBean bean) throws SQLException {
        /*
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 수동 commit : transaction
        sqlSession.insert("insertData", bean);
        sqlSession.commit(); // sqlSession.rollback();
        sqlSession.close();
         */
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // 자동 commit
        sqlSession.insert("insertData", bean);
        sqlSession.close();
    }

    public void upData(DataBean bean) throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("updateData", bean);
        sqlSession.commit();
        sqlSession.close();
    }

    public boolean delData(int code) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        boolean b = false;
        try {
            int count = sqlSession.delete("deleteData", code);
            if (count > 0) b = true;
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("delData err : " + e);
            sqlSession.rollback();
        } finally {
            if(sqlSession != null) sqlSession.close();
        }

        return b;
    }
}
