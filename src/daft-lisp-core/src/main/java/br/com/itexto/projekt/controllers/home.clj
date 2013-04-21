(ns home)

(defn index [request]
    (hash-map "message" "It's REALLY ALIVE!" "nome" "Kico")
 )

(defn kico [request]
    (hash-map "tipo" "Coisa!")  
)

(defn fat [x]
  (if (< x 2) 1 (* x (fat (- x 1))))
)

(defn fatorial [request]
       (hash-map "resultado" (fat (Integer. (. request parameter "x"))) 
                  "x" (. request parameter "x"))
)