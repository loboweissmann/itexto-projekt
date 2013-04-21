(ns create-controller)
(use 'clojure.java.io)
(defn in-app-directory? []
	(. (new java.io.File (str (. System getProperty "user.dir") "/daftlisp.properties")) exists))

(defn controller-file-name[] 
	(str (. System getProperty "user.dir") "/daft-app/controllers/" (second *command-line-args*) ".clj")
)

(defn create-controller []
	(do
		(println "Creating controller")
		(with-open [wtr (writer (controller-file-name))]
			(.write wtr (str "(ns " (second *command-line-args*) ")\n")))
		(println (str "File " (controller-file-name) " created. :)"))
	)
)

(if (not (in-app-directory?))
	(println "You're not in an app folder")
	(create-controller))
	
