(ns create-controller)
(use 'clojure.java.io)
(defn in-app-directory? []
	(. (new java.io.File (str (. System getProperty "user.dir") "/daftlisp.properties")) exists))

(defn create-controller []
	(with-open [wtr (writer (str (. System getProperty "user.dir") "/daft-app/controllers/" (second *command-line-args*) ".clj"))]
		(.write wtr (str "(ns " (second *command-line-args*) ")\n")))
)

(if (not (in-app-directory?))
	(println "You're not in an app folder")
	(create-controller))
	
