(ns logic-puzzle.puzzles.arch-friends
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]))

;; Title: Babysitting
;; Author: Scott Marley
;; Publication: Dell Logic Puzzles
;; Issue: April, 1998
;; Page: 7
;; Stars: 1
;;
;; Each weekday, Bonnie takes care of five of the neighbors' children. The
;; children's names are Keith, Libby, Margo, Nora, and Otto; last names are
;; Fell, Gant, Hall, Ivey, and Jule. Each is a different number of years old,
;; from two to six. Can you find each child's full name and age?
;;
;; 1. One child is named Libby Jule.
;; 2. Keith is one year older than the Ivey child, who is one year older than Nora.
;; 3. The Fell child is three years older than Margo.
;; 4. Otto is twice as many years old as the Hall child.
;;
;; Determine: First name - Last name - Age
;;
;; Answer: http://brownbuffalo.sourceforge.net/BabysittingAnswer.html

(defn babysittingfd [q]
  (let [->age last

        keith ['keith (lvar) (lvar)]
        libby ['libby (lvar) (lvar)]
        margo ['margo (lvar) (lvar)]
        nora ['nora (lvar) (lvar)]
        otto ['otto (lvar) (lvar)]

        fell [(lvar) 'fell (lvar)]
        gant [(lvar) 'gant (lvar)]
        hall [(lvar) 'hall (lvar)]
        ivey [(lvar) 'ivey (lvar)]
        jule [(lvar) 'jule (lvar)]

        u [keith libby margo nora otto
           fell gant hall ivey jule]
        ages (map ->age u)]
    (all
     (== q (repeatedly 5 lvar))

     (everyg #(membero % q) u)
     (everyg #(fd/in % (fd/interval 2 7)) ages)
     (everyg fd/distinct (partition 5 ages))

     (== libby jule)
     (fd/- (->age keith) (->age ivey) 1)
     (fd/- (->age ivey) (->age nora) 1)

     (fd/- (->age fell) (->age margo) 3)

     (fd/* (->age hall) 2 (->age otto)))))

(comment
  (run 1 [q] (babysittingfd q))
  )
