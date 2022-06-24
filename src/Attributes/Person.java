package Attributes;

import java.io.Serializable;

public class Person implements Serializable {
    String f_name,l_name,age,phone_no,email,gender;
    Date dob;

    public Person( String f_name, String l_name, String age, String phone_no,
                  String email, String gender, Date dob) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.age = age;
        this.phone_no = phone_no;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }
}
