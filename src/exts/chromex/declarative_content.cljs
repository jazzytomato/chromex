(ns chromex.declarative-content (:require-macros [chromex.declarative-content :refer [gen-wrap]])
    (:require [chromex-lib.core]))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-page-changed* [config channel & args]
  (gen-wrap :event ::on-page-changed config channel args))

