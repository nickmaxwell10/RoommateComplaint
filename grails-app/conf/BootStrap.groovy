import com.roommatecomplaint.Role
import com.roommatecomplaint.User;

import org.bson.types.ObjectId

class BootStrap {

    def init = { servletContext ->
		//User.collection.getDB().dropDatabase()
		String userAuthority = "ROLE_USER"
		Role userRole = Role.findByAuthority(userAuthority)
		if(!userRole) {
			userRole = new Role()
			userRole.authority = userAuthority
			userRole.save()
		}
		
		User user = User.findByUid("1")
		if(!user) {
			user = new User()
			user.uid = "1"
			user.accessToken = "abdc"
			user.name = "test user"
			user.dateCreated = new Date()
			user.authorities = new HashSet<Role>()
			user.authorities.add(userRole)
			user.save()
		}
		
    }
    def destroy = {
    }
}
