(ns clojure-logic.utils
  (:require [clojure.core.logic :refer :all]))

(defne aftero [x y l]
  ([_ _ [x y . r]])
  ([_ _ [_ . r]] (aftero x y r)))

(defn not-aftero [x y l]
  (fresh [m]
    (aftero x m l)
    (!= y m)))

(defn secondo [l a]
  (fresh [r]
    (resto l r)
    (firsto r a)))
