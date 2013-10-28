(ns logic-puzzle.utils
  (:require [clojure.core.logic :refer :all]))

(defne aftero [x y l]
  ([_ _ [x y . r]])
  ([_ _ [_ . r]] (aftero x y r)))

(defn secondo [l a]
  (fresh [r]
    (resto l r)
    (firsto r a)))
