(ns pablo.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 60)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  (q/background 0)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})





(defn update-state [state]
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.05)})

(defn draw-state [state]
  ; color
  (q/fill (:color state) 255 255)
  ; path
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin (* 0.4 (q/cos (* 3 (q/sin angle))))))]
    ; center
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ;; squiggle
      (doseq [i (range -24 24)]
        (q/ellipse
         (+ x (* (Math/sin (* angle i (/ 3.14 7.421))) 4))
         (+ y (* i 0.05 angle))
         32 32)))))







(q/defsketch pablo
  :title "(hello :world)"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(defn -main [] [])
