(ns clojure-logic.puzzles.arch-friends
  (:require [clojure.core.logic :refer :all]))

;; http://brownbuffalo.sourceforge.net/ArchFriendsClues.html
;; Author: Mark T. Zegarelli
;; Publication: Dell Logic Puzzles
;; Issue: April, 1998
;; Page: 7
;; Stars: 1
;;
;; Harriet, upon returning from the mall, is happily describing her four shoe
;; purchases to her friend Aurora. Aurora just loves the four different kinds of
;; shoes that Harriet bought (ecru espadrilles, fuchsia flats, purple pumps, and
;; suede sandals), but Harriet can't recall at which different store (Foot Farm,
;; Heels in a Handcart, The Shoe Palace, or Tootsies) she got each pair. Can you
;; help these two figure out the order in which Harriet bought each pair of
;; shoes, and where she bought each?
;;
;; 1. Harriet bought fuchsia flats at Heels in a Handcart.
;; 2. The store she visited just after buying her purple pumps was not Tootsies.
;; 3. The Foot Farm was Harriet's second stop.
;; 4. Two stops after leaving The Shoe Place, Harriet bought her suede sandals.
;;
;; Determine: Order - Shoes - Store
;;
;; Answer: http://brownbuffalo.sourceforge.net/ArchFriendsAnswer.html

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

(defn arch-friendsfd [q]
  (all
   (== [(lvar) (lvar) (lvar) (lvar)] q)

   (membero ['fuchsia-flats (lvar)] q)
   (membero ['purple-pumps (lvar)] q)
   (membero ['suede-sandals (lvar)] q)
   (membero ['ecru-espadrilles (lvar)] q)

   (membero [(lvar) 'foot-farm] q)
   (membero [(lvar) 'heels-in-a-handcart] q)
   (membero [(lvar) 'the-shoe-palace] q)
   (membero [(lvar) 'tootsies] q)

   (membero ['fuchsia-flats 'heels-in-a-handcart] q)
   (not-aftero ['purple-pumps (lvar)] [(lvar) 'tootsies] q)
   (secondo q [(lvar) 'foot-farm])
   (fresh [m n]
     (aftero [(lvar) 'the-shoe-palace] m q)
     (aftero m ['suede-sandals (lvar)] q))))

(comment
  (run* [q] (arch-friendsfd q))
  )
