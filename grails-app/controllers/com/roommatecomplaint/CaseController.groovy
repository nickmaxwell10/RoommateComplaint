package com.roommatecomplaint

import org.springframework.web.multipart.MultipartFile

class CaseController {
	
	def springSecurityService
	
	def amazonS3Service
	
	def caseService

    def index(Long id)  {
		Case _case = Case.get(id)
		def model = [:]
		model._case = _case
		model.user = springSecurityService.currentUser
		render(view:'case', model: model, params: [id:id])
	}
	
	def newCase = {
		Case newCase = new Case()
		User user = springSecurityService.currentUser
		
		if(request.method == 'POST') {
			//save case
			
			newCase.dateCreated = new Date()
			newCase.guiltyVotes = 0
			newCase.innocentVotes = 0
			
			EmbeddedUser plaintiff = new EmbeddedUser()
			plaintiff.uid = user.uid
			plaintiff.name = user.name
			
			newCase.plaintiff = plaintiff
			newCase.plaintiffCaption = params.plaintiffCaption
			
			EmbeddedUser defendant = new EmbeddedUser()
			defendant.uid = params.defendantId
			defendant.name = params.defendantName
			
			newCase.defendant = defendant
			
			MultipartFile file = request.getFile('file')
			def plaintiffImgURL = fileUpload(file)
			if(plaintiffImgURL) {
				newCase.plaintiffImgURL = plaintiffImgURL
			}
			
			if(caseService.save(newCase)) {
				redirect(action:"index", id:newCase.id)
				return
			}
		}
		
		def model = [:]
		model.newCase = newCase
		model.user = user
		render(view:'newCase', model: model, params: null)
	}
	
	private fileUpload(MultipartFile mpFile) {
		if (mpFile != null && !mpFile.isEmpty())
		{
			String guid = UUID.randomUUID().toString()
			// success
			String _file = mpFile.getOriginalFilename().replace(" ", "_");
			String ext = _file.substring(_file.lastIndexOf(".")+1)
			String fileName = guid + "." + ext
			def user = springSecurityService.currentUser
			amazonS3Service.put (mpFile.getInputStream(), fileName, user.uid, ext, mpFile.getSize())
			return "https://s3.amazonaws.com/roommatecomplaint/" + user.uid + "/" + fileName
		} else {
			return false
		}
	}
}
