package project.dao.daoImpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import project.dao.UserDao;
import project.domain.User;
import project.util.JdbcUtils;

import java.util.*;

/**
 * Created by ZIYANG on 2020/3/25.
 */
public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public List<User> listAll() {
        String sql = "SELECT * from user";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM user WHERE id=?";
        template.update(sql, id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT * FROM user WHERE id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET name=?,gender=?,age=?,address=?,qq=?,email=? WHERE id=?";
        template.update(sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail(),
                user.getId());
    }

    @Override
    public void add(User user) {
        // name gender age address qq email
        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        template.update(sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail()
                );
    }

    @Override
    public int getCount(Map<String, String[]> condition) {
        // 模板sql，初始化sql
        String sql = "SELECT COUNT(*) FROM USER WHERE 1=1";

        // 遍历map拼接sql，记录value的List集合
        StringBuffer sqlBuffer = new StringBuffer(sql);
        List<Object> params = new ArrayList<>();
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        for (Map.Entry<String, String[]> element : entries) {
            String key = element.getKey();
            String value = element.getValue()[0];
            // 排除掉分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            // value有值且不为空
            if (value != null && !"".equals(value)) {
                sqlBuffer.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

        // 获取最终的sql
        sql = sqlBuffer.toString();

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<User> findUserByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "SELECT * FROM USER WHERE 1=1 ";
        StringBuffer sqlBuffer = new StringBuffer(sql);

        // 拼接sql字符串
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        List<Object> params = new ArrayList<>();
        for (Map.Entry<String, String[]> element:entries){
            String key = element.getKey();
            String value = element.getValue()[0];

            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }

            // value不为空，拼接字符串
            if (value != null && !"".equals(value)) {
                sqlBuffer.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }

        // 获取全sql字符串
        sql = sqlBuffer.append(" limit ?,? ").toString();
        params.add(start);
        params.add(rows);

        // 测试
        System.out.println(sql);
        System.out.println(params);

        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
        return list;
    }
}
