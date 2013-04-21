(ns run)


(println "Starting your application")

(defn port []
	(if (nil? (script-utils/get-property "daft.port")) 
		8080 
	(Integer. (script-utils/get-property "daft.port"))  )
)

(defn create-stage-dir []
	(let [stage-dir (script-utils/stage-directory-app)]
		(do
			(. stage-dir mkdirs)
			
		)
	)
)

(defn start[] 
	(let [server (new org.eclipse.jetty.server.Server (port))
		  context (new org.eclipse.jetty.server.handler.ContextHandler (script-utils/app-property "app.name"))
		  stage-dir (script-utils/stage-directory-app)]
		(do 
			(create-stage-dir)
			(. server setHandler context)
			(println (str "Ready: go to\n\thttp://localhost:" (port) "/" (script-utils/app-property "app.name")))
		)
	)
)



(if (script-utils/is-application-folder?)
	(start)
	(println "Sorry: this is not an application directory. :/")
)