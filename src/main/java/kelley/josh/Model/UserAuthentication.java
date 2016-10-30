package kelley.josh.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by joshuakelley on 10/30/16.
 */
@RestController
public class UserAuthentication {

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    //logout already defined by spring boot
}
