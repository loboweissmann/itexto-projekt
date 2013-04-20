(ns create-app)
(comment
	Script which creates a new Daft Lisp application
	Parameters:
		app-name (the name of the application which will be created) 
)

(defn curr-dir []
	(. System getProperty "user.dir"))
	
(defn create-dir [path]
	(let [dir (new java.io.File path)]
		(do 
			(println (str "Creating " path "..."))
			(. dir mkdirs)
		)
	))
		
(defn create-daftlisp-dir [name]
	(create-dir (str (curr-dir) "/" name)))
 
(defn script [parameters]
	(let [app-name (first parameters)]
		(do
			(println "Welcome to Daft Lisp")
			(println "I'll create your app in just a moment, please wait :)")
			(create-daftlisp-dir (str app-name "/daftapp/controllers"))
			(create-daftlisp-dir (str app-name "/daftapp/config"))
			(create-daftlisp-dir (str app-name "/daftapp/modules"))
			(create-daftlisp-dir (str app-name "/daftapp/webapp/views"))
			(create-daftlisp-dir (str app-name "/daftapp/webapp/views/layouts"))
			(create-daftlisp-dir (str app-name "/daftapp/webapp/resources"))
			(create-daftlisp-dir (str app-name "/tests")
			(create-daftlisp-dir (str app-name "/libs"))
			(create-daftlisp-dir (str app-name "/scripts"))
			
			(println "Your application is ready now")
		)
	)
)