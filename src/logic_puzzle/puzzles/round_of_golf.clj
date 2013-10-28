(ns logic-puzzle.puzzles.round-of-golf
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]))

;; Title: A Round of Golf
;; Author: Ellen K. Rodehorst
;; Publication: Dell Favorite Logic Problems
;; Issue: Summer, 2000
;; Puzzle #: 9
;; Stars: 1
;;
;; When the Sunny Hills Country Club golf course isn't in use by club members,
;; of course, it's open to the club's employees. Recently, Jack and three other
;; workers at the golf course got together on their day off to play a round of
;; eighteen holes of golf. Afterward, all four, including Mr. Green, went to the
;; clubhouse to total their scorecards. Each man works at a different job (one
;; is a short-order cook), and each shot a different score in the game. No one
;; scored below 70 or above 85 strokes. From the clues below, can you discover
;; each man's full name, job and golf score?
;;
;; 1. Bill, who is not the maintenance man, plays golf often and had the lowest
;; score of the foursome.
;; 2. Mr. Clubb, who isn't Paul, hit several balls into the woods and scored ten
;; strokes more than the pro-shop clerk.
;; 3. In some order, Frank and the caddy scored four and seven more strokes than
;; Mr. Sands.
;; 4. Mr. Carter thought his score of 78 was one of his better games, even
;; though Frank's score was lower.
;; 5. None of the four scored exactly 81 strokes.
;;
;; Determine: First Name - Last Name - Job - Score
;;
;; Answer: http://brownbuffalo.sourceforge.net/RoundOfGolfAnswer.html

(defn golffd [q]
  (let [->score last
        bill ['bill (lvar) (lvar) (lvar)]
        jack ['jack (lvar) (lvar) (lvar)]
        frank ['frank (lvar) (lvar) (lvar)]
        paul ['paul (lvar) (lvar) (lvar)]

        green [(lvar) 'green (lvar) (lvar)]
        clubb [(lvar) 'clubb (lvar) (lvar)]
        carter [(lvar) 'carter (lvar) (lvar)]
        sands [(lvar) 'sands (lvar) (lvar)]

        maint [(lvar) (lvar) 'maint (lvar)]
        caddy [(lvar) (lvar) 'caddy (lvar)]
        clerk [(lvar) (lvar) 'clerk (lvar)]
        cook [(lvar) (lvar) 'cook (lvar)]

        u [bill jack frank paul
           green clubb carter sands
           maint caddy clerk cook]
        scores (vec (map ->score u))]
    (all
     (== q [(lvar) (lvar) (lvar) (lvar)])
     (everyg #(membero % q) u)
     (everyg #(fd/in % (fd/interval 70 86)) scores)
     (everyg fd/distinct (partition 4 scores))

     (!= bill maint)
     (everyg #(fd/< (->score bill) %) (map ->score [jack frank paul]))

     (!= clubb paul)
     (fd/- (->score clubb) (->score clerk) 10)

     (conde
      ((fd/- (->score frank) (->score sands) 4)
       (fd/- (->score caddy) (->score sands) 7))
      ((fd/- (->score frank) (->score sands) 7)
       (fd/- (->score caddy) (->score sands) 4)))

     (== (->score carter) 78)
     (fd/< (->score frank) (->score carter))

     (everyg #(!= 81 %) scores))))

(comment
  (run 1 [q] (golffd q))
  )
