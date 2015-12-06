(ns chromex.ext.commands (:require-macros [chromex.ext.commands :refer [gen-wrap]])
    (:require [chromex-lib.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn get-all* [config]
  (gen-wrap :function ::get-all config))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-command* [config channel & args]
  (gen-wrap :event ::on-command config channel args))
