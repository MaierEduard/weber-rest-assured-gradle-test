package com.restAssuredTest.data;


import com.restAssuredTest.model.user.Admin;
import com.restAssuredTest.model.user.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class TestData {


    private Admin admin = new Admin();
  //  private User user = new User();



    public Admin getAdmin() {
        return admin;
    }



    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
