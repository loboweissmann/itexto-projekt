(ns test)

; Action example. It only receives a request parameter and must return a map
(defn action [request]
  (hash-map "param1" (. request parameter "param1") "param2" (. request parameter "param2"))
)

