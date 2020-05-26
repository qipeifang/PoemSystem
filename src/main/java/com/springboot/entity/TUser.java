package com.springboot.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "T_User", schema = "test")
public class TUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;//邮箱
    private String gender;//“男” “女”
    private int isManager;//是否为管理员 0:不是管理员 1:是管理员
    private int isVIP;//是否为会员 0:是会员   1:不是会员
    private int grade;//用户等级
    public enum Sex{
        男,女;//枚举中常量结束位置要有分号
        public static List<String> toList(){
            Sex[] sex=Sex.values();
            List<String> datas=new ArrayList<>();//定义一个列表容纳所有枚举的数据
            for (Sex s:sex){
                datas.add(s.name());
            }
            return datas;
        }
    };
    private Sex grander;//实际输入数据库的是索引值，即男为0，女为1
    public TUser() {
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getIsVIP() {
        return isVIP;
    }

    public void setIsVIP(int isVIP) {
        this.isVIP = isVIP;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getGrander() {
        return grander;
    }

    public void setGrander(Sex grander) {
        this.grander = grander;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return isManager == tUser.isManager &&
                isVIP == tUser.isVIP &&
                grade == tUser.grade &&
                Objects.equals(id, tUser.id) &&
                Objects.equals(username, tUser.username) &&
                Objects.equals(password, tUser.password) &&
                Objects.equals(email, tUser.email) &&
                grander == tUser.grander;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, grander, isManager, isVIP, grade);
    }
}
