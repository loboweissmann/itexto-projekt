(ns daftlisp-cli)

(println "\n\nWelcome to Daft Lisp - v 0.1")
(println "Author: Henrique Lobo Weissmann - @loboweissmann - http://devkico.itexto.com.br")
(println "itexto Ltd. - http://www.itexto.com.br")
(println "2013\n\n")

(defn script-file [name]
	(str (. System getenv "DAFT_LISP_HOME") "/scripts/" name ".clj"))


(defn script-exists? [script]
	(. (new java.io.File (script-file script)) exists)
)



(defn run-script [script parameters]
	(if (script-exists? script)
		(do
			(load-file (script-file script))
		)
		(do
			(println "So sad... Daft Lisp Script not found. So lost!")
			(load-file (str (. System getenv "DAFT_LISP_HOME") "/scripts/help.clj"))
		)
))


(run-script (first *command-line-args*) (rest *command-line-args*))
