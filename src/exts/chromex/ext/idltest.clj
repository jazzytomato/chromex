(ns chromex.ext.idltest
  "An API to test IDL schema specifications.
   
     * available since Chrome 48
     * https://developer.chrome.com/extensions/idltest"

  (:refer-clojure :only [defmacro defn apply declare meta let])
  (:require [chromex-lib.wrapgen :refer [gen-wrap-from-table]]
            [chromex-lib.callgen :refer [gen-call-from-table gen-tap-all-call]]
            [chromex-lib.config :refer [get-static-config gen-active-config]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro send-array-buffer
  "Functions for testing binary data request/response parameters. The first two just return back the bytes they were passed in
   an array.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([input #_cb] (gen-call :function ::send-array-buffer &form input)))

(defmacro send-array-buffer-view
  "TODO(asargent) - we currently can't have [instanceOf=ArrayBufferView], I think because ArrayBufferView isn't an
   instantiable type. The best we might be able to do is have a 'choices' list including all the typed array subclasses like
   Uint8Array, Uint16Array, Float32Array, etc.
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([input #_cb] (gen-call :function ::send-array-buffer-view &form input)))

(defmacro get-array-buffer
  "
   
   Note: Instead of passing a callback function, you receive a core.async channel as return value."
  ([#_cb] (gen-call :function ::get-array-buffer &form)))

(defmacro nocompile-func
  "This function should not have C++ code autogenerated (the variable name |switch| should cause compile errors if it does).
   But the name should get defined and made visible from within extensions/apps code."
  ([switch] (gen-call :function ::nocompile-func &form switch)))

; -- convenience ------------------------------------------------------------------------------------------------------------

(defmacro tap-all-events
  "Taps all valid non-deprecated events in this namespace."
  [chan]
  (let [static-config (get-static-config)
        config (gen-active-config static-config)]
    (gen-tap-all-call static-config api-table (meta &form) config chan)))

; ---------------------------------------------------------------------------------------------------------------------------
; -- API TABLE --------------------------------------------------------------------------------------------------------------
; ---------------------------------------------------------------------------------------------------------------------------

(def api-table
  {:namespace "chrome.idltest",
   :since "48",
   :functions
   [{:id ::send-array-buffer,
     :name "sendArrayBuffer",
     :callback? true,
     :params
     [{:name "input", :type "ArrayBuffer"}
      {:name "cb", :type :callback, :callback {:params [{:name "array", :type "[array-of-integers]"}]}}]}
    {:id ::send-array-buffer-view,
     :name "sendArrayBufferView",
     :callback? true,
     :params
     [{:name "input", :type "Uint8Array"}
      {:name "cb", :type :callback, :callback {:params [{:name "array", :type "[array-of-integers]"}]}}]}
    {:id ::get-array-buffer,
     :name "getArrayBuffer",
     :callback? true,
     :params [{:name "cb", :type :callback, :callback {:params [{:name "buffer", :type "ArrayBuffer"}]}}]}
    {:id ::nocompile-func, :name "nocompileFunc", :params [{:name "switch", :type "integer"}]}]})

; -- helpers ----------------------------------------------------------------------------------------------------------------

; code generation for native API wrapper
(defmacro gen-wrap [kind item-id config & args]
  (let [static-config (get-static-config)]
    (apply gen-wrap-from-table static-config api-table kind item-id config args)))

; code generation for API call-site
(defn gen-call [kind item src-info & args]
  (let [static-config (get-static-config)
        config (gen-active-config static-config)]
    (apply gen-call-from-table static-config api-table kind item src-info config args)))