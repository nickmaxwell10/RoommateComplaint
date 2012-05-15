package com.roommatecomplaint

class UserService {
	
	static transactional = 'mongo'

    def save(User user) {
		if(!user.save()) {
			return false
		}
		true
    }
}
