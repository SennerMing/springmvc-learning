package club.musician.entity;

public class User {
    private Integer id;
    private String userName;
    private Integer age;
    private String email;
    private String password;
    private String gender;

    private Address address;

    public User() {
    }

    public User(String userName, Integer age, String email, String password,Address address) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(String userName, Integer age, String email, String password, String gender, Address address) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
