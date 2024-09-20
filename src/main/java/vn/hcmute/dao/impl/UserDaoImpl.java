package vn.hcmute.dao.impl;

import vn.hcmute.configs.DBConnection;
import vn.hcmute.dao.IUserDao;
import vn.hcmute.models.UserModel;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DBConnection implements IUserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    @Override
    public List<UserModel> FindAll() throws ClassNotFoundException {
        /*
        String sqlQuery = "Select * from users";
        List<UserModel> list = new ArrayList<>();
        try{
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("images"))); //Add vào
            }
            return list;
        }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }

         */
        String sql = "SELECT * FROM login ";
        try {
            List<UserModel> list = new ArrayList<>();
            conn = new DBConnection().getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("password"),
                        rs.getString("avatar"),
                        rs.getString("email"),
                        rs.getInt("roleid"),
                        rs.getString("phone"),
                        rs.getDate("createDate")));
            }
            return list;
        } catch (Exception e) {e.printStackTrace(); }
        return null;
    }

    @Override
    public UserModel FindById(int id) {
        return null;
    }

    @Override
    public void Insert(UserModel user) throws SQLException, ClassNotFoundException {

        String insertQuery = "INSERT INTO login(username, password, email, fullname, phone, roleid) VALUES (?, ?, ?, ?, ?, ?)";

        conn = super.getDatabaseConnection();

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRoleid());
            ps.executeUpdate();
        }
        /*
        String sqlQuery = "Insert Into login(username, password,email,fullname,phone,roleid) values (?,?,?,?,?,?)";
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getEmail());  // Đảm bảo sử dụng đúng getter
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRoleid());  // Giả sử roleid cũng cần thiết
            ps.executeUpdate();
            */

    }

    @Override
    public UserModel FindByUsername(String username) {
        String sql = "SELECT * FROM login WHERE username = ? ";
        try {
            conn = new DBConnection().getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleid(Integer.parseInt(rs.getString("roleid")));
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getDate("createDate"));
                return user; }
        } catch (Exception e) {e.printStackTrace(); }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<UserModel> list = userDao.FindAll();
        for (UserModel x : list){
            System.out.println(x.toString());
        }
    }

}
