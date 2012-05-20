// MongoHQ
grails {
	mongo {
		host = "50.17.135.240"
		port = 10041
		username = "root"
		password = "password"
		databaseName = "roommatecomplaint"
		options {
			autoConnectRetry = true
			connectTimeout = 300
		}
	}
}

// Local
//grails {
//	mongo {
//		host = "localhost"
//		port = 27017
//		databaseName = "roommatecomplaint"
//	}
//}