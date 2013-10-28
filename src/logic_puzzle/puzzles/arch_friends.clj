(ns logic-puzzle.puzzles.arch-friends
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [logic-puzzle.utils :refer [aftero secondo]]))

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

(defn arch-friendso [q]
  (let [fuchsia-flats ['fuchsia-flats (lvar)]
        purple-pumps ['purple-pumps (lvar)]
        suede-sandals ['suede-sandals (lvar)]
        ecru-espadrilles ['ecru-espadrilles (lvar)]

        foot-farm [(lvar) 'foot-farm]
        heels-in-a-handcart [(lvar) 'heels-in-a-handcart]
        the-shoe-palace [(lvar) 'the-shoe-palace]
        tootsies [(lvar) 'tootsies]

        u [fuchsia-flats purple-pumps suede-sandals ecru-espadrilles
           foot-farm heels-in-a-handcart the-shoe-palace tootsies]]
    (all
     (== (vec (repeatedly 4 lvar)) q)
     (everyg #(membero % q) u)
     (== fuchsia-flats heels-in-a-handcart)
     (fresh [m]
       (aftero purple-pumps m q)
       (!= m tootsies))
     (secondo q foot-farm)
     (fresh [m n]
       (aftero the-shoe-palace m q)
       (aftero m suede-sandals q)))))

(comment
  (run 1 [q] (arch-friendso q))
  )
