(ns daft_lisp_script)

(defn script [parameters]
	(do
		(println "Test script")
		(println "Two things")
		(+ (Integer. (first parameters)) (Integer. (last parameters)))))