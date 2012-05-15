package com.roommatecomplaint

import java.util.Date;

import org.bson.types.ObjectId
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class RoommateComplaintUserDetails extends GrailsUser {

    final ObjectId id 
	final String uid //facebook id
	final String accessToken
	final String name
	final Date dateCreated
	final Collection<GrantedAuthority> authorities
   

   RoommateComplaintUserDetails(ObjectId id, String uid, String accessToken, String name, 
	   Date dateCreated, Collection<GrantedAuthority> authorities) {
	   
	   super(uid, uid, true, true, true, true, authorities, id)
	   
	   this.id = id
	   this.uid = uid
	   this.accessToken = accessToken
	   this.name = name
	   this.dateCreated = dateCreated
	   this.authorities = authorities
   }
}
