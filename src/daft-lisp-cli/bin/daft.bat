set CLASSPATH_DAFT_LISP=%CLASSPATH%;"%DAFT_LISP_HOME%\lib\*"
java -cp %CLASSPATH_DAFT_LISP% clojure.main %DAFT_LISP_HOME%\scripts\main.clj %*