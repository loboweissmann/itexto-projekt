(ns script-utils)

(comment 
	Utility functions for all Daft Lisp scripts
)

; The current directory
(defn current-dir [] 
	(. System getProperty "user.dir")
)

; Get a system property
(defn get-property [name]
	(. System getProperty name)
)

; Get an environment variable
(defn env [name]
	(. System getenv name)
)

; Daft Lisp Home directory
(defn daft-lisp-home [] (env "DAFT_LISP_HOME"))

; Return the daft.properties file
(defn project-metadata-file []
	(new java.io.File (str (current-dir) "/daftlisp.properties") )
)

; Return true if the current directory is a Daft Lisp application one
(defn is-application-folder? []
	(. (project-metadata-file) exists)
)

; Return an application property based on the metadata application file
(defn app-property [name]
	(let [props (new java.util.Properties)]
		(do 
			(. props load (new java.io.FileInputStream (project-metadata-file)))
			(. props getProperty name)
		)
	)
)

; Return the stage-dir of daft-lisp
(defn stage-directory []
	(new java.io.File (str (get-property "user.home") "/.daftlisp/0.1"))
)

; Return the stage-dir of daft-lisp application
(defn stage-directory-app []
	(new java.io.File (str (get-property "user.home") "/.daftlisp/0.1/" (app-property "app.name")))
)

