(ns chromex.ext.automation-internal (:require-macros [chromex.ext.automation-internal :refer [gen-wrap]])
    (:require [chromex-lib.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn enable-tab* [config args]
  (gen-wrap :function ::enable-tab config args))

(defn enable-frame* [config tree-id]
  (gen-wrap :function ::enable-frame config tree-id))

(defn enable-desktop* [config routing-id]
  (gen-wrap :function ::enable-desktop config routing-id))

(defn perform-action* [config args opt-args]
  (gen-wrap :function ::perform-action config args opt-args))

(defn query-selector* [config args]
  (gen-wrap :function ::query-selector config args))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-accessibility-event* [config channel & args]
  (gen-wrap :event ::on-accessibility-event config channel args))
(defn on-accessibility-tree-destroyed* [config channel & args]
  (gen-wrap :event ::on-accessibility-tree-destroyed config channel args))
(defn on-tree-change* [config channel & args]
  (gen-wrap :event ::on-tree-change config channel args))
