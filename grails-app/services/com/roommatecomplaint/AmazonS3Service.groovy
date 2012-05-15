package com.roommatecomplaint

//import org.apache.http.client.CredentialsProvider
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.impl.rest.httpclient.RestS3Service
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object
import org.jets3t.service.security.AWSCredentials

import com.sun.org.apache.bcel.internal.generic.NEW;



class AmazonS3Service {
	
	static String accessKey="AKIAJPFPFLSV5KRGERRQ"
	static String secretKey="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"
	static RestS3Service s3 = new RestS3Service(new AWSCredentials(accessKey, secretKey))
	static transactional = false
	String rootBucketPath="roommatecomplaint/"
	String defaultBucketLocation=S3Bucket.LOCATION_US_STANDARD
	
	Map mimeExtensionMap = [
			"png" : "image/png",
			"jpg": "image/jpeg",
			"gif": "image/gif",
			"tiff": "image/tiff",
			"pdf": "application/pdf",
			"mpeg": "video/mpeg",
			"mp4": "video/mp4",
			"mov": "video/quicktime",
			"wmv": "video/x-ms-wmv",
			"html": "text/html",
			"xml": "text/xml",
			"mp3": "audio/mpeg",
			"flv": "application/octet-stream"
	]
	
	S3Bucket getOrCreateBucket(uid)
	{
		S3Bucket bucket = s3.getOrCreateBucket((rootBucketPath + uid), defaultBucketLocation)
		bucket.setAcl AccessControlList.REST_CANNED_PUBLIC_READ
		return bucket
	}
	
	void put(inputstream, name, uid, ext, length)
	{
		if (mimeExtensionMap.containsKey(ext.toLowerCase()))
		{
			//CredentialsProvider cp = NEW Cr
			String mime = mimeExtensionMap[ext.toLowerCase()];
			S3Bucket bucket = getOrCreateBucket(uid)
			S3Object up = new S3Object()
			up.setAcl AccessControlList.REST_CANNED_PUBLIC_READ
			up.setContentLength length
			up.setContentType mime
			up.setDataInputStream inputstream
			up.setKey name
			up.setBucketName bucket.getName()
			s3.putObject bucket, up
		}
	}
	void putXML(text, name, uid)
	{
		String mime = mimeExtensionMap["xml"];
		S3Bucket bucket = makeBucket(uid)
		S3Object up = new S3Object(bucket, name, text)
		up.setAcl AccessControlList.REST_CANNED_PUBLIC_READ
		up.setContentLength text.length()
		up.setContentType mime
		s3.putObject bucket, up
	}
}