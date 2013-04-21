(ns create-app)

(defn app-dir []
	(str (. System getProperty "user.dir") "/" (second *command-line-args*)))



(defn create-folder [name]
	(do
		(println (str "Creating folder" name))
		(. (new java.io.File (str (app-dir) "/" name)) mkdirs)
	)
)

(defn create-meta-info []
	(let [meta-file (new java.io.File (str (app-dir) "/daftlisp.properties"))
	      properties (new java.util.Properties)]
		(do
		   (. properties setProperty "app.name" (second *command-line-args*))
		   (. properties setProperty "app.version" "0.1")
		   (. properties setProperty "daftlisp.version" "0.1")

		   (. properties store (new java.io.FileOutputStream meta-file) "Daft Lisp metadata")
		)
))

(defn create-app []
	(do
		(println "\tCreating your application. Please wait...")
		(create-folder "daft-app/controllers")
		(create-folder "daft-app/configuration")
		(create-folder "daft-app/functions")
		(create-folder "lib")
		(create-folder "test/clojure")
		(create-folder "test/java")
		(create-folder "src/java")
		(create-folder "src/clojure")
		(create-folder "daft-app/webapp/resources/javascript")
		(create-folder "daft-app/webapp/resources/images")
		(create-folder "daft-app/webapp/resources/css")
		(create-folder "daft-app/webapp/WEB-INF")
		(create-folder "daft-app/webapp/META-INF")
		(create-meta-info)
		(println "Your application is ready. Go code now!")

	)
)

(let [folder (new java.io.File (app-dir))]
	(if (not (. folder exists))
		(create-app)
		(println "I'll only create an application on a new folder. Not an existing one ok?")))
