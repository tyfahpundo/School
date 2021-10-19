package zw.co.afrosoft.domain.dto.request;

import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.enums.StudentStatus;


public class StudentDetailsRequest {
    private String name;
    private String surname;
    private int age;
    private StudentLevel studentLevel;
    private StudentStatus studentStatus;
    private ContactDetail contactDetail;
    private Address address;

    public StudentDetailsRequest() {
    }

    public StudentDetailsRequest(String name, String surname, int age,
                                 StudentLevel studentLevel,
                                 StudentStatus studentStatus,
                                 ContactDetail contactDetail,
                                 Address address){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.studentLevel = studentLevel;
        this.studentStatus = studentStatus;
        this.contactDetail = contactDetail;
        this.address = address;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StudentLevel getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(StudentLevel studentLevel) {
        this.studentLevel = studentLevel;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
