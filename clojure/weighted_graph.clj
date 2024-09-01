
#!clojure

(defn traverse-graph-dfs [g start]
  (loop [vertices []                          ; The vertices in the order they are explored
         explored #{start}                    ; Set to track explored vertices
         frontier [{:node start :weight 0}]] ; Stack for DFS (last in, first out)
    (if (empty? frontier)
      vertices
      (let [v (peek frontier)                ; Peek the last element
            frontier (pop frontier)          ; Pop the last element
            vertices (conj vertices [(:node v) (:weight v)])] ; Add the node to the result
        (recur vertices
               (conj explored (:node v)) ; Add the current node to explored
               (into frontier
                     (remove #(explored (:node %))
                             (reverse (get g (:node v))))))))))
                             ; reverse to preserve DFS order

(def graph
  {1 [{:node 2 :weight 1} {:node 3 :weight 2}]
   2 [{:node 4 :weight 2}]
   3 [{:node 4 :weight 2}]
   4 []})

(println (traverse-graph-dfs graph 1))