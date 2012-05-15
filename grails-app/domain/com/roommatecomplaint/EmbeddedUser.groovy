package com.roommatecomplaint

import org.bson.types.ObjectId;

class EmbeddedUser {
	static mapWith = 'mongo'
	
	String uid //facebook id
	String name

    static constraints = {
		uid blank: false, unique: true
    }
}
