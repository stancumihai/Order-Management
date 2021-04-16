package org.stancuMihai.model;

/***
 * Client pojo database class
 */
public class Client {

    private Integer id;
    private String name;
    private String email;
    private String address;
    private Integer age;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    /***
     * It constructs the client object
     * @param name name of the client
     * @param email email of the client
     * @param address address of the client
     * @param age age of the client
     */
    public Client(String name, String email, String address, Integer age) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    public Client(Integer id, String name, String email, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
