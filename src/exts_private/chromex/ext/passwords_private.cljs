(ns chromex.ext.passwords-private (:require-macros [chromex.ext.passwords-private :refer [gen-wrap]])
    (:require [chromex.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn remove-saved-password* [config login-pair]
  (gen-wrap :function ::remove-saved-password config login-pair))

(defn remove-password-exception* [config exception-url]
  (gen-wrap :function ::remove-password-exception config exception-url))

(defn request-plaintext-password* [config login-pair]
  (gen-wrap :function ::request-plaintext-password config login-pair))

(defn get-saved-password-list* [config]
  (gen-wrap :function ::get-saved-password-list config))

(defn get-password-exception-list* [config]
  (gen-wrap :function ::get-password-exception-list config))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-saved-passwords-list-changed* [config channel & args]
  (gen-wrap :event ::on-saved-passwords-list-changed config channel args))

(defn on-password-exceptions-list-changed* [config channel & args]
  (gen-wrap :event ::on-password-exceptions-list-changed config channel args))

(defn on-plaintext-password-retrieved* [config channel & args]
  (gen-wrap :event ::on-plaintext-password-retrieved config channel args))

