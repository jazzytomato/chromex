(ns chromex.app.power (:require-macros [chromex.app.power :refer [gen-wrap]])
    (:require [chromex.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn request-keep-awake* [config level]
  (gen-wrap :function ::request-keep-awake config level))

(defn release-keep-awake* [config]
  (gen-wrap :function ::release-keep-awake config))

