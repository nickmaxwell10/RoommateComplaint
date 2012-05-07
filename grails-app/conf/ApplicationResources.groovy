modules = {
    application {
        resource url:'js/application.js'
    }
	
	core {
        dependsOn 'jquery, blueprint'
        resource url:'/js/application.js', disposition: 'head'
        resource url:'/css/errors.css' 
        resource url:'/css/main.css'
        resource url:'/css/mobile.css'
    }
	
}